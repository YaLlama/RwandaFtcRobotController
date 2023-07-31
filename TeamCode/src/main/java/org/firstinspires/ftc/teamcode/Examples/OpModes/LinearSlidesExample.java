package org.firstinspires.ftc.teamcode.Examples.OpModes;

import org.firstinspires.ftc.teamcode.Utilities.Control.PID;
import org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpMode;

import static org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpModeUtilities.*;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

//What will be displayed on the phone
@TeleOp (name = "Linear Slides Example")

public class LinearSlidesExample extends SimplifiedOpMode {

    @Config
    //The section title that will be displayed on FTC dashboard
    public static class ExampleDashboard{
        // everything needs to be public static
        public static double slidesP = 0.008, slidesI = 0, slidesD = 0.002, slidesFeedForward = 0.15, slidesMinimumPower = 0.1, deadZone = 20;
        public static double targetSlidesPosition = 0;
    }

    //Global Object Declarations
    PID slidesPID;
    ElapsedTime timer;

    /**
     * Will execute once when the init button is pressed
     */
    @Override
    public void setup() {
        //Create runtime
        timer = new ElapsedTime();

        //Create PID
        slidesPID = new PID(LinearSlidesExample.ExampleDashboard.slidesP, LinearSlidesExample.ExampleDashboard.slidesI, LinearSlidesExample.ExampleDashboard.slidesD);

        //Motor power required for the slides to hold a height without moving
        slidesPID.setFeedForward(ExampleDashboard.slidesFeedForward);
        //Motor power required for the slides to move
        slidesPID.setLowerLimit(ExampleDashboard.slidesMinimumPower);
        //Distance from target that power will be set to 0
        slidesPID.setDeadZone(ExampleDashboard.deadZone);

        println("Initialized");
    }

    /**
     * Will execute when the play button is pressed once
     */
    @Override
    public void onStart() {
        //Reset timer
        timer.reset();
    }

    /**
     * What will run while the play button is pressed
     */
    @Override
    public void mainLoop() {
        //get the current slide position from the motor encoder
        double currentSlidesPosition = hardware.liftEncoder.getPosition();

        //Update PID with current set values
        slidesPID.setConstants(LinearSlidesExample.ExampleDashboard.slidesP, LinearSlidesExample.ExampleDashboard.slidesI, LinearSlidesExample.ExampleDashboard.slidesD);
        slidesPID.setFeedForward(ExampleDashboard.slidesFeedForward);
        slidesPID.setLowerLimit(ExampleDashboard.slidesMinimumPower);
        slidesPID.setDeadZone(ExampleDashboard.deadZone);

        //Give in the distance the slides are from their target and store the power the slides need to move at to reach it
        double slidesPower = slidesPID.getCorrection(ExampleDashboard.targetSlidesPosition - currentSlidesPosition);

        //Tell the motors to spin at the required power to reach their target
        hardware.liftRightMotor.setPower(slidesPower);
        hardware.liftLeftMotor.setPower(slidesPower);

        //print useful information to the screen
        println("Current Slides Position", currentSlidesPosition);
        println("Target Slides Position: " + ExampleDashboard.targetSlidesPosition);
        println("Slides Power", slidesPower);
        println("Time Running", timer.seconds());
    }
}

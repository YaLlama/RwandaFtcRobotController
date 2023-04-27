package org.firstinspires.ftc.teamcode.ExampleOpModes;

import org.firstinspires.ftc.teamcode.Utilities.Control.PID;
import org.firstinspires.ftc.teamcode.Utilities.HardwareDevices.*;
import org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpMode;

import static org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpModeUtilities.*;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
//What will be displayed on the phone
@TeleOp (name = "Linear Slides Example")

public class LinearSlidesOpModeExample extends SimplifiedOpMode {

    @Config
    //The section title that will be displayed on FTC dashboard
    public static class ExampleDashboard{
        // everything needs to be public static
        public static double slidesP = 0, slidesI = 0, slidesD = 0, slidesFeedForward = 0, slidesMinimumPower = 0;
        public static double targetSlidesPosition = 0;
    }

    //Global Object Declarations
    Motor leftLiftMotor;
    Motor rightLiftMotor;

    PID slidesPID;

    ElapsedTime timer;

    /**
     * Will execute once when the init button is pressed
     */
    @Override
    public void setup() {
        //Create motor objects
        leftLiftMotor     = new Motor("leftLiftMotor");
        rightLiftMotor    = new Motor("rightLiftMotor", true, true);

        //Create runtime
        timer = new ElapsedTime();

        //Create PID
        slidesPID = new PID(LinearSlidesOpModeExample.ExampleDashboard.slidesP, LinearSlidesOpModeExample.ExampleDashboard.slidesI, LinearSlidesOpModeExample.ExampleDashboard.slidesD);

        //Motor power required for the slides to hold a height without moving
        slidesPID.setFeedForward(ExampleDashboard.slidesFeedForward);
        //Motor power required for the slides to move
        slidesPID.setLowerLimit(ExampleDashboard.slidesMinimumPower);
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
        double currentSlidesPosition = rightLiftMotor.encoder.getPosition();

        //Give in the distance the slides are from their target and store the power the slides need to move at to reach it
        double slidesPower = slidesPID.getCorrection(currentSlidesPosition - ExampleDashboard.targetSlidesPosition);

        //Tell the motors to spin at the required power to reach their target
        rightLiftMotor.setPower(slidesPower);
        leftLiftMotor.setPower(slidesPower);

        //print useful information to the screen
        println("Current Slides Position", currentSlidesPosition);
        println("Target Slides Position", ExampleDashboard.targetSlidesPosition);
        println("Slides Power", slidesPower);
        println("Time Running", timer.seconds());
    }
}

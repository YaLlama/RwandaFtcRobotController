package org.firstinspires.ftc.teamcode.ExampleClasses;

import static org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpModeUtilities.println;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ExampleOpModes.LinearSlidesOpModeExample;
import org.firstinspires.ftc.teamcode.Utilities.Control.PID;
import org.firstinspires.ftc.teamcode.Utilities.HardwareDevices.Motor;

public class LinearSlidesClassExample {


    //Global Object Declarations
    Motor leftLiftMotor;
    Motor rightLiftMotor;

    PID slidesPID;

    ElapsedTime timer;

    /**
     * Constructor - what executes when "new LinearSlidesClassExample();" is called
     */
    public LinearSlidesClassExample(){
        //Create motor objects
        leftLiftMotor     = new Motor("leftLiftMotor");
        rightLiftMotor    = new Motor("rightLiftMotor", true, true);

        //Create runtime
        timer = new ElapsedTime();

        //Create PID
        slidesPID = new PID(LinearSlidesOpModeExample.ExampleDashboard.slidesP, LinearSlidesOpModeExample.ExampleDashboard.slidesI, LinearSlidesOpModeExample.ExampleDashboard.slidesD);

        //Motor power required for the slides to hold a height without moving
        slidesPID.setFeedForward(LinearSlidesOpModeExample.ExampleDashboard.slidesFeedForward);
        //Motor power required for the slides to move
        slidesPID.setLowerLimit(LinearSlidesOpModeExample.ExampleDashboard.slidesMinimumPower);
    }

    public void onStart() {
        //Reset timer
        timer.reset();
    }

    /**
     * what should be called every loop to update the depositors position based on its target
     */
    public void update() {
        //get the current slide position from the motor encoder
        double currentSlidesPosition = rightLiftMotor.encoder.getPosition();

        //Give in the distance the slides are from their target and store the power the slides need to move at to reach it
        double slidesPower = slidesPID.getCorrection(currentSlidesPosition - LinearSlidesOpModeExample.ExampleDashboard.targetSlidesPosition);

        //Tell the motors to spin at the required power to reach their target
        rightLiftMotor.setPower(slidesPower);
        leftLiftMotor.setPower(slidesPower);

        //print useful information to the screen
        println("Current Slides Position", currentSlidesPosition);
        println("Target Slides Position", LinearSlidesOpModeExample.ExampleDashboard.targetSlidesPosition);
        println("Slides Power", slidesPower);
        println("Time Running", timer.seconds());
    }
}

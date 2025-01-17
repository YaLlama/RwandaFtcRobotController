package org.firstinspires.ftc.teamcode.Examples.Classes;

import static org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpModeUtilities.println;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Examples.OpModes.LinearSlidesExample;
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
    public LinearSlidesClassExample(Motor leftLiftMotor, Motor rightLiftMotor){
        //Create motor objects
        this.leftLiftMotor     = leftLiftMotor;
        this.rightLiftMotor    = rightLiftMotor;

        //Create runtime
        timer = new ElapsedTime();

        //Create PID
        slidesPID = new PID(LinearSlidesExample.ExampleDashboard.slidesP, LinearSlidesExample.ExampleDashboard.slidesI, LinearSlidesExample.ExampleDashboard.slidesD);

        //Motor power required for the slides to hold a height without moving
        slidesPID.setFeedForward(LinearSlidesExample.ExampleDashboard.slidesFeedForward);
        //Motor power required for the slides to move
        slidesPID.setLowerLimit(LinearSlidesExample.ExampleDashboard.slidesMinimumPower);
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
        double slidesPower = slidesPID.getCorrection(currentSlidesPosition - LinearSlidesExample.ExampleDashboard.targetSlidesPosition);

        //Tell the motors to spin at the required power to reach their target
        rightLiftMotor.setPower(slidesPower);
        leftLiftMotor.setPower(slidesPower);

        //print useful information to the screen
        println("Current Slides Position", currentSlidesPosition);
        println("Target Slides Position", LinearSlidesExample.ExampleDashboard.targetSlidesPosition);
        println("Slides Power", slidesPower);
        println("Time Running", timer.seconds());
    }
}

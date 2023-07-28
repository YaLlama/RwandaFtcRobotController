package org.firstinspires.ftc.teamcode.Utilities.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robocol.Command;

import org.firstinspires.ftc.teamcode.Utilities.HardwareDevices.Gyro;
import org.firstinspires.ftc.teamcode.Utilities.HardwareDevices.Motor;
import org.firstinspires.ftc.teamcode.Utilities.HardwareDevices.MotorEncoder;


public abstract class SimplifiedOpMode extends LinearOpMode {

    /**
     * Initialize Servos and other code necessary to run the robot
     * Will execute once when the init button is pressed
     */
    public abstract void setup();

    /**
     * Will repeat while the play button is not pressed
     */
    public void initializationLoop(){}

    /**
     * Will execute when the play button is pressed once
     */
    public void onStart(){}

    /**
     * What will run while the play button is pressed
     */
    public abstract void mainLoop();

    /**
     * Will execute once when the stop button is pressed
     */
    public void onStop(){
        Motor.stopMotors();
    }

    @Override
    public void runOpMode() throws InterruptedException {

        initializeUtilities();
        setup();

        do{
            initializationLoop();
        }while (opModeInInit());

        onStart();

        while (opModeIsActive()){
            updateUtilities();
            mainLoop();
        }
        onStop();
    }

    private void initializeUtilities(){
        SimplifiedOpModeUtilities.setOpMode(this);
        Motor.resetMotorList();
        MotorEncoder.resetEncoderList();
        Gyro.resetGyroList();
    }

    private void updateUtilities(){
        SimplifiedOpModeUtilities.updateTelemetry();
        SimplifiedOpModeUtilities.updateControllers();
        Motor.commandPowers();
        MotorEncoder.updateEncoders();
        Gyro.updateAngles();
    }


}

package org.firstinspires.ftc.teamcode.Utilities;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Utilities.HardwareDevices.Motor;
import org.firstinspires.ftc.teamcode.Utilities.HardwareDevices.MotorEncoder;


public abstract class SimplifiedOpMode extends LinearOpMode {

    /**
     * Initialize Servos and other code necessary to run the robot
     */
    public abstract void initialize();

    /**
     * Will repeat while the play button is not pressed
     */
    public void initializationLoop(){}

    /**
     * What will run while the play button is pressed
     */
    public abstract void runLoop();

    /**
     * What will happen when the stop button is pressed
     */
    public void onStop(){
        Motor.stopMotors();
    }


    @Override
    public void runOpMode() throws InterruptedException {

        initializeUtilities();
        initialize();

        do{
            initializationLoop();
        }while (opModeInInit());

        while (opModeIsActive()){
            update();
            runLoop();
        }
        onStop();

    }

    private void initializeUtilities(){
        SimplifiedOpModeUtilities.setOpMode(this);
    }

    private void update(){
        SimplifiedOpModeUtilities.updateTelemetry();
        Motor.commandPowers();
        MotorEncoder.updateEncoders();
    }


}

package org.firstinspires.ftc.teamcode.Examples;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Utilities.HardwareDevices.*;

public class ExampleHardware {

    public Motor   driveLeftFront, driveRightFront,
            driveLeftBack,  driveRightBack;

    public Motor   leftLiftMotor, rightLiftMotor;
    public Motor   leftIntakeMotor, rightIntakeMotor;

    public MotorEncoder liftEncoder;
    public MotorEncoder yOdometryWheel, xOdometryWheel;

    public Servo s;

    public Gyro gyro;

    /**
     * Constructor - will create all the hardware objects
     */
    public ExampleHardware(){
        //get all the drive motor
        driveLeftFront  = new Motor("driveLeftFront", true);
        driveLeftBack   = new Motor("driveLeftBack", true);
        driveRightFront = new Motor("driveRightFront");
        driveRightBack  = new Motor("driveRightBack");

        //create gyro instance
        gyro = new Gyro("imu");

        //set encoder to true if the motor's encoder is plugged into the same port as the motor
        leftLiftMotor   = new Motor("leftLiftMotor", false, true);
        rightLiftMotor  = new Motor("rightLiftMotor", true);

        //making the lift encoder easier to access
        liftEncoder = leftLiftMotor.encoder;

        //The encoder is identified based on which motor port it is plugged into
        xOdometryWheel  = new MotorEncoder("leftIntakeMotor");
        yOdometryWheel  = new MotorEncoder("rightIntakeMotor");

    }
}

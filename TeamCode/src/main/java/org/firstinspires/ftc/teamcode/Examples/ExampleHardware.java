package org.firstinspires.ftc.teamcode.Examples;

import org.firstinspires.ftc.teamcode.Utilities.HardwareDevices.*;

public class ExampleHardware {

    public Motor   driveLeftFront, driveRightFront,
            driveLeftBack,  driveRightBack;

    public Motor   leftLiftMotor, rightLiftMotor;
    public Motor   leftIntakeMotor, rightIntakeMotor;

    public Gyro gyro;

    /**
     * Constructor - will create all the hardware objects
     */
    public ExampleHardware(){
        driveLeftFront  = new Motor("driveLeftFront", true);
        driveLeftBack   = new Motor("driveLeftBack", true);
        driveRightFront = new Motor("driveRightFront");
        driveRightBack  = new Motor("driveRightBack");
    }
}

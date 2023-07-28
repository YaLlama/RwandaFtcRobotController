package org.firstinspires.ftc.teamcode.Examples.OpModes;

import static org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpModeUtilities.*;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpMode;
@TeleOp (name = "Tank Drive Example")
public class TankDriveExample extends SimplifiedOpMode {
    @Override
    public void setup() {
        println("Initialized");
    }

    @Override
    public void mainLoop() {

        println("Press cross to switch driving modes");

        println("Left Stick Y", driver1.leftStick.Y());
        println("Right Stick Y", driver1.rightStick.Y());
        println("Left Stick X", driver1.leftStick.X());
        println("Right Stick X", driver1.rightStick.X());

        //swap control system
        if(driver1.cross.isToggled()){
            //one joystick controls direction and the other heading
            println("\nDrive Mode 1");

            //Joystick values are between -1 and 1
            double drivePower = driver1.leftStick.Y();
            double turnPower = driver1.rightStick.X();

            //motors can be set to a power from -1 to 1
            hardware.driveLeftBack.setPower(drivePower + turnPower);
            hardware.driveLeftFront.setPower(drivePower + turnPower);
            hardware.driveRightFront.setPower(drivePower - turnPower);
            hardware.driveRightBack.setPower(drivePower - turnPower);

        }else{
            //each joystick controls a side of the chassis
            println("\nDrive Mode 2");

            //Joystick values are between -1 and 1
            double rightSidePower = driver1.rightStick.Y();
            double leftSidePower = driver1.leftStick.Y();

            //motors can be set to a power from -1 to 1
            hardware.driveLeftBack.setPower(leftSidePower);
            hardware.driveLeftFront.setPower(leftSidePower);
            hardware.driveRightFront.setPower(rightSidePower);
            hardware.driveRightBack.setPower(rightSidePower);
        }
    }
}

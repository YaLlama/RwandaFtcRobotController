package org.firstinspires.ftc.teamcode.Examples.OpModes;

import static org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpModeUtilities.driver1;
import static org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpModeUtilities.hardware;
import static org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpModeUtilities.println;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpMode;

@TeleOp(name = "Mecanum Drive Example")

public class MecanumDriveExample extends SimplifiedOpMode {

    int driveMode = 0;

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

        if(driver1.cross.isTapped()){
            driveMode = (driveMode + 1) % 3;
        }

        println("Drive Mode", driveMode+1);

        //swap control system
        switch(driveMode){
            default:
            case 0:
                //one joystick controls direction and the other heading

                //Joystick values are between -1 and 1
                double driveForwardPower = driver1.leftStick.Y();
                double driveSideWaysPower = driver1.leftStick.X();
                double turnPower = driver1.rightStick.X();

                //motors can be set to a power from -1 to 1
                hardware.driveLeftBack.setPower     (driveForwardPower + turnPower - driveSideWaysPower);
                hardware.driveLeftFront.setPower    (driveForwardPower + turnPower + driveSideWaysPower);
                hardware.driveRightFront.setPower   (driveForwardPower - turnPower - driveSideWaysPower);
                hardware.driveRightBack.setPower    (driveForwardPower - turnPower + driveSideWaysPower);

                break;

            case 1:
                //each joystick controls a side of the chassis

                //Joystick values are between -1 and 1
                double rightSideForwardPower = driver1.rightStick.Y();
                double rightSideSidewaysPower = driver1.rightStick.X();
                double leftSideForwardPower = driver1.leftStick.Y();
                double leftSideSidewaysPower = driver1.leftStick.X();

                //motors can be set to a power from -1 to 1
                hardware.driveLeftBack.setPower(leftSideForwardPower - leftSideSidewaysPower);
                hardware.driveLeftFront.setPower(leftSideForwardPower + leftSideSidewaysPower);
                hardware.driveRightFront.setPower(rightSideForwardPower - rightSideSidewaysPower);
                hardware.driveRightBack.setPower(rightSideForwardPower + rightSideSidewaysPower);

                break;

            case 2:
                //Field Centered Drive
                println("Press left stick to reset heading");
                if(driver1.leftStick.isPressed()){
                    hardware.gyro.resetHeading();
                }

                double heading = hardware.gyro.getHeading();

                double x = driver1.leftStick.X();
                double y = driver1.leftStick.Y();

                double rotatedX = x * Math.cos(-heading) - y * Math.sin(-heading);
                double rotatedY = x * Math.sin(-heading) + y * Math.cos(-heading);

                double turningPower = driver1.rightStick.X();

                hardware.driveLeftBack.setPower     (rotatedY + turningPower - rotatedX);
                hardware.driveLeftFront.setPower    (rotatedY + turningPower + rotatedX);
                hardware.driveRightFront.setPower   (rotatedY - turningPower - rotatedX);
                hardware.driveRightBack.setPower    (rotatedY - turningPower + rotatedX);

                break;
        }
    }
}

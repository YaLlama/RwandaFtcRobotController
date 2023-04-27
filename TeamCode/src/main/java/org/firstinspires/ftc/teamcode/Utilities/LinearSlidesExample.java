package org.firstinspires.ftc.teamcode.Utilities;

import org.firstinspires.ftc.teamcode.Utilities.Control.PID;
import org.firstinspires.ftc.teamcode.Utilities.HardwareDevices.*;
import org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpMode;

import static org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpModeUtilities.*;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp (name = "Linear Slides Example")

public class LinearSlidesExample extends SimplifiedOpMode {

    Motor leftLiftMotor;
    Motor rightLiftMotor;

    PID

    @Override
    public void setup() {
        Motor leftLiftMotor     = new Motor("leftLiftMotor");
        Motor rightLiftMotor    = new Motor("rightLiftMotor", true);
    }

    @Override
    public void mainLoop() {

    }
}

package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.Utilities.HardwareDevices.*;
import static org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpModeUtilities.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpMode;

//@Disabled
@Autonomous (name = "New Autonomous")
public class SampleOpMode extends SimplifiedOpMode {
    @Override
    public void setup() {
        //Initialize servos and classes here

    }

    @Override
    public void initializationLoop() {
        //scan vision task with camera here
    }

    @Override
    public void mainLoop() {
       //this code will be repeated
        println("Hello");
    }
}

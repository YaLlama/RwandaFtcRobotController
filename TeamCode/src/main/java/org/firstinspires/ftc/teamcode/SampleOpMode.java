package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Utilities.SimplifiedOpModeUtilities.*;

import org.firstinspires.ftc.teamcode.Utilities.SimplifiedOpMode;

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

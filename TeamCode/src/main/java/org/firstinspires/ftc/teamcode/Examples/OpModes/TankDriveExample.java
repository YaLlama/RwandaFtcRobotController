package org.firstinspires.ftc.teamcode.Examples.OpModes;

import static org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpModeUtilities.*;

import org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpMode;

public class TankDriveExample extends SimplifiedOpMode {
    @Override
    public void setup() {

    }

    @Override
    public void mainLoop() {

        //swap control system
        if(driver1.cross.isToggled()){
            //one joystick controls direction and the other heading

        }else{
            //each joystick controls a side of the chassis

        }
    }
}

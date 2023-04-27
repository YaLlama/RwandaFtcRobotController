package org.firstinspires.ftc.teamcode.Utilities.HardwareDevices;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpModeUtilities;

import java.util.ArrayList;

public class MotorEncoder {

    public static ArrayList<MotorEncoder> encoders = new ArrayList<>();

    DcMotor encoder;
    double position = 0;
    double offset = 0;

    /**
     * Gets new encoder values for all encoders
     */

    public static void updateEncoders(){
        for(MotorEncoder e : encoders){
            e.update();
        }
    }

    /**
     *
     * @return - returns the encoder value
     */
    public double getPosition(){
        return position + offset;
    }

    /**
     * retets the encdoer and offset to 0
     */
    public void resetEncoder(){
        position = 0;
        offset = 0;

        encoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public MotorEncoder(String name){
        encoders.add(this);
        encoder = SimplifiedOpModeUtilities.hardwareMap.dcMotor.get(name);
    }

    private void update(){
        position = encoder.getCurrentPosition();
    }

    public void setOffset(double offset){
        this.offset = offset;
    }
}

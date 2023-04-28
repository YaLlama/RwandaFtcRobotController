package org.firstinspires.ftc.teamcode.Utilities.HardwareDevices;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Utilities.OpMode.SimplifiedOpModeUtilities;

public class Gyro {

    private BNO055IMU controlHubIMU;

    private double wrappedHeading = 0;
    private double rawHeading = 0;
    private double offset = 0;

    public Gyro(){
        controlHubIMU = imuConstructor("imu");
    }

    public double getHeading(){
        return wrappedHeading;
    }

    //maybe make total heading
    public double getRawHeading(){
        return rawHeading;
    }

    public void reset(){
        offset = rawHeading;
    }

    public void update(){
        Orientation angles = controlHubIMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);

        //TODO revHub orientation might matter
        double rawYaw = angles.firstAngle - offset;
//        double rawPitch = angles.secondAngle;
//        double rawRoll = angles.thirdAngle;

        rawHeading = rawYaw;
        wrappedHeading = wrapAngle(rawYaw);
    }

    public double wrapAngle(double angle){
        //Makes sure angle is from -Pi to Pi
        while (angle > Math.PI){
            angle -= Math.PI * 2;
        }
        while (angle < -Math.PI){
            angle += Math.PI * 2;
        }

        return angle;
    }

    private BNO055IMU imuConstructor(String deviceName){
        BNO055IMU imu;
        imu = SimplifiedOpModeUtilities.hardwareMap.get(BNO055IMU.class, deviceName);
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json"; // see the calibration sample opMode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        imu.initialize(parameters);
        return imu;
    }
}

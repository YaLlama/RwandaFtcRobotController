package org.firstinspires.ftc.teamcode.Utilities.OpMode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Utilities.HardwareDevices.Controller;
import org.firstinspires.ftc.teamcode.Utilities.Telemetry.RobotLogTelemetry;
import org.firstinspires.ftc.teamcode.Utilities.Telemetry.ThrowbackTelemetry;
import org.firstinspires.ftc.teamcode.Utilities.Telemetry.VoidTelemetry;

import java.util.List;

public class SimplifiedOpModeUtilities {

    public static HardwareMap hardwareMap;
    public static SimplifiedOpMode opMode;

    public static Telemetry telemetry;
    public static FtcDashboard dashboard = FtcDashboard.getInstance();
    public static Telemetry dashboardTelemetry = dashboard.getTelemetry();
    public static ThrowbackTelemetry multTelemetry;

    public static Controller driver1, driver2;
    public static List<LynxModule> allHubs;

    /**
     * Initialize opMode Utilities
     * @param opMode
     */
    public static void setOpMode(SimplifiedOpMode opMode){
        SimplifiedOpModeUtilities.opMode = opMode;
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;
        telemetry.setMsTransmissionInterval(5);
        multTelemetry = new ThrowbackTelemetry(new MultipleTelemetry(telemetry, dashboardTelemetry));

        driver1 = new Controller(opMode.gamepad1);
        driver2 = new Controller(opMode.gamepad2);

        allHubs = hardwareMap.getAll(LynxModule.class);

        for(LynxModule hub : allHubs){
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

    }

    /**
     * Telemetry print data
     * @param s - identifier
     * @param o - data
     */
    public static void println(String s, Object o){
        multTelemetry.addData(s, o);
    }

    /**
     * Telemetry print string
     * @param o - string
     */
    public static void println(Object o){
        multTelemetry.addLine((String) o);
    }

    /**
     * Console print
     * @param s - string
     */
    public static void printlnToComputer(String s){
        System.out.println(s);
    }

    public static boolean isInInit(){
        return opMode.opModeInInit();
    }
    public static boolean isActive(){
        return opMode.opModeIsActive();
    }

    public static boolean mainThreadRunning(){
        return isInInit() || isActive();
    }

    public static void setNoTelemetry(){
        multTelemetry.setTelemetry(new VoidTelemetry());
    }

    public static void setRobotLogTelemetry(){
        multTelemetry.setTelemetry(new RobotLogTelemetry());
    }
    public static void updateTelemetry(){
        multTelemetry.update();
    }

    public static void updateControllers(){
        driver1.update(); driver2.update();
    }



}

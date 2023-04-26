package org.firstinspires.ftc.teamcode.Utilities;

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

    public static Controller driver, operator;
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

        driver = new Controller(opMode.gamepad1);
        operator = new Controller(opMode.gamepad2);

        allHubs = hardwareMap.getAll(LynxModule.class);

        for(LynxModule hub : allHubs){
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

    }

    /**
     * telemetry print
     */
    public static void print(){

    }

    /**
     * Console print
     * @param o
     */
    public static void printToComputer(Object o){
        System.out.println(o);
    }

    public static boolean isInit(){
        return opMode.opModeInInit();
    }
    public static boolean isActive(){
        return opMode.opModeIsActive();
    }

    public static boolean runThread(){
        return isInit() || isActive();
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
        driver.update(); operator.update();
    }



}

package org.firstinspires.ftc.teamcode.Utilities;

public class Math {
    public static double wrapAngle(double angle){
        //Makes sure angle is from -Pi to Pi
        while (angle > java.lang.Math.PI){
            angle -= java.lang.Math.PI * 2;
        }
        while (angle < -java.lang.Math.PI){
            angle += java.lang.Math.PI * 2;
        }

        return angle;
    }
}

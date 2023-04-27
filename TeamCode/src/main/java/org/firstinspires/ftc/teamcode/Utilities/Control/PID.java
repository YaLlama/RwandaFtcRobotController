package org.firstinspires.ftc.teamcode.Utilities.Control;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PID {

    double kP, kI, kD;
    double feedForwardConstant = 0, lowerLimitConstant = 0, deadZone = 0;

    double integralSum = 0;
    RingBuffer<Double> prevErrorRingBuffer = new RingBuffer<>(3,0.0);
    RingBuffer<Double> timeRingBuffer = new RingBuffer<>(3,0.0);

    ElapsedTime runtime = new ElapsedTime();

    public PID(double kP, double kI, double kD){
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;

        reset();
    }

    public void setConstants(double kP, double kI, double kD){
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public void setFeedForward(double constant){
        this.feedForwardConstant = constant;
    }

    public void setDeadZone(double deadZone){
        this.deadZone = deadZone;
    }

    public void setLowerLimit(double constant){
        this.lowerLimitConstant = constant;
    }

    public void reset(){
        prevErrorRingBuffer.fill(0.0);
        timeRingBuffer.fill(0.0);
        runtime.reset();
    }

    public double getCorrection(double error){
        double currentTime = runtime.milliseconds();
        double prevTime = timeRingBuffer.getValue(currentTime);
        double deltaTime = currentTime - prevTime;

        if(deltaTime > 200){
            reset();
            deltaTime = 0;
        }

        double previousError = prevErrorRingBuffer.getValue(error);

        if(Math.abs(error) < deadZone){
            integralSum = 0;
            return 0;
        }

        //Proportional component
        double pComponent = error * kP;

        //Integral component
        if(Math.signum(previousError) != Math.signum(error)){
            integralSum = 0;
        }

        integralSum += kI * error;

        double iComponent = integralSum * deltaTime;

        //Derivative Component
        double dComponent = (error - previousError) / deltaTime;

        //FeedForward Component
        double lowerLimitComponent = Math.signum(error) * lowerLimitConstant;

        return pComponent + iComponent + dComponent + lowerLimitComponent + feedForwardConstant;
    }
}

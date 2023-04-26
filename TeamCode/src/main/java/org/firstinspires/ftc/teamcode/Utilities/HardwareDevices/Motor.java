package org.firstinspires.ftc.teamcode.Utilities.HardwareDevices;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImplEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Utilities.SimplifiedOpModeUtilities;

import java.util.ArrayList;

public class Motor {

    public static ArrayList<Motor> motors = new ArrayList<>();
    private DcMotorImplEx motor;
    public MotorEncoder encoder;

    private final double currentOverloadBuffer = 300;
    private final double powerScalar;
    private ElapsedTime currentOverloadTimer = new ElapsedTime();

    private double power = 0;

    /**
     * commands all the motor powers to what they have been set
     */
    public static void commandPowers(){
        for(Motor m : motors){
            m.commandPower();
        }
    }

    /**
     * sets all motor powers to zero
     */
    public static void stopMotors(){
        for(Motor m : motors){
            m.setPower(0);
            m.commandPower();
        }
    }

    /**
     * Sets the power to be commanded
     * @param p - power set to motor from -1 to 1
     */
    public void setPower(double p){
        power = p;
    }

    /**
     * returns the current draw in milli-amps
     */
    public double getCurrent(){
        return motor.getCurrent(CurrentUnit.MILLIAMPS);
    }

    /**
     *
     * @param name - hardware device name
     * @param reversed - whether the motor should run backwards
     * @param encoder - if the motor has an attached encoder
     * @param maximumCurrent - maximum current in milli-amps before the motor will disable
     * @param powerScalar - if the motor powers need to be scaled down to match other motors
     */
    public Motor(String name, boolean reversed, boolean brake, boolean encoder, double maximumCurrent, double powerScalar){
        motors.add(this);

        motor = (DcMotorImplEx) SimplifiedOpModeUtilities.hardwareMap.dcMotor.get(name);

        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);

        if(brake) motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        else motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        if(reversed) this.powerScalar = -1 * Math.abs(powerScalar);
        else this.powerScalar = Math.abs(powerScalar);

        if(encoder) this.encoder = new MotorEncoder(name);

        motor.setCurrentAlert(maximumCurrent, CurrentUnit.MILLIAMPS);
    }

    public Motor(String name, boolean reversed, boolean brake, boolean encoder){
        this(name, reversed, brake, encoder, 10000, 1);
    }

    public Motor(String name, boolean reversed, boolean brake){
        this(name, reversed, brake, false, 10000, 1);
    }

    public Motor(String name, boolean reversed){
        this(name, reversed, true, false, 10000, 1);
    }

    public Motor(String name){
        this(name, false, true, false, 10000, 1);
    }

    private void checkMaximumLoad(){
        if(motor.isOverCurrent()){
            motor.setMotorDisable();
            currentOverloadTimer.reset();
        }else if(currentOverloadTimer.milliseconds() > 300){
            motor.setMotorEnable();
        }
    }

    private void commandPower(){
        motor.setPower(power * powerScalar);
    }

}

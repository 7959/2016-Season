package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by Joseph on 12/17/2016.
 */

public abstract class Krusher99 extends OpMode {
    protected ColorSensor sensor1;
    protected ColorSensor sensor2;
    protected GyroSensor Gsensor;
    protected OpticalDistanceSensor ODsensor;
    protected class LaunCher{
        private DcMotor Right;
        private DcMotor LeftWHalE;
        public void init(){
            Right = hardwareMap.dcMotor.get("PewPewLeft");
            LeftWHalE = hardwareMap.dcMotor.get("PewPewRight");

            Right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            LeftWHalE.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            Right.setDirection(DcMotorSimple.Direction.FORWARD);
            LeftWHalE.setDirection(DcMotorSimple.Direction.REVERSE);

            Right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            LeftWHalE.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        }
        public void setPower(double power){
            Right.setPower(power);
            LeftWHalE.setPower(power);
        }
        public void getPower(){
            double out = Right.getPower() + LeftWHalE.getPower();
            out/=2;
        }



    }
    protected class Left {
        private DcMotor frontL;
        private DcMotor middleL;
        private DcMotor backL;
        public void init() {
            frontL = hardwareMap.dcMotor.get("Front Left");
            middleL = hardwareMap.dcMotor.get("Middle Left");
            backL = hardwareMap.dcMotor.get("Back Left");

            frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            middleL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            frontL.setDirection(DcMotorSimple.Direction.FORWARD);
            middleL.setDirection(DcMotorSimple.Direction.FORWARD);
            backL.setDirection(DcMotorSimple.Direction.FORWARD);

            frontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            middleL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        public void setPower(double power) {
            frontL.setPower(power);
            middleL.setPower(power);
            backL.setPower(power);
        }
        public double getPower() {
            double out;
            out = frontL.getPower() + middleL.getPower() + backL.getPower();
            out /= 3;
            return out;
        }
    }
    protected class Right {
        private DcMotor frontR;
        private DcMotor middleR;
        private DcMotor backR;
        public void init() {
            frontR = hardwareMap.dcMotor.get("Front Right");
            middleR = hardwareMap.dcMotor.get("Middle Right");
            backR = hardwareMap.dcMotor.get("Back Right");

            frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            middleR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            frontR.setDirection(DcMotorSimple.Direction.FORWARD);
            middleR.setDirection(DcMotorSimple.Direction.REVERSE);
            backR.setDirection(DcMotorSimple.Direction.REVERSE);

            frontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            middleR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        public void setPower(double power) {
            frontR.setPower(power);
            middleR.setPower(power);
            backR.setPower(power);
        }
        public double getPower() {
            double out;
            out = frontR.getPower() + middleR.getPower() + backR.getPower();
            out /= 3;
            return out;
        }
    }
    protected LaunCher launcher;
    protected Left left;
    protected Right right;
    @Override
    public void init() {
        left.init();
        right.init();
        launcher.init();

        sensor1 = hardwareMap.colorSensor.get("Up Sensor");
        sensor2 = hardwareMap.colorSensor.get("Down Sensor");
        Gsensor = hardwareMap.gyroSensor.get("Gyro Sensor");
        ODsensor = hardwareMap.opticalDistanceSensor.get("OD Sensor");

    }
}

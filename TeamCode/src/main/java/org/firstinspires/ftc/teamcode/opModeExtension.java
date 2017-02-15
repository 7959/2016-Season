package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;

/**
 * Created by Joseph on 2/15/2017.
 */

abstract public class opModeExtension extends OpMode {
    protected DcMotor fL;
    protected DcMotor fR;
    protected DcMotor bL;
    protected DcMotor bR;
    protected DcMotor lL;
    protected DcMotor lR;
    protected DcMotor loader;
    protected ColorSensor topLeft;
    protected ColorSensor topRight;
    protected ColorSensor deltaBottom;
    protected ColorSensor deltaLeft;
    protected ColorSensor deltaRight;
    protected ColorSensor deltaMiddle;
    protected ModernRoboticsI2cGyro g;
    public void init() {
        fL = hardwareMap.dcMotor.get("Front Left");
        fR = hardwareMap.dcMotor.get("Front Right");
        bL = hardwareMap.dcMotor.get("Back Left");
        bR = hardwareMap.dcMotor.get("Back Right");
        lL = hardwareMap.dcMotor.get("Launcher Left");
        lR = hardwareMap.dcMotor.get("Launcher Right");
        loader = hardwareMap.dcMotor.get("Loader");
        fL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        loader.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        lR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        loader.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fL.setDirection(DcMotorSimple.Direction.FORWARD);
        fR.setDirection(DcMotorSimple.Direction.REVERSE);
        bL.setDirection(DcMotorSimple.Direction.FORWARD);
        bR.setDirection(DcMotorSimple.Direction.REVERSE);
        lL.setDirection(DcMotorSimple.Direction.FORWARD);
        lR.setDirection(DcMotorSimple.Direction.REVERSE);
        loader.setDirection(DcMotorSimple.Direction.FORWARD);



        topLeft = hardwareMap.colorSensor.get("Top Left");
        topLeft.setI2cAddress(I2cAddr.create8bit(0x40));
        topRight = hardwareMap.colorSensor.get("Top Right");
        topRight.setI2cAddress(I2cAddr.create8bit(0x41));
        deltaBottom = hardwareMap.colorSensor.get("Delta Bottom");
        deltaBottom.setI2cAddress(I2cAddr.create8bit(0x42));
        deltaLeft = hardwareMap.colorSensor.get("Delta Left");
        deltaLeft.setI2cAddress(I2cAddr.create8bit(0x43));
        deltaRight = hardwareMap.colorSensor.get("Delta Right");
        deltaRight.setI2cAddress(I2cAddr.create8bit(0x44));
        deltaMiddle = hardwareMap.colorSensor.get("Delta Middle");
        deltaMiddle.setI2cAddress(I2cAddr.create8bit(0x45));



        g = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("Gyro Sensor");
    }
}
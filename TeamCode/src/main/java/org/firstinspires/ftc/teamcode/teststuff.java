package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;


/**
 * Created by Robi on 2/15/2017.
 */
@TeleOp(name = "teststuff")
public class teststuff extends OpMode{
    protected DcMotor fL;
    protected DcMotor fR;
    protected DcMotor bL;
    protected DcMotor bR;
    protected DcMotor lL;
    protected DcMotor lR;
    double speedl;
    double speedr;
   // protected DcMotor loader;
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
        //loader = hardwareMap.dcMotor.get("Loader");
        fL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
       // loader.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        lR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
       // loader.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fL.setDirection(DcMotorSimple.Direction.FORWARD);
        fR.setDirection(DcMotorSimple.Direction.REVERSE);
        bL.setDirection(DcMotorSimple.Direction.FORWARD);
        bR.setDirection(DcMotorSimple.Direction.REVERSE);
        lL.setDirection(DcMotorSimple.Direction.FORWARD);
        lR.setDirection(DcMotorSimple.Direction.REVERSE);
        //loader.setDirection(DcMotorSimple.Direction.FORWARD);



        //topLeft = hardwareMap.colorSensor.get("Top Left");
        //topLeft.setI2cAddress(I2cAddr.create7bit(0x19));
        topRight = hardwareMap.colorSensor.get("Top Right");
        topRight.setI2cAddress(I2cAddr.create7bit(0x1e));
        deltaLeft = hardwareMap.colorSensor.get("Delta Left");
        deltaLeft.setI2cAddress(I2cAddr.create7bit(0x1f));
        deltaRight = hardwareMap.colorSensor.get("Delta Right");
        deltaRight.setI2cAddress(I2cAddr.create7bit(0x1d));
        deltaMiddle = hardwareMap.colorSensor.get("Delta Middle");
        //deltaMiddle.setI2cAddress(I2cAddr.create8bit(0x45));/////////////////NOT SET UP CORRECTLY YET



        g = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("Gyro Sensor");
        telemetry.addData("BACK OFF", "I AM CALIBRATING");
        telemetry.update();
        g.calibrate();
        while(g.isCalibrating());
        telemetry.clearAll();
        telemetry.addData("Thanks", "I am calibrated");
        telemetry.update();
        lL.setPower(1);
        lR.setPower(1);
    }
    public void loop(){

        telemetry.addData("gamepad L bumper", gamepad1.left_bumper);
        telemetry.addData("gamepad R bumper", gamepad1.right_bumper);
        telemetry.addData("gamepad L trigger", gamepad1.left_trigger);
        telemetry.addData("gamepad R trigger", gamepad1.right_trigger);
        telemetry.addData("joystick y", gamepad1.left_stick_y);
        telemetry.addData("joystick x", gamepad1.left_stick_x);
        telemetry.addData("Up Right sensor B", topRight.blue());
        telemetry.addData("Up Right sensor R", topRight.red());
        telemetry.addData("Up Left sensor B", topLeft.blue());
        telemetry.addData("Up Left sensor R", topLeft.red());
        telemetry.addData("Delta Middle", deltaMiddle.green());
        telemetry.addData("Delta Right", deltaRight.green());
        telemetry.addData("Delta Left", deltaLeft.green());
        telemetry.addData("Z integrated", g.getIntegratedZValue());
        telemetry.addData("Heading", g.getHeading());
        telemetry.update();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

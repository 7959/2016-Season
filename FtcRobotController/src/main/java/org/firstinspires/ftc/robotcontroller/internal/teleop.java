package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import java.util.Timer;

import javax.xml.datatype.Duration;

/**
 * Created by Joseph on 10/4/2016.
 */
@TeleOp(name="Controlled Drive")
public class teleop extends OpMode {

    private DcMotor frontL;
    private DcMotor frontR;
    private DcMotor backL;
    private DcMotor backR;
    private DcMotor poker;
    private DcMotor loader;
    private DcMotor reloader;

    private Servo launchReset;
    private Servo launcherUD;

    private TouchSensor loadTester;

    private boolean gamepad1turn = false;

    @Override
    public void init() {
        frontL = hardwareMap.dcMotor.get("Front Left");
        frontR = hardwareMap.dcMotor.get("Front Right");
        backL = hardwareMap.dcMotor.get("Back Left");
        backR = hardwareMap.dcMotor.get("Back Right");
        poker = hardwareMap.dcMotor.get("Poker");
        loader = hardwareMap.dcMotor.get("Loader");
        reloader = hardwareMap.dcMotor.get("Reloader");

        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        poker.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        loader.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        reloader.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontL.setDirection(DcMotorSimple.Direction.REVERSE);
        frontR.setDirection(DcMotorSimple.Direction.FORWARD);
        backL.setDirection(DcMotorSimple.Direction.REVERSE);
        backR.setDirection(DcMotorSimple.Direction.FORWARD);
        poker.setDirection(DcMotorSimple.Direction.FORWARD);
        loader.setDirection(DcMotorSimple.Direction.FORWARD);
        reloader.setDirection(DcMotorSimple.Direction.FORWARD);


        launchReset = hardwareMap.servo.get("Launcher Axis");
        launcherUD = hardwareMap.servo.get("Launcher Y");

        launcherUD.scaleRange(0, 0.25);
        launchReset.scaleRange(0, 0.5);


        loadTester = hardwareMap.touchSensor.get("Load Tester");
    }
    @Override
    public void start() {
        telemetry.addData("LOADER DIRECTION", "IN");
        loader.setPower(0.3);
        launchReset.setPosition(0);
        launcherUD.setPosition(0.5);
    }
    @Override
    public void loop() {
        if (!gamepad1.right_bumper && !gamepad1.left_bumper) {
            frontL.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x);
            frontR.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x);
            backL.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x);
            backR.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x);
            telemetry.addData("ROBOT SPEED", 100 * (Math.abs(gamepad1.left_stick_y) + Math.abs(gamepad1.left_stick_x)) + "%");
            gamepad1turn = gamepad1.left_stick_x != 0 || gamepad1.left_stick_y != 0;
        } else if (gamepad1.right_bumper) {
            frontL.setPower(1);
            frontR.setPower(-1);
            backL.setPower(1);
            backR.setPower(-1);
            gamepad1turn = true;
        } else {
            frontL.setPower(-1);
            frontR.setPower(1);
            backL.setPower(-1);
            backR.setPower(1);
            gamepad1turn = true;
        }

        if (gamepad1.dpad_left) {
            poker.setPower(-0.2);
        } else if (gamepad1.dpad_right) {
            poker.setPower(0.2);
        } else {
            poker.setPower(0);
        }

        
        if (!loadTester.isPressed()) {
            if (gamepad2.x) {
                reloader.setPower(0.1);
            } else {
                reloader.setPower(0);
            }
            telemetry.addData("READY TO FIRE", "FALSE");
        } else {
            reloader.setPower(0.1);
            telemetry.addData("READY TO FIRE", "TRUE");
        }

        if (gamepad2.a) {
            launchReset.setPosition(1);
        } else if (gamepad2.b) {
            launchReset.setPosition(0);
        }

        if (gamepad2.right_bumper) {
            loader.setPower(-0.3);
            telemetry.addData("LOADER DIRECTION", "OUT");
        } else {
            loader.setPower(0.3);
            telemetry.addData("LOADER DIRECTION: ", "IN");
        }

        if (gamepad2.dpad_up) {
            launcherUD.setPosition(launcherUD.getPosition() + 0.01);
        } else if (gamepad2.dpad_down) {
            launcherUD.setPosition(launcherUD.getPosition() - 0.01);
        }

        if (!gamepad1turn) {
            if (gamepad2.dpad_left) {
                frontL.setPower(-1);
                frontR.setPower(1);
                backL.setPower(-1);
                backR.setPower(1);
            } else if (gamepad2.dpad_right) {
                frontL.setPower(1);
                frontR.setPower(-1);
                backL.setPower(1);
                backR.setPower(-1);
            }
        }
    }
}

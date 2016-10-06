package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Joseph on 10/4/2016.
 */

public class teleop extends OpMode {
    private DcMotor frontL;
    private DcMotor frontR;
    private DcMotor backL;
    private DcMotor backR;
    private DcMotor poker;
    private DcMotor loader;
    private DcMotor launcher;
    private Servo launcherY;
    private boolean launcherYChange;
    @Override
    public void init() {
        // Initiates Primitive Variables
        launcherYChange = false;
        // Initiates Dc Motors
        frontL = hardwareMap.dcMotor.get("Front Left");
        frontR = hardwareMap.dcMotor.get("Front Right");
        backL = hardwareMap.dcMotor.get("Back Left");
        backR = hardwareMap.dcMotor.get("Back Right");
        poker = hardwareMap.dcMotor.get("Poker");
        loader = hardwareMap.dcMotor.get("Loader");
        launcher = hardwareMap.dcMotor.get("Launcher");
        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        poker.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        loader.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontL.setDirection(DcMotorSimple.Direction.REVERSE);
        frontR.setDirection(DcMotorSimple.Direction.FORWARD);
        backL.setDirection(DcMotorSimple.Direction.REVERSE);
        backR.setDirection(DcMotorSimple.Direction.FORWARD);
        poker.setDirection(DcMotorSimple.Direction.FORWARD);
        loader.setDirection(DcMotorSimple.Direction.FORWARD);
        launcher.setDirection(DcMotorSimple.Direction.FORWARD);
        // Initiates Servo Motors
        launcherY = hardwareMap.servo.get("Launcher Axis");
        launcherY.scaleRange(0, 0.2);
    }
    @Override
    public void start() {
        telemetry.addData("LOADER DIRECTION: ", "IN");
        loader.setPower(0.3);
        launcherY.setPosition(0.5);
    }
    @Override
    public void loop() {
        /*
         * Gamepad 1 Controls
         */
        // Movement controls
        if (!gamepad1.right_bumper && !gamepad1.left_bumper) {
            frontL.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x);
            frontR.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x);
            backL.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x);
            backR.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x);
        } else if (gamepad1.right_bumper) {
            frontL.setPower(1);
            frontR.setPower(-1);
            backL.setPower(1);
            backR.setPower(-1);
        } else {
            frontL.setPower(-1);
            frontR.setPower(1);
            backL.setPower(-1);
            backR.setPower(1);
        }
        // Poker
        if (gamepad1.dpad_left) {
            poker.setPower(-0.2);
        } else if (gamepad1.dpad_right) {
            poker.setPower(0.2);
        } else {
            poker.setPower(0);
        }
        /*
         * Gamepad 2 Controls
         */
        // Launcher
        if (gamepad2.dpad_up && !launcherYChange) {
            launcherY.setPosition(launcherY.getPosition() + 0.1);
            launcherYChange = true;
        } else if (gamepad2.dpad_down && !launcherYChange) {
            launcherY.setPosition(launcherY.getPosition() - 0.1);
            launcherYChange = true;
        } else {
            launcherYChange = false;
        }
    }
}

package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Joseph on 10/4/2016.
 *
 * Jasera's Wolf Head
 */

@TeleOp(name="Default Controlled Drive", group = "TeleOps")
public class teleop extends OpMode {

    private DcMotor frontL;
    private DcMotor frontR;
    private DcMotor backL;
    private DcMotor backR;
    //private DcMotor poker;
    private DcMotor loader;
    //private DcMotor launcher;

    @Override
    public void init() {
        frontL = hardwareMap.dcMotor.get("Front Left");
        frontR = hardwareMap.dcMotor.get("Front Right");
        backL = hardwareMap.dcMotor.get("Back Left");
        backR = hardwareMap.dcMotor.get("Back Right");
        //poker = hardwareMap.dcMotor.get("Poker");
        loader = hardwareMap.dcMotor.get("Loader");
        //launcher = hardwareMap.dcMotor.get("Launcer");

        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //poker.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        loader.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontL.setDirection(DcMotorSimple.Direction.REVERSE);
        frontR.setDirection(DcMotorSimple.Direction.FORWARD);
        backL.setDirection(DcMotorSimple.Direction.REVERSE);
        backR.setDirection(DcMotorSimple.Direction.FORWARD);
        //poker.setDirection(DcMotorSimple.Direction.FORWARD);
        loader.setDirection(DcMotorSimple.Direction.FORWARD);
        //launcher.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    @Override
    public void start() {

    }

    @Override
    public void loop() {
        if (!gamepad1.right_bumper && !gamepad1.left_bumper) {
            if (gamepad1.left_stick_y == 0 || gamepad1.left_stick_x == 0) {
                frontL.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x);
                frontR.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x);
                backL.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x);
                backR.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x);
            }/* else if (gamepad2.dpad_left) {
                frontL.setPower(-1);
                frontR.setPower(1);
                backL.setPower(-1);
                backR.setPower(1);
            } else if (gamepad2.dpad_right) {
                frontL.setPower(1);
                frontR.setPower(-1);
                backL.setPower(1);
                backR.setPower(-1);
            } else {
                frontL.setPower(0);
                frontR.setPower(0);
                backL.setPower(0);
                backR.setPower(0);
            }*/
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
        /*
        if (gamepad1.dpad_left) poker.setPower(-0.2);
        else if (gamepad1.dpad_right) poker.setPower(0.2);
        else poker.setPower(0);
        */
        if (gamepad1.a) loader.setPower(1);
        else if (gamepad1.b) loader.setPower(-1);
        else if (gamepad1.x) loader.setPower(0.1);
        else if (gamepad1.y) loader.setPower(-0.1);
        else loader.setPower(0);

        if (gamepad1.start && gamepad1.back) requestOpModeStop();
    }
}

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Joseph on 7/7/2016.
 */
public class OpModeCompetitionBase1 extends OpMode {
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    //DcMotors
    @Override
    public void init() {
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        //Shortens variables
        backLeft.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        backRight.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        frontLeft.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        frontRight.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        //Activates and configures motors.
    }
    @Override
    public void loop() {
        double bl;
        double br;
        double fl;
        double fr;
        bl = gamepad1.left_stick_y - gamepad1.left_stick_x;
        br = gamepad1.left_stick_y + gamepad1.left_stick_x;
        fl = gamepad1.left_stick_y + gamepad1.left_stick_x;
        fr = gamepad1.left_stick_y - gamepad1.left_stick_x;
        backLeft.setPower(bl);
        backRight.setPower(br);
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
    }
    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {

        }
    }
}

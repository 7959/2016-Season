package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Joseph on 7/8/2016.
 */
public class OpModeCompetitionBase3 extends OpMode {
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor center;
    @Override
    public void init() {
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        center = hardwareMap.dcMotor.get("center");
        backLeft.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        backRight.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        frontLeft.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        frontRight.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        center.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
    }
    @Override
    public void loop() {
        float y = gamepad1.left_stick_y;
        float x = gamepad1.left_stick_x;
        backLeft.setPower(y);
        backRight.setPower(y);
        frontLeft.setPower(y);
        frontRight.setPower(y);
        center.setPower(x);
    }
}

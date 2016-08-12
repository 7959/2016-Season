package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Joseph on 7/7/2016.
 */
public class OpModeCompetitionBase2 extends OpMode {
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    @Override
    public void init() {
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        backRight.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        frontLeft.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        frontRight.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
    }
    @Override
    public void loop() {
        float t = -gamepad1.left_stick_y;
        float d = gamepad1.left_stick_x;
        float right = t - d;
        float left = t + d;
        right = wheels(right);
        left = wheels(left);
        frontRight.setPower(right);
        backRight.setPower(right);
        backLeft.setPower(left);
        frontLeft.setPower(left);
    }
    private float wheels(float out) {
        out = Range.clip(out, -1, 1);
        out = (float)scaleInput(out);
        return out;
    }
    private double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };
        int index = (int) (dVal * 16.0);
        if (index < 0) {
            index = -index;
        }
        if (index > 16) {
            index = 16;
        }
        double dScale;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }
        return dScale;
    }
}

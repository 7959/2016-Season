package org.firstinspires.ftc.teamcode;

/**
 * Created by Robi on 2/23/2017.
 */

public class TeleOp extends functionlist{
    
    public void runOpMode(){
        Map();
        MotorSet();
        waitForStart();
        while(opModeIsActive()){
            if(!gamepad1.right_bumper) {
                fL.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x);
                bL.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x);
                fR.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x);
                bR.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x);
            } else {
                fL.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x)/4);
                bL.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x)/4);
                fR.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x/4);
                bR.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x)/4);
            }

            if (gamepad1.right_trigger > 0) {
                lL.setPower(1);
                lR.setPower(1);
            } else {
                lL.setPower(0);
                lR.setPower(0);
            }
        }
    }
}

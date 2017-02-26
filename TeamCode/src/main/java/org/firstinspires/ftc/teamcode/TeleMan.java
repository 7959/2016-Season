package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Robi on 2/24/2017.
 */
@TeleOp(name = "Lock Smock Rock Operated Autonomous")
public class TeleMan extends functionlist{

    public void runOpMode(){
        Map();
        //loaderMap();
        MotorSet();
        waitForStart();
        while(opModeIsActive()){
            if(!gamepad1.right_bumper) {
                fL.setPower(-(gamepad1.left_stick_y - gamepad1.left_stick_x));
                bL.setPower(-(gamepad1.left_stick_y - gamepad1.left_stick_x));
                fR.setPower(-(gamepad1.left_stick_y + gamepad1.left_stick_x));
                bR.setPower(-(gamepad1.left_stick_y + gamepad1.left_stick_x));
            } else {
                fL.setPower(-((gamepad1.left_stick_y - gamepad1.left_stick_x)/4));
                bL.setPower(-((gamepad1.left_stick_y - gamepad1.left_stick_x)/4));
                fR.setPower(-(gamepad1.left_stick_y + gamepad1.left_stick_x/4));
                bR.setPower(-((gamepad1.left_stick_y + gamepad1.left_stick_x)/4));
            }
            if(gamepad1.left_bumper){
                //load.setPower(1);
            }
            if(gamepad1.left_trigger > 0){
                //spin.setPower(1);
            }
            if (gamepad1.right_trigger > 0) {
                lL.setPower(1);
                lR.setPower(1);
            } else {
                lL.setPower(0);
                lR.setPower(0);
            }
            telemetry.addData("R Red", uR.red());
            telemetry.addData("L Red", uL.red());
            telemetry.addData("R Blue", uR.blue());
            telemetry.addData("L Blue", uL.blue());
            telemetry.update();

            ///loader code
        }
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Robi on 2/15/2017.
 */
@TeleOp(name ="Cool TeleOp")
public class CoolTeleop extends opModeExtension {
    public void loop(){
        /**
        Controls:
         Leftstick- normal movement
         bumpers- strafe
         left trigger- loader
         right trigger- PEW PEW
        **/
        if(!gamepad1.right_bumper || !gamepad1.left_bumper){
            fL.setPower(gamepad1.left_stick_y+gamepad1.left_stick_x);
            fR.setPower(gamepad1.left_stick_y+gamepad1.left_stick_x);
            bL.setPower(gamepad1.left_stick_y-gamepad1.left_stick_x);
            bR.setPower(gamepad1.left_stick_y-gamepad1.left_stick_x);
        } else if(gamepad1.right_bumper || !gamepad1.left_bumper){
            fL.setPower(1);
            fR.setPower(-1);
            bL.setPower(-1);
            bR.setPower(1);
        } else if (!gamepad1.right_bumper || gamepad1.left_bumper){
            fL.setPower(-1);
            fR.setPower(1);
            bL.setPower(1);
            bR.setPower(-1);
        }


        if(gamepad1.right_trigger > 0){
            lL.setPower(1);
            lR.setPower(1);
        } else {
            lL.setPower(0);
            lR.setPower(0);
        }
        loader.setPower(gamepad1.left_trigger);
    }
}

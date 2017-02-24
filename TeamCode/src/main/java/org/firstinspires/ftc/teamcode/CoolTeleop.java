package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Robi on 2/15/2017.
 */
@TeleOp(name ="Billy Hilly Lily Shmilly")
public class CoolTeleop extends opModeExtension {
    boolean tank;
    boolean omi;
    int z;
    double ffl;
    double bbl;
    double ffr;
    double bbr;
    double Y = 1;
    double X = 1;
    int angle = 0;
    public void loop(){
        telemetry.addData("omi", omi);
        telemetry.addData("tank", tank);
        telemetry.addData("Fl", fL.getPower());
        telemetry.addData("FR", fR.getPower());
        telemetry.addData("bL", bL.getPower());
        telemetry.addData("bR", bR.getPower());
        telemetry.update();
        /**
        Controls:
         Leftstick- normal movement
         triggers - movements
         bumpers- pew pew and load load
         y omi directional drive
         b tank drive
        **/
        if(gamepad1.b){
            tank = true;
            omi = false;
        }
        if(gamepad1.y){
            tank = false;
            omi = true;
        }
        if(gamepad1.dpad_down){
            z = gyro.getIntegratedZValue();
            ffl = Y + X + (z - angle) / 10;
            bbl = Y - X + (z - angle) / 10;
            ffr = Y - X - (z - angle) / 10;
            bbr = Y + X - (z - angle) / 10;

            bbl = Range.clip(bbl, -1, 1);
            bbr = Range.clip(bbr, -1, 1);
            ffl = Range.clip(ffl, -1, 1);
            ffr = Range.clip(ffr, -1, 1);
            fL.setPower(ffl);
            fR.setPower(ffr);
            bL.setPower(bbl);
            bR.setPower(bbr);
        }
        if(tank) {
            if (gamepad1.right_trigger < .2 && gamepad1.left_trigger < .2) {
                fL.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x);
                fR.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x);
                bL.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x);
                bR.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x);
            } else if (gamepad1.left_trigger > 0 && gamepad1.right_trigger < .2) {
                fL.setPower(gamepad1.left_trigger);
                fR.setPower(-gamepad1.left_trigger);
                bL.setPower(-gamepad1.left_trigger);
                bR.setPower(gamepad1.left_trigger);
            } else if (gamepad1.left_trigger < .2 && gamepad1.right_trigger > 0) {
                fL.setPower(-gamepad1.right_trigger);
                fR.setPower(gamepad1.right_trigger);
                bL.setPower(gamepad1.right_trigger);
                bR.setPower(-gamepad1.right_trigger);
            } else if (gamepad1.right_trigger > 0 && gamepad1.left_trigger > 0) {
                fL.setPower(0);
                fR.setPower(0);
                bL.setPower(0);
                bR.setPower(0);
            } else {
                fL.setPower(0);
                fR.setPower(0);
                bL.setPower(0);
                bR.setPower(0);
            }
        }

        if(omi){
        fL.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x);
        bL.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x);
        fR.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x);
        bR.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x);
}

        if(gamepad1.right_bumper){
            lL.setPower(1);
            lR.setPower(1);
        } else {
            lL.setPower(0);
            lR.setPower(0);
        }

        //loader.setPower(gamepad1.left_trigger);
    }
}

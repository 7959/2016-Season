package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Robi on 2/24/2017.
 */
@TeleOp(name = "Hilly Billy Shilly tests")
public class Functiontests extends functionlist {
    double timer;
    public void runOpMode() {
        Map();
        MotorSet();
        gy.calibrate();
        while (!isStopRequested() && gy.isCalibrating()) telemetry.addData("...", gy.isCalibrating());
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Down Right", dR.green());
            telemetry.addData("Down Left", dL.green());
            telemetry.addData("Up Right R", uR.red());
            telemetry.addData("Up Right B", uR.blue());
            telemetry.addData("Up Left R", uR.red());
            telemetry.addData("Up Left L", uL.blue());
            telemetry.addData("right boolean", rightLine);
            telemetry.addData("Z", gy.getIntegratedZValue());
            telemetry.update();
            if(gamepad1.dpad_right){
                straightTillWhite(.1, 50, gy.getIntegratedZValue());
            }
            if (gamepad1.dpad_up) {
                straighttime(90, .1, 1);
            }
            if(gamepad1.dpad_down){
                straighttime(90, -.1, 1);
            }
            if (gamepad1.y) {
                linefindR(.1);
            }
            if(gamepad1.a){
                followpushR(.1, 90);
            }
            if(gamepad1.x){
                linefollowR(.1, 1);
            }
            if(gamepad1.left_bumper && gamepad1.right_bumper){
                rightLine = null;

            }
            if(gamepad1.right_trigger > .2){

                straighttime(90, .1, 1.5);
                timer = getRuntime();
                checkR(true, rightLine);
                straighttime(90, -.1, .2);

                while (!tcorrect1 && opModeIsActive()) {
                    while (getRuntime()  <= timer + 5);
                    straighttime(90, .1, .5);
                    timer = getRuntime();
                    checkR(true, rightLine);
                    straighttime(90, -.1, .2);
                }
                straighttime(90, -.1, .3);
            }

        }
    }
}
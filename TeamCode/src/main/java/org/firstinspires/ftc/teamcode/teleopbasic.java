package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Joseph on 12/17/2016.
 */

@TeleOp(name = "Basic TeleOp", group = "TeleOps")
public class teleopbasic extends Krusher99 {
    public void loop() {
        left.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x);
        right.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x);
    }
}

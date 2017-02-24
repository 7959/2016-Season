package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Robi on 2/23/2017.
 */
@TeleOp(name = "Judging")
public class Demo extends functionlist {
    double speed = 0;
    double angle = 0;
    public void runOpMode() {
        Map();
        MotorSet();
        telemetry.addData("Hey you!", "BACK OFF BUCKO I AM CALIBRATING");
        telemetry.update();
        gy.calibrate();

        while (!isStopRequested() && gy.isCalibrating()) ;

        telemetry.addData("All done", "Thanks");
        telemetry.update();
        waitForStart();
        gy.resetZAxisIntegrator();
        while(opModeIsActive()){
            straighttime(angle, speed, .1);
            if(gamepad1.a){
                speed = .1;
            }
            if(gamepad1.y){
                angle = 90;
            }
            if(gamepad1.b){
                angle = 0;
                speed = 0;
            }
        }
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Robi on 2/24/2017.
 */
@TeleOp(name = "Smest jest test lest pest", group = "Tests")
public class Test extends functionlist {
    public void runOpMode(){
        Map();
        MotorSet();
        telemetry.addData("Hey you!", "BACK OFF BUCKO I AM CALIBRATING");
        telemetry.update();
        gy.calibrate();
        while(!isStopRequested() && gy.isCalibrating());

        telemetry.addData("All done", "Thanks");
        telemetry.update();
        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("Mc fl", fL.getPortNumber());
            telemetry.addData("Mc fr", fR.getPortNumber());
            telemetry.addData("Mc bl", bL.getPortNumber());
            telemetry.addData("Mc br", bR.getPortNumber());
            telemetry.addData("UR", uR.green());
            telemetry.addData("UL", uL.green());
            telemetry.addData("DR", dR.green());
            telemetry.addData("DL", dL.green());
            telemetry.update();
        }
    }
}

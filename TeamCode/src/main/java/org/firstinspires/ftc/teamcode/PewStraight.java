package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Robi on 2/23/2017.
 */
@Autonomous(name = "Zilly Pew Pew Go Go")
public class PewStraight extends functionlist{
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
        sleep(7000);
        pew(true);
        //Power on loader as well.... in function
        sleep(2000);
        pew(false);
        straighttime(gy.getIntegratedZValue(), 1, 2.5);
    }
}

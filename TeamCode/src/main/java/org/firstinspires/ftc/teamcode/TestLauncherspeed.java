package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Robi on 2/22/2017.
 */
@TeleOp(name = "launch test")
public class TestLauncherspeed extends LinearOpMode{
    DcMotor lL;
    DcMotor lR;
    double startspeedR;
    double startspeedL;
    double thingdistanceL;
    double thingdistanceR;
    double distanceL;
    double distanceR;
    boolean a;
    double timee;
    double speedthing2 = 0;
    double j;
    double n;
    double b;
    public void runOpMode(){
        lL = hardwareMap.dcMotor.get("Launcher Left");
        lR = hardwareMap.dcMotor.get("Launcher Right");

        lL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        lR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        lL.setDirection(DcMotorSimple.Direction.FORWARD);
        lR.setDirection(DcMotorSimple.Direction.REVERSE);
        lR.setPower(1);
        lL.setPower(1);
        sleep(2000);
        while(!isStarted()){
            startspeedR = lR.getCurrentPosition();
            startspeedL = lL.getCurrentPosition();
        }
        while(opModeIsActive()){
            if(gamepad1.a){
                a = true;
                timee = getRuntime() + 1;
                thingdistanceL = lL.getCurrentPosition();
                thingdistanceR = lR.getCurrentPosition();
            } else a = false;
            while(a){
                if(getRuntime() >= timee) {
                    j = lL.getCurrentPosition() - thingdistanceL;
                    n = lR.getCurrentPosition() - thingdistanceR;
                    a = false;
                    speedthing2 = j;
                    b = n;
                }
            }
            distanceL = startspeedL - lL.getCurrentPosition();
            distanceR = startspeedR - lR.getCurrentPosition();
            telemetry.addData("manual test L", j);
            telemetry.addData("manual test R", speedthing2);
            telemetry.addData("Left speed", distanceL/getRuntime());
            telemetry.addData("Right speed", distanceR/getRuntime());
            telemetry.addData("L encoder", lL.getCurrentPosition());
            telemetry.addData("R encoder", lR.getCurrentPosition());
            telemetry.update();
        }

    }
}

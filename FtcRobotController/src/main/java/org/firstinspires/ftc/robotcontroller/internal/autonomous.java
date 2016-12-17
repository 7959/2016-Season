package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by Robi on 12/15/2016.
 * PLEASE DRAFT CODE IN AUTREDALL THANKS!
 */
@Autonomous(name = "AutoRed")
public class autonomous extends Krusher99Linear {

    private DcMotor frontL; // 1
    private DcMotor frontR; // 2
    private DcMotor backL; // 3
    private DcMotor backR; // 4
    private DcMotor middleL; // 5
    private DcMotor middleR; // 6
    private ColorSensor sensor1;
    private ColorSensor sensor2;
    private GyroSensor sensor3;
    private int phase = 0;// phase of autonomous duh
    private boolean correctbeacon = false;// true is beacon on left
    private int t = 0;// timer

    @Override
    public void runOpMode() {
        runOnInit();
    }

    public void findwhiteline() {//phase 0
        frontL.setPower(1);
        frontR.setPower(1);
        middleR.setPower(1);
        middleL.setPower(1);
        backR.setPower(1);
        backL.setPower(1);
        if (sensor2.red() > -1/*white*/) {
            phase = 1;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                telemetry.addData("Error", "Would not sleep");
            }
            frontL.setPower(0);
            frontR.setPower(0);
            middleR.setPower(0);
            middleL.setPower(0);
            backR.setPower(0);
            backL.setPower(0);

        }

    }

    public void getonline() {//phase1
        frontL.setPower(-1);
        frontR.setPower(1);
        middleR.setPower(1);
        middleL.setPower(-1);
        backR.setPower(1);
        backL.setPower(-1);
        if (sensor2.red() > -1/*white*/) {
            phase = 2;
            frontL.setPower(0);
            frontR.setPower(0);
            middleR.setPower(0);
            middleL.setPower(0);
            backR.setPower(0);
            backL.setPower(0);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                telemetry.addData("Error", "Robot.exe has insomnia");
            }
        }
    }


    public void followline() {//phase2
        if (sensor1.red() >= 3 || sensor1.blue() >= 3) {
            if(sensor1.red() > 3 && sensor1.blue() < 2){
                phase = 3;
                correctbeacon = true;
            }
            if(sensor1.blue() > 3 && sensor1.red() < 2){
                phase = 3;
                correctbeacon = false;
            }
        }
        if (sensor2.red() < -1/*white*/) {
            frontL.setPower(0);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(0);
            backR.setPower(1);
            backL.setPower(0);
        } else frontL.setPower(1);
        frontR.setPower(0);
        middleR.setPower(0);
        middleL.setPower(1);
        backR.setPower(0);
        backL.setPower(1);
    }
    public void pushbeacon(){

        } //CODE FOR PUSHY THING





    public void lastmove() {//set t to 0 in code before phase 10
        if (t < -1/*timetolaunch*/) {
            frontL.setPower(1);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(1);
            backR.setPower(1);
            backL.setPower(1);
            t++;
        } else if (t == -1/*timetolaunch*/){
            frontL.setPower(0);
            frontR.setPower(0);
            middleR.setPower(0);
            middleL.setPower(0);
            backR.setPower(0);
            backL.setPower(0);
            //PEW PEW CODE safe to use sleep here
            t++;
        } else frontL.setPower(1);
        frontR.setPower(1);
        middleR.setPower(1);
        middleL.setPower(1);
        backR.setPower(1);
        backL.setPower(1);
        if(sensor2.red() > -1/*floorred*/){
            phase=11;
            t=0;
        }
    }

    public void park(){//phase11
        if(t < 500) {//Might be a bit too much may need testing
            frontL.setPower(1);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(1);
            backR.setPower(1);
            backL.setPower(1);
            t++;
        } else frontL.setPower(0);
        frontR.setPower(0);
        middleR.setPower(0);
        middleL.setPower(0);
        backR.setPower(0);
        backL.setPower(0);
        phase=99;
    }


}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Robi on 12/15/2016.
 * PLEASE DRAFT CODE IN AUTREDALL THANKS!
 */
@Autonomous(name = "AutoRed")
public class autonomous extends Krusher99Linear {
    private int phase = 0;// phase of autonomous duh
    private boolean correctbeacon = false;// true is beacon on left
    private int t = 0;// timer
    private int tt = 0;
    private boolean pushError = false;

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
            phase++;
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
            phase++;
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
    public void selfcheck(){//set t = 0 before
        if(sensor1.blue() >= 3 && sensor1.red() < 2){
            pushError=true;
        }
        if(t < 200){
            frontL.setPower(-.5);
            frontR.setPower(-.5);
            middleR.setPower(-.5);
            middleL.setPower(-.5);
            backR.setPower(-.5);
            backL.setPower(-.5);
        } else if(pushError){
            phase++;
        }else frontL.setPower(0);
        frontR.setPower(0);
        middleR.setPower(0);
        middleL.setPower(0);
        backR.setPower(0);
        backL.setPower(0);
    }
    public void selfcorrect() {//set t = 0
        if (!pushError) {
            phase++;
        } else if (t < 4900) {
            frontL.setPower(0);
            frontR.setPower(0);
            middleR.setPower(0);
            middleL.setPower(0);
            backR.setPower(0);
            backL.setPower(0);
        } else if (tt < 300) {
            frontL.setPower(.5);
            frontR.setPower(.5);
            middleR.setPower(.5);
            middleL.setPower(.5);
            backR.setPower(.5);
            backL.setPower(.5);
        }
    }


    public void followline() {//phase2 Robot may need timer start to get slightly closer to beacon
        if (sensor1.red() >= 3 || sensor1.blue() >= 3) {
            if(sensor1.red() > 3 && sensor1.blue() < 2){
                phase++;
                correctbeacon = true;
            }
            if(sensor1.blue() > 3 && sensor1.red() < 2){
                phase++;
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
    public void aimbeacon() {//next
        if (correctbeacon == true) {
            frontL.setPower(0.5);
            frontR.setPower(-.5);
            middleR.setPower(-0.5);
            middleL.setPower(0.5);
            backR.setPower(-0.5);
            backL.setPower(0.5);
            if (sensor2.red() >= -1/*white*/) {
                frontL.setPower(0);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(0);
                backR.setPower(0);
                backL.setPower(0);
                phase++;
            }
        }
        if (correctbeacon == false) {
            frontL.setPower(-0.5);
            frontR.setPower(0.5);
            middleR.setPower(0.5);
            middleL.setPower(-0.5);
            backR.setPower(0.5);
            backL.setPower(-0.5);
            if (sensor2.red() >= -1/*white*/) {
                frontL.setPower(0);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(0);
                backR.setPower(0);
                backL.setPower(0);
                phase++;
            }
        }
    }
         //CODE FOR PUSHY THING





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
            phase++;
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

package org.firstinspires.ftc.robotcontroller.internal;

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
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "AutoRed")
public class autonomous extends LinearOpMode {

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

    /*
    Stuff we have drafted:

    EVERYTHING but its loops are weird so we wanna unify it under one and then once we have all functions tested, we will copy and
    paste it together.

<<<<<<< HEAD
    Put phases at end please.
=======
    Stuff needed put a "-" if coded and an "x" if tested "?" if currently working on and don't want other to mess with.
    -find white line
    -adjust to face beacon
    -check beacon
    push beacon
    double check to make sure didn't push the wrong one
    fix if broken
    move to next white line
    repeat find adjust and push
    back up to edge of white line
    use gyro sensor to turn perfectly to face ball and line up a shot
    move and shoot
    park on thingy using sensor2
>>>>>>> 82111237b28aab0a6427f9acd66f3e76328f5353

    Stuff needed put a "-" if coded and an "x" if tested "?" if currently working on and don't want other to mess with.
    -find white line 0
    -adjust to face beacon 1
    -check beacon 2
    ?push beacon 3
    double check to make sure didnt push the wrong one 4
    fix if broken 5
    move to next white line 6
    repeat find adjust and push 7
    back up to edge of white line 8
    use gyro sensor to turn perfectly to face ball and line up a shot 9
    ?move and shoot 10
    -park on thingy using sensor2 11
    end 99
     */

    @Override
    public void runOpMode() {
        frontL = hardwareMap.dcMotor.get("Front Left"); // 1
        frontR = hardwareMap.dcMotor.get("Front Right"); // 2

        middleL = hardwareMap.dcMotor.get("Middle Left"); // 3
        middleR = hardwareMap.dcMotor.get("Middle Right"); // 4

        backL = hardwareMap.dcMotor.get("Back Left"); // 5
        backR = hardwareMap.dcMotor.get("Back Right"); // 6

        sensor1 = hardwareMap.colorSensor.get("Up Sensor");
        sensor2 = hardwareMap.colorSensor.get("Down Sensor");

        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 1
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 2

        middleL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 3
        middleR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 4

        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 5
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); //

        frontL.setDirection(DcMotorSimple.Direction.REVERSE); // 1
        frontR.setDirection(DcMotorSimple.Direction.REVERSE); // 2

        middleL.setDirection(DcMotorSimple.Direction.REVERSE); // 4
        middleR.setDirection(DcMotorSimple.Direction.FORWARD); // 3

        backL.setDirection(DcMotorSimple.Direction.REVERSE); // 5
        backR.setDirection(DcMotorSimple.Direction.FORWARD); // 6

        frontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 1
        frontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 2

        middleL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 3
        middleR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 4

        backL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 5
        backR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 6
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

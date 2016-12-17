package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.ColorSensor;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.hardware.TouchSensor;
        import com.qualcomm.robotcore.hardware.GyroSensor;
/**
 * Created by Robi on 12/7/2016.
 * Draft and test simple functions here
 */

@Disabled
@Autonomous(name = "Auto Red All")
                                                                                                                                                                                                                                                public abstract class AutoRedAll extends LinearOpMode {
    private DcMotor frontL; // 1
    private DcMotor frontR; // 2
    private DcMotor backL; // 3
    private DcMotor backR; // 4
    private DcMotor middleL; // 5
    private DcMotor middleR; // 6
    private ColorSensor sensor1;
    private ColorSensor sensor2;
    private GyroSensor sensor3;
    private boolean Thing = false;
    private boolean THING = false;
    private int THINg = 0;
    private int thing;
    private void Testythingy(){
        while(true){
            thing=sensor3.rawY();
            thing++;
        }
    }
    private void wherebetheline(){
        while(true){
            frontL.setPower(-1);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(-1);
            backR.setPower(1);
            backL.setPower(-1);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                telemetry.addData("Error", "Robot has insomnia");
            }
            THINg++;
            if(sensor2.red() > 0/*white*/ && THINg < 390){
                frontL.setPower(0);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(0);
                backR.setPower(0);
                backL.setPower(0);
                THING = true;
                THINg = 0;
                break;
            }
            if(sensor2.red() > 0/*white*/ && THINg > 390){
                frontL.setPower(0);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(0);
                backR.setPower(0);
                backL.setPower(0);
                THING = false;
                THINg = 0;
                break;
            }

        }
    }
    private void CallibrateThingy(){//PROTOTYPE RED LEFT BEACON PUSH 2
        while(true){
            if(sensor2.red() < 0/*WHITE*/) {
                frontL.setPower(0);
                frontR.setPower(1);
                middleR.setPower(1);
                middleL.setPower(0);
                backR.setPower(1);
                backL.setPower(0);
            }
            if(sensor2.red() > 0/*WHITE*/){
                frontL.setPower(1);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(1);
                backR.setPower(0);
                backL.setPower(1);
            }
        }
    }

    public void findWhiteLine() {//find white line
        while (true) {
            frontL.setPower(1);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(1);
            backR.setPower(1);
            backL.setPower(1);
            if (sensor2.red() > -1/*White*/) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    telemetry.addData("Error", "Robot has insomnia");
                }
                break;
            }
        }
    }
    private void I_have_no_idea_what_this_does(){//test for push the right color
        frontL.setPower(0);
        frontR.setPower(0);
        middleR.setPower(0);
        middleL.setPower(0);
        backR.setPower(0);
        backL.setPower(0);
        try {
            Thread.sleep(75);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Robot has insomnia");
        }
        if(sensor1.blue() > 2 && sensor1.red() < 1){
            while(true) {
                frontL.setPower(-1);
                frontR.setPower(-1);
                middleR.setPower(-1);
                middleL.setPower(-1);
                backR.setPower(-1);
                backL.setPower(-1);
                if(sensor2.red() > 0/*WHITE*/){
                    frontL.setPower(0);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(0);
                    backR.setPower(0);
                    backL.setPower(0);
                    break;
                }
            }
        }
    }
    private void faceBeacon() {//face beacon
        while (true) {
            frontL.setPower(-1);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(-1);
            backR.setPower(1);
            backL.setPower(-1);
            if (sensor2.red() > 0/* WHITE */ && sensor1.red() + sensor1.blue() > 0/* VALUE */) {
                break;

            }
        }
    }

    private void moveFowardToBeacon() {//move closer to beacon
        while (true) {
            if (sensor2.red() < 0/*WHITE*/)
                frontL.setPower(0);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(0);
            backR.setPower(1);
            backL.setPower(0);
            if (sensor2.red() > 0/*WHITE*/) {
                frontL.setPower(1);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(1);
                backR.setPower(0);
                backL.setPower(1);
            }
            if (sensor1.red() < 0/*CLOSEDISTANCE*/|| sensor1.blue() < 0 )  {
                break;
            }

        }
    }
    private void findColorSide1(){//find color on sensor1 side
        while(true){
            if (sensor1.red() > 3 && sensor2.blue() < 1) {
                Thing = true;
                break;
            }
            if (sensor1.red() > 3 && sensor2.blue() < 1) {
                Thing = false;
                break;

            }
        }
    }

    private void pushBeacon() {// push beacon NEEDS IMPROVEMENT WHEN SENSORS ARRIVE
        if (Thing == true) {
            frontL.setPower(0.5);
            frontR.setPower(-.5);
            middleR.setPower(-0.5);
            middleL.setPower(0.5);
            backR.setPower(-0.5);
            backL.setPower(0.5);
            try {
                Thread.sleep(78);
            } catch (InterruptedException e) {
                telemetry.addData("Error", "Robot has insomnia");
            }
        }
        if (Thing == false) {
            frontL.setPower(-0.5);
            frontR.setPower(0.5);
            middleR.setPower(0.5);
            middleL.setPower(-0.5);
            backR.setPower(0.5);
            backL.setPower(-0.5);
            try {
                Thread.sleep(78);
            } catch (InterruptedException e) {
                telemetry.addData("Error", "Robot has insomnia");
            }
        }
        frontL.setPower(.5);
        frontR.setPower(.5);
        middleR.setPower(.5);
        middleL.setPower(.5);
        backR.setPower(.5);
        backL.setPower(0.5);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Robot has insomnia");
        }
        frontL.setPower(-.5);
        frontR.setPower(-.5);
        middleR.setPower(-.5);
        middleL.setPower(-.5);
        backR.setPower(-.5);
        backL.setPower(-0.5);
        try {
            Thread.sleep(75);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Robot has insomnia");
        }

    }

    private void returnWhite() {//Return to white line
        if(Thing = false)
            while(true){
                frontL.setPower(0.5);
                frontR.setPower(-0.5);
                middleR.setPower(-0.5);
                middleL.setPower(0.5);
                backR.setPower(-0.5);
                backL.setPower(0.5);
                if(sensor2.red() > 0/*WHITE*/){
                    break;
                }


            }
        if (Thing == true){
            while(true) {
                frontL.setPower(-0.5);
                frontR.setPower(0.5);
                middleR.setPower(0.5);
                middleL.setPower(-0.5);
                backR.setPower(0.5);
                backL.setPower(-0.5);
                if(sensor2.red() > 0/*WHITE*/){
                    break;
                }
            }
        }
        while(true){
            if(sensor2.red() < 0/*WHITE*/)
            frontL.setPower(-1);
            frontR.setPower(0);
            middleR.setPower(0);
            middleL.setPower(-1);
            backR.setPower(0);
            backL.setPower(-1);

            if (sensor2.red() > 0/*WHITE*/) {
                frontL.setPower(0);
                frontR.setPower(-1);
                middleR.setPower(-1);
                middleL.setPower(0);
                backR.setPower(-1);
                backL.setPower(0);
            }
            if (sensor1.red() > 0/*VALUE*/ && sensor2.blue() < 0/*(WHITE)*/) {

                Thing = false;
                break;
            }
        }
    }
    private void turnToBeacon2() {//Right turn to 2nd beacon
        frontL.setPower(1);
        frontR.setPower(-1);
        middleR.setPower(-1);
        middleL.setPower(1);
        backR.setPower(-1);
        backL.setPower(1);
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Robot has insomnia");
        }
    }
    public void end(){
        frontL.setPower(-1);
        frontR.setPower(1);
        middleR.setPower(1);
        middleL.setPower(-1);
        backR.setPower(1);
        backL.setPower(-1);
        try {
            Thread.sleep(0/*TURN TO CAPBALL*/);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Robot has insomnia");
        }
        while(true){
            if(sensor2.red() > 0/*REDORBLUEIDUNNO*/){
                frontL.setPower(1);
                frontR.setPower(1);
                middleR.setPower(1);
                middleL.setPower(1);
                backR.setPower(1);
                backL.setPower(1);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    telemetry.addData("Error", "Robot has insomnia");
                }
                break;
            } else  frontL.setPower(1);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(1);
            backR.setPower(1);
            backL.setPower(1);
        }
        frontL.setPower(0);
        frontR.setPower(0);
        middleR.setPower(0);
        middleL.setPower(0);
        backR.setPower(0);
        backL.setPower(0);
        }




    }

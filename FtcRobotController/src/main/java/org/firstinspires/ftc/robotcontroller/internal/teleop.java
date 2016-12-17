package org.firstinspires.ftc.robotcontroller.internal;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;


/**
 * Created by Team 7959 on 10/4/2016.
 * I fucked up but Ill fix it and make it better than ever!
 * Jasera's Wolf Head
 */


@TeleOp(name="Default TeleOp", group = "TeleOps")
public class teleop extends OpMode {


    private DcMotor frontL; // 1
    private DcMotor frontR; // 2
    private DcMotor backL; // 3
    private DcMotor backR; // 4
    private DcMotor middleL; // 5
    private DcMotor middleR; // 6
    private ColorSensor sensor1;
    private ColorSensor sensor2;
    private GyroSensor Gsensor;
    private boolean thing = false;
    private boolean Thing = false;
    private int THing = 0;
    

    @Override
    public void init()
    {
        frontL = hardwareMap.dcMotor.get("Front Left"); // 1
        frontR = hardwareMap.dcMotor.get("Front Right"); // 2
        middleL = hardwareMap.dcMotor.get("Middle Left"); // 3
        middleR = hardwareMap.dcMotor.get("Middle Right"); // 4
        backL = hardwareMap.dcMotor.get("Back Left"); // 5
        backR = hardwareMap.dcMotor.get("Back Right"); // 6
        sensor1 = hardwareMap.colorSensor.get("Up Sensor");
        sensor2 = hardwareMap.colorSensor.get("Down Sensor");
        Gsensor = hardwareMap.gyroSensor.get("Gyro Sensor");
        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 1
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 2
        middleL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 3
        middleR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 4
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 5
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 6
        frontL.setDirection(DcMotorSimple.Direction.FORWARD); // 1
        frontR.setDirection(DcMotorSimple.Direction.FORWARD); // 2
        middleL.setDirection(DcMotorSimple.Direction.FORWARD); // 4
        middleR.setDirection(DcMotorSimple.Direction.REVERSE); // 3
        backL.setDirection(DcMotorSimple.Direction.FORWARD); // 5
        backR.setDirection(DcMotorSimple.Direction.REVERSE); // 6
        frontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 1
        frontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 2
        middleL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 3
        middleR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 4
        backL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 5
        backR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 6
    }
    @Override
    public void start()
    {
        sensor1.enableLed(true);
        //sensor2.enableLed(true);
    }
    public void pushbotL() {
        while (true) {
            if (gamepad1.b) {
                break;
            }
            if (testthing < 1/*WHITE*/) {
                frontL.setPower(0);
                frontR.setPower(1);
                middleR.setPower(1);
                middleL.setPower(0);
                backR.setPower(1);
                backL.setPower(0);
            } else {
                frontL.setPower(1);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(1);
                backR.setPower(0);
                backL.setPower(1);
            }
        }
    }
    private int testthing = 0;
    private int test2 = 0;
    public void pushbotR() {
        while (true) {
            if (gamepad1.b) {

                break;
            }
            if (!gamepad2.y/*WHITE*/) {
                frontL.setPower(0);
                frontR.setPower(1);
                middleR.setPower(1);
                middleL.setPower(0);
                backR.setPower(1);
                backL.setPower(0);
                testthing++;
            } else {
                frontL.setPower(1);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(1);
                backR.setPower(0);
                backL.setPower(1);
                test2++;
                testthing = 0;
            }
        }
    }
    public void aimbotR() {
        Thing = false;
        THing = 0;
        while (true) {
            if (!thing) {
                frontL.setPower(1);
                frontR.setPower(-1);
                middleL.setPower(1);
                middleR.setPower(-1);
                backL.setPower(1);
                backR.setPower(-1);
            } else {
                frontL.setPower(-1);
                frontR.setPower(1);
                middleL.setPower(-1);
                middleR.setPower(1);
                backL.setPower(-1);
                backR.setPower(1);
            }
            if (testthing > 0/*WHITE*/) {
                break;
            }
            if (gamepad1.b) {
                Thing = true;
                break;
            }
            while (true) {
                frontL.setPower(1);
                frontR.setPower(1);
                middleR.setPower(1);
                middleL.setPower(1);
                backR.setPower(1);
                backL.setPower(1);
                if (testthing > 0/*WHITE*/) {
                    break;
                }

            }
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    telemetry.addData("Error", "Would not sleep");
                }

                if (gamepad1.b) {
                    Thing = true;
                    break;
                }
                if (THing < 0/*VALUE TO GET RIGHT DISTANCE*/) {
                    frontL.setPower(1);
                    frontR.setPower(1);
                    middleR.setPower(1);
                    middleL.setPower(1);
                    backR.setPower(1);
                    backL.setPower(1);
                }
            }
        }
    }

    public void aimbotL(){
        Thing = false;
        THing = 0;
        while (true){
            if(!thing) {
                frontL.setPower(-1);
                frontR.setPower(1);
                middleL.setPower(-1);
                middleR.setPower(1);
                backL.setPower(-1);
                backR.setPower(1);
            } else {
                frontL.setPower(1);
                frontR.setPower(-1);
                middleL.setPower(1);
                middleR.setPower(-1);
                backL.setPower(1);
                backR.setPower(-1);
            }
            if(testthing > 0/*WHITE*/){
                break;
            }
            if(gamepad1.b){
                Thing = true;
                break;
            }
            while(true){
                frontL.setPower(1);
                frontR.setPower(1);
                middleR.setPower(1);
                middleL.setPower(1);
                backR.setPower(1);
                backL.setPower(1);
                if(testthing > 0/*WHITE*/){
                    break;
                }
                if (gamepad1.b) {
                    Thing = true;
                    break;
                }
            }
            while(true){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    telemetry.addData("Error", "Would not sleep");
                }
                if(gamepad1.b){
                    Thing = true;
                    break;
                }
                if(THing < 0/*VALUE TO GET RIGHT DISTANCE*/) {
                    frontL.setPower(1);
                    frontR.setPower(1);
                    middleR.setPower(1);
                    middleL.setPower(1);
                    backR.setPower(1);
                    backL.setPower(1);
                }
                if(THing >= 0/*VALUE TO GET RIGHT DISTANCE)*/) {
                    frontL.setPower(0);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(0);
                    backR.setPower(0);
                    backL.setPower(0);
                    //INSERT LAUNCH CODE HERE
                }
            }
        }
    }

    @Override
    public void loop(){
        if (gamepad1.start && gamepad1.back && gamepad2.start && gamepad2.back) requestOpModeStop();
        if(!thing) {
            frontL.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x); // 1
            frontR.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x); // 2
            middleL.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x); // 3
            middleR.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x); // 4
            backL.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x); // 5
            backR.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x); // 6
        } else {
            frontL.setPower(-(gamepad1.left_stick_y + gamepad1.left_stick_x)); // 1
            frontR.setPower(-(gamepad1.left_stick_y - gamepad1.left_stick_x)); // 2
            middleL.setPower(-(gamepad1.left_stick_y + gamepad1.left_stick_x)); // 3
            middleR.setPower(-(gamepad1.left_stick_y - gamepad1.left_stick_x)); // 4
            backL.setPower(-(gamepad1.left_stick_y + gamepad1.left_stick_x)); // 5
            backR.setPower(-(gamepad1.left_stick_y - gamepad1.left_stick_x)); // 6
        }
        /*if(gamepad1.left_bumper == true){
            pushbotL();
        }
        if(gamepad1.right_bumper == true){
            pushbotR();
        }
        if(gamepad1.a == true){
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                telemetry.addData("Error", "Would not sleep");
            }
            if(thing == true){
                thing = false;
            }
            else thing = true;
        }*/
        telemetry.addData("Dblue", sensor2.blue());
        telemetry.addData("Dgreen", sensor2.green());
        telemetry.addData("Dred", sensor2.red());
        telemetry.addData("Ublue", sensor1.blue());
        telemetry.addData("Ugreen", sensor1.green());
        telemetry.addData("Ured", sensor1.red());
        telemetry.addData("Gsensory", Gsensor.rawY());
        telemetry.addData("Gsensorx", Gsensor.rawX());
        telemetry.addData("Gsensorz", Gsensor.rawZ());
        telemetry.addData("Speed", (frontL.getPower() + frontR.getPower() / 2) + "%");
        telemetry.addData("Runtime", getRuntime());
        //telemetry.addData("Osensor2 connection", Osensor.getConnectionInfo());

    }
}

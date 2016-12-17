package org.firstinspires.ftc.robotcontroller.internal;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;


/**
 * Created by Team 7959 on 10/4/2016.
 * I fucked up but Ill fix it and make it better than ever!
 * Jasera's Wolf Head
 */


@TeleOp(name="Semi-Auto TeleOp", group = "TeleOps")
public class teleop extends Krusher99 {
    private boolean recentToggle = false;
    private boolean toggled = false;
    private boolean Thing = false;
    private int THing = 0;
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
            if (!toggled) {
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
            if(!toggled) {
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
        if (gamepad1.start && gamepad1.back) requestOpModeStop();

        if(!toggled) {
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

        if (gamepad1.a && !recentToggle) {
            recentToggle = true;
            toggled = !toggled;
        } else if (!gamepad1.a) recentToggle = false;

        telemetry.addData("Dblue", sensor2.blue());
        telemetry.addData("Dgreen", sensor2.green());
        telemetry.addData("Dred", sensor2.red());
        telemetry.addData("Ublue", sensor1.blue());
        telemetry.addData("Ugreen", sensor1.green());
        telemetry.addData("Ured", sensor1.red());
        telemetry.addData("Gsensorx", Gsensor.rawX());
        telemetry.addData("Gsensory", Gsensor.rawY());
        telemetry.addData("Gsensorz", Gsensor.rawZ());
        telemetry.addData("Average Motor Speed", (frontL.getPower() + frontR.getPower() / 2) + "%");
        telemetry.addData("Runtime", getRuntime());
        telemetry.addData("OSesnor", ODsensor.getRawLightDetected());
        //telemetry.addData("Osensor2 connection", Osensor.getConnectionInfo());

    }
}

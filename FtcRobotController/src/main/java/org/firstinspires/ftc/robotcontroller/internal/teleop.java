package org.firstinspires.ftc.robotcontroller.internal;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


/**
 * Created by Team 7959 on 10/4/2016.
 *
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
    private ColorSensor usensor;
    private ColorSensor dsensor;
    boolean thing = false;
    boolean Thing = false;
    int THing = 0;


    @Override
    public void init()
    {
        frontL = hardwareMap.dcMotor.get("Front Left"); // 1
        frontR = hardwareMap.dcMotor.get("Front Right"); // 2
        middleL = hardwareMap.dcMotor.get("Middle Left"); // 3
        middleR = hardwareMap.dcMotor.get("Middle Right"); // 4
        backL = hardwareMap.dcMotor.get("Back Left"); // 5
        backR = hardwareMap.dcMotor.get("Back Right"); // 6
        usensor = hardwareMap.colorSensor.get("Up Sensor");
        //dsensor = hardwareMap.colorSensor.get("Down Sensor");
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
        usensor.enableLed(true);
        //dsensor.enableLed(true);
    }

    public void aimbotR() {
        Thing = false;
        THing = 0;
        while (true) {
            if (thing == false) {
                frontL.setPower(1);
                frontR.setPower(-1);
                middleL.setPower(1);
                middleR.setPower(-1);
                backL.setPower(1);
                backR.setPower(-1);
            }
            if (thing == true) {
                frontL.setPower(-1);
                frontR.setPower(1);
                middleL.setPower(-1);
                middleR.setPower(1);
                backL.setPower(-1);
                backR.setPower(1);
            }
            if (dsensor.red() > 0/*WHITE*/) {
                break;
            }
            if (gamepad1.b == true) {
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
                if (dsensor.red() > 0/*WHITE*/) {
                    break;
                }

            }
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    telemetry.addData("Error", "Would not sleep");
                }

                if (gamepad1.b == true) {
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
            if(thing == false) {
                frontL.setPower(-1);
                frontR.setPower(1);
                middleL.setPower(-1);
                middleR.setPower(1);
                backL.setPower(-1);
                backR.setPower(1);
            }
            if(thing == true){
                frontL.setPower(1);
                frontR.setPower(-1);
                middleL.setPower(1);
                middleR.setPower(-1);
                backL.setPower(1);
                backR.setPower(-1);
            }
            if(dsensor.red() > 0/*WHITE*/){
                break;
            }
            if(gamepad1.b == true){
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
                if(dsensor.red() > 0/*WHITE*/){
                    break;
                }
                if (gamepad1.b == true) {
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

                if(gamepad1.b == true){
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
                if(THing >= 0/*VALUE TO GET RIGHT DISTANCE)*/){
                    frontL.setPower(0);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(0);
                    backR.setPower(0);
                    backL.setPower(0);
                    //INSERT LAUNCH CODE HERE
                } else THing++;


            }
        }


    }

    @Override
    public void loop(){
        if (gamepad1.start && gamepad1.back && gamepad2.start && gamepad2.back) requestOpModeStop();
        if(thing == false) {
            frontL.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x); // 1
            frontR.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x); // 2
            middleL.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x); // 3
            middleR.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x); // 4
            backL.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x); // 5
            backR.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x); // 6
        }
        if(thing == true) {
            frontL.setPower(-(gamepad1.left_stick_y + gamepad1.left_stick_x)); // 1
            frontR.setPower(-(gamepad1.left_stick_y - gamepad1.left_stick_x)); // 2
            middleL.setPower(-(gamepad1.left_stick_y + gamepad1.left_stick_x)); // 3
            middleR.setPower(-(gamepad1.left_stick_y - gamepad1.left_stick_x)); // 4
            backL.setPower(-(gamepad1.left_stick_y + gamepad1.left_stick_x)); // 5
            backR.setPower(-(gamepad1.left_stick_y - gamepad1.left_stick_x)); // 6
        }
        if(gamepad1.left_bumper == true){
            aimbotL();
        }
        if(gamepad1.right_bumper == true){
            aimbotR();
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
        }
        /*telemetry.addData("Dblue", dsensor.blue());
        telemetry.addData("Dgreen", dsensor.green());
        telemetry.addData("Dred", dsensor.red());
        */telemetry.addData("Ublue", usensor.blue());
        telemetry.addData("Ugreen", usensor.green());
        telemetry.addData("Ured", usensor.red());
        /*telemetry.addData("Gsensor connection", Gsensor.getConnectionInfo());
        telemetry.addData("ODsensor connection", Osensor.getConnectionInfo());
        telemetry.addData("Usensor connection", usensor.getConnectionInfo());
        telemetry.addData("Dsensor connection", dsensor.getConnectionInfo());
        */
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Robi on 1/11/2017.
 */
@Disabled
@Autonomous(name = "autored")
public class Autored extends OpMode {
    protected DcMotor frontL; // 1
    protected DcMotor frontR; // 2
    protected DcMotor backL; // 3
    protected DcMotor backR; // 4
    protected DcMotor middleL; // 5
    protected DcMotor middleR; // 6
    //protected DcMotor launcher;
    protected ColorSensor sensor1;
    protected ColorSensor sensor2;
    protected GyroSensor Gsensor;
    private int phase = 0;
    private boolean Correct1 = false;


    //protected OpticalDistanceSensor ODsensor;
    //protected I2cDeviceSynchImpl sensor1imp = new I2cDeviceSynchImpl(, sensor1.getI2cAddress(), false);
    public void init() {
        frontL = hardwareMap.dcMotor.get("Front Left"); // 1
        frontR = hardwareMap.dcMotor.get("Front Right"); // 2
        middleL = hardwareMap.dcMotor.get("Middle Left"); // 3
        middleR = hardwareMap.dcMotor.get("Middle Right"); // 4
        backL = hardwareMap.dcMotor.get("Back Left"); // 5
        backR = hardwareMap.dcMotor.get("Back Right"); // 6
        /// launcher = hardwareMap.dcMotor.get("Launcher");
        //sensor1 = hardwareMap.colorSensor.get("Up Sensor");
        //sensor1.setI2cAddress(I2cAddr.create8bit(0x40));
        sensor2 = hardwareMap.colorSensor.get("Down Sensor");
        //sensor2.setI2cAddress(I2cAddr.create8bit(0x41));
        Gsensor = hardwareMap.gyroSensor.get("Gyro Sensor");
        //ODsensor = hardwareMap.opticalDistanceSensor.get("OD Sensor");
        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 1
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 2
        middleL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 3
        middleR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 4
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 5
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 6
        //launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
        ///launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        telemetry.addData("Current phase", phase);
        telemetry.addData("Dsensor", sensor2.green());
        telemetry.addData("runtime", getRuntime());
    }

    public void loop() {
        telemetry.addData("runtime", getRuntime());
        telemetry.addData("Dsensor", sensor2.green());
        telemetry.addData("Bsensor", sensor1.blue());
        telemetry.addData("Rsensor", sensor1.red());
        if (phase == 0 || phase == 6) {
            telemetry.addData("phase", phase);
            frontL.setPower(1);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(1);
            backR.setPower(1);
            backL.setPower(1);
            if (sensor2.green() > 3) {
                phase++;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    telemetry.addData("Oh noes", "Robot.exe has insomnia");
                }
                frontL.setPower(0);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(0);
                backR.setPower(0);
                backL.setPower(0);

            }

        }
        if (phase == 1 || phase == 7) {
            telemetry.addData("phase", phase);
            frontL.setPower(-1);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(-1);
            backR.setPower(1);
            backL.setPower(-1);
            if (sensor2.green() > 3) {
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
                    telemetry.addData("Oh noes", "Robot.exe has insomnia");
                }
            }
        }
        if (phase == 2){//close in on beacon code feel free to reuse in other phases
            telemetry.addData("phase", phase);
            if (sensor1.red() >= 3 && sensor1.blue() <= 1){
                phase++;
                Correct1= true;
            }
            if (sensor1.red() <= 1 && sensor1.blue() >= 3){
                phase++;
                Correct1= false;
            }
            if (sensor2.green() <= 3) {
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
        if (phase == 3 || phase == -1){
            telemetry.addData("phase", phase);
            if (Correct1){
                frontL.setPower(.5);
                frontR.setPower(.5);
                middleR.setPower(.5);
                middleL.setPower(.5);
                backR.setPower(.5);
                backL.setPower(.5);
                try {
                    Thread.sleep(1000);//Value subject to change
                } catch (InterruptedException e) {
                    telemetry.addData("Oh noes", "Robot.exe has insomnia");
                }
                phase++;
            }
            if(!Correct1){
                telemetry.addData("I HAVE NO IDEA WHAT WE ARE DOING HERE", "AHHHHHHHH");
                try {
                    Thread.sleep(5000);//Value subject to change
                } catch (InterruptedException e) {
                    telemetry.addData("Error", "Robot.exe has insomnia");
                }
                requestOpModeStop();
            }
        }
        if(phase == 5){// it is literally turn left
            telemetry.addData("phase", phase);
            telemetry.addData("Help my turn", "AHHHHH");
            frontL.setPower(1);
            frontR.setPower(-1);
            middleR.setPower(1);
            middleL.setPower(-1);
            backR.setPower(1);
            backL.setPower(-1);
            try {
                Thread.sleep(500);//Value subject to change
            } catch (InterruptedException e) {
                telemetry.addData("Error", "Robot.exe has insomnia");
            }
        }

        }
    }

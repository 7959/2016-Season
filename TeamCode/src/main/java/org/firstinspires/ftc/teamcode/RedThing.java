package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchDevice;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.I2cController;
import com.qualcomm.robotcore.util.TypeConversion;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Robi on 1/19/2017.
 */
@Autonomous(name = "RedThing")
public class RedThing extends OpMode {
        private int phase;
        private int cal = 0;
        protected DcMotor frontL; // 1
        protected DcMotor frontR; // 2
        protected DcMotor backL; // 3
        protected DcMotor backR; // 4
        protected DcMotor middleL; // 5
        protected DcMotor middleR; // 6
        //protected DcMotor launcher;
        //protected DcMotor launcher2;
        protected ColorSensor sensor1;
        protected ColorSensor sensor2;
        protected GyroSensor Gsensor;
        //protected DeviceInterfaceModule dim;
        private int P = 0;
        //protected OpticalDistanceSensor ODsensor;
        public void init() {
        frontL = hardwareMap.dcMotor.get("Front Left"); // 1
        frontR = hardwareMap.dcMotor.get("Front Right"); // 2
        middleL = hardwareMap.dcMotor.get("Middle Left"); // 3
        middleR = hardwareMap.dcMotor.get("Middle Right"); // 4
        backL = hardwareMap.dcMotor.get("Back Left"); // 5
        backR = hardwareMap.dcMotor.get("Back Right"); // 6
        //launcher = hardwareMap.dcMotor.get("Launcher");

            //dim = hardwareMap.deviceInterfaceModule.get("dim");

            //launcher2 = hardwareMap.dcMotor.get("Launcher2");
            sensor1 = hardwareMap.colorSensor.get("Up Sensor");
            sensor1.setI2cAddress(I2cAddr.create7bit(0x1e));
            sensor2 = hardwareMap.colorSensor.get("Down Sensor");
            sensor2.setI2cAddress(I2cAddr.create7bit(0x1d));
            Gsensor = hardwareMap.gyroSensor.get("Gyro Sensor");
            //ODsensor = hardwareMap.opticalDistanceSensor.get("OD Sensor");
        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 1
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 2
        middleL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 3
        middleR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 4
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 5
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 6

            //launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            //launcher2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontL.setDirection(DcMotorSimple.Direction.FORWARD); // 1
        frontR.setDirection(DcMotorSimple.Direction.FORWARD); // 2
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
        //launcher2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            //launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            telemetry.addData("phase", phase);
            //dim.setLED(0x0, false);
            //dim.setLED(0x1, true);
            //dim.setLED(0x2, true);
        }
        public void loop(){
            telemetry.addData("runtime", getRuntime());
            telemetry.addData("Dsensor", sensor2.green());
            telemetry.addData("Bsensor", sensor1.blue());
            telemetry.addData("Rsensor", sensor1.red());
            telemetry.addData("pointy thingy", cal);
            if (phase == 0 || phase == 6) {
                telemetry.addData("phase", phase);
                frontL.setPower(.5);
                frontR.setPower(.5);
                middleR.setPower(.5);
                middleL.setPower(.5);
                backR.setPower(.5);
                backL.setPower(.5);
                if (sensor2.green() > 2) {
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
            if (phase == 1 || phase == 7) {
                telemetry.addData("phase", phase);
                frontL.setPower(-.1);
                frontR.setPower(.1);
                middleR.setPower(.1);
                middleL.setPower(-.1);
                backR.setPower(.1);
                backL.setPower(-.1);
                if (sensor2.green() > 3) {
                    phase++;
                    frontL.setPower(0);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(0);
                    backR.setPower(0);
                    backL.setPower(0);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        telemetry.addData("Oh noes", "Robot.exe has insomnia");
                    }
                }
            }
            if(phase == 2){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    telemetry.addData("Oh noes", "Robot.exe has insomnia");
                }
                frontL.setPower(-.1);
                frontR.setPower(.1);
                middleR.setPower(.1);
                middleL.setPower(-.1);
                backR.setPower(.1);
                backL.setPower(-.1);
                cal++;
                if(sensor2.green() < 3){
                    phase++;
                    frontL.setPower(0);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(0);
                    backR.setPower(0);
                    backL.setPower(0);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        telemetry.addData("Oh noes", "Robot.exe has insomnia");
                    }
                    cal=cal/2;
                }
            }
            if(phase == 3){
                /*try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    telemetry.addData("Oh noes", "Robot.exe has insomnia");
                }
                frontL.setPower(.1);
                frontR.setPower(-.1);
                middleR.setPower(-.1);
                middleL.setPower(1);
                backR.setPower(-.1);
                backL.setPower(.1);*/
                //cal--;
                if (sensor1.green() >= 0) {
                    frontL.setPower(0);
                    frontR.setPower(.25);
                    middleR.setPower(.25);
                    middleL.setPower(0);
                    backR.setPower(.25);
                    backL.setPower(0);
                }
                if (sensor1.green() <= 0) {
                    frontL.setPower(0);
                    frontR.setPower(.25);
                    middleR.setPower(.25);
                    middleL.setPower(0);
                    backR.setPower(.25);
                    backL.setPower(0);
                }
                if(sensor1.red() >= 3){
                    phase++;
                    frontL.setPower(0);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(0);
                    backR.setPower(0);
                    backL.setPower(0);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        telemetry.addData("Oh noes", "Robot.exe has insomnia");
                    }
                }
            }
            if(phase==4){
                telemetry.addData("phase", "YAY :D");
                frontL.setPower(.25);
                frontR.setPower(.25);
                middleR.setPower(.25);
                middleL.setPower(.25);
                backR.setPower(.25);
                backL.setPower(.25);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    telemetry.addData("Oh noes", "Robot.exe has insomnia");
                }
            }
        }

}

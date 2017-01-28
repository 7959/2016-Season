package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;

/**
 * Created by Robi on 1/21/2017.
 */
@Autonomous(name = "Foward")
public class Foward extends OpMode {
    protected DcMotor frontL; // 1
    protected DcMotor frontR; // 2
    protected DcMotor backL; // 3
    protected DcMotor backR; // 4
    protected DcMotor middleL; // 5
    protected DcMotor middleR; // 6
    //protected DcMotor launcher;
    //protected DcMotor launcher2;
    /*protected ColorSensor sensor1;
    protected ColorSensor sensor2;
    protected ColorSensor sensor3;
    protected GyroSensor Gsensor;
    */private boolean k = true;
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
  /*      sensor3 = hardwareMap.colorSensor.get("Up Sensor2");
        sensor3.setI2cAddress(I2cAddr.create7bit(0x1f));
        sensor1 = hardwareMap.colorSensor.get("Up Sensor");
        sensor1.setI2cAddress(I2cAddr.create7bit(0x1e));
        sensor2 = hardwareMap.colorSensor.get("Down Sensor");
        sensor2.setI2cAddress(I2cAddr.create7bit(0x1d));
        Gsensor = hardwareMap.gyroSensor.get("Gyro Sensor");
        //ODsensor = hardwareMap.opticalDistanceSensor.get("OD Sensor");
        */frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 1
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
    }
    public void loop(){
        if (k) {
            frontL.setPower(1);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(1);
            backR.setPower(1);
            backL.setPower(1);
            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
                telemetry.addData("Oh noes", "Robot.exe has insomnia");
            }
            k=false;
        }
        frontL.setPower(0);
        frontR.setPower(0);
        middleR.setPower(0);
        middleL.setPower(0);
        backR.setPower(0);
        backL.setPower(0);
    }
}

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
 * Created by Joseph on 12/17/2016.
 */
@Autonomous(name = "test")
public class Test extends OpMode {
    protected DcMotor frontL; // 1
    protected DcMotor frontR; // 2
    protected DcMotor backL; // 3
    protected DcMotor backR; // 4
    protected DcMotor middleL; // 5
    protected DcMotor middleR; // 6
    protected DcMotor launcher;
    protected DcMotor launcher2;
    protected ColorSensor sensor1;
    protected ColorSensor sensor2;
    protected GyroSensor Gsensor;
    protected DeviceInterfaceModule dim;
    private int P = 0;
    protected OpticalDistanceSensor ODsensor;
    public void init() {
        /*frontL = hardwareMap.dcMotor.get("Front Left"); // 1
        frontR = hardwareMap.dcMotor.get("Front Right"); // 2
        middleL = hardwareMap.dcMotor.get("Middle Left"); // 3
        middleR = hardwareMap.dcMotor.get("Middle Right"); // 4
        backL = hardwareMap.dcMotor.get("Back Left"); // 5
        backR = hardwareMap.dcMotor.get("Back Right"); // 6
        *///launcher = hardwareMap.dcMotor.get("Launcher");

        //dim = hardwareMap.deviceInterfaceModule.get("dim");

        //launcher2 = hardwareMap.dcMotor.get("Launcher2");
        sensor1 = hardwareMap.colorSensor.get("Up Sensor");
        sensor1.setI2cAddress(I2cAddr.create7bit(0x1e));
        sensor2 = hardwareMap.colorSensor.get("Down Sensor");
        sensor2.setI2cAddress(I2cAddr.create8bit(0x1d));
        //Gsensor = hardwareMap.gyroSensor.get("Gyro Sensor");
        //ODsensor = hardwareMap.opticalDistanceSensor.get("OD Sensor");
        /*frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 1
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 2
        middleL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 3
        middleR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 4
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 5
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 6
        */
        //launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //launcher2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        /*.setDirection(DcMotorSimple.Direction.FORWARD); // 1
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
        *///launcher2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        //launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        telemetry.addData("sg2", sensor2.green());
        telemetry.addData("sr2", sensor2.red());
        telemetry.addData("sb2", sensor2.blue());
        telemetry.addData("timer", getRuntime());
        telemetry.addData("sg1", sensor1.green());
        telemetry.addData("sr1", sensor1.red());
        telemetry.addData("sb1", sensor1.blue());
        /*telemetry.addData("y", Gsensor.rawY());
        telemetry.addData("x", Gsensor.rawX());
        telemetry.addData("Z", Gsensor.rawZ());
        */telemetry.addData("ads1", sensor1.getI2cAddress());
        telemetry.addData("ads2", sensor2.getI2cAddress());
        telemetry.addData("conninfo1", sensor1.getConnectionInfo());
        telemetry.addData("conninfo2", sensor2.getConnectionInfo());
        telemetry.addData("Gconninfo", Gsensor.getConnectionInfo());
        //dim.setLED(0x0, false);
        //dim.setLED(0x1, true);
        //dim.setLED(0x2, true);
    }
    public void loop(){
        telemetry.addData("sg2", sensor2.green());
        telemetry.addData("sr2", sensor2.red());
        telemetry.addData("sb2", sensor2.blue());
        telemetry.addData("timer", getRuntime());
        telemetry.addData("sg1", sensor1.green());
        telemetry.addData("sr1", sensor1.red());
        telemetry.addData("sb1", sensor1.blue());
        //telemetry.addData("y", Gsensor.rawY());
        //telemetry.addData("x", Gsensor.rawX());
        //telemetry.addData("Z", Gsensor.rawZ());
        telemetry.addData("ads1", sensor1.getI2cAddress());
        telemetry.addData("ads2", sensor2.getI2cAddress());
        telemetry.addData("conninfo1", sensor1.getConnectionInfo());
        telemetry.addData("conninfo2", sensor2.getConnectionInfo());
        /*telemetry.addData("Gconninfo", Gsensor.getConnectionInfo());
        telemetry.addData("launcher Power", launcher.getPower());
        if(Gsensor.rawY()+Gsensor.rawX()+Gsensor.rawZ()>10000){
            launcher2.setPower(P +.1);
            launcher.setPower(P+.1);
        */}



    }


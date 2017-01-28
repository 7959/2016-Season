package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
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
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.TypeConversion;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Robi on 1/24/2017.
 */
@Autonomous(name = "Autocorrect")
public class AutoCorrect  extends LinearOpMode {

    //protected CRServo Servo;
    //protected DeviceInterfaceModule dim;

    private int s = 0;
    //protected OpticalDistanceSensor ODsensor;
    private int phase;
    private int cal = 0;
    private int a;
    private int z =0;
    private boolean line;
    private boolean RC = false;
    private boolean LC = false;
    protected DcMotor frontL; // 1
    protected DcMotor frontR; // 2
    protected DcMotor backL; // 3
    protected DcMotor backR; // 4
    protected DcMotor middleL; // 5
    protected DcMotor middleR; // 6
    //protected DcMotor launcher;
    //protected DcMotor launcher2;
    //protected ColorSensor sensor1;
    //protected ColorSensor sensor2;
    //protected ColorSensor sensor3;
    protected ModernRoboticsI2cGyro Gsensor;
    private int L = 0;
    private int R = 0;
    private int DA = 0;
    //protected DeviceInterfaceModule dim;
    private int P = 0;
    //protected OpticalDistanceSensor ODsensor;


    public void runOpMode() {
        frontL = hardwareMap.dcMotor.get("Front Left"); // 1
        frontR = hardwareMap.dcMotor.get("Front Right"); // 2
        middleL = hardwareMap.dcMotor.get("Middle Left"); // 3
        middleR = hardwareMap.dcMotor.get("Middle Right"); // 4
        backL = hardwareMap.dcMotor.get("Back Left"); // 5
        backR = hardwareMap.dcMotor.get("Back Right"); // 6
        //launcher = hardwareMap.dcMotor.get("Launcher");

        //dim = hardwareMap.deviceInterfaceModule.get("dim");

        //launcher2 = hardwareMap.dcMotor.get("Launcher2");
        /*sensor3 = hardwareMap.colorSensor.get("Up Sensor2");
        sensor3.setI2cAddress(I2cAddr.create7bit(0x1f));
        sensor1 = hardwareMap.colorSensor.get("Up Sensor");
        sensor1.setI2cAddress(I2cAddr.create7bit(0x1e));
        sensor2 = hardwareMap.colorSensor.get("Down Sensor");
        *///sensor2.setI2cAddress(I2cAddr.create7bit(0x1d));
        Gsensor = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("Gyro Sensor");

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
        telemetry.addData("Hey!", "I'm calibrating right now let me be!");
        telemetry.update();
        Gsensor.calibrate();

        while (!isStopRequested() && Gsensor.isCalibrating()) {
            sleep(50);
            idle();
        }

        telemetry.addData("Hey!", "Thanks! Let's go!");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Z heading", Gsensor.getIntegratedZValue());
            telemetry.addData("y", Gsensor.rawY());
            telemetry.addData("x", Gsensor.rawX());
            telemetry.addData("z", Gsensor.rawZ());
            telemetry.addData("R", R);
            telemetry.addData("L", L);
            telemetry.update();
            z = Gsensor.getIntegratedZValue();
            L = s + ((z - DA) / 50);
            R = s - ((z - DA) / 50);
            if (R > 1) {
                R = 1;
            }
            if (R < -1) {
                R = -1;
            }
            if (L > 1) {
                L = 1;
            }
            if (L < -1) {
                L = -1;
            }
            frontL.setPower(L);
            middleL.setPower(L);
            backL.setPower(L);
            frontR.setPower(R);
            middleR.setPower(R);
            backR.setPower(R);
        }
    }
}
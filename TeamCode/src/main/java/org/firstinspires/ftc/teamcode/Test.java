package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchDevice;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.I2cController;
import com.qualcomm.robotcore.util.TypeConversion;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
/**
 * Created by Joseph on 12/17/2016.
 */
@Autonomous(name = "test")
public class Test extends LinearOpMode {
    protected DcMotor frontL; // 1
    protected DcMotor frontR; // 2
    protected DcMotor backL; // 3
    protected DcMotor backR; // 4
    protected DcMotor middleL; // 5
    protected DcMotor middleR; // 6
    protected DcMotor launcher;
    protected DcMotor launcher2;
    int x;
    int y;
    int z;
    int heading;
    int zz;
    //protected ColorSensor sensor1;
    //protected ColorSensor sensor2;
    //protected ColorSensor sensor3;
    protected ModernRoboticsI2cGyro Gsensor;
    //protected CRServo Servo;
    //protected DeviceInterfaceModule dim;
    private int P = 0;

    //protected OpticalDistanceSensor ODsensor;
    public void runOpMode() {
      /*  frontL = hardwareMap.dcMotor.get("Front Left"); // 1
        frontR = hardwareMap.dcMotor.get("Front Right"); // 2
        middleL = hardwareMap.dcMotor.get("Middle Left"); // 3
        middleR = hardwareMap.dcMotor.get("Middle Right"); // 4
        backL = hardwareMap.dcMotor.get("Back Left"); // 5
        backR = hardwareMap.dcMotor.get("Back Right"); // 6
        *///Servo = hardwareMap.crservo.get("Servo");
        //launcher = hardwareMap.dcMotor.get("Launcher");

        //dim = hardwareMap.deviceInterfaceModule.get("dim");

        //launcher2 = hardwareMap.dcMotor.get("Launcher2");
        // sensor3 = hardwareMap.colorSensor.get("Up Sensor2");
        //sensor3.setI2cAddress(I2cAddr.create7bit(0x1f));
        //sensor1 = hardwareMap.colorSensor.get("Up Sensor");
        //sensor1.setI2cAddress(I2cAddr.create7bit(0x1e));
        //sensor2 = hardwareMap.colorSensor.get("Down Sensor");
        //sensor2.setI2cAddress(I2cAddr.create7bit(0x1d));

        Gsensor = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("Gyro Sensor");
        //Servo = hardwareMap.crservo.get("Servo");
        //ODsensor = hardwareMap.opticalDistanceSensor.get("OD Sensor");
        /*frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 1
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
*/
        Gsensor = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("Gyro Sensor");

        // start calibrating the Gsensor.
        telemetry.addData(">", "Gyro Calibrating. Do Not move!");
        telemetry.update();
        Gsensor.calibrate();

        // make sure the Gsensor is calibrated.
        while (!isStopRequested() && Gsensor.isCalibrating())  {
            sleep(50);
            idle();
        }

        telemetry.addData(">", "Gyro Calibrated.  Press Start.");
        telemetry.update();


        // wait for the start button to be pressed.
        waitForStart();

        while (opModeIsActive())   {
            x = Gsensor.rawX();
            y = Gsensor.rawY();
            z = Gsensor.rawZ();

            // get the heading info.
            // the Modern Robotics' Gsensor sensor keeps
            // track of the current heading for the Z axis only.
            heading = Gsensor.getHeading();
            zz  = Gsensor.getIntegratedZValue();

            telemetry.addData(">", "Press A & B to reset Heading.");
            telemetry.addData("0", "Heading %03d", heading);
            telemetry.addData("1", "Int. Ang. %03d", zz);
            telemetry.addData("2", "X av. %03d", x);
            telemetry.addData("3", "Y av. %03d", y);
            telemetry.addData("4", "Z av. %03d", z);
            telemetry.update();
        }
    }
}

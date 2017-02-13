package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Robi on 2/12/2017.
 */
@Autonomous(name = "demo")
public class Demonstation extends OpMode{
    private DcMotor frontL; // 1
    private DcMotor frontR; // 2
    private DcMotor backL; // 3
    private DcMotor backR; // 4
    private DcMotor middleL; // 5
    private DcMotor middleR;
    private ModernRoboticsI2cGyro G;
    private ColorSensor UR;
    private ColorSensor UL;
    private ColorSensor DR;
    private ColorSensor DL;
    private DcMotor Pew;
    private DcMotor PewPew;
    protected CRServo Servo;
    public double Right = 0;
    public double Left = 0;
    public double speed = .5;
    public double angle;
    public void init(){
        frontL = hardwareMap.dcMotor.get("Front Left"); // 1
        frontR = hardwareMap.dcMotor.get("Front Right"); // 2
        middleL = hardwareMap.dcMotor.get("Middle Left"); // 3
        middleR = hardwareMap.dcMotor.get("Middle Right"); // 4
        backL = hardwareMap.dcMotor.get("Back Left"); // 5
        backR = hardwareMap.dcMotor.get("Back Right"); // 6

        Pew = hardwareMap.dcMotor.get("Right Launcher");
        PewPew = hardwareMap.dcMotor.get("Left Launcher");

        UR = hardwareMap.colorSensor.get("Up Right");
        UR.setI2cAddress(I2cAddr.create7bit(0x1e));
        UL = hardwareMap.colorSensor.get("Up Left");
        UL.setI2cAddress(I2cAddr.create7bit(0x19));
        DR = hardwareMap.colorSensor.get("Down Right");
        DR.setI2cAddress(I2cAddr.create7bit(0x1d));
        DL = hardwareMap.colorSensor.get("Down Left");
        DL.setI2cAddress(I2cAddr.create7bit(0x1f));

        Servo = hardwareMap.crservo.get("Servo");
        Servo.setDirection(CRServo.Direction.FORWARD);

        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 1
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 2
        middleL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 3
        middleR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 4
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 5
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 6

        Pew.setDirection(DcMotorSimple.Direction.REVERSE);
        PewPew.setDirection(DcMotorSimple.Direction.FORWARD);

        Pew.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        PewPew.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontR.setDirection(DcMotorSimple.Direction.FORWARD); // 1
        frontL.setDirection(DcMotorSimple.Direction.REVERSE); // 2
        middleR.setDirection(DcMotorSimple.Direction.FORWARD); // 4
        middleL.setDirection(DcMotorSimple.Direction.REVERSE); // 3
        backR.setDirection(DcMotorSimple.Direction.FORWARD); // 5
        backL.setDirection(DcMotorSimple.Direction.REVERSE); // 6



        frontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 1
        frontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 2
        middleL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 3
        middleR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 4
        backL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 5
        backR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 6

        UL.enableLed(false);
        UR.enableLed(false);

        G = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("Gyro Sensor");

        telemetry.addData("Hey!", "I'm calibrating right now let me be!");
        telemetry.update();
        G.calibrate();

        while(G.isCalibrating());

        telemetry.addData("Hey", "Let's go get 'em!");
        telemetry.update();
        angle = G.getIntegratedZValue();
    }
    public void loop(){
        telemetry.addData("dr", DR.green());
        int z = G.getIntegratedZValue();
        Right = speed - (z - angle) / 100;
        Left = speed + (z - angle) / 100;
        Left = Range.clip(Left, -1, 1);
        Right = Range.clip(Right, -1, 1);

        frontL.setPower(Left);
        middleL.setPower(Left);
        backL.setPower(Left);

        frontR.setPower(Right);
        middleR.setPower(Right);
        backR.setPower(Right);
        telemetry.addData("right", Right);
        telemetry.addData("left", Left);
        telemetry.addData("RPower", frontR.getPower());
        telemetry.addData("LPower", frontL.getPower());
        telemetry.addData("Dr", DR.green());
        telemetry.update();
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Robi on 1/28/2017.
 */
@Autonomous(name = "Red Pushy")
public class Redpushypushy extends LinearOpMode{
    DcMotor frontL; // 1
    DcMotor frontR; // 2
    DcMotor backL; // 3
    DcMotor backR; // 4
    DcMotor middleL; // 5
    DcMotor middleR;
    ModernRoboticsI2cGyro Gsensor;
    int L = 0;
    int s = 50;
    int R = 0;
    int Z = 0;
    int x = 0;
    int z = 0;
    int hz = 0;
    int DA = 0;
    int task = 0;

    public void runOpMode(){
        frontL = hardwareMap.dcMotor.get("Front Left"); // 1
        frontR = hardwareMap.dcMotor.get("Front Right"); // 2
        middleL = hardwareMap.dcMotor.get("Middle Left"); // 3
        middleR = hardwareMap.dcMotor.get("Middle Right"); // 4
        backL = hardwareMap.dcMotor.get("Back Left"); // 5
        backR = hardwareMap.dcMotor.get("Back Right"); // 6

        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 1
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 2
        middleL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 3
        middleR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 4
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 5
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 6

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

        Gsensor = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("Gyro Sensor");

        telemetry.addData("Hey!", "I'm calibrating right now let me be!");
        telemetry.update();
        Gsensor.calibrate();

        while (!isStopRequested() && Gsensor.isCalibrating()) {
            sleep(50);
            idle();
        }

        telemetry.addData("Hey!", "Thanks! Calculating Angles now.");
        telemetry.update();
        DA = Gsensor.getIntegratedZValue();
        telemetry.addData("Hey", "Let's go get 'em!");
        telemetry.update();
        waitForStart();

        /**
         * Tasks List
         * 0 forward until white line.
         * 1 beacon face
         * 2 beacon check
         * 3 beacon reconfigure if needed
         * 
         */

        while(opModeIsActive())   {

        }

    }
}

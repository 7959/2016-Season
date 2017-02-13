package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Robi on 2/12/2017.
 */
@Autonomous(name = "newthing")
public class shootfirething extends OpMode {
    private DcMotor frontL; // 1
    private DcMotor frontR; // 2
    private DcMotor backL; // 3
    private DcMotor backR; // 4
    private DcMotor middleL; // 5
    private DcMotor middleR;
    private DcMotor Pew;
    private DcMotor PewPew;
    int phase = 0;
    private CRServo Servo;
    public void init(){
        frontL = hardwareMap.dcMotor.get("Front Left"); // 1
        frontR = hardwareMap.dcMotor.get("Front Right"); // 2
        middleL = hardwareMap.dcMotor.get("Middle Left"); // 3
        middleR = hardwareMap.dcMotor.get("Middle Right"); // 4
        backL = hardwareMap.dcMotor.get("Back Left"); // 5
        backR = hardwareMap.dcMotor.get("Back Right"); // 6

        Pew = hardwareMap.dcMotor.get("Right Launcher");
        PewPew = hardwareMap.dcMotor.get("Left Launcher");

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
    }
    public void loop(){
        Pew.setPower(1);
        PewPew.setPower(1);
        Servo.setPower(1);

        }


    public void pew(double speed){
        Pew.setPower(speed);
        PewPew.setPower(speed);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Robot has insomnia");
        }
        Servo.setPower(1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Robot has insomnia");
        }
        Servo.setPower(1);

        // servo code ////////////////////////////////////////////

    }

}


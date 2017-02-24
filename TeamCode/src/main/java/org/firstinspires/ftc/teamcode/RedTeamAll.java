package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;

/**
 * Created by Robi on 2/15/2017.
 */
@Autonomous(name = "Billy Red")
public class RedTeamAll extends linearOpModeExtension {
    public void runOpMode(){
        fL = hardwareMap.dcMotor.get("Front Left");
        fR = hardwareMap.dcMotor.get("Front Right");
        bL = hardwareMap.dcMotor.get("Back Left");
        bR = hardwareMap.dcMotor.get("Back Right");
        lL = hardwareMap.dcMotor.get("Launcher Left");
        lR = hardwareMap.dcMotor.get("Launcher Right");
        //loader = hardwareMap.dcMotor.get("Loader");
        fL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //loader.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        lR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        //loader.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fL.setDirection(DcMotorSimple.Direction.FORWARD);
        fR.setDirection(DcMotorSimple.Direction.REVERSE);
        bL.setDirection(DcMotorSimple.Direction.FORWARD);
        bR.setDirection(DcMotorSimple.Direction.REVERSE);
        lL.setDirection(DcMotorSimple.Direction.FORWARD);
        lR.setDirection(DcMotorSimple.Direction.REVERSE);
        //loader.setDirection(DcMotorSimple.Direction.FORWARD);


        topLeft = hardwareMap.colorSensor.get("Top Left");
        topLeft.setI2cAddress(I2cAddr.create7bit(0x19));
        topRight = hardwareMap.colorSensor.get("Top Right");
        topRight.setI2cAddress(I2cAddr.create7bit(0x1e));
        deltaLeft = hardwareMap.colorSensor.get("Delta Left");
        deltaLeft.setI2cAddress(I2cAddr.create7bit(0x1f));
        deltaRight = hardwareMap.colorSensor.get("Delta Right");
        deltaRight.setI2cAddress(I2cAddr.create7bit(0x1d));
        deltaMiddle = hardwareMap.colorSensor.get("Delta Middle");
        deltaMiddle.setI2cAddress(I2cAddr.create8bit(0x45));/////////////////NOT SET UP CORRECTLY YET



        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("Gyro Sensor");
        telemetry.addData("Hey You!", "BACK OFF BUCKO I AM CALIBRATING");
        telemetry.update();
        gyro.calibrate();
        while(!isStopRequested() && gyro.isCalibrating()){
            sleep(20);
            idle();
        }
        telemetry.clearAll();
        telemetry.addData("Thanks", "I am calibrated");
        waitForStart();
        int poop;
        //whitestraight(.1);
        //reallign(.1, 90);

        /**
         * IMPORTANT:
         * New Wheel design alert. Will need to change newmovetime()
         * Create a line follow function using strafe and auto correct angles.
         *
         */

        //////skipping diagonal movement for now
        //assume at current time robot middle sensor is above  the while line
        int failedtries = 0;
        colorcheck();
        while(true) {
            if (bluerightredleft) {
                Lline(.1);
                straightmovetime(90, 1.5, .1);
                straightmovetime(90, 1.5, -.1);
                break;
            } else if (redrightblueleft) {
                Rline(.1);
                straightmovetime(90, 1.5, .1);
                straightmovetime(90, 1.5, -.1);
                break;
            } else if (Blueall) {
                Lline(.1);
                straightmovetime(90, 1.5, .1);
                straightmovetime(90, 1.5, -.1);
                break;
            } else if (Redall){
                break;
            } if(failedtries > 3){
                straightmovetime(90, .1, .1);
            } else {
                failedtries++;
                colorcheck();
            }
        }

        strafemid(.1);
        Redall = false;
        Blueall = false;
        redrightblueleft = false;
        bluerightredleft = false;
        failedtries = 0;
        colorcheck();

        while(true) {
            if (bluerightredleft) {
                Lline(.1);
                straightmovetime(90, 1.5, .1);
                straightmovetime(90, 1.5, -.1);
                break;
            } else if (redrightblueleft) {
                Rline(.1);
                straightmovetime(90, 1.5, .1);
                straightmovetime(90, 1.5, -.1);
                break;
            } else if (Blueall) {
                Lline(.1);
                straightmovetime(90, 1.5, .1);
                straightmovetime(90, 1.5, -.1);
                break;
            } else if (Redall){
                break;
            } if(failedtries > 3){
                straightmovetime(90, .1, .1);
            } else {
                failedtries++;
                colorcheck();
            }
        }




    }
}

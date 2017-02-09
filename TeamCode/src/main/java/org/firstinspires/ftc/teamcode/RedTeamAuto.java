package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Robi on 2/4/2017.
 */
@Autonomous(name = "BILLY JOEL")
public class RedTeamAuto extends LinearOpMode {
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
    public double Rline;
    public double LLine;
    public boolean GoLeftLine;

    public double speed;
    public void runOpMode() throws InterruptedException{
        frontL = hardwareMap.dcMotor.get("Front Left"); // 1
        frontR = hardwareMap.dcMotor.get("Front Right"); // 2
        middleL = hardwareMap.dcMotor.get("Middle Left"); // 3
        middleR = hardwareMap.dcMotor.get("Middle Right"); // 4
        backL = hardwareMap.dcMotor.get("Back Left"); // 5
        backR = hardwareMap.dcMotor.get("Back Right"); // 6

        UR = hardwareMap.colorSensor.get("Up Right");
        UR.setI2cAddress(I2cAddr.create7bit(0x1e));
        //UL = hardwareMap.colorSensor.get("Up Left");
        //UL.setI2cAddress(I2cAddr.create7bit(0x1f));
        DR = hardwareMap.colorSensor.get("Down Right");
        DR.setI2cAddress(I2cAddr.create7bit(0x1d));
        //DL = hardwareMap.colorSensor.get("Down Left");
        //DL.setI2cAddress(I2cAddr.create7bit();

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

        G = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("Gyro Sensor");

        telemetry.addData("Hey!", "I'm calibrating right now let me be!");
        telemetry.update();
        G.calibrate();

        while (!isStopRequested() && G.isCalibrating()){
            sleep(100);
            idle();
        }

        telemetry.addData("Hey", "Let's go get 'em!");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()){
            forwardthing(0, 0);
            idle();
        }
    }
    public void forwardthing(double speed, double angle) throws InterruptedException {
        double Right;
        double Left;
        int z = G.getIntegratedZValue();
        Right = speed - (z - angle)/100;
        Left = speed - (z - angle)/100;
        Left = Range.clip(Left, 1, -1);
        Right = Range.clip(Right, 1, -1);
        frontL.setPower(Left);
        middleL.setPower(Left);
        backL.setPower(Left);

        frontR.setPower(Right);
        middleR.setPower(Right);
        backR.setPower(Right);
        telemetry.addData("Right", frontR.getPower());
        telemetry.addData("Left", frontL.getPower());
        }


    public void straightwhite(double speed) throws InterruptedException{
        double Right;
        double Left;
        double angle = G.getIntegratedZValue();
        while(UR.green() < 50){
            int z = G.getIntegratedZValue();
            Right = speed - (z - angle) / 100;
            Left = speed + (z - angle) / 100;
            Left = Range.clip(Left, 1, -1);
            Right = Range.clip(Right, 1, -1);

            frontL.setPower(Left);
            middleL.setPower(Left);
            backL.setPower(Left);

            frontR.setPower(Right);
            middleR.setPower(Right);
            backR.setPower(Right);
            idle();

        }
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);
    }
    public void straighttime(double angle, double speed, double time){
        double Right;
        double Left;
        double timeS = time + getRuntime();
        while(getRuntime() < timeS){
            int z = G.getIntegratedZValue();
            Right = speed - (z - angle) / 100;
            Left = speed + (z - angle) / 100;
            Left = Range.clip(Left, 1, -1);
            Right = Range.clip(Right, 1, -1);

            frontL.setPower(Left);
            middleL.setPower(Left);
            backL.setPower(Left);

            frontR.setPower(Right);
            middleR.setPower(Right);
            backR.setPower(Right);
            idle();
        }
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);
    }
    public void turn(int angle){
        double Right;
        double Left;
        while(!(G.getHeading() == angle)){
            int z = G.getIntegratedZValue();
            Right = 0 - (z - angle) / 100;
            Left = (z - angle) / 100;
            Left = Range.clip(Left, 1, -1);
            Right = Range.clip(Right, 1, -1);

            frontL.setPower(Left);
            middleL.setPower(Left);
            backL.setPower(Left);

            frontR.setPower(Right);
            middleR.setPower(Right);
            backR.setPower(Right);
        }
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);
    }
    public void LineRThing(int speed) {
        int poop;
        if (DR.green() < 3) {
            if(GoLeftLine){
                GoLeftLine= false;
            } else {
                GoLeftLine = true;
            }
            if (GoLeftLine) {
                frontL.setPower(0);
                middleL.setPower(0);
                backL.setPower(0);

                frontR.setPower(speed);
                middleR.setPower(speed);
                backR.setPower(speed);
            } else {
                frontL.setPower(speed);
                middleL.setPower(speed);
                backL.setPower(speed);

                frontR.setPower(0);
                middleR.setPower(0);
                backR.setPower(0);
            }
        }
    }
    public void turn2(int angle, double speed){
        while(true) {
            if ((G.getHeading() > angle)) {
                frontL.setPower(speed);
                middleL.setPower(speed);
                backL.setPower(speed);

                frontR.setPower(-speed);
                middleR.setPower(-speed);
                backR.setPower(-speed);
            } else {
                frontL.setPower(-speed);
                middleL.setPower(-speed);
                backL.setPower(-speed);

                frontR.setPower(speed);
                middleR.setPower(speed);
                backR.setPower(speed);
            }
            if (G.getHeading() == angle) {
                break;
            }
        }

    }

    public void Rdown(double speed){
        while(!(DR.green() < 3)){
            frontL.setPower(-speed);
            middleL.setPower(-speed);
            backL.setPower(-speed);

            frontR.setPower(speed);
            middleR.setPower(speed);
            backR.setPower(speed);
        }
    }
    public void Ldown(double speed){
        while(!(DL.green() < 3)){
            frontL.setPower(speed);
            middleL.setPower(speed);
            backL.setPower(speed);

            frontR.setPower(-speed);
            middleR.setPower(-speed);
            backR.setPower(-speed);
        }

    }
    public boolean BeaconFind(){
        double Right;
        double Left;
        int angle = G.getIntegratedZValue();
        while(UR.red() < 2 && UR.blue() < 2){
            int z = G.getIntegratedZValue();
            Right = .10 - (z - angle) / 100;
            Left = .10 + (z - angle) / 100;
            Left = Range.clip(Left, 1, -1);
            Right = Range.clip(Right, 1, -1);

            frontL.setPower(Left);
            middleL.setPower(Left);
            backL.setPower(Left);

            frontR.setPower(Right);
            middleR.setPower(Right);
            backR.setPower(Right);
        }
        if(UR.red() > 2 && UR.blue() < 2){
            return true;
        } else return false;
    }
    public void turnanglething(int angle, double speed){
        double Z = G.getIntegratedZValue();
        while(Math.abs(Z - angle) > 3){
            if(Z > 0){
                frontL.setPower(speed);
                middleL.setPower(speed);
                backL.setPower(speed);

                frontR.setPower(-speed);
                middleR.setPower(-speed);
                backR.setPower(-speed);
            } else {
                frontL.setPower(-speed);
                middleL.setPower(-speed);
                backL.setPower(-speed);

                frontR.setPower(speed);
                middleR.setPower(speed);
                backR.setPower(speed);
            }
        }
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);
    }
    public void readjustR(double time){
        double speed = .25;
        double Ttime= getRuntime() + time;
        while(getRuntime() < Ttime){
            if(DR.green() < 3){
                speed=-speed;
            }
        }
    }

}

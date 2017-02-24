package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Robi on 2/4/2017.
 */
@Disabled
@Autonomous(name = "RedAll")
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
    private DcMotor Pew;
    private DcMotor PewPew;
    protected CRServo Servo;
    public boolean Redright1;
    public boolean Redright2;
    public boolean Blueright1;
    public boolean Blueright2;
    public boolean Redleft1;
    public boolean Redleft2;
    public boolean Blueleft1;
    public boolean Blueleft2;
    public boolean GoLeftLine;

    public double speed;
    @Override
    public void runOpMode(){
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

        while(!isStopRequested() && G.isCalibrating()){
            sleep(50);
            idle();
        }
        G.resetZAxisIntegrator();

        telemetry.addData("Hey", "Let's go get 'em!");
        telemetry.update();
        waitForStart();
        //while(!isStopRequested() && !isStarted());
        //while(!opModeIsActive() && !isStopRequested()) {
          //  waitForStart();
            //isStopRequested();
            //idle();
        //}



            telemetry.addData("Current Task","start loop");
            telemetry.update();
            straightwhite(.1, 200);
            telemetry.addData("Current Task","straight white");
            telemetry.update();
            linefindR(.1);
            telemetry.addData("Current Task","findL");
            telemetry.update();
            linefollowR(.1, 1);

            telemetry.addData("Current Task","turn");
            telemetry.update();

            Rturn90();
            straighttime(90, -.1, 2);
            telemetry.addData("Current Task","push");
            telemetry.update();

            Rpush1(.1);

            if(Redright1){
                straighttime(90, 0.1, 1);
                sleep(250);
                straighttime(90, -0.1, .7);
            } else {/// REPLACE THIS ELSE STATEMENT
                straighttime(90, -1, .25);
                telemetry.addData("Current Task","turn0");
                telemetry.update();
                turnto160(.1);
                telemetry.addData("Current Task", "straight");
                telemetry.update();
                straighttime(180, 0.1, .15);
                telemetry.addData("Current Task","fineline");
                telemetry.update();
                linefindL(.1);
                telemetry.addData("Current Task","followline");
                telemetry.update();
                linefollowL(.1, 1);
                telemetry.addData("Current Task","turn90");
                telemetry.update();
                Rturn90();
                telemetry.addData("Current Task","back");
                telemetry.update();
                straighttime(90, -.1, 1);
                telemetry.addData("Current Task","push");
                telemetry.update();
                Lpush1(.1);
               /* if((Redleft1 && Blueright1) || (Blueleft1 && Blueright1)){
                    straighttime(90, 0.1, 1);
                    sleep(250);
                    straighttime(90, -0.1, .7);
                } else {
                    frontL.setPower(0);
                    middleL.setPower(0);
                    backL.setPower(0);

                    frontR.setPower(0);
                    middleR.setPower(0);
                    backR.setPower(0);
                }*/
                straighttime(90, 0.1, 1);
                sleep(250);
                straighttime(90, -0.1, .7);
                lineshot(.1);
                pew(1);
               /* if(Redleft1){
                    straighttime(90, 0.1, 1);
                    sleep(250);
                    straighttime(90, -0.1, .7);
                }
            */}

        ///////////////Beacon2
        negto0();
        Pstraightwhite(.1, 0, 200);
        linefindR(.1);
        telemetry.addData("Current Task","findL");
        telemetry.update();
        linefollowR(.1, .5);

        telemetry.addData("Current Task","turn");
        telemetry.update();

        Rturn90();
        straighttime(90, -.1, 2);
        telemetry.addData("Current Task","push");
        telemetry.update();

        Rpush1(.1);

        if(Redright1){
            straighttime(90, 0.1, 1);
            sleep(250);
            straighttime(90, -0.1, .7);
        } else {
            straighttime(90, -1, .25);
            telemetry.addData("Current Task","turn0");
            telemetry.update();
            turnto160(.1);
            telemetry.addData("Current Task", "straight");
            telemetry.update();
            straighttime(180, 0.1, .15);
            telemetry.addData("Current Task","fineline");
            telemetry.update();
            linefindL(.1);
            telemetry.addData("Current Task","followline");
            telemetry.update();
            linefollowL(.1, .5);
            telemetry.addData("Current Task","turn90");
            telemetry.update();
            Rturn90();
            telemetry.addData("Current Task","back");
            telemetry.update();
            straighttime(90, -.1, 1);
            telemetry.addData("Current Task","push");
            telemetry.update();
            Lpush1(.1);
            straighttime(90, 0.1, 1);
            sleep(250);
            straighttime(90, -0.1, .7);
               /* if(Redleft1){
                    straighttime(90, 0.1, 1);
                    sleep(250);
                    straighttime(90, -0.1, .7);
                }
            */}


            while(opModeIsActive() && true){
                telemetry.addData("Z", G.getIntegratedZValue());
                telemetry.addData("RR", Redright1);
                telemetry.addData("BR", Blueright1);
                telemetry.addData("RL", Redleft1);
                telemetry.addData("BL", Blueleft1);
                telemetry.addData("Lline", DL.green());
                telemetry.addData("Rline", DR.green());

                telemetry.update();
                sleep(50);
                if(UR.green() == 230){
                    break;
                }
                telemetry.addData("Rred", UR.red());
                telemetry.addData("Rblue", UR.blue());

            }
            telemetry.addData("Current Task","turn");
            telemetry.update();
            //straighttime(90, .1, 2.0);
            telemetry.addData("Current Task","done");
            telemetry.update();
            //requestOpModeStop();


    }
    public void lineshot(double speed){
        while(opModeIsActive() && G.getIntegratedZValue() > -90){
            frontL.setPower(speed);
            middleL.setPower(speed);
            backL.setPower(speed);

            frontR.setPower(-speed);
            middleR.setPower(-speed);
            backR.setPower(-speed);
        }
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);
    }
    public void Rbeaconred1(){
        if(UR.red() >= 2 && UR.blue() < 1){
            Redright1 = true;
        }
        if(UR.red() < 1 && UR.blue() >= 2){
            Redright1 = false;
            Blueright1 = true;
        }
    }
    public void Lpush1(double speed){
        double Right;
        double Left;
        double angle = 90;
        while(opModeIsActive()) {
            telemetry.addData("R", UR.red());
            telemetry.addData("B", UR.blue());
            if(UL.red() >= 2 && UL.blue() < 1){
                Redleft1 = true;
                Blueleft1 = false;
                break;
            }
            if(UR.red() < 1 && UR.blue() >= 2){
                Redleft1 = false;
                Blueleft1 = true;
                break;
            }
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
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);
    }
    public void Rpush1(double speed) {
        double Right;
        double Left;
        double angle = 90;
        while(opModeIsActive()) {
            telemetry.addData("R", UR.red());
            telemetry.addData("B", UR.blue());
            if(UR.red() >= 2 && UR.blue() < 1){
                Redright1 = true;
                Blueright1 = false;
                break;
            }
            if(UR.red() < 1 && UR.blue() >= 2){
                Redright1 = false;
                Blueright1 = true;
                break;
            }
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
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);
    }
    public void fix(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Robot has insomnia");
        }
        boolean turnleft;
        boolean ready = false;
        if(G.getIntegratedZValue() > 90){
            turnleft = false;
        } else {
            turnleft = true;
        }
        if (G.getIntegratedZValue() == 90){
            ready = true;
        }

        while(opModeIsActive() && !ready && turnleft && G.getIntegratedZValue() < 90){
            frontL.setPower(-.075);
            middleL.setPower(-.075);
            backL.setPower(-.075);

            frontR.setPower(.075);
            middleR.setPower(.075);
            backR.setPower(.075);
        }
        while(opModeIsActive() && !ready && !turnleft && G.getIntegratedZValue() > 90){
            frontL.setPower(.75);
            middleL.setPower(.75);
            backL.setPower(.75);

            frontR.setPower(-.75);
            middleR.setPower(-.75);
            backR.setPower(-.75);
        }
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);

    }

    public void Rturn90(){

        boolean turnleft;
        boolean ready = false;
        if(G.getIntegratedZValue() > 90){
            turnleft = false;
        } else {
            turnleft = true;
        }
        if (G.getIntegratedZValue() == 90){
            ready = true;
        }
        while(opModeIsActive() && !ready && turnleft && G.getIntegratedZValue() < 90){
            frontL.setPower(-.1);
            middleL.setPower(-.1);
            backL.setPower(-.1);

            frontR.setPower(.1);
            middleR.setPower(.1);
            backR.setPower(.1);
        }
        while(opModeIsActive() && !ready && !turnleft && G.getIntegratedZValue() > 90){
            frontL.setPower(.1);
            middleL.setPower(.1);
            backL.setPower(.1);

            frontR.setPower(-.1);
            middleR.setPower(-.1);
            backR.setPower(-.1);
        }
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);

    }
    public void negto0(){
        telemetry.addData("Current Task","turn to angle");
        telemetry.update();

        while(opModeIsActive() && G.getIntegratedZValue() < 0){
            frontL.setPower(speed);
            middleL.setPower(speed);
            backL.setPower(speed);

            frontR.setPower(-speed);
            middleR.setPower(-speed);
            backR.setPower(-speed);
        }
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);
    }
    public void Rturntoangle(double speed, int angle){
        telemetry.addData("Current Task","turn to angle");
        telemetry.update();

        while(opModeIsActive() && G.getIntegratedZValue() != angle){
            frontL.setPower(speed);
            middleL.setPower(speed);
            backL.setPower(speed);

            frontR.setPower(-speed);
            middleR.setPower(-speed);
            backR.setPower(-speed);
        }
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Robot has insomnia");
        }
        while(opModeIsActive() && G.getIntegratedZValue() != angle){
            frontL.setPower(-05);
            middleL.setPower(-05);
            backL.setPower(-.05);

            frontR.setPower(.05);
            middleR.setPower(.05);
            backR.setPower(.05);
        }
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);
    }
    public void linefindL(double speed){
        telemetry.addData("Current Task", "linefindL");
        telemetry.update();
        while(opModeIsActive() && DL.green() < 2){
            frontL.setPower(speed);
            middleL.setPower(speed);
            backL.setPower(speed);

            frontR.setPower(-speed);
            middleR.setPower(-speed);
            backR.setPower(-speed);
        }
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);
    }
    public void linefindR(double speed){
        telemetry.addData("Current Task", "linefindR");
        telemetry.update();
        while(opModeIsActive() && DR.green() < 2){
            frontL.setPower(-speed);
            middleL.setPower(-speed);
            backL.setPower(-speed);

            frontR.setPower(speed);
            middleR.setPower(speed);
            backR.setPower(speed);
        }
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);
    }
    
    public void pew(double speed){
        Pew.setPower(speed);
        PewPew.setPower(speed);
        sleep(250);
        Servo.setPower(1);
        sleep(2000);
        Servo.setPower(1);

        // servo code ////////////////////////////////////////////

    }
    public void linefollowL(double speed, double time){
        telemetry.addData("Current Task", "linefollow");
        telemetry.update();
        double Ttime = time+getRuntime();
        while(opModeIsActive() && Ttime > getRuntime()) {
            if (DR.green() == 0) {
                frontL.setPower(speed);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(speed);
                backR.setPower(0);
                backL.setPower(speed);
            }
            if (DR.green() > 0) {
                frontL.setPower(0);
                frontR.setPower(speed);
                middleR.setPower(speed);
                middleL.setPower(0);
                backR.setPower(speed);
                backL.setPower(0);
            }
        }
        frontL.setPower(0);
        frontR.setPower(0);
        middleR.setPower(0);
        middleL.setPower(0);
        backR.setPower(0);
        backL.setPower(0);
    }
    public void linefollowR(double speed, double time){
        telemetry.addData("Current Task", "linefollow");
        telemetry.update();
        double Ttime = time+getRuntime();
        while(opModeIsActive() && Ttime > getRuntime()) {

            if (DR.green() == 0) {
                frontL.setPower(speed);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(speed);
                backR.setPower(0);
                backL.setPower(speed);
            }
            if (DR.green() > 0) {
                frontL.setPower(0);
                frontR.setPower(speed);
                middleR.setPower(speed);
                middleL.setPower(0);
                backR.setPower(speed);
                backL.setPower(0);
            }
        }
        frontL.setPower(0);
        frontR.setPower(0);
        middleR.setPower(0);
        middleL.setPower(0);
        backR.setPower(0);
        backL.setPower(0);
    }
    public void turnto160(double speed){
        while(opModeIsActive() && G.getIntegratedZValue() < 160) {
            frontL.setPower(-speed);
            middleL.setPower(-speed);
            backL.setPower(-speed);

            frontR.setPower(speed);
            middleR.setPower(speed);
            backR.setPower(speed);
        }
        frontL.setPower(0);
        frontR.setPower(0);
        middleR.setPower(0);
        middleL.setPower(0);
        backR.setPower(0);
        backL.setPower(0);
    }
    public void forwardthing(double speed, double angle) {
        double Right;
        double Left;
        int z = G.getIntegratedZValue();
        Right = speed - (z - angle)/50;
        Left = speed - (z - angle)/50;
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
    public void RfindBeaconand(){
        boolean line;
        double thing;
        boolean swap = false;
        boolean GoLeft = true;
        boolean ready = false;
        while(opModeIsActive() && !ready){
            if(DR.green() < 3){
                GoLeft = !GoLeft;
                swap = true;
            }
            if(GoLeft){
                frontL.setPower(0);
                middleL.setPower(0);
                backL.setPower(0);

                frontR.setPower(.25);
                middleR.setPower(.25);
                backR.setPower(.25);
            } else {
                frontL.setPower(.25);
                middleL.setPower(.25);
                backL.setPower(.25);

                frontR.setPower(0);
                middleR.setPower(0);
                backR.setPower(0);
            }
            if(swap){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    telemetry.addData("Error", "Robot has insomnia");
                }
                swap = false;
            }
            if(UR.red() > 3|| UR.blue() > 3 ){
                ready = true;
                frontL.setPower(0);
                middleL.setPower(0);
                backL.setPower(0);

                frontR.setPower(0);
                middleR.setPower(0);
                backR.setPower(0);
            }

        }
    }
    public void Pstraightwhite(double speed, int angle, int aftertime){
        telemetry.addData("Current Task", "straightwhite");
        telemetry.update();
        double Right;
        double Left;
        while(opModeIsActive() && DR.green() <= 1){
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
        telemetry.addData("t", "t");
        frontL.setPower(speed);
        middleL.setPower(speed);
        backL.setPower(speed);

        frontR.setPower(speed);
        middleR.setPower(speed);
        backR.setPower(speed);
        try {
            Thread.sleep(aftertime);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Robot has insomnia");
        }
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);
    }

    public void straightwhite(double speed, int aftertime){
        telemetry.addData("Current Task", "straightwhite");
        telemetry.update();
        double Right;
        double Left;
        double angle = G.getIntegratedZValue();
        while(opModeIsActive() && DR.green() <= 1){
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
        telemetry.addData("t", "t");
        frontL.setPower(speed);
        middleL.setPower(speed);
        backL.setPower(speed);

        frontR.setPower(speed);
        middleR.setPower(speed);
        backR.setPower(speed);
        try {
            Thread.sleep(aftertime);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Robot has insomnia");
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
        while(opModeIsActive() && timeS >= getRuntime()){
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
            telemetry.update();


        }
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);
    }
    public void turn(int angle){
        telemetry.addData("Current Task", "turn");
        double Right;
        double Left;
        while(opModeIsActive() && !(G.getHeading() == angle)){
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
    public void LineRThing(double speed) {
        int poop;
        boolean Recent = false;
        while(opModeIsActive() && true) {
            if(gamepad1.a){
                break;
            }
            if (DR.green() < 3) {
                if (GoLeftLine) {
                    GoLeftLine = false;
                    Recent = true;

                } else {
                    GoLeftLine = true;
                    Recent = true;
                }
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

            if(Recent){
                Recent =  false;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    telemetry.addData("Error", "Robot has insomnia");
                }
            }
        }
    }
    public void turn2(int angle, double speed){
        while(opModeIsActive() && true) {
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
        while(opModeIsActive() && !(DR.green() < 3)){
            frontL.setPower(-speed);
            middleL.setPower(-speed);
            backL.setPower(-speed);

            frontR.setPower(speed);
            middleR.setPower(speed);
            backR.setPower(speed);
        }
    }
    public void Ldown(double speed){
        while(opModeIsActive() && !(DL.green() < 3)){
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
        while(opModeIsActive() && UR.red() < 2 && UR.blue() < 2){
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
        while(opModeIsActive() && Math.abs(Z - angle) > 3){
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
        frontL.setPower(speed);
        middleL.setPower(speed);
        backL.setPower(speed);

        frontR.setPower(speed);
        middleR.setPower(speed);
        backR.setPower(speed);

    }
    public void nowhilestraight(double speed, double angle){

    }
    public void readjustR(double time){
        double speed = .25;
        boolean thing = false;
        double Ttime= getRuntime() + time;
        while(opModeIsActive() && getRuntime() < Ttime){
            if(DR.green() < 3 && thing){
                speed=-speed;
            }
            frontL.setPower(-speed);
            middleL.setPower(-speed);
            backL.setPower(-speed);

            frontR.setPower(speed);
            middleR.setPower(speed);
            backR.setPower(speed);

        }
        frontL.setPower(0);
        middleL.setPower(0);
        backL.setPower(0);

        frontR.setPower(0);
        middleR.setPower(0);
        backR.setPower(0);
    }
    public void LeftLthing(double speed, double time){
        while(opModeIsActive() && DL.green() < 0){
            frontL.setPower(speed);
            middleL.setPower(speed);
            backL.setPower(speed);

            frontR.setPower(-speed);
            middleR.setPower(-speed);
            backR.setPower(-speed);
        }
        double Ttime = getRuntime() + time;
        while(opModeIsActive() && Ttime > getRuntime()){

        }

    }

}

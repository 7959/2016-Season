package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;

/**
 * Created by Robi on 1/28/2017.
 */
@Autonomous(name = "Red Pushy")
public class Redpushypushy extends LinearOpMode{
    private DcMotor frontL; // 1
    private DcMotor frontR; // 2
    private DcMotor backL; // 3
    private DcMotor backR; // 4
    private DcMotor middleL; // 5
    private DcMotor middleR;
    private ModernRoboticsI2cGyro Gsensor;
    private ColorSensor UR;
    private ColorSensor UL;
    private ColorSensor DR;
    private ColorSensor DL;
    private int phase;
    private boolean line;
    private boolean LC;
    private boolean RC;
    private int cal = 0;
    private int L = 0;
    private int s = 50;
    private int R = 0;
    private int Z = 0;
    private int x = 0;
    private int k = 0;
    private int z = 0;
    private int t = 0;
    private int DA = 0;
    private int task = 0;

    private void ACal() {
        telemetry.addData("Z heading", Gsensor.getIntegratedZValue());
        telemetry.addData("y", Gsensor.rawY());
        telemetry.addData("x", Gsensor.rawX());
        telemetry.addData("z", Gsensor.rawZ());
        telemetry.update();
        z = Gsensor.getIntegratedZValue();
        k = z - DA;
        k = k / 50;
        R = s - k;
        L = s + k;
        telemetry.addData("R", R);
        telemetry.addData("L", L);
        telemetry.addData("R", R);
        telemetry.addData("L", L);
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
    }
    private void Motors(){
        frontL.setPower(L);
        middleL.setPower(L);
        backL.setPower(L);
        frontR.setPower(R);
        middleR.setPower(R);
        backR.setPower(R);
    }

    public void runOpMode(){
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
            telemetry.addData("phase", phase);
            telemetry.addData("runtime", getRuntime());
            telemetry.addData("Line", DR.green());
            telemetry.addData("Blue", UR.blue());
            telemetry.addData("Red", UR.red());
            telemetry.addData("FR", frontR.getPower());
            telemetry.addData("FL", frontL.getPower());
            telemetry.addData("MR", middleR.getPower());
            telemetry.addData("ML", middleL.getPower());
            telemetry.addData("BR", backR.getPower());
            telemetry.addData("BL", backL.getPower());
            while (phase == 0) {
                telemetry.addData("phase", phase);
                frontL.setPower(.5);
                frontR.setPower(.5);
                middleR.setPower(.5);
                middleL.setPower(.5);
                backR.setPower(.5);
                backL.setPower(.5);
                if (DR.green() > 2) {
                    phase++;
                    frontL.setPower(0);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(0);
                    backR.setPower(0);
                    backL.setPower(0);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        telemetry.addData("Oh noes", "Robot.exe has insomnia");
                    }

                }

            }
            while (phase == 1) {
                telemetry.addData("phase", phase);
                frontL.setPower(-.1);
                frontR.setPower(.1);
                middleR.setPower(.1);
                middleL.setPower(-.1);
                backR.setPower(.1);
                backL.setPower(-.1);
                if (DR.green() > 3) {
                    phase++;
                    frontL.setPower(0);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(0);
                    backR.setPower(0);
                    backL.setPower(0);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        telemetry.addData("Oh noes", "Robot.exe has insomnia");
                    }
                }
            }
            while (phase == 2) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    telemetry.addData("Oh noes", "Robot.exe has insomnia");
                }

                frontL.setPower(-.1);
                frontR.setPower(.1);
                middleR.setPower(.1);
                middleL.setPower(-.1);
                backR.setPower(.1);
                backL.setPower(-.1);
                if (DR.green() > 3) {
                    phase++;
                    frontL.setPower(0);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(0);
                    backR.setPower(0);
                    backL.setPower(0);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        telemetry.addData("Oh noes", "Robot.exe has insomnia");
                    }
                }
            }
            while (phase == 3) {
                /*try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    telemetry.addData("Oh noes", "Robot.exe has insomnia");
                }
                frontL.setPower(.1);
                frontR.setPower(-.1);
                middleR.setPower(-.1);
                middleL.setPower(1);
                backR.setPower(-.1);
                backL.setPower(.1);*/
                //cal--;
                if (UR.green() == 0) {
                    frontL.setPower(0.25);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(.25);
                    backR.setPower(0);
                    backL.setPower(.25);
                }
                if (UR.green() > 0) {
                    frontL.setPower(0);
                    frontR.setPower(.25);
                    middleR.setPower(.25);
                    middleL.setPower(0);
                    backR.setPower(.25);
                    backL.setPower(0);
                }
                if (UR.red() >= 3 && UR.blue() <= 1) {
                    phase++;
                    frontL.setPower(0);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(0);
                    backR.setPower(0);
                    backL.setPower(0);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        telemetry.addData("Oh noes", "Robot.exe has insomnia");
                    }
                }
                if (UR.blue() >= 3 && UR.red() <= 1) {
                    frontL.setPower(0);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(0);
                    backR.setPower(0);
                    backL.setPower(0);
                    phase = 100;
                }
                if (DR.green() > 0) {
                    frontL.setPower(.25);
                    frontR.setPower(-.25);
                    middleR.setPower(-.25);
                    middleL.setPower(.25);
                    backR.setPower(-.25);
                    backL.setPower(.25);
                } else {
                    frontL.setPower(-.25);
                    frontR.setPower(.25);
                    middleR.setPower(.25);
                    middleL.setPower(-.25);
                    backR.setPower(-.25);
                }
            }
            while (phase == 4) {
                if (DR.green() > 0) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        telemetry.addData("Oh noes", "Robot.exe has insomnia");
                    }
                    frontL.setPower(-.25);
                    frontR.setPower(.25);
                    middleR.setPower(.25);
                    middleL.setPower(-.25);
                    backR.setPower(.25);
                    backL.setPower(-.25);
                } else {
                    RC = true;
                    phase++;
                }
            }

            while (phase == 5) {
                if (DR.green() == 0 && !line) {
                    frontL.setPower(.25);
                    frontR.setPower(-.25);
                    middleR.setPower(-.25);
                    middleL.setPower(.25);
                    backR.setPower(-.25);
                    backL.setPower(.25);
                } else {
                    line = true;
                }

                if (DR.green() > 0 && line) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        telemetry.addData("Oh noes", "Robot.exe has insomnia");
                    }
                    frontL.setPower(.25);
                    frontR.setPower(-.25);
                    middleR.setPower(-.25);
                    middleL.setPower(.25);
                    backR.setPower(-.25);
                    backL.setPower(.25);
                    cal++;
                } else {
                    LC = true;
                    phase++;
                    cal = cal / 2;
                }
            }
            if (phase == 6) {
                frontL.setPower(-.25);
                frontR.setPower(.25);
                middleR.setPower(.25);
                middleL.setPower(-.25);
                backR.setPower(.25);
                backL.setPower(-.25);
                try {
                    Thread.sleep(cal);
                } catch (InterruptedException e) {
                    telemetry.addData("Oh noes", "Robot.exe has insomnia");
                }
                phase++;
            }
            if (phase == 7) {
                telemetry.addData("phase", "YAY :D");
                frontL.setPower(.05);
                frontR.setPower(.05);
                middleR.setPower(.05);
                middleL.setPower(.05);
                backR.setPower(.05);
                backL.setPower(.05);
                try {
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    telemetry.addData("Oh noes", "Robot.exe has insomnia");
                }
                phase++;
            }
            while(phase == 8){
                if (UR.green() == 0) {
                    frontL.setPower(-0.25);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(-.25);
                    backR.setPower(0);
                    backL.setPower(-.25);
                }
                if (UR.green() > 0) {
                    frontL.setPower(0);
                    frontR.setPower(-.25);
                    middleR.setPower(-.25);
                    middleL.setPower(0);
                    backR.setPower(-.25);
                    backL.setPower(0);
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    telemetry.addData("Oh noes", "Robot.exe has insomnia");
                }
                t++;
                if(t == 5000){
                    frontL.setPower(0);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(0);
                    backR.setPower(0);
                    backL.setPower(0);
                    phase++;
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        telemetry.addData("Oh noes", "Robot.exe has insomnia");
                    }
                }
            }
            while(phase == 9){
                if(DR.green() > 0) {
                    frontL.setPower(.25);
                    frontR.setPower(-.25);
                    middleR.setPower(-.25);
                    middleL.setPower(.25);
                    backR.setPower(-.25);
                    backL.setPower(.25);
                } else {
                    frontL.setPower(0);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(0);
                    backR.setPower(0);
                    backL.setPower(0);
                    phase++;
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        telemetry.addData("Oh noes", "Robot.exe has insomnia");
                    }
                }
            }
            if(phase == 10){
                frontL.setPower(.25);
                frontR.setPower(-.25);
                middleR.setPower(-.25);
                middleL.setPower(.25);
                backR.setPower(-.25);
                backL.setPower(.25);
                phase++;
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    telemetry.addData("Oh noes", "Robot.exe has insomnia");
                }
                frontL.setPower(0);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(0);
                backR.setPower(0);
                backL.setPower(0);
            }
            while (phase == 34) {
                if (DR.green() > 0) {
                    frontL.setPower(.25);
                    frontR.setPower(-.25);
                    middleR.setPower(-.25);
                    middleL.setPower(.25);
                    backR.setPower(-.25);
                    backL.setPower(.25);
                } else {
                    frontL.setPower(-.25);
                    frontR.setPower(.25);
                    middleR.setPower(.25);
                    middleL.setPower(-.25);
                    backR.setPower(-.25);
                }
                if (UR.red() > 5) {
                    frontL.setPower(0);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(0);
                    backR.setPower(0);
                    backL.setPower(0);
                    phase = 7;
                }
            }
            if (phase == 100) {
                frontL.setPower(0);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(0);
                backR.setPower(0);
                backL.setPower(0);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    telemetry.addData("Oh noes", "Robot.exe has insomnia");
                }
            }

            /////////////////////SWAP DOWN SENSORS
            if (false) {
                frontL.setPower(-1);
                frontR.setPower(-1);
                middleR.setPower(-1);
                middleL.setPower(-1);
                backR.setPower(-1);
                backL.setPower(-1);
                try {
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    telemetry.addData("Oh noes", "Robot.exe has insomnia");
                }
                while (DR.green() <= 3) {
                    frontL.setPower(.5);
                    frontR.setPower(-.5);
                    middleR.setPower(-.5);
                    middleL.setPower(.5);
                    backR.setPower(-.5);
                    backL.setPower(.5);
                }
                phase++;
            }
        }

    }
}

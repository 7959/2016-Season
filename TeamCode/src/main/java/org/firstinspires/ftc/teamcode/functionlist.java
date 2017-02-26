package org.firstinspires.ftc.teamcode;

import android.text.BoringLayout;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Robi on 2/23/2017.
 */

public abstract class functionlist extends LinearOpMode {
    protected DcMotor fL;
    protected DcMotor fR;
    protected DcMotor bR;
    protected DcMotor bL;
    boolean thing = false;
    Boolean finalcorrect;
    boolean recheck = false;
    boolean rescancorrect = false;

    protected ModernRoboticsI2cGyro gy;

    protected DcMotor lL;
    protected DcMotor lR;

    protected DcMotor load;
    protected DcMotor spin;

    protected ColorSensor uL;
    protected ColorSensor uR;
    protected ColorSensor dR;
    protected ColorSensor dL;

    protected Boolean correct1;

    protected Boolean tcorrect1;


    protected Boolean leftLine;
    protected Boolean rightLine;
    public void loaderMap(){
        load = hardwareMap.dcMotor.get("Loader");
        spin = hardwareMap.dcMotor.get("Spin");

        load.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spin.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        load.setDirection(DcMotorSimple.Direction.FORWARD);
        spin.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void Map() {
        fL = hardwareMap.dcMotor.get("Front Left");
        fR = hardwareMap.dcMotor.get("Front Right");
        bL = hardwareMap.dcMotor.get("Back Left");
        bR = hardwareMap.dcMotor.get("Back Right");

        lL = hardwareMap.dcMotor.get("Right Launcher");
        lR = hardwareMap.dcMotor.get("Left Launcher");
        uR = hardwareMap.colorSensor.get("Up Right");
        uR.setI2cAddress(I2cAddr.create7bit(0x1e));
        uL = hardwareMap.colorSensor.get("Up Left");
        uL.setI2cAddress(I2cAddr.create7bit(0x19));
        dR = hardwareMap.colorSensor.get("Down Right");
        dR.setI2cAddress(I2cAddr.create7bit(0x1d));
        dL = hardwareMap.colorSensor.get("Down Left");
        dL.setI2cAddress(I2cAddr.create7bit(0x1f));



        gy = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("Gyro Sensor");
    }
    public void Rscan(boolean isred){

    }


    public void rescan(double time, double speed, double refangle, boolean isred){
        double Ttime = time +getRuntime();
        boolean left = true;
        while (opModeIsActive() && Ttime >= getRuntime()){
            if(isred) {
                if (uR.red() > 2 && uR.blue() < 2) {
                    rescancorrect = true;
                    break;
                }
                if (uL.blue() < 2 && uL.red() > 2) {
                    rescancorrect = true;
                    break;
                }
                if (uR.red() < 2 && uR.blue() > 2) {
                    rescancorrect = false;
                    break;
                }
                if (uL.blue() > 2 && uL.red() < 2) {
                    rescancorrect = false;
                    break;
                }
            } else {
                if (uR.red() < 2 && uR.blue() > 2) {
                    rescancorrect = true;
                    break;
                }
                if (uL.blue() > 2 && uL.red() < 2) {
                    rescancorrect = true;
                    break;
                }
                if (uR.red() > 2 && uR.blue() < 2) {
                    rescancorrect = false;
                    break;
                }
                if (uL.blue() < 2 && uL.red() > 2) {
                    rescancorrect = false;
                    break;
                }
            }
            if(left){
                fL.setPower(-speed);
                fR.setPower(speed);
                bL.setPower(speed);
                bR.setPower(-speed);
            } else {
                fL.setPower(speed);
                fR.setPower(-speed);
                bL.setPower(-speed);
                bR.setPower(speed);
            }
        }
    }
    public void followpushR(double speed, double angle){
        while(opModeIsActive()) {
            if(uR.red() > 4){
                break;
            }

            if (dR.green() > 0) {
                fL.setPower(speed);
                fR.setPower(0);
                bR.setPower(0);
                bL.setPower(speed);
            } else if (dR.green() == 0) {
                fL.setPower(0);
                fR.setPower(speed);
                bR.setPower(speed);
                bL.setPower(0);
            }
        }
        straighttime(angle, .1, 1);
        stopwheels();
    }
    public void pew(boolean on){
        if(on){
            lR.setPower(1);
            lL.setPower(1);
            load.setPower(1);
        } else {
            lR.setPower(0);
            lL.setPower(0);
            load.setPower(0);
        }
    }
    public void checkL(boolean isred, boolean isRight) {
        if(isRight) {
            if (uR.red() > 2 && uR.blue() < 2) {
                if (isred) {
                    tcorrect1 = true;
                } else tcorrect1 = false;
            }else {
                if (uR.red() < 2 && uR.blue() > 2) {
                    if (isred) {
                        tcorrect1 = false;
                    } else tcorrect1 = true;
                }
            }
        } else {
            if (uL.red() > 2 && uL.blue() < 2) {
                if (isred) {
                    tcorrect1 = true;
                } else tcorrect1 = false;
            }else {
                if (uL.red() < 2 && uL.blue() > 2) {
                    if (isred) {
                        tcorrect1 = false;
                    } else tcorrect1 = true;
                }
            }
        }
    }
    public void checkagainR(boolean isred){
        if(isred){
            if(uR.red() < 2 && uR.blue() > 2){
                finalcorrect = false;
            } else if(uR.red() > 2 && uR.blue() < 2){
                finalcorrect = true;
            } else {
                finalcorrect = true;
                rescancorrect = true;
            }
        } else {
            if (uR.red() < 2 && uR.blue() > 2) {
                finalcorrect = true;
            } else if (uR.red() > 2 && uR.blue() < 2) {
                finalcorrect = false;
            } else {
                finalcorrect = true;
                rescancorrect = true;
            }
        }
    }
    public void checkagainL(boolean isred) {
        if (isred) {
            if (uL.red() < 2 && uL.blue() > 2) {
                finalcorrect = false;
            } else if (uL.red() > 2 && uL.blue() < 2) {
                finalcorrect = true;
            } else {
                finalcorrect = true;
                recheck = true;
            }
        } else {
            if (uL.red() < 2 && uL.blue() > 2) {
                finalcorrect = true;
            } else if (uL.red() > 2 && uL.blue() < 2) {
                finalcorrect = false;
            } else {
                finalcorrect = true;
                recheck = true;
            }
        }
    }



    public void checkR(boolean isred, boolean isRight){

        if(isRight) {
            if (uR.red() > 2 && uR.blue() < 2) {
                if (isred) {
                    tcorrect1 = true;
                } else tcorrect1 = false;
            }else {
                if (uR.red() < 2 && uR.blue() > 2) {
                    if (isred) {
                        tcorrect1 = false;
                    } else tcorrect1 = true;
                }
            }
        } else {
            if (uL.red() > 2 && uL.blue() < 2) {
                if (isred) {
                    tcorrect1 = true;
                } else tcorrect1 = false;
            }else {
                if (uL.red() < 2 && uL.blue() > 2) {
                    if (isred) {
                        tcorrect1 = false;
                    } else tcorrect1 = true;
                }
            }
        }
    }


    public void turnto0(double speed){
        if(gy.getIntegratedZValue() > 0){
            fR.setPower(-speed);
            fL.setPower(speed);
            bR.setPower(-speed);
            bL.setPower(speed);
        } else if(gy.getIntegratedZValue() > 0){
            fR.setPower(speed);
            fL.setPower(-speed);
            bR.setPower(speed);
            bL.setPower(-speed);
        }
        while(gy.getIntegratedZValue() != 0 && opModeIsActive());
        stopwheels();
    }

    public void MotorSet() {
        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        lR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        fL.setDirection(DcMotorSimple.Direction.REVERSE);
        bL.setDirection(DcMotorSimple.Direction.REVERSE);
        bR.setDirection(DcMotorSimple.Direction.FORWARD);
        fR.setDirection(DcMotorSimple.Direction.REVERSE);

        lL.setDirection(DcMotorSimple.Direction.FORWARD);
        lR.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void stopwheels(){
        fL.setPower(0);
        bL.setPower(0);

        fR.setPower(0);
        bR.setPower(0);
    }
    public void linefindR2(double speed){
        telemetry.addData("Current Task", "linefindR");
        telemetry.update();
        while(opModeIsActive() && dR.green() > 2){
            fL.setPower(speed);
            bL.setPower(speed);

            fR.setPower(-speed);
            bR.setPower(-speed);
        }
        stopwheels();
    }
    public void linefindL2(double speed){
        telemetry.addData("Current Task", "linefindR");
        telemetry.update();
        while(opModeIsActive() && dL.green() > 2){
            fL.setPower(speed);
            bL.setPower(speed);

            fR.setPower(-speed);
            bR.setPower(-speed);
        }
        stopwheels();
        sleep(100);
    }

    public void linefindR(double speed){
        telemetry.addData("Current Task", "linefindR");
        telemetry.update();
        while(opModeIsActive() && dR.green() > 2){
            fL.setPower(-speed);
            bL.setPower(-speed);

            fR.setPower(speed);
            bR.setPower(speed);
        }
        stopwheels();
    }
    public void linefindL(double speed){
        telemetry.addData("Current Task", "linefindR");
        telemetry.update();
        while(opModeIsActive() && dL.green() > 2){
            fL.setPower(-speed);
            bL.setPower(-speed);

            fR.setPower(speed);
            bR.setPower(speed);
        }
        stopwheels();
        sleep(1000);
    }

    public void findcolorL(double speed, double angle, double t, boolean isred){
        double Right;
        double Left;
        double giveuptime = t + getRuntime();
        while (opModeIsActive() && giveuptime > getRuntime()) {
            if(uL.red() > 2 && uL.blue() == 0){
                if(isred) {
                    correct1 = true;
                } else correct1 = false;
                break;
            }
            if(uL.blue() > 2 && uL.red() == 0){
                if(isred){
                    correct1 = false;
                } else correct1 = true;
                break;
            }
            telemetry.addData("dr", dR.green());
            int z = gy.getIntegratedZValue();
            Right = speed - (z - angle) / 100;
            Left = speed + (z - angle) / 100;
            Left = Range.clip(Left, -1, 1);
            Right = Range.clip(Right, -1, 1);

            fL.setPower(Left);
            bL.setPower(Left);

            fR.setPower(Right);
            bR.setPower(Right);
            telemetry.addData("findcolorL", "ye");
            telemetry.addData("right", Right);
            telemetry.addData("left", Left);
            telemetry.addData("RPower", fR.getPower());
            telemetry.addData("LPower", fL.getPower());
            telemetry.addData("Dr", dR.green());
            telemetry.update();
        }
    }


    public void findcolorR(double speed, double angle, double t, boolean isred){
        double Right;
        double Left;
        double giveuptime = t + getRuntime();
        while (opModeIsActive() && giveuptime > getRuntime()) {
            if(uR.red() > 2 && uR.blue() == 0){
                if(isred) {
                    correct1 = true;
                } else correct1 = false;
                break;
            }
            if(uR.blue() > 2 && uR.red() == 0){
                if(isred){
                    correct1 = false;
                } else correct1 = true;
                break;
            }
            telemetry.addData("dr", dR.green());
            int z = gy.getIntegratedZValue();
            straighttime(angle, speed, .1);
            telemetry.addData("Red", dR.red());
            telemetry.addData("RPower", fR.getPower());
            telemetry.addData("LPower", fL.getPower());
            telemetry.addData("Dr", dR.green());
            telemetry.update();
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void beaconpush(double angle, double speed, double time) {//Play with sensors and see what you can do with finding beacon change!
        double Right;
        double Left;
        double timeS = time + getRuntime();
        while (opModeIsActive() && timeS >= getRuntime()) {

            int z = gy.getIntegratedZValue();
            Right = speed - (z - angle) / 100;
            Left = speed + (z - angle) / 100;
            Left = Range.clip(Left, -1, 1);
            Right = Range.clip(Right, -1, 1);

            fL.setPower(Left);
            bL.setPower(Left);

            fR.setPower(Right);
            bR.setPower(Right);
            telemetry.addData("right", Right);
            telemetry.addData("left", Left);
            telemetry.addData("RPower", fR.getPower());
            telemetry.addData("LPower", fL.getPower());
            telemetry.update();
        }
    }
    public void straighttime(double angle, double speed, double time) {
        double Right;
        double Left;
        double timeS = time + getRuntime();
        while (opModeIsActive() && timeS >= getRuntime()) {

            int z = gy.getIntegratedZValue();
            Right = speed - (z - angle) / 100;
            Left = speed + (z - angle) / 100;
            Left = Range.clip(Left, -1, 1);
            Right = Range.clip(Right, -1, 1);

            fL.setPower(Left);
            bL.setPower(Left);

            fR.setPower(Right);
            bR.setPower(Right);
            telemetry.addData("right", Right);
            telemetry.addData("left", Left);
            telemetry.addData("RPower", fR.getPower());
            telemetry.addData("LPower", fL.getPower());
            telemetry.update();
        }
        stopwheels();
    }
    public void linefollowL2(double speed, double time){
        telemetry.addData("Current Task", "linefollow");
        telemetry.update();
        double Ttime = time+getRuntime();
        while(opModeIsActive() && Ttime > getRuntime()) {

            if (dL.green() > 1) {
                fL.setPower(0);
                fR.setPower(speed);
                bR.setPower(speed);
                bL.setPower(0);
            } else{
                fL.setPower(speed);
                fR.setPower(0);
                bR.setPower(0);
                bL.setPower(speed);
            }
        }
        stopwheels();
    }
    public void linefollowL(double speed, double time){
        telemetry.addData("Current Task", "linefollow");
        telemetry.update();
        double Ttime = time+getRuntime();
        while(opModeIsActive() && Ttime > getRuntime()) {

            if (dL.green() > 1) {
                fL.setPower(speed);
                fR.setPower(0);
                bR.setPower(0);
                bL.setPower(speed);
            } else{
                fL.setPower(0);
                fR.setPower(speed);
                bR.setPower(speed);
                bL.setPower(0);
            }
        }
        stopwheels();
    }
    public void linefollowR2(double speed, double time){
        telemetry.addData("Current Task", "linefollow2");
        telemetry.update();
        double Ttime = time+getRuntime();
        while(opModeIsActive() && Ttime > getRuntime()){
            if (dR.green() > 1) {
                fL.setPower(0);
                fR.setPower(speed);
                bR.setPower(speed);
                bL.setPower(0);
            } else{
                fL.setPower(speed);
                fR.setPower(0);
                bR.setPower(0);
                bL.setPower(speed);
            }
        }
        stopwheels();
        }
    public void linefollowR(double speed, double time){
        telemetry.addData("Current Task", "linefollow");
        telemetry.update();
        double Ttime = time+getRuntime();
        while(opModeIsActive() && Ttime > getRuntime()) {

            if (dR.green() > 1) {
                fL.setPower(speed);
                fR.setPower(0);
                bR.setPower(0);
                bL.setPower(speed);
            } else{
                fL.setPower(0);
                fR.setPower(speed);
                bR.setPower(speed);
                bL.setPower(0);
            }
        }
        stopwheels();
    }

    public void straightTillWhite(double speed, int aftertime, double angle) {
        telemetry.addData("Current Task", "straightwhite");
        telemetry.update();
        double Right;
        double Left;
        while (opModeIsActive()) {
            telemetry.addData("dr", dR.green());
            int z = gy.getIntegratedZValue();
            Right = speed - (z - angle) / 100;
            Left = speed + (z - angle) / 100;
            Left = Range.clip(Left, -1, 1);
            Right = Range.clip(Right, -1, 1);

            fL.setPower(Left);
            bL.setPower(Left);

            fR.setPower(Right);
            bR.setPower(Right);
            telemetry.addData("right", Right);
            telemetry.addData("left", Left);
            telemetry.addData("RPower", fR.getPower());
            telemetry.addData("LPower", fL.getPower());
            telemetry.addData("Dr", dR.green());
            telemetry.update();
            if(dR.green() > 0){
                rightLine = true;
                break;
            }
            if(dL.green() > 0){
                rightLine = false;
                break;
            }
        }
        sleep(aftertime);
        stopwheels();
    }
}

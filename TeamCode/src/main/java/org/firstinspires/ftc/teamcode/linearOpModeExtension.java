package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.configuration.ModernRoboticsConstants;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Robi on 2/15/2017.
 */

public abstract class linearOpModeExtension extends LinearOpMode{
    protected DcMotor fL;
    protected DcMotor fR;
    protected DcMotor bR;
    protected DcMotor bL;
    protected ModernRoboticsI2cGyro gyro;
    protected ColorSensor topLeft;
    protected ColorSensor topRight;
    protected ColorSensor deltaLeft;
    protected ColorSensor deltaRight;
    protected ColorSensor deltaMiddle;
    protected DcMotor lL;
    protected DcMotor lR;
    protected OpticalDistanceSensor OD;
    protected DcMotor loader;
    public boolean Redall = false;
    public boolean Blueall = false;
    public boolean redrightblueleft = false;
    public boolean bluerightredleft = false;
    public void stopwheels(){
        fL.setPower(0);
        bL.setPower(0);

        fR.setPower(0);
        bR.setPower(0);
    }
    public void strafemid(double speed){
        while(opModeIsActive() && deltaRight.green() < 1){
            fL.setPower(speed);
            bL.setPower(-speed);
            fR.setPower(-speed);
            bR.setPower(speed);
        }
        stopwheels();
    }
    public void whitestraight(double speed){
        int angle = gyro.getIntegratedZValue();
        double ffl;
        double ffr;
        double bbl;
        double bbr;
        double z;
        while(opModeIsActive() && deltaMiddle.green() > 0){
            z = gyro.getIntegratedZValue();
            bbl = speed - (z - angle) / 100;
            ffl = speed + (z - angle) / 100;
            ffr = speed - (z - angle) / 100;
            bbr = speed + (z - angle) / 100;
            bbl = Range.clip(bbl, -1, 1);
            bbr = Range.clip(bbr, -1, 1);
            ffl = Range.clip(ffl, -1, 1);
            ffr = Range.clip(ffr, -1, 1);
            fL.setPower(ffl);
            fR.setPower(ffr);
            bL.setPower(bbl);
            bR.setPower(bbr);
        }
        stopwheels();
    }
    public void newmove(double X, double Y, double turn){//////WARNING I HAVE TO DO SOME MATH ON THIS TO CHECK IT BUT IN THEORY SHOULD WORK
        fL.setPower(Y + turn + X);
        bL.setPower(Y + turn - X);
        fR.setPower(Y - turn - X);
        bR.setPower(Y - turn + X);
    }
    public void newpew(){
        boolean dif = false;
        lL.setPower(1);
        lR.setPower(1);
        sleep(500);
        double ll = lL.getCurrentPosition();
        double rr = lR.getCurrentPosition();
        sleep(500);
        double l = (ll - lL.getCurrentPosition()) / 500;
        double r = lR.getCurrentPosition();
        while(opModeIsActive() && dif){

        }
    }
    public void newstraighttime(double X, double Y, int angle, double time){// new auto correct needs testing
        double T = getRuntime() + time;
        double ffl;
        double bbl;
        double ffr;
        double bbr;
        double z;
        while(opModeIsActive() && T >= getRuntime()){
            z = gyro.getIntegratedZValue();
            ffl = Y + X + (z - angle) / 100;
            bbl = Y - X + (z - angle) / 100;
            ffr = Y - X - (z - angle) / 100;
            bbr = Y + X - (z - angle) / 100;

            bbl = Range.clip(bbl, -1, 1);
            bbr = Range.clip(bbr, -1, 1);
            ffl = Range.clip(ffl, -1, 1);
            ffr = Range.clip(ffr, -1, 1);
            fL.setPower(ffl);
            fR.setPower(ffr);
            bL.setPower(bbl);
            bR.setPower(bbr);
        }
        stopwheels();
    }

    public void strafetime(int angle, double time, double speed){
        double T = getRuntime() + time;
        double ffl;
        double ffr;
        double bbl;
        double bbr;
        double z;
        while(opModeIsActive() && opModeIsActive() && T >= getRuntime()){
            z = gyro.getIntegratedZValue();
            bbl = speed - (z - angle) / 100;
            ffl = speed + (z - angle) / 100;
            ffr = speed - (z - angle) / 100;
            bbr = speed + (z - angle) / 100;
            bbl = Range.clip(bbl, -1, 1);
            bbr = Range.clip(bbr, -1, 1);
            ffl = Range.clip(ffl, -1, 1);
            ffr = Range.clip(ffr, -1, 1);
            fL.setPower(ffl);
            fR.setPower(ffr);
            bL.setPower(bbl);
            bR.setPower(bbr);
        }
        stopwheels();
    }
    public void pew(){
        lR.setPower(1);
        lL.setPower(1);
        loader.setPower(.5);
        sleep(1500);
        lR.setPower(0);
        lL.setPower(0);
        loader.setPower(0);
    }
    public void straightfindwall(int angle, double speed){
        double T = getRuntime() + time;
        double Right;
        double Left;
        while(opModeIsActive() && OD.getLightDetected() > 1) {
            int z = gyro.getIntegratedZValue();
            Right = speed - (z - angle) / 100;
            Left = speed + (z - angle) / 100;
            Left = Range.clip(Left, -1, 1);
            Right = Range.clip(Right, -1, 1);

            fL.setPower(Left);
            bL.setPower(Left);

            fR.setPower(Right);
            bR.setPower(Right);
        }
        stopwheels();
    }
    public void Rline(double speed){//left strafe
        while (opModeIsActive() && deltaRight.green() < 1){
            fL.setPower(-speed);
            bL.setPower(speed);
            fR.setPower(speed);
            bR.setPower(-speed);
        }
        stopwheels();
    }
    public void Lline(double speed){//right strafe
        while(opModeIsActive() && deltaLeft.green() < 1){
            fL.setPower(speed);
            bL.setPower(-speed);
            fR.setPower(-speed);
            bR.setPower(speed);
        }
        stopwheels();
    }
    
    public void straightmovetime(int angle, double time, double speed){
        double T = getRuntime() + time;
        double Right;
        double Left;
        while(opModeIsActive() && T >= getRuntime()) {
            int z = gyro.getIntegratedZValue();
            Right = speed - (z - angle) / 100;
            Left = speed + (z - angle) / 100;
            Left = Range.clip(Left, -1, 1);
            Right = Range.clip(Right, -1, 1);

            fL.setPower(Left);
            bL.setPower(Left);

            fR.setPower(Right);
            bR.setPower(Right);
        }
        stopwheels();
    }
    public void strafelinefind(int angle, double time, double speed){
        double T = getRuntime() + time;
        double ffl;
        double ffr;
        double bbl;
        double bbr;
        double z;
        while(opModeIsActive() &&(deltaLeft.green() <= 2 || deltaMiddle.green() <= 2 || deltaRight.green() <= 2)){
            z = gyro.getIntegratedZValue();
            bbl = speed - (z - angle) / 100;
            ffl = speed + (z - angle) / 100;
            ffr = speed - (z - angle) / 100;
            bbr = speed + (z - angle) / 100;
            bbl = Range.clip(bbl, -1, 1);
            bbr = Range.clip(bbr, -1, 1);
            ffl = Range.clip(ffl, -1, 1);
            ffr = Range.clip(ffr, -1, 1);
            fL.setPower(ffl);
            fR.setPower(ffr);
            bL.setPower(bbl);
            bR.setPower(bbr);
        }
        stopwheels();
    }
    public void followline(double time){

    }
    public int findoppositeangle(int angle){
        if(angle < 0){
            angle=360+angle;
        }
        angle=angle+180;
        if(angle > 360){
            angle=angle-360;
        }
        if(angle == 360){
            angle=0;
        }
        return angle;
    }
    public void colorcheck(){
        if(topLeft.red() >= 2 && topLeft.blue() == 0 && topRight.red() >= 2 && topRight.blue() == 0){
            Redall = true;
        }
        if(topLeft.red() >= 2 && topLeft.blue() == 0 && topRight.blue() >= 2 && topRight.red() == 0){
            bluerightredleft = true;
        }
        if(topLeft.blue() >= 2 && topLeft.red() == 0 && topRight.red() >= 2 && topRight.red() == 0){
            redrightblueleft= true;
        }
        if(topLeft.blue() >= 2 && topLeft.red() == 0 && topRight.blue() >= 2 && topRight.red() == 0){
            Blueall = true;
        }
    }

    public void counterclockturn(int angle, double speed){
        int z = gyro.getIntegratedZValue();
        while(z != angle){
            z = gyro.getIntegratedZValue();
                fR.setPower(speed);
                bR.setPower(speed);
                fL.setPower(-speed);
                bL.setPower(-speed);
        }

    }
    public void followline(double speed, double time){
        double Ttime = getRuntime() + time;
        boolean goleft = true;
        while (getRuntime() < Ttime){
            if(goleft){

            }
        }
    }
    public void clockturn(int angle, double speed){
        int z = gyro.getIntegratedZValue();
        while(z != angle){
            z = gyro.getIntegratedZValue();
            fR.setPower(-speed);
            bR.setPower(-speed);
            fL.setPower(speed);
            bL.setPower(speed);
        }

    }
}

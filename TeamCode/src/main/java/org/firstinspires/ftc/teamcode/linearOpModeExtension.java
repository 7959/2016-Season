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
    protected ColorSensor deltaBottom;
    protected ColorSensor deltaLeft;
    protected ColorSensor deltaRight;
    protected ColorSensor deltaMiddle;
    protected DcMotor lL;
    protected DcMotor lR;
    protected OpticalDistanceSensor OD;
    protected DcMotor loader;
    public void stopwheels(){
        fL.setPower(0);
        bL.setPower(0);

        fR.setPower(0);
        bR.setPower(0);
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
        while(opModeIsActive() && deltaBottom.green() <= 2 || deltaLeft.green() <= 2 || deltaMiddle.green() <= 2 || deltaRight.green() <= 2){
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
    public void turn(int angle, double speed){

        int opposite = (Math.abs(angle)) + 540;
        if(angle < 0){
            angle= 360 - Math.abs(angle);
        }
        angle=angle+360;
        int z = gyro.getHeading();
        while(z != angle){
            z = gyro.getHeading() + 360;
            if(opposite > z && z > angle){

            }
        }

    }
}

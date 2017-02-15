package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
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
    public void strafetime(int angle, float time, float speed){
        double T = getRuntime() + time;
        double ffl;
        double ffr;
        double bbl;
        double bbr;
        double z;
        while(T >= getRuntime()){
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
        
    }
    
    public void straightmovetime(int angle, float time, float speed){
        double T = getRuntime() + time;
        float Right;
        float Left;
        while(T >= getRuntime()) {
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
    }
}

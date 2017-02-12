package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImplOnSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Robi on 12/17/2016.
 */

@TeleOp(name = "Billy", group = "TeleOps")
public class Autotest extends OpMode {
    protected DcMotor frontL; // 1
    protected DcMotor frontR; // 2
    protected DcMotor backL; // 3
    protected DcMotor backR; // 4
    protected DcMotor middleL; // 5
    protected DcMotor middleR; // 6
    protected DcMotor Pew;
    protected DcMotor PewPew;
    protected ColorSensor UR;
    protected ColorSensor UL;
    protected ColorSensor DR;
    protected ColorSensor DL;
    protected ModernRoboticsI2cGyro G;
    //protected Servo Servo;
    double P = 0.0;
    double right;
    double left;
    boolean test = false;
    boolean iftest;
    boolean whiletest = false;
    boolean GoLeftLine = false;
    //protected Servo Servo;
    //protected DcMotor launcher;
    //protected ColorSensor sensor1;
    //protected ColorSensor sensor2;
    //protected GyroSensor Gsensor;
    //protected OpticalDistanceSensor ODsensor;
    //protected I2cDeviceSynchImpl sensor1imp = new I2cDeviceSynchImpl(, sensor1.getI2cAddress(), false);
    public void init() {
        frontL = hardwareMap.dcMotor.get("Front Left"); // 1
        frontR = hardwareMap.dcMotor.get("Front Right"); // 2
        middleL = hardwareMap.dcMotor.get("Middle Left"); // 3
        middleR = hardwareMap.dcMotor.get("Middle Right"); // 4
        backL = hardwareMap.dcMotor.get("Back Left"); // 5
        backR = hardwareMap.dcMotor.get("Back Right"); // 6
        Pew = hardwareMap.dcMotor.get("Right Launcher");
        PewPew = hardwareMap.dcMotor.get("Left Launcher");
        //Servo = hardwareMap.servo.get("Servo");

        UR = hardwareMap.colorSensor.get("Up Right");
        UR.setI2cAddress(I2cAddr.create7bit(0x1e));
        UL = hardwareMap.colorSensor.get("Up Left");
        UL.setI2cAddress(I2cAddr.create7bit(0x19));
        DR = hardwareMap.colorSensor.get("Down Right");
        DR.setI2cAddress(I2cAddr.create7bit(0x1d));
        DL = hardwareMap.colorSensor.get("Down Left");
        DL.setI2cAddress(I2cAddr.create7bit(0x1f));

        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 1
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 2
        middleL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 3
        middleR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 4
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 5
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 6
        Pew.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        PewPew.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //Servo.setDirection(com.qualcomm.robotcore.hardware.Servo.Direction.FORWARD);

        frontL.setDirection(DcMotorSimple.Direction.REVERSE); // 1
        frontR.setDirection(DcMotorSimple.Direction.REVERSE); // 2
        middleL.setDirection(DcMotorSimple.Direction.FORWARD); // 4
        middleR.setDirection(DcMotorSimple.Direction.REVERSE); // 3
        backL.setDirection(DcMotorSimple.Direction.FORWARD); // 5
        backR.setDirection(DcMotorSimple.Direction.REVERSE); // 6

        Pew.setDirection(DcMotorSimple.Direction.FORWARD);
        PewPew.setDirection(DcMotorSimple.Direction.REVERSE);


        frontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 1
        frontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 2
        middleL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 3
        middleR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 4
        backL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 5
        backR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // 6

        Pew.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        PewPew.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        G = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("Gyro Sensor");

        telemetry.addData("Hey!", "I'm calibrating right now let me be!");
        telemetry.update();
        G.calibrate();

        while (G.isCalibrating()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                telemetry.addData("Oh noes", "Robot.exe has insomnia");
            }
        }

        telemetry.addData("Hey", "Let's go get 'em!");
        telemetry.update();
    }

    public void loop() {
        if (!test) {
            if (gamepad1.dpad_up) {
                P = P + .1;
            }
            if (gamepad1.dpad_down) {
                P = P - .1;
            }

            Range.clip(P, 0, 1);
            telemetry.addData("PewPewPower", P);
            if (gamepad1.right_trigger > 0) {
                Pew.setPower(P);
                PewPew.setPower(P);
            } else if (gamepad2.left_trigger > 0) {
                Pew.setPower(-P);
                PewPew.setPower(-P);
            } else {
                Pew.setPower(0);
                PewPew.setPower(0);
            }


            right = gamepad1.left_stick_y + gamepad1.left_stick_x;
            left = gamepad1.left_stick_y - gamepad1.left_stick_x;
            if (gamepad1.right_bumper) {
                right = right / 2;
                left = left / 2;
            }
            frontL.setPower(left);
            middleL.setPower(left);
            backL.setPower(left);
            frontR.setPower(right);
            middleR.setPower(right);
            backR.setPower(right);
        }
        if (gamepad1.b) {
            whiletest = true;
        }
        if (gamepad1.y) {
            iftest = true;
        }
        while (whiletest) {
            LineRThing(.25);
        }
        if (iftest) {
            straightwhite(0);
        }
        telemetry.addData("Left Power", frontL.getPower());
        telemetry.addData("Right Power", frontR.getPower());
        telemetry.addData("Right Thing", right);
        telemetry.addData("Left Thing", left);
        telemetry.addData("joystick y", gamepad1.left_stick_y);
        telemetry.addData("joystick x", gamepad1.left_stick_x);
        telemetry.addData("Bumper", gamepad1.right_bumper);
        telemetry.addData("thing", test);
        //launcher.setPower(gamepad1.right_stick_y);
        //telemetry.addData("Fight klub", sensor1.getI2cAddress());
        //telemetry.addData("sg1", sensor1.green());
        //telemetry.addData("sr1", sensor1.red());
        //telemetry.addData("sb1", sensor1.blue());
        //>3
        /*if(gamepad1.right_bumper){
            launcher.setMode(1);
            launcher2.setMode(1);
        } else {
            launcher.setMode(0);
            launcher2.setMode(0);
        }
        if(gamepad1.a){
            Servo.setPosition((Servo.getPosition() + .1));
        }*/
    }

    public void straightwhite(double speed) {
        double Right;
        double Left;
        double angle = G.getIntegratedZValue();
        while (DR.green() > 2) {
            int z = G.getIntegratedZValue();
            Right = speed - (z - angle) / 100;
            Left = speed + (z - angle) / 100;
            Left = Range.clip(Left, 1, -1);
            Right = Range.clip(Right, 1, -1);
            telemetry.addData("DR", DR.green());
            frontL.setPower(Left);
            middleL.setPower(Left);
            backL.setPower(Left);

            frontR.setPower(Right);
            middleR.setPower(Right);
            backR.setPower(Right);

        }
    }
    public void LineRThing(double speed) {
        int poop;
        if (DR.green() < 3) {
            if (GoLeftLine) {
                GoLeftLine = false;
            } else {
                GoLeftLine = true;
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
    }

}

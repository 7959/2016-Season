package org.firstinspires.ftc.robotcontroller.internal;

import android.content.Context;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.io.File;
import java.io.PrintWriter;

/**
 * Created by Joseph on 11/3/2016.
 */

@TeleOp(name = "Autonomous Recorder", group = "Recorder")
public class Recorder extends OpMode {

    private DcMotor frontL;
    private DcMotor frontR;
    private DcMotor backL;
    private DcMotor backR;
    private DcMotor poker;
    private DcMotor loader;
    private DcMotor reloader;

    private Servo launchReset;
    private Servo launcherUD;

    private PrintWriter out;

    private PowerTracker powerTracker;

    private boolean changedToZero = false;

    private class PowerTracker {
        public void init() {
            for (byte i = 0; i < 7; i++) {
                previousPower[i] = 0;
            }
        }
        public byte changePower(DcMotor motor, float power) {
            String write;
            byte index;
            if (motor.getDeviceName().equals("Front Left")) {
                write = "m0;";
                index = 0;
            }
            else if (motor.getDeviceName().equals("Front Right")) {
                write = "m1;";
                index = 1;
            }
            else if (motor.getDeviceName().equals("Back Left")) {
                write = "m2;";
                index = 2;
            }
            else if (motor.getDeviceName().equals("Back Right")) {
                write = "m3;";
                index = 3;
            }
            else if (motor.getDeviceName().equals("Poker")) {
                write = "m4;";
                index = 4;
            }
            else if (motor.getDeviceName().equals("Loader")) {
                write = "m5;";
                index = 5;
            }
            else if (motor.getDeviceName().equals("Reloader")) {
                write = "m6;";
                index = 6;
            } else return 0;
            if (motor.getPower() != power) motor.setPower(power);
            else return -1;
            write = write + power;
            out.write(write);
            return 1;
        }
        private float[] previousPower = new float[7];
    }

    @Override
    public void init() {
        {
            char[] letter = new char[26];
            {
                int index = 0;
                for (char l = 'a'; l <= 'z'; l++) {
                    letter[index++] = l;
                }
            }
            char[] file = new char[128];
            {
                boolean r_change = false;
                int n = 0;
                byte index = 0;
                while (true) {
                    if (gamepad1.dpad_up && !r_change) {
                        r_change = true;
                        n++;
                        file[index] = letter[n];
                    } else if (gamepad1.dpad_down && !r_change) {
                        r_change = true;
                        n--;
                        file[index] = letter[n];
                    } else if (gamepad1.dpad_left) {
                        r_change = true;
                        index--;
                        if (index < 0) index = 0;
                    } else if (gamepad1.dpad_right) {
                        r_change = true;
                        index++;
                        if (index > 25) index = 25;
                    } else r_change = false;
                    telemetry.addData("File: ", file);
                    if (gamepad1.a) break;
                }
            }
            telemetry.clear();
            File outF;
            try {
                Context context; // If you are using Android Studio, ignore the error here and on the next two lines.
                context.getApplicationContext();
                outF = new File(context.getFilesDir(), file.toString() + ".JRRF"); // JRRF- Joe's Robot Recording Format
            } catch (Exception e) {
                telemetry.clear();
                telemetry.addData("ERROR", "File path failed to be found.");
                while (!gamepad1.a && !gamepad1.b);
                requestOpModeStop();
            }
            if (!outF.exists()) {
                try {
                    if (!outF.createNewFile()) requestOpModeStop();
                } catch (Exception e) {
                    telemetry.clear();
                    telemetry.addData("ERROR", "File could not be created.");
                    while (!gamepad1.a && !gamepad1.b);
                    requestOpModeStop();
                }
            }
            if (!outF.canWrite()) {
                if (!outF.setWritable(true)) requestOpModeStop();
            }
            try {
                out = new PrintWriter(outF, "UTF-8");
            } catch (Exception e) {
                telemetry.clear();
                telemetry.addData("ERROR", "Filewriter could not open file.");
                while (!gamepad1.a && !gamepad1.b);
                requestOpModeStop();
            }
        }

        frontL = hardwareMap.dcMotor.get("Front Left");
        out.write("m0\"Front Left\"");
        frontR = hardwareMap.dcMotor.get("Front Right");
        out.write("m1\"Front Right\"");
        backL = hardwareMap.dcMotor.get("Back Left");
        out.write("m2\"Back Left\"");
        backR = hardwareMap.dcMotor.get("Back Right");
        out.write("m3\"Back Right\"");
        poker = hardwareMap.dcMotor.get("Poker");
        out.write("m4\"Poker\"");
        loader = hardwareMap.dcMotor.get("Loader");
        out.write("m5\"Loader\"");
        reloader = hardwareMap.dcMotor.get("Reloader");
        out.write("m6\"Reloader\"");

        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        poker.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        loader.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        reloader.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontL.setDirection(DcMotorSimple.Direction.REVERSE);
        frontR.setDirection(DcMotorSimple.Direction.FORWARD);
        backL.setDirection(DcMotorSimple.Direction.REVERSE);
        backR.setDirection(DcMotorSimple.Direction.FORWARD);
        poker.setDirection(DcMotorSimple.Direction.FORWARD);
        loader.setDirection(DcMotorSimple.Direction.FORWARD);
        reloader.setDirection(DcMotorSimple.Direction.FORWARD);


        launchReset = hardwareMap.servo.get("Launcher Axis");
        out.write("s0\"Launcher Axis\"");
        launcherUD = hardwareMap.servo.get("Launcher Y");
        out.write("s1\"Launcher Y\"");

        launcherUD.scaleRange(0, 0.25);
        out.write("ss0;0;0.25");
        launchReset.scaleRange(0, 0.5);
        out.write("ss0;0;0.5");
    }

    @Override
    public void loop() {
        if (gamepad1.left_stick_x != 0 || gamepad1.left_stick_y != 0) {
            powerTracker.changePower(frontL, gamepad1.left_stick_y + gamepad1.left_stick_x);
            powerTracker.changePower(frontR, gamepad1.left_stick_y - gamepad1.left_stick_x);
            powerTracker.changePower(backL, gamepad1.left_stick_y + gamepad1.left_stick_x);
            powerTracker.changePower(backR, gamepad1.left_stick_y - gamepad1.left_stick_x);
            changedToZero = true;
        } else if (gamepad1.left_trigger > 0) {
            powerTracker.changePower(frontL, -gamepad1.left_trigger);
            powerTracker.changePower(frontR, gamepad1.left_trigger);
            powerTracker.changePower(backL, -gamepad1.left_trigger);
            powerTracker.changePower(backR, gamepad1.left_trigger);
            changedToZero = true;
        } else if (gamepad1.right_trigger > 0) {
            powerTracker.changePower(frontL, gamepad1.right_trigger);
            powerTracker.changePower(frontR, -gamepad1.right_trigger);
            powerTracker.changePower(backL, gamepad1.right_trigger);
            powerTracker.changePower(backR, -gamepad1.right_trigger);
            changedToZero = true;
        } else if (changedToZero) {
            powerTracker.changePower(frontL, 0);
            powerTracker.changePower(frontR, 0);
            powerTracker.changePower(backL, 0);
            powerTracker.changePower(backR, 0);
            changedToZero = false;
        }
        if (gamepad1.left_bumper) {
            powerTracker.changePower(poker, 1);
        }
    }
}

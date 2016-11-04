package org.firstinspires.ftc.robotcontroller.internal;

import android.content.Context;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.io.File;
import java.io.IOException;
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
        launchReset.scaleRange(0, 0.5);
    }
    @Override
    public void loop() {

    }
}

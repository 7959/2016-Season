package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.VoltageSensor;

/**
 * Created by Joseph on 2/21/2017.
 */

public class testIfReady extends linearOpModeExtension {
    public void runOpMode()
    {
        fL = hardwareMap.dcMotor.get("Front Left");
        fR = hardwareMap.dcMotor.get("Front Right");
        bL = hardwareMap.dcMotor.get("Back Left");
        bR = hardwareMap.dcMotor.get("Back Right");
        lL = hardwareMap.dcMotor.get("Launcher Left");
        lR = hardwareMap.dcMotor.get("Launcher Right");
        loader = hardwareMap.dcMotor.get("Loader");
        while (!opModeIsActive());
        telemetry.addData("Working: ", "Checking battery");
        telemetry.update();
        fL.setPower(1);
        fR.setPower(1);
        bL.setPower(1);
        bR.setPower(1);
        lL.setPower(1);
        lR.setPower(1);
        loader.setPower(1);
        boolean batteryGood = true;
        for (int i = 0; i < hardwareMap.voltageSensor.size(); i++) {
            VoltageSensor sensor = hardwareMap.voltageSensor.iterator().next();
            if (sensor.getVoltage() < 12)
            {
                batteryGood = false;
                break;
            }
        }
        fL.setPower(0);
        fR.setPower(0);
        bL.setPower(0);
        bR.setPower(0);
        lL.setPower(0);
        lR.setPower(0);
        loader.setPower(0);
        telemetry.addData("Battery good", batteryGood);
        telemetry.addData("Working", "Flashing lights");
        telemetry.addData("Do", "Press \'a\' for true, and \'b\' for false");
        telemetry.update();
        boolean lights;
        {

        }
    }
}

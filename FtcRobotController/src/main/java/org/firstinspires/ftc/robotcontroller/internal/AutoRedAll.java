package org.firstinspires.ftc.robotcontroller.internal;

        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.ColorSensor;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Robi on 12/7/2016.
 */

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Auto Red All")
public class AutoRedAll extends LinearOpMode {
    private DcMotor frontL; // 1
    private DcMotor frontR; // 2
    private DcMotor backL; // 3
    private DcMotor backR; // 4
    private DcMotor middleL; // 5
    private DcMotor middleR; // 6
    private ColorSensor sensor1;
    private ColorSensor sensor2;
    boolean Thing = false;
    int Thingy = 0;

    @Override
    public void runOpMode() {


        frontL = hardwareMap.dcMotor.get("Front Left"); // 1
        frontR = hardwareMap.dcMotor.get("Front Right"); // 2
        middleL = hardwareMap.dcMotor.get("Middle Left"); // 3
        middleR = hardwareMap.dcMotor.get("Middle Right"); // 4
        backL = hardwareMap.dcMotor.get("Back Left"); // 5
        backR = hardwareMap.dcMotor.get("Back Right"); // 6
        sensor1 = hardwareMap.colorSensor.get("Up Sensor");
        sensor2 = hardwareMap.colorSensor.get("Down Sensor");
        frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 1
        frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 2
        middleL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 3
        middleR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 4
        backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // 5
        backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); //
        frontL.setDirection(DcMotorSimple.Direction.REVERSE); // 1
        frontR.setDirection(DcMotorSimple.Direction.REVERSE); // 2
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
    }

    public void Doohickey() {
        while (true) {
            frontL.setPower(1);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(1);
            backR.setPower(1);
            backL.setPower(1);
            if (sensor1.red() > -1/*Need testing*/) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    telemetry.addData("Error", "Would not sleep");
                }
                break;
            }
        }
    }

    public void thingyMuBobber() {
        while (true) {
            frontL.setPower(-1);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(-1);
            backR.setPower(1);
            backL.setPower(-1);
            if (sensor2.red() > 0/* WHITE */ && sensor1.red() + sensor1.blue() > 0/* VALUE */) {
                break;

            }
        }
    }

    public void Contraption() {
        while (true) {
            if (sensor2.red() < 0/*WHITE*/)
                frontL.setPower(0);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(0);
            backR.setPower(1);
            backL.setPower(0);
            if (sensor2.red() > 0/*WHITE*/) {
                frontL.setPower(1);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(1);
                backR.setPower(0);
                backL.setPower(1);
            }
            if (sensor1.red() > 3 && sensor2.blue() < 1) {
                Thing = true;
                break;
            }
            if (sensor1.red() > 3 && sensor2.blue() < 1) {
                Thing = false;
                break;

            }
        }
    }

    private void PushymcThingy() {
        if (Thing == true) {
            frontL.setPower(0.5);
            frontR.setPower(-0);
            middleR.setPower(-0.5);
            middleL.setPower(0.5);
            backR.setPower(-0.5);
            backL.setPower(0.5);
            try {
                Thread.sleep(78);
            } catch (InterruptedException e) {
                telemetry.addData("Error", "Would not sleep");
            }
        }
        if (Thing == false) {
            frontL.setPower(-0.5);
            frontR.setPower(0.5);
            middleR.setPower(0.5);
            middleL.setPower(-0.5);
            backR.setPower(0.5);
            backL.setPower(-0.5);
            try {
                Thread.sleep(78);
            } catch (InterruptedException e) {
                telemetry.addData("Error", "Would not sleep");
            }
        }
        frontL.setPower(.5);
        frontR.setPower(.5);
        middleR.setPower(.5);
        middleL.setPower(.5);
        backR.setPower(.5);
        backL.setPower(0.5);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Would not sleep");
        }
        frontL.setPower(-.5);
        frontR.setPower(-.5);
        middleR.setPower(-.5);
        middleL.setPower(-.5);
        backR.setPower(-.5);
        backL.setPower(-0.5);
        try {
            Thread.sleep(75);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Would not sleep");
        }

    }

    public void GETOFFMYLAWNYEDARNKIDS() {
        if(Thing = false)
            while(true){
                frontL.setPower(0.5);
                frontR.setPower(-0.5);
                middleR.setPower(-0.5);
                middleL.setPower(0.5);
                backR.setPower(-0.5);
                backL.setPower(0.5);
                if(sensor2.red() > 0/*WHITE*/){
                    break;
                }


            }
        if (Thing == true){
            while(true) {
                frontL.setPower(-0.5);
                frontR.setPower(0.5);
                middleR.setPower(0.5);
                middleL.setPower(-0.5);
                backR.setPower(0.5);
                backL.setPower(-0.5);
                if(sensor2.red() > 0/*WHITE*/){
                    break;
                }
            }
        }
        while(true){
            if(sensor2.red() < 0/*WHITE*/)
            frontL.setPower(-1);
            frontR.setPower(0);
            middleR.setPower(0);
            middleL.setPower(-1);
            backR.setPower(0);
            backL.setPower(-1);

            if (sensor2.red() > 0/*WHITE*/) {
                frontL.setPower(0);
                frontR.setPower(-1);
                middleR.setPower(-1);
                middleL.setPower(0);
                backR.setPower(-1);
                backL.setPower(0);
            }
            if (sensor1.red() > 0/*VALUE*/ && sensor2.blue() < 0/*(WHITE)*/) {

                Thing = false;
                break;
            }
        }
    }
    public void science_is_an_act_of_dark_magic() {
        frontL.setPower(-1);
        frontR.setPower(1);
        middleR.setPower(1);
        middleL.setPower(-1);
        backR.setPower(1);
        backL.setPower(-1);
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Would not sleep");
        }
    }
    public void Traffic_aint_gunna_get_in_my_way_again_I_will_buy_an_off_road_truck_and_go_on_long_journeys_into_the_countrside(){
        frontL.setPower(-1);
        frontR.setPower(1);
        middleR.setPower(1);
        middleL.setPower(-1);
        backR.setPower(1);
        backL.setPower(-1);
        try {
            Thread.sleep(0/*VALUE*/);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Would not sleep");
        }
        while(true){
            if(sensor2.red() > 0/*VALUE*/){
                frontL.setPower(1);
                frontR.setPower(1);
                middleR.setPower(1);
                middleL.setPower(1);
                backR.setPower(1);
                backL.setPower(1);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    telemetry.addData("Error", "Would not sleep");
                }
                break;
            } else  frontL.setPower(1);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(1);
            backR.setPower(1);
            backL.setPower(1);
        }
        frontL.setPower(0);
        frontR.setPower(0);
        middleR.setPower(0);
        middleL.setPower(0);
        backR.setPower(0);
        backL.setPower(0);
        }



    @Override
    public synchronized void waitForStart() throws InterruptedException {
        super.waitForStart();
        Doohickey();
        thingyMuBobber();
        Contraption();
        PushymcThingy();
        GETOFFMYLAWNYEDARNKIDS();
        science_is_an_act_of_dark_magic();
        Doohickey();
        thingyMuBobber();
        Contraption();
        PushymcThingy();
        GETOFFMYLAWNYEDARNKIDS();
        Traffic_aint_gunna_get_in_my_way_again_I_will_buy_an_off_road_truck_and_go_on_long_journeys_into_the_countrside();
        requestOpModeStop();
    }
}
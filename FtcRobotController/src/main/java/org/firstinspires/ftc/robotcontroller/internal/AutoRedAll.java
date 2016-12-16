package org.firstinspires.ftc.robotcontroller.internal;

        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.ColorSensor;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.hardware.TouchSensor;
        import com.qualcomm.robotcore.hardware.GyroSensor;
/**
 * Created by Robi on 12/7/2016.
 * Draft and test simple functions here
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
    private GyroSensor sensor3;
    boolean Thing = false;
    boolean THING = false;
    int THINg = 0;

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
    int thing;
    public void Testythingy(){
        while(true){
            thing=sensor3.rawY();
            thing++;


        }

    }
    public void wherebetheline(){
        while(true){
            frontL.setPower(-1);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(-1);
            backR.setPower(1);
            backL.setPower(-1);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                telemetry.addData("Error", "Would not sleep");
            }
            THINg++;
            if(sensor2.red() > 0/*white*/ && THINg < 390){
                frontL.setPower(0);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(0);
                backR.setPower(0);
                backL.setPower(0);
                THING = true;
                THINg = 0;
                break;
            }
            if(sensor2.red() > 0/*white*/ && THINg > 390){
                frontL.setPower(0);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(0);
                backR.setPower(0);
                backL.setPower(0);
                THING = false;
                THINg = 0;
                break;
            }

        }
    }
    public void CallibrateThingy(){//PROTOTYPE RED LEFT BEACON PUSH 2
        while(true){
            if(sensor2.red() < 0/*WHITE*/) {
                frontL.setPower(0);
                frontR.setPower(1);
                middleR.setPower(1);
                middleL.setPower(0);
                backR.setPower(1);
                backL.setPower(0);
            }
            if(sensor2.red() > 0/*WHITE*/){
                frontL.setPower(1);
                frontR.setPower(0);
                middleR.setPower(0);
                middleL.setPower(1);
                backR.setPower(0);
                backL.setPower(1);
            }
        }
    }

    public void Doohickey() {//find white line
        while (true) {
            frontL.setPower(1);
            frontR.setPower(1);
            middleR.setPower(1);
            middleL.setPower(1);
            backR.setPower(1);
            backL.setPower(1);
            if (sensor2.red() > -1/*White*/) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    telemetry.addData("Error", "Would not sleep");
                }
                break;
            }
        }
    }
    public void I_have_no_idea_what_this_does(){//test for push the right color
        frontL.setPower(0);
        frontR.setPower(0);
        middleR.setPower(0);
        middleL.setPower(0);
        backR.setPower(0);
        backL.setPower(0);
        try {
            Thread.sleep(75);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Would not sleep");
        }
        if(sensor1.blue() > 2 && sensor1.red() < 1){
            while(true) {
                frontL.setPower(-1);
                frontR.setPower(-1);
                middleR.setPower(-1);
                middleL.setPower(-1);
                backR.setPower(-1);
                backL.setPower(-1);
                if(sensor2.red() > 0/*WHITE*/){
                    frontL.setPower(0);
                    frontR.setPower(0);
                    middleR.setPower(0);
                    middleL.setPower(0);
                    backR.setPower(0);
                    backL.setPower(0);
                    break;
                }
            }
        }
    }
    public void thingyMuBobber() {//face beacon
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

    public void Contraption() {//move closer to beacon
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
            if (sensor1.red() < 0/*CLOSEDISTANCE*/|| sensor1.blue() < 0 )  {
                break;
            }

        }
    }
    public void TimyWimyThingy(){//find color on sensor1 side
        while(true){
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

    private void PushymcThingy() {// push beacon NEEDS IMPROVEMENT WHEN SENSORS ARRIVE
        if (Thing == true) {
            frontL.setPower(0.5);
            frontR.setPower(-.5);
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

    public void GETOFFMYLAWNYEDARNKIDS() {//Return to white line
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
    public void Cookie() {//Right turn to 2nd beacon
        frontL.setPower(1);
        frontR.setPower(-1);
        middleR.setPower(-1);
        middleL.setPower(1);
        backR.setPower(-1);
        backL.setPower(1);
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Would not sleep");
        }
    }
    public void When_I_wake_up_well_I_know_Im_gonna_be_Im_gonna_be_the_man_who_wakes_up_next_to_you_When_I_go_out_yeah_I_know_Im_gonna_be_Im_gonna_be_the_man_who_goes_along_with_you_If_I_get_drunk_well_I_know_Im_gonna_be_Im_gonna_be_the_man_who_gets_drunk_next_to_you_And_if_I_haver_hey_I_know_Im_gonna_be_Im_gonna_be_the_man_whos_havering_to_you_But_I_would_walk_five_hundred_miles_Dada_da_dun_diddle_un_diddle_un_diddle_uh_da_da_When_Im_lonely_well_I_know_Im_gonna_be_Im_gonna_be_the_man_whos_lonely_without_you_And_when_Im_dreaming_well_I_know_Im_gonna_dream_Im_gonna_dream_about_the_time_when_Im_with_you_When_I_go_out_When_I_go_out_well_I_know_Im_gonna_be_Im_gonna_be_the_man_who_goes_along_with_you_And_when_I_come_home_When_I_come_home_yes_I_know_Im_gonna_be_Im_gonna_be_the_man_who_comes_back_home_with_you_Im_gonna_be_the_man_whos_coming_home_with_you_But_I_would_walk_five_hundred_miles_And_I_would_walk_five_hundred_more_Just_to_be_the_man_who_walked_a_thousand_miles_To_fall_down_at_your_door_Da_lat_da_Da_lat_da_da_lat_da_Da_lat_da_Da_da_da_dun_diddle_un_diddle_un_diddle_uh_da_da_Da_lat_da_Da_lat_da_da_lat_da_Da_lat_da_Da_da_da_dun_diddle_un_diddle_un_diddle_uh_da_da_Da_lat_da_Da_lat_da_da_lat_da_Da_lat_da_Da_da_da_dun_diddle_un_diddle_un_diddle_uh_da_da_Da_lat_da_Da_lat_da_da_lat_da_Da_lat_da_Da_da_da_dun_diddle_un_diddle_un_diddle_uh_da_da_And_I_would_walk_five_hundred_miles_And_I_would_walk_five_hundred_more_Just_to_be_the_man_who_walked_a_thousand_miles_To_fall_down_at_your_door(){
        frontL.setPower(-1);
        frontR.setPower(1);
        middleR.setPower(1);
        middleL.setPower(-1);
        backR.setPower(1);
        backL.setPower(-1);
        try {
            Thread.sleep(0/*TURN TO CAPBALL*/);
        } catch (InterruptedException e) {
            telemetry.addData("Error", "Would not sleep");
        }
        while(true){
            if(sensor2.red() > 0/*REDORBLUEIDUNNO*/){
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
        Doohickey();//NEEDS TESTING
        thingyMuBobber();//NEEDS TESTING
        Contraption();//NEEDS TESTING
        TimyWimyThingy();//NEEDS TESTING
        PushymcThingy();//NEEDS TESTING
        GETOFFMYLAWNYEDARNKIDS();//NEEDS TESTING
        Cookie();//NEEDS TESTING
        Doohickey();//NEEDS TESTING
        thingyMuBobber();//NEEDS TESTING
        Contraption();//NEEDS TESTING
        TimyWimyThingy();//NEEDS TESTING
        PushymcThingy();//NEEDS TESTING
        GETOFFMYLAWNYEDARNKIDS();//NEEDS TESTING
        When_I_wake_up_well_I_know_Im_gonna_be_Im_gonna_be_the_man_who_wakes_up_next_to_you_When_I_go_out_yeah_I_know_Im_gonna_be_Im_gonna_be_the_man_who_goes_along_with_you_If_I_get_drunk_well_I_know_Im_gonna_be_Im_gonna_be_the_man_who_gets_drunk_next_to_you_And_if_I_haver_hey_I_know_Im_gonna_be_Im_gonna_be_the_man_whos_havering_to_you_But_I_would_walk_five_hundred_miles_Dada_da_dun_diddle_un_diddle_un_diddle_uh_da_da_When_Im_lonely_well_I_know_Im_gonna_be_Im_gonna_be_the_man_whos_lonely_without_you_And_when_Im_dreaming_well_I_know_Im_gonna_dream_Im_gonna_dream_about_the_time_when_Im_with_you_When_I_go_out_When_I_go_out_well_I_know_Im_gonna_be_Im_gonna_be_the_man_who_goes_along_with_you_And_when_I_come_home_When_I_come_home_yes_I_know_Im_gonna_be_Im_gonna_be_the_man_who_comes_back_home_with_you_Im_gonna_be_the_man_whos_coming_home_with_you_But_I_would_walk_five_hundred_miles_And_I_would_walk_five_hundred_more_Just_to_be_the_man_who_walked_a_thousand_miles_To_fall_down_at_your_door_Da_lat_da_Da_lat_da_da_lat_da_Da_lat_da_Da_da_da_dun_diddle_un_diddle_un_diddle_uh_da_da_Da_lat_da_Da_lat_da_da_lat_da_Da_lat_da_Da_da_da_dun_diddle_un_diddle_un_diddle_uh_da_da_Da_lat_da_Da_lat_da_da_lat_da_Da_lat_da_Da_da_da_dun_diddle_un_diddle_un_diddle_uh_da_da_Da_lat_da_Da_lat_da_da_lat_da_Da_lat_da_Da_da_da_dun_diddle_un_diddle_un_diddle_uh_da_da_And_I_would_walk_five_hundred_miles_And_I_would_walk_five_hundred_more_Just_to_be_the_man_who_walked_a_thousand_miles_To_fall_down_at_your_door();
        requestOpModeStop();
    }
}
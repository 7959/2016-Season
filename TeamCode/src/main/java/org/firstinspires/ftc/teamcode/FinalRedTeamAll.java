package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;



/**
 * Created by Robi on 2/23/2017.
 */
@Autonomous(name = "Red Hilly Shilly Lily Killy Billy Yilly")
public class FinalRedTeamAll extends functionlist{
    double timer;

    public void runOpMode(){
        Map();
        MotorSet();
        telemetry.addData("Hey you!", "BACK OFF BUCKO I AM CALIBRATING");
        telemetry.update();
        gy.calibrate();

        while(!isStopRequested() && gy.isCalibrating());

        telemetry.addData("All done", "Thanks");
        telemetry.update();
        waitForStart();
        straightTillWhite(.2, 25, gy.getIntegratedZValue());
        sleep(50);


        ///////////////Beacon 1
        if(rightLine) {
            linefindR(.1);
            sleep(500);
            linefollowR(.1, 1);
            sleep(500);
            linefollowR(.1, 1.5);

       /* if(correct1){
            If you can manage the color sensor config to test beacon change time please do it and not boot leg it like it is now
        }*/
            straighttime(90, .2, 1.5);
            sleep(500);
            checkagainR(true);

            //timer = getRuntime();
            //checkR(true, rightLine);
            straighttime(90, -.1, .4);
            stopwheels();
            if(rescancorrect){
                checkagainR(true);
            }
            if(!finalcorrect){
                sleep(4500);
                straighttime(90, .2, .3);
            }

            /*while (!tcorrect1 && opModeIsActive()) {
                while (getRuntime() - timer <= timer + 5) ;
                straighttime(90, .1, .5);
                timer = getRuntime();
                checkR(true, rightLine);
                straighttime(90, -.1, .2);
            }
            straighttime(90, -.1, .3);*/
        } else{
            linefindL(.2);
            sleep(500);
            linefollowL(.1, 2.5);
            straighttime(90, .1, .2);

       /* if(correct1){
            If you can manage the color sensor config to test beacon change time please do it and not boot leg it like it is now
        }*/
            straighttime(90, .1, 1.5);
            sleep(500);
            checkagainL(true);

            //timer = getRuntime();
            //checkR(true, rightLine);
            straighttime(90, -.1, .4);
            if(rescancorrect){
                checkagainL(true);
            }
            if(!finalcorrect){
                sleep(4500);
                straighttime(90, .1, 1);
                straighttime(90, -.1, .3);
            }
            /*while(thing) {
                checkL(true, rightLine);
                straighttime(90,.075, .2);
                sleep(100);
            }
            checkL(true, rightLine);
            straighttime(90, -.1, .2);
            if(!correct1){
                sleep(3000);
                straighttime(90, .1, .5);
            }*/

            /*while (!tcorrect1 && opModeIsActive()) {
                while (getRuntime() - timer <= timer + 5) ;
                straighttime(90, .1, .5);
                timer = getRuntime();
                checkL(true, rightLine);
                straighttime(90, -.1, .2);
            }
            straighttime(90, -.1, .3);*/
        }
        straighttime(0, .1 , 1);
////////////////////////


        tcorrect1 = null;
        correct1 = null;
        rightLine = null;
        finalcorrect = null;
        straightTillWhite(.2, 50, 0);


        ////////////Beacon 2
        if(rightLine) {
            linefindR(.1);
            sleep(500);
            linefollowR(.1, 1);
            sleep(500);
            linefollowR(.1, 1.5);

       /* if(correct1){
            If you can manage the color sensor config to test beacon change time please do it and not boot leg it like it is now
        }*/
            straighttime(90, .2, 1.5);
            sleep(500);
            checkagainR(true);

            //timer = getRuntime();
            //checkR(true, rightLine);
            straighttime(90, -.1, .2);
            stopwheels();
            if(rescancorrect){
                checkagainR(true);
            }
            if(!finalcorrect){
                sleep(4500);
                straighttime(90, .2, .3);
            }

            /*while (!tcorrect1 && opModeIsActive()) {
                while (getRuntime() - timer <= timer + 5) ;
                straighttime(90, .1, .5);
                timer = getRuntime();
                checkR(true, rightLine);
                straighttime(90, -.1, .2);
            }
            straighttime(90, -.1, .3);*/
        } else{
            linefindL(.2);
            sleep(500);
            linefollowL(.1, 2.5);
            straighttime(90, .1, .2);

       /* if(correct1){
            If you can manage the color sensor config to test beacon change time please do it and not boot leg it like it is now
        }*/
            straighttime(90, .1, 1.5);
            sleep(500);
            checkagainL(true);

            //timer = getRuntime();
            //checkR(true, rightLine);
            straighttime(90, -.1, .4);
            if(rescancorrect){
                checkagainL(true);
            }
            if(!finalcorrect){
                sleep(4500);
                straighttime(90, .1, 1);
                straighttime(90, -.1, .3);
            }
            /*while(thing) {
                checkL(true, rightLine);
                straighttime(90,.075, .2);
                sleep(100);
            }
            checkL(true, rightLine);
            straighttime(90, -.1, .2);
            if(!correct1){
                sleep(3000);
                straighttime(90, .1, .5);
            }*/

            /*while (!tcorrect1 && opModeIsActive()) {
                while (getRuntime() - timer <= timer + 5) ;
                straighttime(90, .1, .5);
                timer = getRuntime();
                checkL(true, rightLine);
                straighttime(90, -.1, .2);
            }
            straighttime(90, -.1, .3);*/
        }
        //////////////////////

        ///knockball
        straighttime(-135, 0, .5);
        straighttime(-135, .5, 3.5);



    }

}

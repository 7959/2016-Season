package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by Robi on 2/23/2017.
 */
@Disabled
@Autonomous(name = "Blue Hilly Shilly Lily Killy Billy Yilly")
public class FinalBlueTeamAll extends functionlist{
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


        ///////////////Beacon 1
        if(rightLine) {
            linefindR(.2);
            linefollowR(.2, 1);
            findcolorR(.1, -90, 3, false);

       /* if(correct1){
            If you can manage the color sensor config to test beacon change time please do it and not boot leg it like it is now
        }*/
            straighttime(-90, .1, 1.5);
            timer = getRuntime();
            checkR(false, rightLine);
            straighttime(-90, -.1, .2);

            while (!tcorrect1 && opModeIsActive()) {
                while (getRuntime() - timer <= timer + 5) ;
                straighttime(-90, .1, .5);
                timer = getRuntime();
                checkR(false, rightLine);
                straighttime(-90, -.1, .2);
            }
            straighttime(-90, -.1, .3);
        } else{
            linefindL(.2);
            linefollowL(.2, 1);
            findcolorL(.1, -90, 3, false);

       /* if(correct1){
            If you can manage the color sensor config to test beacon change time please do it and not boot leg it like it is now
        }*/
            straighttime(-90, .1, 1.5);
            timer = getRuntime();
            checkL(false, rightLine);
            straighttime(-90, -.1, .2);

            while (!tcorrect1 && opModeIsActive()) {
                while (getRuntime() - timer <= timer + 5) ;
                straighttime(-90, .1, .5);
                timer = getRuntime();
                checkL(false, rightLine);
                straighttime(-90, -.1, .2);
            }
            straighttime(-90, -.1, .3);
        }
        turnto0(.2);
////////////////////////


        tcorrect1 = null;
        correct1 = null;
        rightLine = null;
        straightTillWhite(.2, 500, 0);


        ////////////Beacon 2
        if(rightLine) {
            linefindR(.2);
            linefollowR(.2, 1);
            findcolorR(.1, -90, 3, false);

       /* if(correct1){
            If you can manage the color sensor config to test beacon change time please do it and not boot leg it like it is now
        }*/
            straighttime(-90, .1, 1.5);
            timer = getRuntime();
            checkR(false, rightLine);
            straighttime(-90, -.1, .2);

            while (!tcorrect1 && opModeIsActive()) {
                while (getRuntime() - timer <= timer + 5) ;
                straighttime(-90, .1, .5);
                timer = getRuntime();
                checkR(false, rightLine);
                straighttime(-90, -.1, .2);
            }
            straighttime(-90, -.1, .3);
        } else {
            linefindL(.2);
            linefollowL(.2, 1);
            findcolorL(.1, -90, 3, false);

       /* if(correct1){
            If you can manage the color sensor config to test beacon change time please do it and not boot leg it like it is now
        }*/
            straighttime(-90, .1, 1.5);
            timer = getRuntime();
            checkL(false, rightLine);
            straighttime(-90, -.1, .2);

            while (!tcorrect1 && opModeIsActive()) {
                while (getRuntime() - timer <= timer + 5) ;
                straighttime(-90, .1, .5);
                timer = getRuntime();
                checkL(false, rightLine);
                straighttime(-90, -.1, .2);
            }
            straighttime(-90, -.1, .3);
        }
            //////////////////////

            ///knockball
            straighttime(-135, 0, .5);
            straighttime(-135, .5, 2.5);


    }

}

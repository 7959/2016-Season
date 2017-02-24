package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Robi on 2/23/2017.
 */
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
        straightTillWhite(.2, 500, gy.getIntegratedZValue());


        ///////////////Beacon 1
        linefindR(.2);
        linefollowR(.2, 1);
        findcolor(.1, -90, 3, false);
       /* if(correct1){
            If you can manage the color sensor config to test beacon change time please do it and not boot leg it like it is now
        }*/
        straighttime(-90, .1, 1.5);
        timer = getRuntime();
        check(false);
        straighttime(-90, -.1, .2);

        while(!tcorrect1){
            while(getRuntime() - timer <= timer + 5);
            straighttime(-90 , .1, .5);
            timer = getRuntime();
            check(false);
            straighttime(-90, -.1, .2);
        }
        straighttime(-90, -.1, .3);
        turnto0(.2);
////////////////////////


        tcorrect1 = null;
        correct1 = null;
        straightTillWhite(.2, 500, 0);


        ////////////Beacon 2
        linefindR(.2);
        linefollowR(.2, 1);
        findcolor(.1, -90, 3, false);
       /* if(correct1){
            If you can manage the color sensor config to test beacon change time please do it and not boot leg it like it is now
        }*/
        straighttime(-90, .1, 1.5);
        timer = getRuntime();
        check(false);
        straighttime(-90, -.1, .2);

        while(!tcorrect1){
            while(getRuntime() - timer <= timer + 5);
            straighttime(-90 , .1, .5);
            check(false);
            timer = getRuntime();
            straighttime(-90, -.1, .2);
        }
        straighttime(-90, -.1, 1);
        //////////////////////

        ///knockball
        straighttime(225, 0, .5);
        straighttime(225, .5, 2.5);



    }

}

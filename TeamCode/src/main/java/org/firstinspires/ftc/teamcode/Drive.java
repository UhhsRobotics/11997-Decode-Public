package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.*;


import kotlin.Triple;

public class Drive  {
@Config
           public  static  class Globals{

      public static FieldLocalizer fromLocalizer(Localizer localizer) {
           return  new FieldLocalizer() {
               @Override
               public double getFieldRelativePosX() {
                   return    localizer.getPose().position.x;
               }

               @Override
               public double getFieldRelativePosY() {
                   return    localizer.getPose().position.y;
               }
           };

       }
//this is getting removed soon
public static final    Hardware hardwareNames = new Hardware.HardwareBuilder().frontLeft(new Actuator("frontL",false))
        .frontRight(new Actuator("frontR",false))
        .backLeft(new Actuator("backL",false))
        .backRight(new Actuator("backR",false)).build();


    //a lot of random pid numbers huh
         public static    double kp=0.0004; public static double ki=0;public static double kd=0.00005;
    public static IMU.Parameters ImuParams = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
}
        public static ServoGroup initServosPid(HardwareMap hardwareMap){
    PIDSettings settings = new PIDSettings(-1,1, (double) 1 /2,.05,.09,.15,0,1);
            Triple<Double,Double,Double> pid= new Triple<>(Globals.kp, Globals.ki, Globals.kd);
               AnalogFeedbackServoPIDF frontLeft = new AnalogFeedbackServoPIDF(hardwareMap.get(CRServo.class,"fl"),
                       hardwareMap.get(AnalogInput .class,"Afl"),false,pid,settings);
               AnalogFeedbackServoPIDF backLeft =new AnalogFeedbackServoPIDF(hardwareMap.get(CRServo.class,"bl"),
                       hardwareMap.get(AnalogInput .class,"Abl"),false,pid,settings);
               AnalogFeedbackServoPIDF frontRight = new AnalogFeedbackServoPIDF(hardwareMap.get(CRServo.class,"fr"),
                       hardwareMap.get(AnalogInput .class,"Afr"),false,pid,settings);
               AnalogFeedbackServoPIDF backRight = new AnalogFeedbackServoPIDF(hardwareMap.get(CRServo.class,"br"),
                       hardwareMap.get(AnalogInput .class,"Abr"),false,pid,settings);
               return new ServoGroup(frontLeft,backLeft,frontRight,backRight);

           }   public static ServoGroup initServos(HardwareMap hardwareMap){
               AnalogFeedbackServo frontLeft = new AnalogFeedbackServo(hardwareMap.get(Servo.class,"fl"),
                       hardwareMap.get(AnalogInput .class,"Afl"),false);
               AnalogFeedbackServo backLeft =new AnalogFeedbackServo(hardwareMap.get(Servo.class,"bl"),
                       hardwareMap.get(AnalogInput .class,"Abl"),false);
               AnalogFeedbackServo frontRight = new AnalogFeedbackServo(hardwareMap.get(Servo.class,"fr"),
                       hardwareMap.get(AnalogInput .class,"Afr"),false);
               AnalogFeedbackServo backRight = new AnalogFeedbackServo(hardwareMap.get(Servo.class,"br"),
                       hardwareMap.get(AnalogInput .class,"Abr"),false);
               return new ServoGroup(frontLeft,backLeft,frontRight,backRight);

           }
}

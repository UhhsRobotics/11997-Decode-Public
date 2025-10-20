package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Optional;


@Autonomous(name = "some testing", group = "Autonomous")
public class kds3a1 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        ServoGroup servos = Drive.initServosPid(hardwareMap);
        FieldLocalizer localizer1 = Drive.Globals.fromLocalizer(localizer);
        //Random pid Settings

        Swerve swerve = new Swerve(new PhysSwerve<DcMotor>(servos, hardwareMap.get(DcMotor.class, "br"), hardwareMap.get(DcMotor.class, "fl"), hardwareMap.get(DcMotor.class, "fr"), hardwareMap.get(DcMotor.class, "bl")), localizer1, new PIDSettings(-1, 1, (double) 1 / 2, .05, .09, .15, 0, 1), new PIDSettings(-1, 1, (double) 1 / 2, .05, .09, .15, 0, 1), null);

        waitForStart();

        while (opModeIsActive()) {
            swerve.curveTo(new Point(1, 2), new Point(3, 2), Optional.of(new Point(4, 2)));


        }

    }

}




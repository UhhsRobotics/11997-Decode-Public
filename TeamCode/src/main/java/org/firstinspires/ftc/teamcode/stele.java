package org.firstinspires.ftc.teamcode;

import com.outoftheboxrobotics.photoncore.Photon;
import com.outoftheboxrobotics.photoncore.hardware.motor.PhotonAdvancedDcMotor;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.List;

@Photon
@TeleOp(name = "sTelecvbjm;", group = "TeleOp")
public class stele extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Hardware hardware = Drive.Globals.hardwareNames;
        ServoGroup servos = Drive.initServosPid(hardwareMap);
        PhotonAdvancedDcMotor frontLeft = (PhotonAdvancedDcMotor) hardwareMap.get(DcMotor.class, hardware.frontLeft.name());
        PhotonAdvancedDcMotor backLeft = (PhotonAdvancedDcMotor) hardwareMap.get(DcMotor.class, hardware.backLeft.name());
        PhotonAdvancedDcMotor frontRight = (PhotonAdvancedDcMotor) hardwareMap.get(DcMotor.class, hardware.frontRight.name());
        PhotonAdvancedDcMotor backRight = (PhotonAdvancedDcMotor) hardwareMap.get(DcMotor.class, hardware.backRight.name());
        frontLeft.getMotor().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        frontRight.getMotor(). setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        backLeft.getMotor(). setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        backRight.getMotor().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setCacheTolerance(0.05);
        frontRight.setCacheTolerance(0.05);
        backLeft.setCacheTolerance(0.05);
        backRight.setCacheTolerance(0.05);
        IMU imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(Drive.Globals.ImuParams);
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        Integer prevAngle = null;
        waitForStart();
        while (opModeIsActive()) {
            float leftStickX = gamepad1.left_stick_x;
            float leftStickY = gamepad1.left_stick_y;
            double rot = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            double X = leftStickX * Math.cos(-rot) - leftStickY * Math.sin(-rot);
            double Y = leftStickX * Math.sin(-rot) + leftStickY * Math.cos(-rot);

            if (Math.abs(X) > 0.05 || Math.abs(Y) > 0.05) {
                int angle = (int) Math.toDegrees(Math.atan2(X, -Y));
                angle=angle<0?360-Math.abs(angle):angle;
                if (prevAngle != null) {
                    if (!(angle <= prevAngle + 5 && angle >= prevAngle - 5)) {
                        servos.moveEach(angle, angle, angle, angle).run();
                    }
                    prevAngle = angle;
                } else {
                    servos.moveEach(angle, angle, angle, angle).run();
                    prevAngle = angle;

                }

                X *= 1.1;
                double power = Math.abs(Math.hypot(X, Y));
                double r = gamepad1.right_stick_x;
                double max = 1;
                if (power + r > 1) {
                    max = power + r;
                    //power cant be negative
                }
                double Right = (power - r) / max;
                double Left = (power + r) / max;
                frontLeft.setPower(Left);

                frontRight.setPower(Right);
                backLeft.setPower(Left);
                backRight.setPower(Right);
            } else {
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }
        }
    }
}

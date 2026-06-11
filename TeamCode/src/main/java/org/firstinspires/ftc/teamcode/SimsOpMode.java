package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "SimsOpMode")
public class SimsOpMode extends LinearOpMode {
    FreshieDrive drive;
    ArmManual arm;

    @Override
    public void runOpMode()
    {
        drive = new FreshieDrive(hardwareMap);
        arm = new ArmManual(hardwareMap);

        waitForStart();
        while(opModeIsActive())
        {
            //drive.drive(gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x);
            drive.fieldRelativeDrive(gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x, gamepad1.a);
            //drive.tester(gamepad1.a);
            arm.movement(gamepad1.dpad_up, gamepad1.dpad_down);

        }

    }
}
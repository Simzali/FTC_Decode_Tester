package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "SimsOpMode", group = "Robot")
public class SimsOpMode extends LinearOpMode {
    SimpleDrive drive;
    //Arm arm;
    //HardwareMap hardwareMap;

    @Override
    public void runOpMode()
    {
        drive = new SimpleDrive(hardwareMap);
        //arm = new Arm(hardwareMap);
        waitForStart();
        while(opModeIsActive())
        {
            drive.fieldRelativeDrive(gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x, gamepad1.a);
            //arm.movement(gamepad1.dpad_up, gamepad1.dpad_down);

        }

    }
}

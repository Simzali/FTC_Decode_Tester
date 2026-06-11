package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ArmManual {

    DcMotor arm;

    double power = 0.5;   // tune this for arm speed

    public ArmManual(HardwareMap hardwaremap)
    {
        arm = hardwaremap.get(DcMotor.class, "arm");
        arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void movement(boolean up, boolean down)
    {
        if (up) {
            arm.setPower(power);     // clockwise
        }
        else if (down) {
            arm.setPower(-power);    // counter-clockwise
        }
        else {
            arm.setPower(0);         // stop when nothing is held
        }
    }
}
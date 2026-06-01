package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    DcMotor intake;
    Servo gate;
    DigitalChannel ballOne;
    DigitalChannel ballTwo;
    DigitalChannel ballThree;

    public Intake(HardwareMap hardwaremap)
    {
        intake = hardwaremap.get(DcMotor.class, "intake");
        gate = hardwaremap.get(Servo.class, "gate");
        ballOne = hardwaremap.digitalChannel.get("ballOne");
        ballTwo = hardwaremap.digitalChannel.get("ballTwo");
        ballThree = hardwaremap.digitalChannel.get("ballThree");
    }

    public boolean hasBalls()
    {
        return false;
    }

    public void intakeOn()
    {

    }

    public void intakeOff()
    {

    }

    public void gateUp()
    {

    }

    public void gateDown()
    {

    }




}

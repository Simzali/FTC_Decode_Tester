package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class SimpleDrive {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    //IMU imu;
    GoBildaPinpointDriver pinpoint;
    public final static double xOffset = -3.69;
    public final static double yOffset = -1.879;

    public SimpleDrive(HardwareMap hardwaremap) {
        frontLeft = hardwaremap.get(DcMotor.class, "FL");
        frontRight = hardwaremap.get(DcMotor.class, "FR");
        backLeft = hardwaremap.get(DcMotor.class, "BL");
        backRight = hardwaremap.get(DcMotor.class, "BR");
        //imu = hardwaremap.get(IMU.class, "imu");
        pinpoint = hardwaremap.get(GoBildaPinpointDriver.class, "pinpoint");


        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);   // Reverse this
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);  // And this
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);    // Keep
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);   // Keep

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection =
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection =
                RevHubOrientationOnRobot.UsbFacingDirection.UP;

        RevHubOrientationOnRobot orientationOnRobot = new
                RevHubOrientationOnRobot(logoDirection, usbDirection);
        //imu.initialize(new IMU.Parameters(orientationOnRobot));

        pinpoint.setOffsets(xOffset, yOffset, DistanceUnit.INCH);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);
        pinpoint.resetPosAndIMU();   // recalibrates IMU + zeroes pose; keep robot still
    }

    public void fieldRelativeDrive(double forward, double right, double rotate, boolean resetYaw)
    {
        if (resetYaw) {
            //imu.resetYaw();
            pinpoint.resetPosAndIMU();
        }
        pinpoint.update();

        double theta = Math.atan2(forward, right);
        double r = Math.hypot(right, forward);

        theta = AngleUnit.normalizeRadians(theta + pinpoint.getHeading(AngleUnit.RADIANS));
        double newForward = r * Math.sin(theta);
        double newRight = r * Math.cos(theta);

        double frontLeftPower = newForward - newRight + rotate;
        double frontRightPower = newForward + newRight - rotate;
        double backRightPower = newForward - newRight - rotate;
        double backLeftPower = newForward + newRight + rotate;

        double maxPower = 0.5;
        double maxSpeed = 0.25;

        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));

        frontLeft.setPower(maxSpeed * (frontLeftPower/maxPower));
        frontRight.setPower(maxSpeed * frontRightPower/maxPower);
        backLeft.setPower(maxSpeed * backLeftPower/maxPower);
        backRight.setPower(maxSpeed * backRightPower/maxPower);
    }

    public void drive(double forward, double right, double rotate)
    {
        double frontLeftPower = forward - right - rotate;
        double frontRightPower = forward + right + rotate;
        double backRightPower = forward - right + rotate;
        double backLeftPower = forward + right - rotate;

        double maxPower = 1.0;
        double maxSpeed = 0.5;

        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));

        frontLeft.setPower(maxSpeed * (frontLeftPower/maxPower));
        frontRight.setPower(maxSpeed * frontRightPower/maxPower);
        backLeft.setPower(maxSpeed * backLeftPower/maxPower);
        backRight.setPower(maxSpeed * backRightPower/maxPower);
    }

    public void tester(boolean on)
    {
        if(on)
        {
            frontLeft.setPower(0.5);
        }
        else
        {
            frontLeft.setPower(0);
        }

    }



    }

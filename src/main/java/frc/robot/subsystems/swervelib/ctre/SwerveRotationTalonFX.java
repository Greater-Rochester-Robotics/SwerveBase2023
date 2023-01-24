// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.swervelib.ctre;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;

import frc.robot.subsystems.swervelib.interfaces.SwerveRotationMotor;

/** Add your docs here. */
public class SwerveRotationTalonFX implements SwerveRotationMotor {
    private TalonFX rotationMotor;
    
    public SwerveRotationTalonFX(int rotationMotorID) {
        this(rotationMotorID, new TalonFXConfiguration());
    }

    public SwerveRotationTalonFX(int rotationMotorID, TalonFXConfiguration config) {
        this(rotationMotorID, config, 0.0);
    }

    public SwerveRotationTalonFX(int rotationMotorID, TalonFXConfiguration config, double encToRadConvFactor) {
        rotationMotor = new TalonFX(rotationMotorID);
        rotationMotor.configAllSettings(config);
        rotationMotor.configSelectedFeedbackCoefficient(encToRadConvFactor);
    }

    @Override
    public void setRotationMotorPIDF(double P, double I, double D, double F) {
        rotationMotor.config_kP(0, P);
        rotationMotor.config_kD(0, D);
        rotationMotor.config_kI(0, I);
        rotationMotor.config_kF(0, F);
    }

    @Override
    public double getRelEncCount() {
        return rotationMotor.getSelectedSensorPosition();
    }

    @Override
    public double getRelEncSpeed() {
        return rotationMotor.getSelectedSensorVelocity();
    }

    @Override
    public void driveRotateMotor(double dutyCycle) {
        rotationMotor.set(TalonFXControlMode.PercentOutput, dutyCycle);
    }

    @Override
    public void setRotationMotorPosition(double output) {
        rotationMotor.set(TalonFXControlMode.Position, output);
    }

    @Override
    public void stopRotation() {
        rotationMotor.set(TalonFXControlMode.PercentOutput, 0.0);
    }
    
}

package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class TurnLeft90DegreesCommand extends BaseCommand {
	
	DriveSubsystem drive;
	double position;
	double initial;
	double goal;
	double error;
	boolean done;
	
	@Inject
	public TurnLeft90DegreesCommand(DriveSubsystem driveSubsystem) {
		this.drive = driveSubsystem;
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		initial = drive.gyro.getYaw();
		goal = initial + 90;
		if (goal > 180) {
			goal -= 360;
		}
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		position = drive.gyro.getYaw();
		double oldError = error;
		error = Math.abs(goal - position);
		
		double power = .055 * error - 0.5 * (oldError - error);
		
		drive.tankDrive(-power, power);
		
		oldError = error;
		
		if ((error > -.1 && error < .1) /*&& (power < 0.1 && power > -0.1)*/) {
			done = true;
		}
	}

	@Override
	public boolean isFinished() {
		return done;
	}

}

package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class DriveToPositionCommand extends BaseCommand {

	DriveSubsystem drive;
	double setPosition;
	double myInitial;
	double midPoint = setPosition/2;
	double myPosition;
	
	@Inject
	public DriveToPositionCommand(DriveSubsystem driveSubsystem) {
		this.drive = driveSubsystem;
	}
	
	public void setTargetPosition(double position) {
		// This method will be called by the test, and will give you a goal distance.
		// You'll need to remember this target position and use it in your calculations.
		setPosition = position;
	}
	
	@Override
	public void initialize() {
		// If you have some one-time setup, do it here.
		myInitial = drive.distanceSensor.getDistance();
	}

	@Override
	public void execute() {
		// Here you'll need to figure out a technique that:
		// - Gets the robot to move to the target position 
		// - Hint: use drive.distanceSensor.get() to find out where you are
		// - Gets the robot stop (or at least be moving really really slowly) at the target position
		
		// How you do this is up to you. If you get stuck, ask a mentor or student for some hints!
		
		myPosition = drive.distanceSensor.getDistance();
		
		if (myPosition < setPosition) {
			drive.tankDrive(1, 1);
		} else if (myPosition > setPosition) {
			drive.tankDrive(-1, -1);
		} else {
			drive.tankDrive(0, 0);
		}
	}
	
	@Override
	public boolean isFinished() {
		// Modify this to return true once you have met your goal, 
		// and you're moving fairly slowly (ideally stopped)
		return false;
	}

}

package org.usfirst.frc.team3335.robot.commands.autonomous;

import org.usfirst.frc.team3335.robot.commands.Delay;
import org.usfirst.frc.team3335.robot.commands.BallShiftLow;
import org.usfirst.frc.team3335.robot.commands.GateControl;
import org.usfirst.frc.team3335.robot.commands.GearControl;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlaceDropGearVisionTurnPID3 extends CommandGroup {
	public AutoPlaceDropGearVisionTurnPID3(double distInitial, double turnAngle, double distPost, double distBack,
			double speed) {
		double scalar = 0.5; // drive speed scalar

		//addSequential(new Delay(0));
		addSequential(new BallShiftLow());
		//addSequential(new AutoDriveToPeg(distInitial, speed));
		if (Math.abs(distInitial) != 0) {
			//addSequential(new AutoDriveToPeg(distInitial, speed, true, 24, scalar*speed));
			addSequential(new AutoDriveToPeg(distInitial, 0.7, true, 24, 0.25));
		}
		if (turnAngle != 0) {
			//addSequential(new AutoTurnToPegSimple(turnAngle));
			addSequential(new AutoTurnToPegSimple(turnAngle, 0.3));
			//addSequential(new AutoTurnToPegEncoders(turnAngle, 0.5));
			//addSequential(new Delay(500));
		}
		addSequential(new Delay(500));
		//addSequential(new Delay(750));

		// Turn using vision
		//addSequential(new AutoVisionSimple(0.4, 0.3));
		//addSequential(new AutoTurnByVisionSimple());
		//addSequential(new AutoDriveToPeg(distPost, speed, true, 24, scalar*speed));
		addSequential(new AutoTurnToPeg3());
		//addSequential(new AutoDriveToPeg(distPost));

		// Open gate, pause, backup 12 in, close gate, move forward 10 in, then backup distFinal
		addSequential(new GearControl(false)); // open
		addSequential(new Delay(250));
		addSequential(new AutoDriveToPeg(distBack, scalar*speed));
		addSequential(new GearControl(true)); // close
		/*
		addSequential(new AutoDriveToPeg(10, scalar*speed));
		//addSequential(new AutoDelay(500));
		addSequential(new AutoDriveToPeg(distBack, scalar*speed));
		*/
	}
}

package org.usfirst.frc.team5941.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.TalonSRX;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Servo;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String middleAuto = "Middle";
    final String leftAuto = "Left";
    final String rightAuto = "Right";
    final String fuelAuto = "Fuel";
    String autoSelected;
    SendableChooser chooser;


	VictorSP right = new VictorSP(0);  //Right Side Drive
	VictorSP left = new VictorSP(1);   //Left Side Drive
	VictorSP winchA = new VictorSP(2);  // PWM 2 - Winch Motor Controller
	VictorSP winchB = new VictorSP(3); // PWM 3 - Winch Motor Controller
	VictorSP intake = new VictorSP(4); // PWM 4 - Intake
	TalonSRX shooter = new TalonSRX(5); // PWM 5 - Shooter	
	Servo ballStop = new Servo(6); //PWM 6 - Ball Stop
	
	/*
	//TESTING
	TalonSRX testStuff = new TalonSRX(9); // PWM 9 - Test/Talon
	VictorSP practiceLeftA = new VictorSP(5); //practice left drive motor A
	VictorSP practiceLeftB = new VictorSP(6); //practice left drive motor B
	VictorSP practiceRightA = new VictorSP(7); //practice right drive motor A
	VictorSP practiceRightB = new VictorSP(8); //practice right drive motor B
	int testMotor = 0;
	
	//PWM lineSensor = new PWM(4);
	//END TEST STUFF
	*/
	
	
    XboxController xbox = new XboxController(0); 

	int climbing = 0;
	boolean climbingReverseEnabled = false;
	
	double driverForwardSpeed = 0.5;
	double driverTurnSpeed = 0.2;
	
	boolean goodDriver = false;
	
	boolean runIntake = true;
	boolean shooterReady = false;
	boolean charging = false;
	
	boolean runAuto = true;
	Timer t = new Timer();
    
	
	
	/**
	 * This function is for 1 run of autonomous
	 */
	public void autoCode(String selection){
		
		//start timer
		t.start();
		
		if (selection.equals("Middle")){
			
			/**
			 * Currently (for testing):
			 * -waits 1 second
			 * -moves at 0.33
			 * -stop motors
			 */
			
			while (t.get() < 1.0){
				left.set(0);
	        	right.set(0);
	    		//practiceLeftA.set(0);
	    		//practiceLeftB.set(0);
	    		//practiceRightA.set(0);
	    		//practiceRightB.set(0);
			}
			while (t.get() < 2.8){ //Backward for 1.5 seconds (for gear delivery) 
				/*
				 * for 0.5 speed (0.46 right), 1.75 sec
				 * for 0.35 speed (0.33 right), 2.5 sec
				 */
	        	left.set(-0.35);
	        	right.set(0.33);
	    		//practiceLeftA.set(0.35); 
	    		//practiceLeftB.set(0.35);
	    		//practiceRightA.set(-0.33); 
	    		//practiceRightB.set(-0.33);
			}
			left.set(0);
        	right.set(0);
    		//practiceLeftA.set(0);
    		//practiceLeftB.set(0);
    		//practiceRightA.set(0);
    		//practiceRightB.set(0);
		} else if (selection.equals("Left")){
			//60 degree turns
			//TEST CODE for Left Turn, not Left Side Start
			/**
			 * Currently (for testing):
			 * -waits 1 second
			 * -spins to the left
			 * -stop motors
			 */
			
			while (t.get() < 1.0){
				left.set(0);
	        	right.set(0);
	    		//practiceLeftA.set(0);
	    		//practiceLeftB.set(0);
	    		//practiceRightA.set(0);
	    		//practiceRightB.set(0);
			}
			while (t.get() < 1.25){ //Spin counter clockwise at 0.35/0.33 speed for 0.25 seconds
	        	left.set(-0.35);
	        	right.set(-0.33);
	    		//practiceLeftA.set(-0.35); //Since Left is Negative=Forward, Positive is Reverse
	    		//practiceLeftB.set(-0.35);
	    		//practiceRightA.set(-0.33); //Since Right is Positive=Forward, Negative is Reverse
	    		//practiceRightB.set(-0.33);
			}
			left.set(0);
        	right.set(0);
    		//practiceLeftA.set(0);
    		//practiceLeftB.set(0);
    		//practiceRightA.set(0);
    		//practiceRightB.set(0);
		} else if (selection.equals("Right")){
			while(t.get() < 1.0){
				left.set(0);
				right.set(0);
			}
			while(t.get() < 2.9){
				left.set(-0.34);
				right.set(0.33);
			}
			while(t.get() < 3.0){
				left.set(0);
				right.set(0);
			}
			while(t.get() < 3.3){
				left.set(-0.35);
				right.set(-0.33);
			}
			while(t.get() < 3.5){
				left.set(0);
				right.set(0);
			}
			while(t.get() < 4.25){
				left.set(-0.35);
				right.set(0.33);
			}
			left.set(0);
			right.set(0);
			
			/*
			 * turn a bit more, right side too fast on straight, short distance too short
			 */
			
			
			
		} else if (selection.equals("Fuel")){
			//getAlliance stuff
		} else {
			//do nothing because for some reason it broke
		}
		
		t.stop();
		t.reset();
	}
	
	
	
	
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", middleAuto);
        chooser.addObject("Left", leftAuto);
        chooser.addObject("Right", rightAuto);
        chooser.addObject("Fuel", fuelAuto);
        SmartDashboard.putData("Auto choices", chooser);
        CameraServer.getInstance().startAutomaticCapture(0);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case middleAuto:
    		if (runAuto){
    			autoCode("Middle");
    			runAuto = false;
    		}
            break;
    	case leftAuto:
    		if (runAuto){
    			autoCode("Left");
    			runAuto = false;
    		}
            break;
    	case rightAuto:
    		if (runAuto){
    			autoCode("Right");
    			runAuto = false;
    		}
            break;
    	case fuelAuto:
    		if (runAuto){
    			autoCode("Fuel");
    			runAuto = false;
    		}
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
		
    	/*
    	 * if (runIntake)
    	 *     intake.set(0.3);
    	 * else
    	 *     intake.set(0);
    	 */
    	/*
    	if (lineSensor.getRaw() > 650)
    		testStuff.set(0.5);
    	else
    		testStuff.set(0);
    	*/
		//Driving
    	double rightAxis = xbox.getRawAxis(5), leftAxis = -xbox.getRawAxis(1); 
		
    	
		//SWITCHING DRIVER SPEEDS
		if(xbox.getRawButton(7) && xbox.getRawButton(10)){ //Back Button + Right Stick Click
						
			goodDriver = !goodDriver;
					
		}
		
    	
    	if (goodDriver){
    		
    		driverForwardSpeed = 0.75;
			driverTurnSpeed = 0.33;
			
    	} else {
    		
    		driverForwardSpeed = 0.5;
			driverTurnSpeed = 0.2;
			
    	}
    	
    	
		//If turning motors at 33% speed, else at 75% speed
    	if((rightAxis > 0 && leftAxis > 0) || (rightAxis < 0 && leftAxis < 0)){
    		
			right.set(rightAxis*driverTurnSpeed);
    		left.set(leftAxis*driverTurnSpeed);
			//practiceLeftA.set(leftAxis*driverTurnSpeed);
			//practiceLeftB.set(leftAxis*driverTurnSpeed);
			//practiceRightA.set(rightAxis*driverTurnSpeed);
			//practiceRightB.set(rightAxis*driverTurnSpeed);
    		
    	} else {
    		
			right.set(rightAxis*driverForwardSpeed);
        	left.set(leftAxis*driverForwardSpeed);
    		//practiceLeftA.set(leftAxis*driverForwardSpeed);
    		//practiceLeftB.set(leftAxis*driverForwardSpeed);
    		//practiceRightA.set(rightAxis*driverForwardSpeed);
    		//practiceRightB.set(rightAxis*driverForwardSpeed);
    		
		}
		
    	

    	//Weekend 1
    	
    	//Climbing
    	
    	if (!climbingReverseEnabled && xbox.getRawButton(8) && xbox.getRawButton(5) && xbox.getRawButton(6)) { //if start + left bumper + right bumper, unlock winch reverse
    		climbingReverseEnabled = true; 
    	} //if start + left bumper + right bumper, unlock winch reverse
    	
    	if (climbingReverseEnabled && (xbox.getRawAxis(2) > 0.05)){ //if winch can reverse and left trigger, reverse at speed of press
    		winchA.set(-(xbox.getRawAxis(2))*0.1);
    		winchB.set(-(xbox.getRawAxis(2))*0.1);
    	} else if (xbox.getRawAxis(3) > 0.05) { //else if right trigger, climb (forward) at speed of press
    		winchA.set(xbox.getRawAxis(3)*0.5);
    		winchB.set(xbox.getRawAxis(3)*0.5);
    	} else { //else stop
    		winchA.set(0);
    		winchB.set(0);
    	}
		
    	
    	//Intake
    	if (xbox.getRawButton(5)) //Left Bumper (?)
    		intake.set(0.3);
    	else
    		intake.set(0);
    	
    	
    	
    	//Shooter
    	if(xbox.getRawButton(6) && !charging) {//Right Bumper (?)
    		if(!shooterReady){
    			charging = true;
    			t.reset();
    			shooter.set(0.75);
    			t.start();
    		} else if (shooterReady){
    			ballStop.setAngle(0); //Lower servo to stop balls
    			shooter.set(0);
    			shooterReady = false;
    		}
    	}
    	
    	if(!shooterReady && t.get() > 1.5){
    		shooterReady = true;
    		t.stop();
    		t.reset();
    	}
    	
    	if(shooterReady){
    		ballStop.setAngle(90); //Raise servo to let balls into shooter
    		charging = false;
    	}
    	
    	
    	
    	
    	
    	
    	
    	
		/* OLD CODE
		//Climbing
		if(xbox.getRawButton(8)){ //Start button
			
			if (climbing == 2){
				
				climbing = 0;
				
			} else {
				
				climbing++;
				
			}
			
		}
		
		
		if(climbing != 0 && xbox.getRawButton(5)){ //Left Bumper
			
			if (climbing == 1){
				
				winch.set(1);
				
				if (testMotor == 0)
					testStuff.set(1);
			
			} else if (climbing == 2){
				
				winch.set(-1);
				
				if (testMotor == 0)
					testStuff.set(-1);
			
			}
		
		} else {
		
			winch.set(0);
			
			if (testMotor == 0)
				testStuff.set(0);
		
		}
		
		
		
		//Ball Intake
		if(xbox.getRawAxis(2) > 0.2){ //Left Trigger
			
			intake.set(0.3);
			
			if (testMotor == 1)
				testStuff.set(0.3);
		
		} else {
		
			intake.set(0);
			
			if (testMotor == 1)
				testStuff.set(0);
		
		}
		
		
		
		//Shooter
		if(xbox.getRawAxis(3) > 0.2){ //Right Trigger
		
			//shooter.set(0.7);
			
			if (testMotor == 2)
				testStuff.set(0.7);
		
		} else {
		
			//shooter.set(0);
			
			if (testMotor == 2)
				testStuff.set(0);
		
		}
		*/
    	
    	/*
		//Test Stuff
		if(xbox.getRawButton(3)){ // X Button
			
			if (testMotor > 1){
				
				testMotor = 0;
				
			} else {
				
				testMotor++;
				
			}
		
		}
		*/
		
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

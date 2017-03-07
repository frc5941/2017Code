
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
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;


	VictorSP right = new VictorSP(0);  //Right Side Drive
	VictorSP left = new VictorSP(1);   //Left Side Drive
	VictorSP winch = new VictorSP(2);  // PWM 2 - Winch Motor Controller
	VictorSP intake = new VictorSP(3); // PWM 3 - Ball Intake Motor Controller
	VictorSP shooter = new VictorSP(4); // PWM 4 - Shooter
	// PWM 5 - Right Line Sensor	
	// PWM 6 - Left Line Sensor
	// PWM 7 - Shooter Servo
	// PWM 8 - Door Servo
	
	
	//TESTING
	TalonSRX testStuff = new TalonSRX(9); // PWM 9 - Test/Talon
	VictorSP practiceLeftA = new VictorSP(5); //practice left drive motor A
	VictorSP practiceLeftB = new VictorSP(6); //practice left drive motor B
	VictorSP practiceRightA = new VictorSP(7); //practice right drive motor A
	VictorSP practiceRightB = new VictorSP(8); //practice right drive motor B
	int testMotor = 0;
	
    XboxController xbox = new XboxController(0); 

	int climbing = 0;
	
	double driverForwardSpeed = 0.5;
	double driverTurnSpeed = 0.2;
	
	boolean goodDriver = false;
	
	boolean runIntake = true;
	
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
			 * -moves at half speed forward for 3 seconds
			 * -stop motors
			 */
			
			while (t.get() < 1.0){
				//right.set(0);
	        	//left.set(0);
	    		practiceLeftA.set(0);
	    		practiceLeftB.set(0);
	    		practiceRightA.set(0);
	    		practiceRightB.set(0);
			}
			while (t.get() < 4.0){ //Reverse for 3 seconds (for gear delivery)
				//right.set(-0.5);
	        	//left.set(0.5);
	    		practiceLeftA.set(0.5); //Since Left is Negative=Forward, Positive is Reverse
	    		practiceLeftB.set(0.5);
	    		practiceRightA.set(-0.5); //Since Right is Positive=Forward, Negative is Reverse
	    		practiceRightB.set(-0.5);
			}
			//right.set(0);
        	//left.set(0);
    		practiceLeftA.set(0);
    		practiceLeftB.set(0);
    		practiceRightA.set(0);
    		practiceRightB.set(0);
		} else if (selection.equals("Left Turn")){
			//60 degree turns
			//TEST CODE
			/**
			 * Currently (for testing):
			 * -waits 1 second
			 * -spins to the left
			 * -stop motors
			 */
			
			while (t.get() < 1.0){
				//right.set(0);
	        	//left.set(0);
	    		practiceLeftA.set(0);
	    		practiceLeftB.set(0);
	    		practiceRightA.set(0);
	    		practiceRightB.set(0);
			}
			while (t.get() < 2.0){ //Spin at half speed for 1 second
				//right.set(-0.5);
	        	//left.set(-0.5);
	    		practiceLeftA.set(-0.5); //Since Left is Negative=Forward, Positive is Reverse
	    		practiceLeftB.set(-0.5);
	    		practiceRightA.set(-0.5); //Since Right is Positive=Forward, Negative is Reverse
	    		practiceRightB.set(-0.5);
			}
			//right.set(0);
        	//left.set(0);
    		practiceLeftA.set(0);
    		practiceLeftB.set(0);
    		practiceRightA.set(0);
    		practiceRightB.set(0);
		} else if (selection.equals("Right Gear")){
			
		} else if (selection.equals("Fuel")){
			//getAlliance stuff
		} else {
			//do nothing because for some reason it broke
		}
		
		t.stop();
	}
	
	
	
	
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        //CameraServer.getInstance().startAutomaticCapture(0);
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
    	case customAuto:
    		if (runAuto){
    			autoCode("Middle");
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
    		
			//right.set(rightAxis*driverTurnSpeed);
    		//left.set(leftAxis*driverTurnSpeed);
			practiceLeftA.set(leftAxis*driverTurnSpeed);
			practiceLeftB.set(leftAxis*driverTurnSpeed);
			practiceRightA.set(rightAxis*driverTurnSpeed);
			practiceRightB.set(rightAxis*driverTurnSpeed);
    		
    	} else {
    		
			//right.set(rightAxis*driverForwardSpeed);
        	//left.set(leftAxis*driverForwardSpeed);
    		practiceLeftA.set(leftAxis*driverForwardSpeed);
    		practiceLeftB.set(leftAxis*driverForwardSpeed);
    		practiceRightA.set(rightAxis*driverForwardSpeed);
    		practiceRightB.set(rightAxis*driverForwardSpeed);
    		
		}
		
    	
		
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
		
			shooter.set(0.7);
			
			if (testMotor == 2)
				testStuff.set(0.7);
		
		} else {
		
			shooter.set(0);
			
			if (testMotor == 2)
				testStuff.set(0);
		
		}
		
		
		
		//Test Stuff
		if(xbox.getRawButton(3)){ // X Button
			
			if (testMotor > 1){
				
				testMotor = 0;
				
			} else {
				
				testMotor++;
				
			}
		
		}
		
		
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

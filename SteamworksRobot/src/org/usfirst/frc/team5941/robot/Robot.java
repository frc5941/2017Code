
package org.usfirst.frc.team5941.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
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
<<<<<<< HEAD
	VictorSP right = new VictorSP(0);
    VictorSP left = new VictorSP(1);
    XboxController xbox = new XboxController(0);
    
    
    
    
=======
	
	
	VictorSP right = new VictorSP(0);  //Right Side Drive
    VictorSP left = new VictorSP(1);   //Left Side Drive
	// PWM 2 - Winch Motor Controller
	// PWM 3 - Ball Intake Motor Controller
	// PWM 4 - Right Line Sensor
	// PWM 5 - Left Line Sensor
	
    XboxController xbox = new XboxController(0); 
>>>>>>> origin/master
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
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
    	case customAuto:
        //Put custom auto code here   
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
		
		//Driving
    	double rightAxis = xbox.getRawAxis(5), leftAxis = -xbox.getRawAxis(1); 
		//If turning motors at 33% speed, else at 75% speed
    	if((rightAxis > 0 && leftAxis > 0) || (rightAxis < 0 && leftAxis < 0)){
    		right.set(rightAxis*0.2); //change to 0.33
    		left.set(leftAxis*0.2);
    	} else {
    		right.set(rightAxis*0.5); //change to 0.75
        	left.set(leftAxis*0.5);
    	}
		
		//Climbing
			//Left Bumper (Button 4)
		
		//Ball Intake
			//Left Trigger (Axis 2)
		
		
		//Shooter?
			//Aim
				//Test line sensors, positive response = rumble
				//Right Bumper (Button 5)
			//Firing
				//Right Trigger (Axis 3)
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

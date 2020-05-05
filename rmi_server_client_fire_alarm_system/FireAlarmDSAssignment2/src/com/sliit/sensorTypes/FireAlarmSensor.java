package com.sliit.sensorTypes;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Scanner;

import com.sliit.monitorControllers.SensorObject;

//Class where after validation is used to add the new sensors to the monitor
public class FireAlarmSensor  {
    
    private static FireAlarmSensor fSensor;
    private SmokeLevel smkeLvel;
    private CO2Level co2Lvel;
    
    private SensorObject readin;
    private BufferedReader br;
    private PrintWriter pW;
    private Socket socket;
	private String uname, pwd, userIteractInput;
	private Scanner userInput;
	private int floorNo, roomNo;
	private long breakTheReading;

    public FireAlarmSensor() {
    	userInput = new Scanner(System.in);
    	co2Lvel = new CO2Level();
        readin = new SensorObject();
        smkeLvel = new SmokeLevel();
    }
    
    public void run() throws IOException, InterruptedException {

        
        socket = new Socket("127.0.0.1", 9001);
        
        br = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        pW = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
        	 userIteractInput = br.readLine();
        
        		
                 if (userIteractInput.startsWith("GETNAME") && userIteractInput != null ) {
                 	pW.println(getUsername());
                 } else if (userIteractInput.startsWith("GETPASSWORD") && userIteractInput != null){
                 	pW.println(getPassword());	
                 } else if (userIteractInput.startsWith("GETFLOOR") && userIteractInput != null){
                 	pW.println(getFloor());
                 }
                   else if(userIteractInput.startsWith("GETROOM") && userIteractInput != null) {
                	   
                	   pW.println(getRoom());
              		
              	 }else if (userIteractInput.startsWith("REGISTEREDSENSOR")&& userIteractInput != null){
                 	generateSensorReading();
                 }
			
           
        }
    }
    
    private String getUsername() {
    	try {
    		System.out.print("Enter your sensor username : ");
            uname = userInput.nextLine();
            return uname;
		} catch (Exception e) {
			 
			 return "Invalid Username ";
		}
        
    }
    
    private String getPassword(){  
    	try {
        System.out.print("Enter your sensor password: ");
        pwd = userInput.nextLine();
        return pwd;
    	} catch (Exception e) {
			 
			 return "Invalid Username ";
		}
    	
    } 
    
    private int getFloor() {
        System.out.print("Enter Floor number between(1-10): ");
        floorNo = Integer.parseInt(userInput.nextLine());
        if(floorNo < 1 || floorNo > 10) {
        	System.out.println("Please enter a valid floor number between 1 and 10");
        	 System.out.print("Please Enter Floor Number: ");
        	 floorNo = Integer.parseInt(userInput.nextLine());
        }
        return floorNo;
    }
    
    private int getRoom() {
        System.out.print("Enter Room number between(1-20): ");
        roomNo = Integer.parseInt(userInput.nextLine());
        if(roomNo < 1 || roomNo > 20) {
        	System.out.println("Please enter a valid room number between 1 and 15");
        	 System.out.print("Please Enter Room Number: ");
        	 roomNo = Integer.parseInt(userInput.nextLine());
        }
        return roomNo;
    }

    private void generateSensorReading() throws InterruptedException, IOException {
        for (; ;) {
        	
			readin.setSmokeLevel((int) smkeLvel.generateNewReading());
			readin.setCO2Level(co2Lvel.generateNewReading());
			
			System.out.println(readin);
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try (ObjectOutputStream obj = new ObjectOutputStream( os )) {
				obj.writeObject( readin );	
				
			    pW.println(Base64.getEncoder().encodeToString(os.toByteArray()));
			} catch (IOException exe) {
			    System.err.println(exe.getMessage());
			  }
			
			 breakTheReading = 30000;
             Thread.sleep(breakTheReading);
		}
    }
    
    public static void main(String[] args) throws Exception {
        fSensor = new FireAlarmSensor();
        fSensor.run();
    }
}

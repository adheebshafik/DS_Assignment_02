package com.sliit.monitorControllers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.sliit.mainServers.FireSensorServer;
import com.sliit.sensorTypes.Sensor;

//Sensors related activities implementation
public class SensorController extends Thread implements Runnable {
    
	private SensorObject newReading;
	private String inputtedSensor;
    private final Socket socket;
    private final Sensor sens;
  
    private static HashSet<String> allSensorTypes = new HashSet<String>();

    private static Map<String,Sensor> theMappedSensors = new HashMap<>();
    
    private FireSensorServer fSensorServer;
	private String password, input;
	private int floorNo, roomNo;

    public SensorController(Socket socket, FireSensorServer fSensorServer) {
       this.socket = socket;
       this.sens = new Sensor();
       this.fSensorServer = fSensorServer;
       this.newReading = new SensorObject();
   }

   @Override
   public void run() {
	 
	   BufferedReader br;
	   PrintWriter printWriter;
       try {

    	   br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	   printWriter = new PrintWriter(socket.getOutputStream(), true);

           
    	   while (true) {
			   printWriter.println("GETNAME");
        	   inputtedSensor = br.readLine();
               if (inputtedSensor == null) {
            	   System.out.println("Enter a valid name");
                   return;
               }
               synchronized (allSensorTypes) {
                   if (!allSensorTypes.contains(inputtedSensor)) {
                	   allSensorTypes.add(inputtedSensor);
                       break;
                   }
               }
           }
    	   
    	   while (true){
        	   printWriter.println("GETPASSWORD");
               password = br.readLine();
               if (password == null) {
            	   System.out.println("Enter a valid password");
                   return;
               }
               if(validateSensorUser(inputtedSensor, password)){
                   break;
               } 
               else {
            	   System.out.println("Enter a valid password");
                   return;
               }
            }
           
    	   while (true) {
        	   printWriter.println("GETFLOOR");
               floorNo = Integer.parseInt(br.readLine());
               if (floorNo == 0) {
            	   System.out.println("Enter a valid Floor no");
                   return;
               }
               sens.setFloorNo(floorNo);
               break;
            }
    	   
    	   while (true) {
        	   printWriter.println("GETROOM");
        	   roomNo = Integer.parseInt(br.readLine());
               if (roomNo == 0) {
            	   System.out.println("Enter a valid Room no");
                   return;
               }
               sens.setRoomNo(roomNo);
               break;
            }
    	   
           printWriter.println("REGISTEREDSENSOR");
           
           fSensorServer.addNewSensor(sens);
           
           theMappedSensors.put(inputtedSensor, sens);
           
           while (true) {
                input = br.readLine();
                if (input == null) {
                    return;
                }
                try(ObjectInputStream objc = new ObjectInputStream
                		(new ByteArrayInputStream(Base64.getDecoder().decode(input)))){  
                	newReading = (SensorObject) objc.readObject();  
                    sens.setLatestReading(newReading);
                    theMappedSensors.entrySet().forEach(y->System.out.println(y.getValue())); 
                    
                    authenticateAlertConditions(sens);
                } catch (IOException | ClassNotFoundException e) {  
                    System.err.println(e.getMessage());
                }  
           } 
       } catch (IOException e) {
    	   System.err.println(e.getMessage());
       } finally {
        	fSensorServer.removeExistingSensor(sens);
           allSensorTypes.remove(inputtedSensor);
           try {
			socket.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
            
       }
   }
   
   public static boolean validateSensorUser(String uName, String pwd){
	    String identity, username, password;
	     String[] dataArray;
       try (BufferedReader br = new BufferedReader(new InputStreamReader
       		(SensorController.class.getResourceAsStream("SensorLoginsDetails.txt")))) {
           while ( null != (identity  = br.readLine()) ) { 
               dataArray = identity .split("-");
               username = dataArray[0];
               password = dataArray[1];
               if(username.equals(uName) && password.equals(pwd)){
                       return true;
               }
           }
           
           return false;
       } catch (IOException e) {
           System.err.println(e.getMessage());

       }
       return false;
   }
   
   public void authenticateAlertConditions(Sensor sensor){
        int smke = sensor.getNewReading().getSmokeLevel();
        double co2 = sensor.getNewReading().getCO2Level();
        if( co2 > 5.0 ||  smke > 5){
            System.out.println("In a critical Situation");
            fSensorServer.makeAlertToUser(sensor);
        }

   }
 
  

}


package com.sliit.mainServers;

import java.net.ServerSocket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;


import com.sliit.monitorControllers.SensorController;
import com.sliit.sensorTypes.FireAlarmSensor;

// The class where the rmi registry is created and the service is looked up
 
public class MainRemoteServer {

	
    private static final int PORT_OF_SERVER = 9001;
    
    
    private static FireSensorServer fSensorServer;
    
  
    public static void main(String[] args) throws Exception {
        System.out.println("Getting the server......");
        
        try{
        	
        	fSensorServer = new FireSensorServer();
            FireAlarmSensor fSensor = new FireAlarmSensor();
            Registry reg = LocateRegistry.createRegistry(1099);
      
            Naming.rebind("//127.0.0.1/fireSensor", fSensorServer);
            
            
            Thread.sleep(700);
            System.out.println("Server has been Connected.");
        }
        
        catch(RemoteException e){
            System.err.println(e);
        }
        
        
        try (ServerSocket ss  = new ServerSocket(PORT_OF_SERVER)) {
            while (true) {
                SensorController sensorController  = new SensorController(ss.accept(),fSensorServer);
                sensorController.start();
            }
        }
        catch(RemoteException e){
            System.err.println(e);
        }
    }
}

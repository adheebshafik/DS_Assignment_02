package com.sliit.mainServers;

import java.util.List;

import com.sliit.sensorTypes.Sensor;


// Class to handle clients which are connected to the server
public class MonitorHandler extends Thread implements Runnable {
        
        private FireSensorServer fSS;
        private List<Sensor> allSensors;
        
        
        public MonitorHandler(FireSensorServer fireSensorServer, List<Sensor> sensors) {
            this.fSS = fireSensorServer;
            this.allSensors = sensors;
        }
        
        
        @Override
        public void run(){
            
            while(true){
            	
                try {
                    Thread.sleep(56250);
                    synchronized( allSensors ){
                      	fSS.notifyListeners(allSensors);
                    }
                    System.out.println("Notification is done Successfully!");
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

package com.sliit.monitorControllers;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.rmi.Naming;
import java.rmi.NotBoundException;


import com.sliit.mainServers.FireSensorServerRemote;
import com.sliit.monitorGUI.FireMonitorGUI;
import com.sliit.monitorGUI.SensorMonitorGUI;
import com.sliit.sensorTypes.Sensor;

// Class where components to execute fire monitors are initialized.

public class MonitorApplication {
    
    private Map<Integer,SensorMonitorGUI> sensMonitors = new HashMap<>();
    private List<Integer> sensLocations = new ArrayList<>();
  
    
    public void intializeTheMonitor(){
        try {

            FireSensorServerRemote fSensServerRemote = (FireSensorServerRemote) 
            		Naming.lookup("rmi://localhost/fireSensor");

            
            FireMonitorController monitor = new FireMonitorController(fSensServerRemote);
            
            
            fSensServerRemote.addFSensorMonitor(monitor);

            
            monitor.showMonitor(monitor);
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            System.err.println(e.getMessage());
        }
    
    }
    
    public void addTheMonitor(FireMonitorGUI aMonitorGUI,List<Sensor> allSensors){
        
        allSensors.forEach(sensor ->{
            SensorMonitorGUI monitor;
            
            int floorNo = sensor.getFloorNo();
            int roomNo = sensor.getRoomNo();
            
            if(!sensMonitors.containsKey(floorNo)){
                monitor = new SensorMonitorGUI(floorNo, roomNo);
                
                sensMonitors.put(floorNo, monitor);
                
                aMonitorGUI.addNewMonitor(monitor);
                
                
                sensLocations.add(floorNo);
            }
            
            else{
                monitor = sensMonitors.get(sensor.getFloorNo());
            }
            
            
            monitor.setSmokeLevel(sensor.getNewReading().getSmokeLevel());
            monitor.setCO2Level(sensor.getNewReading().getCO2Level());
        });
        
    }
    
    public void updateTheMonitor(FireMonitorGUI amonitorGUI, Sensor sens){
        if(sensMonitors.containsKey(sens.getFloorNo())){
            SensorMonitorGUI monitor = sensMonitors.get(sens.getFloorNo());
            monitor.setSmokeLevel(sens.getNewReading().getSmokeLevel());
            monitor.setCO2Level(sens.getNewReading().getCO2Level());
        }
    }
    
}

package com.sliit.monitorControllers;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sliit.mainServers.FireSensorServerRemote;
import com.sliit.monitorGUI.FireMonitorGUI;
import com.sliit.sensorTypes.Sensor;
import com.sliit.sensorTypes.SensorListener;

// Class where all callback methods are implemented and is also the middle-tier between server & client to transfer readings.
 
public class FireMonitorController extends UnicastRemoteObject implements SensorListener {
	
	private MonitorApplication mAMonitorController;
    private FireSensorServerRemote fSRemoteServer; 
    private FireMonitorController fMonitor;
    private Sensor sensor;
    private FireMonitorGUI fMonitorGUI;
    
    
    public FireMonitorController(FireSensorServerRemote aSensor) throws RemoteException{
        this.fSRemoteServer = aSensor;
        this.mAMonitorController = new MonitorApplication();
    }
    
    
    public void showMonitor(FireMonitorController fireMon) throws RemoteException{
    	fMonitor = fireMon;
    	fMonitorGUI = new FireMonitorGUI(fireMon);
    	fMonitorGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	fMonitorGUI.setVisible(true);
    	
    	fMonitorGUI.setTheMonitorCount(fSRemoteServer.getConnectedScreensCount());
    	fMonitorGUI.setTheSensorCount(fSRemoteServer.getFSensorCount());
    	fireMon.mAMonitorController.addTheMonitor(fMonitorGUI, fireMon.fSRemoteServer.getTheSensorType());
        
    }
    
    public void updateCurrentReadings(){
        try {
        	fSRemoteServer.getNewReading(fMonitor);
        } catch (RemoteException x) {
            System.out.println(x);
        }
    }
    @Override
    public void makeAlertsOnFailures(Sensor sensor) throws RemoteException {
        JOptionPane.showMessageDialog(fMonitorGUI,"Floor "+sensor.getFloorNo()+" Sensors seem to be failing","Warning",JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void ModifyTheReading(List<Sensor> sensors, int mCount, int sCount) throws RemoteException {
    	fMonitorGUI.setTheMonitorCount(mCount);
    	fMonitorGUI.setTheSensorCount(sCount);
        
        if( !sensors.isEmpty() || sensors != null ){
            this.mAMonitorController.addTheMonitor(fMonitorGUI, sensors);
            
        }
        else{
            JOptionPane.showMessageDialog(null,"Message" ,"Restart Server",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void getNewestReadingsByFloorNo(int floorNo, int roomNo){
        try {
            Sensor sensor = fSRemoteServer.getNewReadingsByFloor(fMonitor, floorNo, roomNo);
            mAMonitorController.updateTheMonitor(fMonitorGUI, sensor);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    @Override
    public void notifyAlert(Sensor sensor) throws RemoteException {
        
    	updateCurrentReadings();
        JOptionPane.showMessageDialog(fMonitorGUI,"Floor "+sensor.getFloorNo()+ " Room " + sensor.getRoomNo() + " might be in danger. Message sent to email!","Warning",JOptionPane.WARNING_MESSAGE);
    } 
}

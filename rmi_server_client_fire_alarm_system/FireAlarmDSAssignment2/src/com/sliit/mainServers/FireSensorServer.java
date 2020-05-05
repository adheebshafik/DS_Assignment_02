package com.sliit.mainServers;

import java.util.ArrayList;
import java.util.List;
import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



import com.sliit.sensorTypes.Sensor;
import com.sliit.sensorTypes.SensorListener;

/** 
 * This is the server class which takes care of the activities regarding the sensor & monitor
 */

public class FireSensorServer extends UnicastRemoteObject implements FireSensorServerRemote{

	private static final long serialVersionUID = 1L;
    
    private List<Sensor> fSensor= new ArrayList<>();
    //FireSensorMonitors Connected with the RemoteServer
    private List<SensorListener> fMonitor = new ArrayList<>();
    
	public FireSensorServer() throws RemoteException{}  
	 
	
    @Override
    public int getConnectedScreensCount() throws RemoteException{
        return fMonitor.size();
    }   
    
    @Override
    public int getFSensorCount() throws RemoteException{
        return fSensor.size();
    }
    
    @Override
    public List<Sensor> getTheSensorType() throws RemoteException {
        return fSensor;
    }
  
    @Override
    public void getNewReading(SensorListener sensorListener) throws RemoteException  {
         	sensorListener.ModifyTheReading(fSensor, getConnectedScreensCount(), getFSensorCount());
    }
     
    @Override
    public void addFSensorMonitor(SensorListener sensorListener) throws RemoteException {
        System.out.println(sensorListener + " has been added");
        
        synchronized(fMonitor){
        	fMonitor.add(sensorListener);
        }
        
        new MonitorHandler(this,fSensor).start();
    }
     
    @Override
    public Sensor getNewReadingsByFloor(SensorListener sensorListener, int floorNo, int roomNo) throws Exception {
        for(Sensor sensor : fSensor){
            if (sensor.getFloorNo() == floorNo && sensor.getRoomNo() == roomNo){
                return sensor;
            }
        }
        
        throw new Exception();
    }
    
    public void addNewSensor(Sensor sensor) {
    	System.out.println(sensor + " has been added");
    	
        synchronized(fSensor){
            this.fSensor.add(sensor);
        }
    }
    
    public void removeExistingSensor(Sensor sensor) {
    	System.out.println(sensor + " has been removed");
    	
        synchronized(fSensor){
            this.fSensor.remove(sensor);
        }
    }
    
    public void makeAlertToUser(Sensor sensor){
        if(!fMonitor.isEmpty()){
            // Notify all the listeners 
        	fMonitor.forEach((listener) -> {
                try {
                    System.out.println(sensor + " seems to be in danger.");
                    // alerting the monitor
                    listener.notifyAlert(sensor);
                }  
                catch (ConnectException e){
                    // remove listeners if failure occurs
                	fMonitor.remove(listener);
                }catch (RemoteException r) {
                        System.err.println(r.getMessage());
                    }
            });
        }
    }
    
    public void notifyListeners(List<Sensor> sensors) {
        if(!fMonitor.isEmpty()){
            // Notify all the listeners 
        	fMonitor.forEach((listener) -> {
                try {
                	//readings get updated
                    listener.ModifyTheReading(sensors, getConnectedScreensCount(), getFSensorCount());
                } catch (ConnectException e){
                	fMonitor.remove(listener);
                    System.err.println(e);
                } catch (RemoteException e) {
                    System.err.println(e);
                }
            });
        }
    }
    
    public void makeAlertsOnFailure(Sensor sensor){
        if(!fMonitor.isEmpty()){
        	
        	fMonitor.forEach((listener) -> {
                try {
                	System.out.println(sensor + " is failing");
                	
                    listener.makeAlertsOnFailures(sensor);
                }
                catch (ConnectException e){
                	
                	
                	fMonitor.remove(listener);
                }catch (RemoteException e) {
                        System.err.println(e.getMessage());
                }
                
            });
        }
    }
}

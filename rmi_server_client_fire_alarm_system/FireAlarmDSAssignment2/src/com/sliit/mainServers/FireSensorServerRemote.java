package com.sliit.mainServers;

import java.rmi.RemoteException;
import java.util.List;

import java.rmi.Remote;



import com.sliit.sensorTypes.Sensor;
import com.sliit.sensorTypes.SensorListener;

//RMI Call back methods contained in this class

public interface FireSensorServerRemote extends Remote{
	
    public int getFSensorCount() throws RemoteException;
    
    public int getConnectedScreensCount() throws RemoteException;

    public void addFSensorMonitor(SensorListener sensorListener) throws RemoteException;
    
    
    public void getNewReading(SensorListener sensorListener) throws RemoteException ;
    
    
    public Sensor getNewReadingsByFloor(SensorListener sensorListener, int floorNo, int roomNo) throws Exception ;
 
    
    public List<Sensor> getTheSensorType() throws RemoteException;

}

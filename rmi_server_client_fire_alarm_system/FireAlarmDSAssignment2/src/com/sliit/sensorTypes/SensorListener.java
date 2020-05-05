package com.sliit.sensorTypes;

import java.rmi.RemoteException;
import java.util.List;
import java.rmi.Remote;



// This is the class which contains the interface to notify all monitors with
// the call back methods
public interface SensorListener extends Remote{
    public void ModifyTheReading(List<Sensor> sensors, int moCount, int sCount) throws RemoteException;
    
    public void notifyAlert(Sensor sensor) throws RemoteException;
    
    public void makeAlertsOnFailures(Sensor sensor) throws RemoteException;
}

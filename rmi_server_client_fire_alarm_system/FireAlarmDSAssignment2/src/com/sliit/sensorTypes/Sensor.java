package com.sliit.sensorTypes;

import java.io.Serializable;

import com.sliit.monitorControllers.SensorObject;


public class Sensor implements Serializable{
    private int floorNo;
    private int roomNo;
    private SensorObject newReading;

    public Sensor() {}
    public int getFloorNo() {
        return floorNo;
    }
    
    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }
    
    public int getRoomNo() {
    	return roomNo;
    }
    
    public void setRoomNo(int roomNo) {
    	this.roomNo = roomNo;
    }
    
    public SensorObject getNewReading() {
        return newReading;
    }
    
    public void setLatestReading(SensorObject newReading) {
        this.newReading = newReading;
    }

    @Override
    public String toString() {
        return "Sensor Reading\n" 
       + "Floor No is: " + floorNo + "Room No is: " + roomNo +
       	 "\nCurrent New Reading is: " + newReading;
    }

}

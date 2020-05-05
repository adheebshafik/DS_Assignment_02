package com.sliit.monitorControllers;

import java.io.Serializable;

//Setters and Getters of Sensor Object

public class SensorObject implements Serializable {
   
	private static final long serialVersionUID = 1L;
    private double cO2Level;
    private int smokeLevel;

    public double getCO2Level() {
        return cO2Level;
    }

    public void setCO2Level(double co2Level) {
        this.cO2Level = co2Level;
    }

    public int getSmokeLevel() {
        return smokeLevel;
    }

    public void setSmokeLevel(int smokeLevel) {
        this.smokeLevel = smokeLevel;
    }

    @Override
    public String toString() {
        return "Sensor Readings as of now \n" 
    		+ "Smoke Level is: " + smokeLevel + "\n"
    		+ "CO2 Level is: " + cO2Level;
    }   
    
}

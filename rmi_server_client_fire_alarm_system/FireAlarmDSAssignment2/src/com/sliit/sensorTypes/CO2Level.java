package com.sliit.sensorTypes;

import java.util.Random;

//Class to have the reading of the CO2 Level
public class CO2Level {

    private double cO2lvel;
    
    public CO2Level() {
    	cO2lvel = 1;
    }
    
    
    public double generateNewReading(){
    	Random random = new Random();
    	int val = random.nextInt(10);
        if (val < 0) {
        	return (cO2lvel = cO2lvel + 1);
        } 
        return (cO2lvel+val);
    }
    
}

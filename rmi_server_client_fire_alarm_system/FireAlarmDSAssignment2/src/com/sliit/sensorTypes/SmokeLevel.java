package com.sliit.sensorTypes;

import java.util.Random;

//Class to have the reading of the smoke Level
public class SmokeLevel {
    private int smkeLvel;
    
    public SmokeLevel() {
    	smkeLvel = 1;
    }
    
    public double generateNewReading(){
    	Random random = new Random();
    	int val = random.nextInt(10);
        if (val < 0) {
        	return (smkeLvel = smkeLvel + 1);
        } 
        
        return (smkeLvel+val);
    }
}

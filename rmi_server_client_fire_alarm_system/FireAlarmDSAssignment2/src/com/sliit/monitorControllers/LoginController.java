package com.sliit.monitorControllers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

//Class to validate client

public class LoginController {
	
 
    public static boolean validateUser(String uName, String pwd) throws IOException{
        String[] logins;
        String username, password, identity;
        try ( BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
        		(LoginController.class.getResourceAsStream("UserLoginDetails.txt")))) {

        	
            while ((identity = bufferedReader.readLine()) != null) { 
            	logins = identity.split("-");
                username = logins[0];
                password = logins[1];
                
                if(username.equals(uName) && password.equals(pwd)){
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
}

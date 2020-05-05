/*
 */
package com.sliit.monitorGUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.IOException;



import com.sliit.monitorControllers.LoginController;
import com.sliit.monitorControllers.MonitorApplication;

//Class holding GUI of login components
public class UserLoginMonitorGUI extends JFrame {
	
	private JButton buttontLogin;
    private JLabel userLabel, passLabel;
    private JPasswordField password;
    private JTextField username;
    private MonitorApplication monApp;
    private JPanel panel;
       
       public UserLoginMonitorGUI() {
        initComponents();
        // Relative to center
        this.setLocationRelativeTo(null);
        //initialize the application
        monApp = new MonitorApplication();
    }

  
    @SuppressWarnings("unchecked")
    private void initComponents() {
    	
    	
    	userLabel = new JLabel();
        userLabel.setText("User Name :");
        username = new JTextField();
        
        passLabel = new JLabel();
        passLabel.setText("Password :");
        password = new JPasswordField();
        
        buttontLogin = new JButton();
        
        panel = new JPanel(new GridLayout(3, 1));
        
        panel.add(userLabel);
        panel.add(username);
        panel.add(passLabel);
        panel.add(password);
        panel.add(buttontLogin);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        add(panel, BorderLayout.CENTER);
        setTitle("Login To Fire Monitor System");
        setSize(400, 120);
        setVisible(true);
    	
        buttontLogin.setText("Login");
        buttontLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
					try {
						btnLoginActionPerformed(evt);
					} catch (IOException | HeadlessException e) {
						System.out.println(e.getMessage());
					}
				 
            }
        });
    	
    }
    
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) throws HeadlessException, IOException {
        if(LoginController.validateUser(username.getText(),password.getText())){
            monApp.intializeTheMonitor();
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(
                                    null,
                                    "Authentication Failed! Please check your username and password",
                                    "Login Failed",
                                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserLoginMonitorGUI().setVisible(true);
            }
        });
    }


  

}

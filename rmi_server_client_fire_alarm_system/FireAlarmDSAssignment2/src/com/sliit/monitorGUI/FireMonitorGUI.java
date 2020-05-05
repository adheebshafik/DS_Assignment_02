package com.sliit.monitorGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import javax.swing.WindowConstants;

import com.sliit.monitorControllers.FireMonitorController;

//Class containing the gui components of the monitor
public class FireMonitorGUI extends JFrame {
    private JButton btnUpdate, btnGetReading;
    private JLabel labelMon, labelSen, labelImg, labelmonitorCount, labelsensorCount,
    labelSensor;
    private JPanel readingPanel;
    private JScrollPane scrollPanel;
	private int floorNo, roomNo;

    private static FireMonitorController fireMonitor;
   
    public FireMonitorGUI(FireMonitorController fireMonitor) {
        initComponents();
        this.fireMonitor = fireMonitor; 
        this.setLocationRelativeTo(null);
       
    }
    
    public void setTheMonitorCount(int monitorCount){
        this.labelmonitorCount.setText(Integer.toString(monitorCount));
    }
    
	public void setTheSensorCount(int sensorCount){
        this.labelsensorCount.setText(Integer.toString(sensorCount));
    }
	
    public void addNewMonitor(SensorMonitorGUI sensorMonitor){
        readingPanel.add(sensorMonitor);
        sensorMonitor.setVisible(true);
    }
    
    
    private void initComponents() {
    	labelMon = new JLabel();
    	labelSensor = new JLabel();
        scrollPanel = new JScrollPane();
        readingPanel = new JPanel();
        labelImg = new JLabel();
        btnUpdate = new JButton();
        labelSen = new JLabel();
        labelmonitorCount = new JLabel();
        labelsensorCount = new JLabel();
        btnGetReading = new JButton();
        
        
        setMaximumSize(new Dimension(1100, 600));
        setMinimumSize(new Dimension(1100, 600));
        setPreferredSize(new Dimension(1100, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(1100, 600));
        getContentPane().setLayout(null);
        setTitle("Fire Alarm System Control Panel");

        readingPanel.setLayout(new GridLayout(0, 14, 100, 150));
        readingPanel.setBackground(new Color(19, 200, 255));
        scrollPanel.setViewportView(readingPanel);

        getContentPane().add(scrollPanel);
        scrollPanel.setBounds(20, 110, 1020, 380);
        scrollPanel.setBackground(new Color(59, 89, 182));

        labelMon.setFont(new java.awt.Font("Arial", 0, 18)); 
        labelMon.setForeground(new java.awt.Color(255, 255, 255));
        labelMon.setText("Monitor Count");
        getContentPane().add(labelMon);
        labelMon.setBounds(20, 30, 170, 21);

        labelSen.setFont(new java.awt.Font("Arial", 0, 18)); 
        labelSen.setForeground(new java.awt.Color(255, 255, 255));
        labelSen.setText("Sensor Count");
        getContentPane().add(labelSen);
        labelSen.setBounds(250, 30, 190, 21);
        
        labelSensor.setFont(new java.awt.Font("Arial", 0, 18)); 
        labelSensor.setForeground(new java.awt.Color(255, 255, 255));
        labelSensor.setText("Sensor Controllers");
        getContentPane().add(labelSensor);
        labelSensor.setBounds(806, 30, 190, 21);

        labelmonitorCount.setFont(new java.awt.Font("Arial", 0, 30)); 
        labelmonitorCount.setForeground(new java.awt.Color(255, 255, 255));
        labelmonitorCount.setText("0");
        getContentPane().add(labelmonitorCount);
        labelmonitorCount.setBounds(70, 30, 50, 75);

        btnUpdate.setText("Latest Reading");
        btnUpdate.setBackground(new Color(189, 28, 230));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        
        labelsensorCount.setFont(new java.awt.Font("Arial", 0, 30)); 
        labelsensorCount.setForeground(new java.awt.Color(255, 255, 255));
        labelsensorCount.setText("4");
        getContentPane().add(labelsensorCount);
        labelsensorCount.setBounds(300, 55, 20, 21);
        getContentPane().add(btnUpdate);
        btnUpdate.setBounds(700, 55, 164, 29);
        
        btnGetReading.setBackground(new Color(189, 28, 230));
        btnGetReading.setText("Get New Monitors");
        btnGetReading.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetReadingActionPerformed(evt);
            }
        });
        getContentPane().add(btnGetReading);
        btnGetReading.setBounds(880, 55, 164, 29);

        

        labelImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sliit/images/fire.jpg"))); 
        getContentPane().add(labelImg);
        labelImg.setBounds(0, 0, 1100, 590);

        pack();
    }
    
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        fireMonitor.updateCurrentReadings();
    }
    
    
    private void btnGetReadingActionPerformed(java.awt.event.ActionEvent evt) {
        fireMonitor.getNewestReadingsByFloorNo(floorNo, roomNo);
    }

    public static void main(String args[]) throws ClassNotFoundException, IllegalAccessException, UnsupportedLookAndFeelException {
        
        try {
            for (UIManager.LookAndFeelInfo data : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(data.getName())) {
                    UIManager.setLookAndFeel(data.getClassName());
                    break;
                }
            }
        }  catch (Exception ex) {
            System.err.println(ex.getMessage());
        } 
      
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FireMonitorGUI(fireMonitor).setVisible(true);
            }
        });
    }

  

   
}

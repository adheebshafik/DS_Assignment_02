package com.sliit.monitorGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class SensorMonitorGUI extends JInternalFrame {
	    
	    private JTextField smoke, Co2level;
	    private JLabel labelsmoke, labelco2, lblsmoke, floorNo, roomNo, labelroomNo, labelfloorNo;
  
    public SensorMonitorGUI(int floor, int roomNo) {
        initComponents();
        this.labelfloorNo.setText(String.valueOf(floor));
        this.labelroomNo.setText(String.valueOf(roomNo));
    }
    
    
    public void setSmokeLevel(int smokeLevel){
        this.smoke.setText(Integer.toString(smokeLevel));
    }
    
    public void setCO2Level(double co2Level){
        this.Co2level.setText(Double.toString(co2Level));
    }
    
    

    //setting up gui components
    @SuppressWarnings("unchecked")
    private void initComponents() {

        smoke = new JTextField();
        labelsmoke = new JLabel();
        floorNo = new JLabel();
        roomNo = new JLabel();
        labelco2 = new JLabel();
        Co2level = new JTextField();
        lblsmoke = new JLabel();
        labelfloorNo = new JLabel();
        labelroomNo = new JLabel();

        setMaximumSize(new java.awt.Dimension(280, 250));
        setSize(new java.awt.Dimension(280, 250));
        setBackground(new Color(255, 204, 204));
        smoke.setEditable(false);
        smoke.setName("smoke"); 
        smoke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smokeActionPerformed(evt);
            }
        });

        labelsmoke.setText("Smoke Level");

        
        floorNo.setText("Floor");
        floorNo.setFont(new Font("Arial", Font.BOLD, 17)); 
        floorNo.setForeground(Color.BLACK);
        floorNo.setAlignmentX(0);
        labelfloorNo.setText("Floor");
        
        roomNo.setText("Room");
        roomNo.setFont(new Font("Arial", Font.BOLD, 17)); 
        roomNo.setForeground(Color.BLACK);
        roomNo.setAlignmentX(0);
        labelroomNo.setText("Room");
        

        labelco2.setText("CO2 Level");
        Co2level.setEditable(false);
        Co2level.setName("Co2level");
        Co2level.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Co2levelActionPerformed(evt);
            }
        });
        
        lblsmoke.setText("");
        
        
        lblsmoke.setFont(new Font("Courier New", Font.ITALIC, 14)); 
        lblsmoke.setForeground(Color.BLUE); 

        
        labelsmoke.setFont(new Font("Arial", Font.BOLD, 15)); 
        labelsmoke.setForeground(Color.BLACK); 
        labelco2.setFont(new Font("Arial", Font.BOLD, 15)); 
        labelco2.setForeground(Color.BLACK); 
        
        labelfloorNo.setFont(new Font("Arial", Font.BOLD, 19)); 
        labelfloorNo.setForeground(Color.RED); 
        
        labelroomNo.setFont(new Font("Arial", Font.BOLD, 19)); 
        labelroomNo.setForeground(Color.RED); 
        
        //set layout for child reading gui
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        
        
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
               
                .addComponent(floorNo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            
            .addGroup(layout.createSequentialGroup()
                    .addGap(130, 130, 130)
                   
                    .addComponent(labelfloorNo)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            //Mods
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
               
                .addComponent(roomNo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            
            .addGroup(layout.createSequentialGroup()
                    .addGap(130, 130, 130)
                   
                    .addComponent(labelroomNo)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            //
            
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelsmoke)
                    .addComponent(labelco2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(smoke, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblsmoke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Co2level, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
        		  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(floorNo)
                .addGroup(layout.createSequentialGroup()
                 .addGap(1, 1, 1)
                .addComponent(labelfloorNo)
            //Mods
                .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(roomNo)
                        .addGroup(layout.createSequentialGroup()
                         .addGap(1, 1, 1)
                        .addComponent(labelroomNo)
               //
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)	
                		
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGap(39, 39, 39)
                        .addComponent(labelsmoke, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGap(42, 42, 42)
                        .addComponent(labelco2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(smoke, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblsmoke))))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Co2level, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            )
                        .addGap(30, 30,30)))
                .addContainerGap(14, Short.MAX_VALUE))
    ))));

        pack();
    }

    private void smokeActionPerformed(java.awt.event.ActionEvent evt) {       
    }

    private void temperatureActionPerformed(java.awt.event.ActionEvent evt) {       
    }

    private void batterylevelActionPerformed(java.awt.event.ActionEvent evt) {       
    }

    private void Co2levelActionPerformed(java.awt.event.ActionEvent evt) {    }


   
}

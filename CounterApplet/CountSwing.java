/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SampleCodes.CounterApplet;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Rohit Ravishankar
 */
public class CountSwing {
    
    JFrame myframe;
    JLabel mylabel;
    JButton mybutton;
    JButton dec;
    JTextField myfield;
    
    int i=0;
    
    public void startactivity(){
        myframe = new JFrame("Counter");
        myframe.setLayout(new FlowLayout());
        myframe.setDefaultCloseOperation(myframe.EXIT_ON_CLOSE);
        
        mybutton = new JButton("Increment");
        mylabel = new JLabel("counter");
        dec = new JButton("Decrement");
        myfield = new JTextField();
        myfield.setEditable(false);
        myfield.setColumns(5);
        
        myframe.add(mylabel);
        //myframe.add(mylabel);
        myframe.add(myfield);
        myframe.add(mybutton);
        myframe.add(dec);
        
        myframe.setBounds(00,0,400,75);
        myframe.setVisible(true);
        
        mybutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                i++;
                myfield.setText(String.valueOf(i));
                
            }
        });
        
        dec.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                i--;
                myfield.setText(String.valueOf(i));
            }
        });
    }
    
}

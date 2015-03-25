package com.zetcode;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class Snake extends JFrame {

    public Snake() {

        add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
    	System.out.println("current working directory is: " + System.getProperty("user.dir")); 
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = new Snake();
                ex.setVisible(true);                
            }
        });
    }
}
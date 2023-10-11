package rhombus;
/**
 * Testing the Rhombus class
 *
 * @Karlie (your name)
 * @Oct 27 2022 (a version number or a date)
 * The testing program creates three different rhombus on seperate windows.
 */

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test
{
    
    //creates three testing Rhombus objects

    
    static Rhombus[] rhombuses = new Rhombus[3];
    static {
    	rhombuses[0] = new Rhombus(40,80,300,80);
        rhombuses[1] = new Rhombus(100,500,200,100);
        rhombuses[2] = new Rhombus(200,300,80,40);
    }

    
    //drawing the Rhombus on JPanel
    
    public static void main(String[] args){
    	//window dimension constants
    	final int PANEL_WIDTH = 1200;
    	final int PANEL_HEIGHT = 800;
    	//JFrame dimension constants
    	final int FRAME_WIDTH = PANEL_WIDTH + 16;
    	final int FRAME_HEIGHT = PANEL_HEIGHT + 38;
 	
    	
        //creates a non-resizable window. User clicks on close button to exit the program.
        JFrame theWindow = new JFrame("Rhombus");
        theWindow.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        theWindow.setResizable(false);
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /* Creates an instance of MyJPanel, which is used to draw the shapes.
         * Sets the background color to off white.
         * Sets the MyJPanel to be the main content pane.     
         */
        
        MyJPanel thePanel = new MyJPanel();
        thePanel.setOpaque(true);
        thePanel.setBackground(new Color(246,242,238));
        theWindow.setContentPane(thePanel);
        
        //makes the window visible for users to see and interactice with
        theWindow.setVisible(true);

        
    }
    
     static class MyJPanel extends JPanel {
        public MyJPanel() {
            super();
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.translate(1,0);
            
            
            
            for(Rhombus r: rhombuses) {
            	r.drawRhombus(g);
            }
        }
        
        
    }

}

package rhombus;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import rhombus.Test.MyJPanel;


/**
 * Creates 16 different EnhancedRhombus objects on a 4x4 grid on a JFrame window
 * Tests the constructor and methods of EnhancedRhombus class
 * Each Rhombus is randomly-generated using methods
 * 
 * @author Karlie Wang
 * @version Nov 21, 2022
 */

public class EnhancedRhombusTest {

	// Creates an array that contains Colors objects used by JPanel
	// This array is used in line 119 to randomly-generate colours used to create a randomly-generated Rhombuses
	static Color[] colors = new Color[] {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, 
										Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
										Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};

       
    public static void main(String[] args){
    	
    	//JFrame dimension constants
    	final int PANEL_WIDTH = 1200;
    	final int PANEL_HEIGHT = 800;
    	final int FRAME_WIDTH = PANEL_WIDTH + 16;
    	final int FRAME_HEIGHT = PANEL_HEIGHT + 38;
 	
    	
        //creates a non-resizable window. User clicks on close button to exit the program.
        JFrame theWindow = new JFrame("Rhombus");
        theWindow.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        theWindow.setResizable(false);
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creates a 4x4 grid to seperate the different versions of Rhombus
        JPanel mainPanel = new JPanel();
        GridBagLayout gridLayout = new GridBagLayout();
        mainPanel.setLayout(gridLayout);
        mainPanel.setOpaque(true);
        theWindow.setContentPane(mainPanel);
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(3,3,3,3);
        constraints.weightx = 0.2;
        constraints.weighty = 0.2;
        constraints.fill = GridBagConstraints.BOTH;
        
        /* Creates 4 rows and 4 columns of Rhombus on JPanel, each with width less than 1/4 of the panel width and height
         * so that no Rhombus overlap the other & each Rhombus can be fully displayed in its grid
         */
        for(int x = 0; x < 4; x++) {
        	for(int y = 0; y < 4; y++) {
        		MyJPanel panel = new MyJPanel(generateRhombus(PANEL_WIDTH/4,PANEL_HEIGHT/4));
        		constraints.gridx = x;
        		constraints.gridy = y;
        		mainPanel.add(panel, constraints);
        	}
        }
        
        //makes the window visible for users to see and interactice with
        theWindow.setVisible(true);

        
    }
    
    // The following methods are used to create a ranndomly-generated Rhombus object
    
    /**
     * Randomly generates an integer between the start and end boundaries
     * @param start		the minimum value of the random number
     * @param end		the max value of the random number
     * @return random integer that's between start and end boundaries
     */
    private static int randomNumber(int start, int end) {
    	return (int)(Math.random() * (end - start + 1)) + start;
    }
    
    /**
     * Randomly generates an integer between zero and the rightbound value(not inclusive)
     * @param rightBound	the max value bondary (not inclusive)
     * @return random integer that's greater or equal to zero and smaller than rightBound
     */
    private static int randomNumber(int rightBound) {
    	return randomNumber(0, rightBound - 1);
    }
    
    /**
     * Creates a randomly-generated Rhombus object
     * @param width		the maximum possible width of the Rhombus
     * @param height	the maximum possible height of the Rhombus
     * @return a Rhombus object (to line 70)
     */
    private static Rhombus generateRhombus(int width, int height) {
    	int cx = width / 2;
    	int cy = height / 2;
    	
    	// Randomly generates the width and height of the Rhombus
    	int w = randomNumber(30, (int)(width * 0.8));
    	int h = randomNumber(30, (int)(height * 0.8));
    	
    	// Randomly generates the four colours in the Rhombus (strokeColor,fillColor,outerFillColor,crossColor) using the colors array I created
    	List<Color> cls = new ArrayList<>();
    	List<Color> selectedColor = new ArrayList<>();
    	for(Color c: colors) {
    		cls.add(c);
    	}
    	// Randomly selects four colours from the colors array to the selectedColor ArrayList
    	for(int i = 0; i < 4; i++) {
    		selectedColor.add(cls.remove(randomNumber(cls.size())));
    	}
    	
    	// Creates a Rhombus object using the randomly-generated values
    	return new EnhancedRhombus(cx,cy,w,h,randomNumber(1, 6),
    			selectedColor.get(0), selectedColor.get(1), 
    			randomNumber(0,1), selectedColor.get(2), 
    			selectedColor.get(3), randomNumber(10,30));
    }
    
    // Draws the current Rhombus that is created in line 70 on JPanel
    static class MyJPanel extends JPanel {
    	private Rhombus rhombus;
        public MyJPanel(Rhombus r) {
            super();
            this.rhombus = r;
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.translate(1,0);
            rhombus.drawRhombus(g);
        }
        
    }

} //End of EnhancedRhombusTest Class

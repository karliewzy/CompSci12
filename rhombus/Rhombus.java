package rhombus;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class defines a Rhombus object that can be drawn on a JFrame Window.
 *
 * @author Karlie Wang
 * @version Nov 2, 2022
 */

public class Rhombus {
	// width and height of the bonding box
    private int boxWidth = 0;
    private int boxHeight = 0;
    
    // xy coordinate of the top-left corner of the bonding box
    private int boxX = 0;
    private int boxY = 0;

    //sets width and height of the bonding box
    public void setWidthHeight(int width, int height) {
        boxWidth = width;
        boxHeight = height;
    }

    /**
     * Sets xy coordinate of the bonding box
     * @param x		x-coordinate of the top-left corner of the bonding box
     * @param y		y-coordinate of the top-left corner of the bonding box
     */
    public void setXY(int x, int y) {
        boxX = x;
        boxY = y;
    }

    /**
     * @return the x-coordinate of the top-left corner of the bonding box
     */
    public int getBoxX() {
    	return boxX;
    }
    
    /**
     * @return the y-coordinate of the top-left corner of the bonding box
     */
    public int getBoxY() {
    	return boxY;
    }
    
    /**
     * @return width of the bonding box
     */
    public int getBoxWidth() {
    	return boxWidth;
    }
    
    /**
     * @return height of the bonding box
     */
    public int getBoxHeight() {
    	return boxHeight;
    }
    
    
    /**
     * Calculates the coordinates of the rhombus and stores them inside a 2D array
     * @return an array containing the four cooordinates of the rhombus
     */
    public int[][] calcCoord() {
    	int[][] vertices = new int[4][2];
        vertices[0][0] = boxX;
        vertices[0][1] = boxY+boxHeight/2;

        vertices[1][0] = boxX+boxWidth/2;
        vertices[1][1] = boxY;
        
        vertices[2][0] = boxX+boxWidth;
        vertices[2][1] = boxY+boxHeight/2;
        
        vertices[3][0] = boxX+boxWidth/2;
        vertices[3][1] = boxY+boxHeight;
        return vertices;
    }
    
    /** 
     * Draws Rhombus with Graphics object
     * @param g			the Graphics object
     */
    public void drawRhombus(Graphics g) {
    	// colour of the rhombus outline
    	g.setColor(Color.BLACK);
    	// draws four lines using coordinates to create the Rhombus shape
    	int[][] coords = calcCoord();
    	for(int i=0; i<4; i++) {
    		int j = (i+1)%4;
    		g.drawLine(coords[i][0],coords[i][1],coords[j][0],coords[j][1]);
    	}
    	// colour of the bonding box
    	g.setColor(Color.GREEN);
    	// draws the bonding box
    	g.drawRect(boxX,boxY,boxWidth,boxHeight);
    }
    
    /**
     * Constructs an Rhombus object
     * @param x			x-coordinate of the top-left corner of the bonding box
     * @param y			y-coordinate of the top-left corner of the bonding box
     * @param width 	width of the bonding box
     * @param height	height of the bonding box
     */
    public Rhombus(int x, int y, int width, int height) {
        boxX = x;
        boxY = y;
        boxWidth = width;
        boxHeight = height;
        
    }
}
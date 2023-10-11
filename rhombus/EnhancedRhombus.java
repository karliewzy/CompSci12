package rhombus;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * EnhancedRhombus is the child class of its parent class, Rhombus. EnhancedRhombus creates an rhombus with extra features. 
 *
 * @author Karlie Wang
 * @version Nov 21, 2022
 */
public class EnhancedRhombus extends Rhombus {

	// subclass-only variables
	
	// width and colour of the rhombus
	private int strokeWidth;
	private Color strokeColor;
	
	// colour of the rhombus
	private Color fillColor;
	
	// shape of the bonding box/oval (rectangle or oval)
	private int outerShape; // 0: rectangle, 1: oval
	
	// colour of the bonding shape (rectangle or oval)
	private Color outerFillColor;
	
	// colour of the center cross
	private Color crossColor;
	
	// coorinates of the center point of the rhombus
	private int cx;
	private int cy;
	
	// size of the center plus-sign(cross)
	private int crossLength;
	
	
	/**
     * Constructs an EnhancedRhombus object
     * @param cx				x-coordinate of the center of the rhombus
     * @param cy				y-coordinate of the center of the rhombus
     * @param width 			width of the rhombus/bonding box/bonding oval
     * @param height			height of the rhombus/bonding box/bonding oval
     * @param strokeWidth		width of the rhombus outline
     * @param strokeColor		colour of the rhombus outline
     * @param fillColor			colour that fills the rhombus shape
     * @param outerShape 		shape of the bonding shape: rectangle or oval
     * @param outerFillColor	colour that fills the bonding shape
     * @param crossColor		colour of the center cross
     * @param crossLength		size of the center plus-sign
     */
	public EnhancedRhombus(int cx, int cy, int width, int height, int strokeWidth, Color strokeColor, Color fillColor, int outerShape, Color outerFillColor, Color crossColor, int crossLength) {
		
		// gets coordinates and dimensions from the superclass, Rhombus, and calculates the center coordinates, cx & cy.
		super(cx - width / 2, cy - height / 2, width, height);
	
		this.strokeWidth = strokeWidth;
		this.strokeColor = strokeColor;
		this.fillColor = fillColor;
		this.outerShape = outerShape;
		this.outerFillColor = outerFillColor;
		this.crossColor = crossColor;
		this.cx = cx;
		this.cy = cy;
		this.crossLength = crossLength;
	}
	
	@Override 
	public void drawRhombus(Graphics g1) {
		
		Graphics2D g = (Graphics2D)g1;
		
		// Fills the bonding rectangle/bonding oval with assigned colour
		g.setStroke(new BasicStroke(1f));
    	g.setColor(outerFillColor);
    	if (outerShape == 0) {
    		g.fillRect(getBoxX(), getBoxY(), getBoxWidth(), getBoxHeight());
    	}
    	else {
    		g.fillOval(getBoxX(), getBoxY(), getBoxWidth(), getBoxHeight());
    	}
    	
    	// Fills the rhombus with assigned colour
    	int[][] coords = calcCoord();
    	g.setColor(fillColor);
    	g.fillPolygon(new int[] {coords[0][0], coords[1][0], coords[2][0], coords[3][0]}, 
    			new int[] {coords[0][1], coords[1][1], coords[2][1], coords[3][1]}, 4);
    	
    	// Draws the plus-sign with assigned colour and size
    	g.setColor(crossColor);
    	g.drawLine(cx - crossLength/2, cy, cx + crossLength/2, cy );
    	g.drawLine(cx, cy - crossLength/2, cx, cy + crossLength/2);
    	
    	// Draws rhombus outline with assigned colour and stroke-width
    	g.setStroke(new BasicStroke(strokeWidth));
    	g.setColor(strokeColor);
    	for(int i=0; i<4; i++) {
    		int j = (i+1)%4;
    		g.drawLine(coords[i][0],coords[i][1],coords[j][0],coords[j][1]);
    	}
    	g.setStroke(new BasicStroke(1f));
    	
    }
	
	
}

//imports all classes from java.awt & java.awt.event & javax.swing by adding an * at the end;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Karlie 
 * @version 4.0 Feb 3, 2023 GAME MODES (WALL MODE) + MENUBAR ADDED + "press space to start" COMPLETED
 * @version 4.1 Feb 4 FIXED apple spawning/Game Ended delay bugs + ADDED ORANGE FOOD MODE, need to add to menu bar
 * @version 4.3 Feb 8 ADDED RESTART GAME FEATURE
 * 
 * Instructions on Snake Game
 * Please choose your game settings/modes in the menu bar before pressing SPACE KEY to start the game
 * Press SPACE KEY to pause and continue the game
 * Please run the program again for another game
 * 
 * Game Mode Info
 * WALL MODE creates a new wall on screen every time snake eats an apple, snake cannot hit wall
 * SPEED CHALLENGE MODE makes snake move faster every time it eats a new apple
 * CLASSIC MODE is the default snake game
 * 
 * Snake Settings Info
 * Short snake initiates a snake with a length of 2 blocks
 * Classic snake initiates a snake with a length of 6 blocks (default snake)
 * Large snake initiates a snake with a length of 10 blocks
 * Colourful Snake initiates a snake with colourful body, this option can be chosen while selecting different snake lengths
 * 
 * New Game Info
 * Select New Game to restart
 */

public class GamePanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25; //dimensions of every objects in pixels
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT/UNIT_SIZE);
	
	//varibles/booleans for different game modes
	public static int delay = 90; //the higher the #, the slower the snake moves
	/*
	 * WALL MODE creates a new wall on screen every time snake eats an apple
	 * if snake hits wall, game over
	 */
	public static boolean wallMode = false;
	/*
	 * SPEED CHALLENGE MODE makes snake move faster every time it eats a new apple
	 */
	public static boolean challengeMode = false;
	
	/*
	 * sets snake body to randomly generated colours (MenuBar Snake Settings)
	 */
	public static boolean colourful = false;
	
	/*
	 * sets snake food "apple" to orange
	 */
	public static boolean orangeC = false;

	//holds the x coordinates of the snake's body parts
	int x[] = new int [GAME_UNITS];
	//holds the y coordinates of the snake's body parts
	int y[] = new int [GAME_UNITS];
	
	//holds x and y coordinates of walls
	int wallX[] = new int [SCREEN_WIDTH];
	int wallY[] = new int [SCREEN_WIDTH];

	public static int bodyParts = 6; //initial length of snake
	public static int applesEaten; //initially be zero
	int appleX; //x-coord of apple appearing (random coord on screen)
	int appleY; //y-coord of apple appearing (random coord on screen)
	char direction = 'R'; // starting direction (R=right, L=left, U=up, D=down)
	boolean running = false;
	boolean ended = false;
	Timer timer;
	Random random;
	
	
	
	GamePanel(){
		random = new Random();
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(new Color(224,224,224)); //backgroud colour of the game window
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	
	
	public void startGame() {
		//creates a new apple on the screen
		newApple();
		
		if(wallMode) {
			newWall();
		}
		
		running = false;
		
		//creates a delay using the actionListener function
		timer = new Timer(delay,this);
		timer.start();
	}
	
	
	
	//used for restart game
	//resets the values of the snake
	public void initSnake(){
		
		delay = 90;

		applesEaten = 0;
		
		ended = false;
		
		Arrays.fill(x, (0));
		Arrays.fill(y, (0));
		
		bodyParts = 6;
				
		Arrays.fill(wallX, (0));
		Arrays.fill(wallY, (0));
		
		System.out.print(x);
		
		direction = 'R';

	}
	
	
	
	public void paintComponent( Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	
	
	public void draw(Graphics g) {
		
		//set colour & font of apples eaten score
		g.setColor(Color.blue);
		g.setFont(new Font("Ink Free", Font.BOLD, 25));
		//displays score on the top centre of screen 
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Apples Eaten: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Apples Eaten: " + applesEaten))/2, g.getFont().getSize());
		
		if(running) {
		
			// draws the snake on screen
			for(int i=0; i< bodyParts; i++) {
				//draws snake head
				if(i == 0) {
					//light blue colour
					g.setColor(new Color(51,153,255));
					g.fillRoundRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE,7,7);
				}
				//draws snake body
				else {
					//purple colour
					g.setColor(new Color(168,105,223));
					
					//optinal enhanced colour-changing snake body 
					if(colourful) {
						g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					}
					g.fillRoundRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE,7,7);
				}
			}
			
			g.setColor(Color.blue);
			g.fillOval(575,575,25,25);
			
			//draws apple
			g.setColor(Color.red);
			//if orange mode, sets food to orange
			if(orangeC) {
				g.setColor(new Color(252, 78, 3));
			}
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			
			//draws wall
			if(wallMode) {
				for(int i=0; i<applesEaten;i++) {
					g.setColor(new Color(128,128,128));
					g.fillRect(wallX[i],wallY[i],UNIT_SIZE, UNIT_SIZE);
				}
			}
			
		}
		else if(!ended) {
			gamePaused(g);
			
		}
		
		else {
			gameOver(g);
		}
		
	}
	
	
	
	//creates an apple on screen that's not underneath the snake
	public void newApple() {
		
		int tempX = random.nextInt(22)*UNIT_SIZE+25;
		int tempY = random.nextInt(20)*UNIT_SIZE+25;
		for (int i = 0; i < bodyParts; i++) {
	        if (tempX == x[i] && tempY == y[i]){    
	        	newApple();
	        }
	        else {
	            appleX = tempX;
	        	appleY = tempY;
	        }
		}
	}
	
	
	
	//creates a new wall on screen every time snake eats an apple
	public void newWall() {
		boolean ok = false;
	    while (!ok) {
	        ok = true;
	    	//holds x and y coordinates of empty space for apple to spawn
	        int tempX = random.nextInt(20)*UNIT_SIZE+25;
	        int tempY = random.nextInt(20)*UNIT_SIZE+25;
	        
	        for (int i = 0; i < bodyParts; i++) {
	            if ((tempX == x[i] && tempY == y[i])&&(tempX==appleX && tempY==appleY)) {
	                ok = false;
	                break;
	            }
	        }
	        if (ok) {
	            wallX[applesEaten] = tempX;
	            wallY[applesEaten] = tempY;
	        }
	    }

	}
	
	
	
	//makes snake move faster every time it eats a new apple (SPEED CHALLENGE MODE)
	public void speedUp() {
		delay = (int) (delay-applesEaten*0.2);
	}
	
	
	
	public void move() {
		//shifts the body parts of the snake around to create "movement"
		for(int i= bodyParts; i>0; i--) { 
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		//allows user to change snake directions with keyboard
		switch(direction) {
		case 'U': //up
			y[0] = y[0] - UNIT_SIZE;
			break;
		
		case 'D': //down
			y[0] = y[0] + UNIT_SIZE;
			break;
			
		case 'L': //left
			x[0] = x[0] - UNIT_SIZE;
			break;
			
		case 'R': //right
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}
	
	
	
	//checks if snake head meets the apple (eats apple)
	public void checkApple() {
		//examine coords of snake and coords of apple
		if((x[0] == appleX) && (y[0] == appleY)) {
			//increments body length
			bodyParts++;
			//keeps score of apples eaten
			applesEaten++;
			//adds a new apple on screen
			newApple();
			if(wallMode) {
				newWall();
			}
		}
	}
	
	
	
	//checks if snake head hits the wall in the wall mode
	public void checkWall() {
		//if hit wall, game ends
		//I use applesEaten to count how many walls there are 
		//In Wall mode, every time snake eats an apple, a wall appears
		for(int i = 0; i<=applesEaten; i++) {
			if((wallX[i] == x[0]) && (wallY[i] == y[0])) {
				ended = true;
				running = false;
			}
		}
	}
	
	
	
	public void checkCollisions() {
		//sets running to false if collided
		//the snake stops running if collided, see actionPerformed method

		//checks if snake head collides with snake body
		for(int i = bodyParts; i>0; i--) {
			if((x[0]==x[i])&&(y[0]==y[i])) {
				ended = true;
				running = false;
			}
		}
		//checks if head touches left boarder
		if(x[0] < 0) {
			ended = true;
			running = false;
		}
		//checks if head touches right boarder
		if(x[0] > (SCREEN_WIDTH-25)) {
			ended = true;
			running = false;
		}
		//checks if head touches top boarder
		if(y[0] < 0) {
			ended = true;
			running = false;
		}
		//checks if head touches top boarder
		if(y[0] > (SCREEN_HEIGHT-75)) {
			ended = true;
			running = false;
		}
		
		//stops the game timer
		if(!running || ended) {
			timer.stop();
		} 
	}
	
	
	
	
	//displays score and Game Over message
	public void gameOver(Graphics g) {
		//displays score
		g.setColor(Color.blue);
		g.setFont(new Font("Ink Free", Font.BOLD, 25));
		//displays score on the top centre of screen 
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Apples Eaten: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Apples Eaten: " + applesEaten))/2, g.getFont().getSize());
		
		//Game Over text
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		//displays "GAME OVER" text on the centre of screen 
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
	}
	
	
	
	//displays instructions to continue the game
	public void gamePaused(Graphics g) {
		
		g.setColor(Color.blue);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		//displays "GAME OVER" text on the centre of screen 
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Press space to start", (SCREEN_WIDTH - metrics2.stringWidth("Press space to start"))/2, SCREEN_HEIGHT/2);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			move();
			checkApple();
			checkCollisions();
			if(wallMode) {
				checkWall();
			}
			// SPEED CHALLENGE MODE makes snake move faster every time it eats a new apple
			if(challengeMode) {
				speedUp();
			}
		}
		repaint(); 
	}
	
	
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			
			//no 180-degree turns or snake head will collide with body
			//limits the user to control only 90-degree turns
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
				
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
				
			case KeyEvent.VK_UP:
				if(direction != 'D') { 
					direction = 'U';
				}
				break;
				
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
			
			int keyCode = e.getKeyCode();
			//space key pauses the game
			if(keyCode == KeyEvent.VK_SPACE) {
				
				if(!ended) {
					newApple();
					running = !running;
					repaint();
				}
			}
		}
	}

}

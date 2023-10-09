import javax.swing.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;

/**
 * @author Karlie 
 * @version Feb 3, 2023 MENUBAR + GAME MODES ADDED
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
 */


public class GameFrame extends JFrame implements ActionListener{

	JMenuBar menuBar;
	JMenu backgroundMenu;
	JMenu modeMenu;
	JMenu snakeMenu;
	JMenu newGameMenu;
	
	//declearing menu items of Backgroud Menu
	JMenuItem setToWhite;
	JMenuItem setToBlack;
	JMenuItem setToGreen;
	//JMenuItem setToOpaque; THIS TRANPARENT BACKGROUND METHOD I FOUND ON THE INTERNET DOESN'T WORK ON MY IDE
	
	//menu items of Game Mode Menu
	JMenuItem wallMode;
	JMenuItem challengeMode;
	JMenuItem classicMode;
	
	//menu items of Snake Menu
	JMenuItem shortSnake;
	JMenuItem classicSnake;
	JMenuItem largeSnake;
	JMenuItem colourful;
	
	//menu items of New Game Menu
	JMenuItem newGame;
	GamePanel gamePanel = new GamePanel();

	
	
			
	GameFrame(){
		
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,600);
		this.setResizable(false);
		this.setLayout(new FlowLayout()); //fits the components nicely on the frame
		
		//creates a menu bar for user to customize game modes
		menuBar = new JMenuBar();
		backgroundMenu = new JMenu("Background");
		modeMenu = new JMenu("Game Mode");
		snakeMenu = new JMenu("Snake Settings");
		newGameMenu = new JMenu("New Game");
		
		//creating menu items of Backgroud Menu
		setToWhite = new JMenuItem("Light");
		setToBlack = new JMenuItem("Dark");
		setToGreen = new JMenuItem("Lawn"); 
		//setToOpaque = new JMenuItem("Transparent"); 
		
		setToWhite.addActionListener(this);
		setToBlack.addActionListener(this);
		setToGreen.addActionListener(this);
		
		//adding menu items of Backgroud Menu
		backgroundMenu.add(setToWhite);
		backgroundMenu.add(setToBlack);
		backgroundMenu.add(setToGreen);
		//backgroundMenu.add(setToOpaque);
		
		//creating menu item(s) of Game Mode Menu
		wallMode = new JMenuItem("Wall Mode");
		challengeMode = new JMenuItem("Speed Challenge Mode");
		classicMode = new JMenuItem("Classic Mode");


		wallMode.addActionListener(this);
		challengeMode.addActionListener(this);
		classicMode.addActionListener(this);

		//adding menu item(s) of Game Mode Menu
		modeMenu.add(wallMode);
		modeMenu.add(challengeMode);
		modeMenu.add(classicMode);
		
		//creating menu items of Snake Settings Menu
		shortSnake = new JMenuItem("Short Snake");
		classicSnake = new JMenuItem("Classic Snake");
		largeSnake = new JMenuItem("Large Snake");
		colourful = new JMenuItem("Colourful Snake");

		shortSnake.addActionListener(this);
		classicSnake.addActionListener(this);
		largeSnake.addActionListener(this);
		colourful.addActionListener(this);
		
		//adding menu items of Snake Settings Menu
		snakeMenu.add(shortSnake);
		snakeMenu.add(classicSnake);
		snakeMenu.add(largeSnake);
		snakeMenu.add(colourful);
		
		//creating menu item of New Game Menu
		newGame = new JMenuItem("New Game");

		newGame.addActionListener(this);
		
		//adding menu items of New Game Menu
		newGameMenu.add(newGame);
		
		//add menus to MenuBar
		menuBar.add(backgroundMenu);
		menuBar.add(modeMenu); 
		menuBar.add(snakeMenu);
		menuBar.add(newGameMenu);
		
		this.setJMenuBar(menuBar);
		
		this.add(gamePanel);

		this.setVisible(true);
		this.setLocationRelativeTo(null); //window appears on the middle of the screen
	}

	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==setToWhite) {
			gamePanel.setBackground(new Color(255,255,204)); //backgroud colour of the game window
		}
		
		if(e.getSource()==setToBlack) {
			gamePanel.setBackground(Color.black); //backgroud colour of the game window
		}
		
		if(e.getSource()==setToGreen) {
			gamePanel.setBackground(new Color(29,209,53)); //backgroud colour of the game window
		}
		
		if(e.getSource()==shortSnake) {
			gamePanel.bodyParts = 2; 
		}
		
		if(e.getSource()==classicSnake) {
			gamePanel.bodyParts = 6; 
		}
		
		if(e.getSource()==largeSnake) {
			gamePanel.bodyParts = 10; 
		}
		
		if(e.getSource()==colourful) {
			gamePanel.colourful = true;
		}

		if(e.getSource()==wallMode) {
			gamePanel.wallMode = true; 
		}
		
		if(e.getSource()==challengeMode) {
			gamePanel.challengeMode = true; 
		}
		
		if(e.getSource()==classicMode) {
			gamePanel.challengeMode = false; 
			gamePanel.wallMode = false; 
		}
		
		if(e.getSource()==newGame) {
			
			//resets the variables
			gamePanel.initSnake();
			//restarts the game
			gamePanel.startGame();
			
		}
	}

}

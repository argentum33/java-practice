package practice.gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * An interface to display a visualization
 * of a Conway's Game of Life simulation
 * 
 * @author Tiffani Singley
 */
public class GameOfLifeView {
	
	private GameOfLife game;
	private JFrame frame;
	private JPanel contentPane;
	
	
	public GameOfLifeView(GameOfLife game) {
		this.game = game;
		boolean[][] gameBoard = game.getGameBoard();
		
		frame = new JFrame();
		// set the close action for the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create the main content panel
		contentPane = new GameOfLifePanel(new BorderLayout(), this.game);
		contentPane.setPreferredSize(new Dimension(gameBoard.length * 20, gameBoard.length * 20));

		// add the main panel to the frame
		frame.getContentPane().add(contentPane);
		// make sure the window can't be resized
        frame.setResizable(false);
        // set the size of the window to be large enough to contain its elements
		frame.pack();
		// make the window visible
		frame.setVisible(true);
		
		// set a timer to update the game of life state and update the window
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.runSimulation(1);
				refresh();
			}
		});
		timer.start();

	}
	
	/**
	 * Method to update the frame
	 */
	public void refresh() {
		frame.repaint();
	}
	
	
	public static void main(String[] args) {
		boolean [][] test = new boolean[25][25];
		Random tempRand = new Random();

		
		for(int i = 0; i < test.length; i++) {
			for(int j = 0; j < test[0].length; j++) {
				test[j][i] = (tempRand.nextInt(100) > 80)? true : false;
			}
		}


		
		GameOfLife testgame = new GameOfLife(test);
		
		System.out.println(testgame.toString());
		
		GameOfLifeView testview = new GameOfLifeView(testgame);
		
	}
	
	
	/**
	 * A JPanel that contains a visualization of Conway's Game of Life
	 * 
	 * @author Tiffani Singley
	 *
	 */
	private class GameOfLifePanel extends JPanel {
		
		GameOfLife game;
		
		
		public GameOfLifePanel(BorderLayout layout, GameOfLife game) {
			super(layout);
			this.game = game;
			
		}
		
		
		/**
		 * Initializes the Conway's Game of Life visualization
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		    Graphics2D g2 = (Graphics2D) g;
		    
			boolean[][] gameBoard = game.getGameBoard();
			
			for(int i = 0; i < gameBoard.length; i++) {
				for(int j = 0; j < gameBoard[0].length; j++) {
					if(gameBoard[j][i]) {
						g2.setColor(Color.BLACK);
						g2.fillRect(j * 20, i * 20, 20, 20);
					} else {
						g2.setColor(Color.WHITE);
						g2.fillRect(j * 20, i * 20, 20, 20);
					}
				}
			}
		}
	}
}

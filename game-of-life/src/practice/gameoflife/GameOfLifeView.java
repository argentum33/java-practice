package practice.gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


/**
 * A view to display a visualization
 * of a Conway's Game of Life simulation
 * 
 * @author Tiffani Singley
 */
public class GameOfLifeView extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private GameOfLifeModel game;
	
	
	public GameOfLifeView(GameOfLifeModel game) {
		this.game = game;
		boolean[][] gameBoard = game.getGameBoard();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(gameBoard.length * 20, gameBoard.length * 20));


	}
	
	/**
	 * Paints the Conway's Game of Life visualization
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

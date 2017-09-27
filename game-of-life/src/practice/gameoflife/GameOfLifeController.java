package practice.gameoflife;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;


/**
 * A controller to handle events for the Game Of Life application
 * 
 * @author Tiffani Singley
 *
 */
public class GameOfLifeController extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private GameOfLifeModel game;
	private GameOfLifeView view;

	
	public GameOfLifeController() {
		game = new GameOfLifeModel();
		view = new GameOfLifeView(game);
		
		// set the close action for the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// add the game of life view to the frame
		this.getContentPane().add(view);
		
		// make sure the window can't be resized
        this.setResizable(false);
        
        // set the size of the window to be large enough to contain its elements
		this.pack();
		
		// make the window visible
		this.setVisible(true);
		
		// set a timer to update the game of life state and update the window
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.runSimulation(1);
				repaint();
			}
		});
		timer.start();
		
	}
	
	public static void main(String[] args) {
		new GameOfLifeController();
	}
	
}

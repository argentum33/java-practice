package application.components;


import application.model.GameOfLifeModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A component to display the Game of Life simulation
 * 
 * @author Tiffani Singley
 *
 */
public class GameOfLifeComponent extends Canvas {
	
	GameOfLifeModel model;
	int sideLength = 15;

	
	public GameOfLifeComponent() {
		super();
		
	}
	
	public GameOfLifeComponent(GameOfLifeModel model) {
		super();
		this.model = model;
		this.setHeight(model.getRows() * sideLength);
		this.setWidth(model.getColumns() * sideLength);
		
	}
	
	/**
	 * Update the component's view based on the model
	 */
	public void updateView() {
		GraphicsContext gc = getGraphicsContext2D();
		int columns = model.getColumns();
		int rows = model.getRows();
		boolean[][] gameBoard = model.getGameBoard();
		
		gc.clearRect(0, 0, model.getColumns() * sideLength, model.getRows() * sideLength);

		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(gameBoard[i][j]) {
					gc.setFill(Color.BLACK);
					gc.fillRoundRect(j * sideLength, i * sideLength, sideLength, sideLength, 4, 4);
				} else {
					gc.setFill(Color.WHITE);
					gc.fillRoundRect(j * sideLength, i * sideLength, sideLength, sideLength, 4, 4);
				}
			}
		}
	}
	
	/**
	 * sets the model for the view
	 * 
	 * @param model - the model for the view
	 */
	public void setModel(GameOfLifeModel model) {
		this.model = model;
		this.setHeight(model.getRows() * sideLength);
		this.setWidth(model.getColumns() * sideLength);

	}

}

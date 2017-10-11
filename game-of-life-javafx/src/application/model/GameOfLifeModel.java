package application.model;

import java.util.Random;

/**
 * A class to simulate Conway's Game of Life
 * 
 * @author Tiffani Singley
 *
 */
public class GameOfLifeModel {
	
	private boolean[][] gameBoard;
	private final int columns;
	private final int rows;
	
	public GameOfLifeModel() {
		this(25, 25);

	}
	
	public GameOfLifeModel(int columns, int rows) {
		this(columns, rows, 60);

	}

	public GameOfLifeModel(int columns, int rows, int threshold) {
		this(columns, rows, threshold, new Random());
	}
	
	public GameOfLifeModel(int columns, int rows, int threshold, int seed) {
		this(columns, rows, threshold, new Random(seed));
	}
	

	private GameOfLifeModel(int columns, int rows, int threshold, Random rand) {
		
		threshold = (threshold > 100)? 100 : ((threshold < 0) ? 0 : threshold);
		columns = (columns < 5) ? 5 : columns ;
		rows = (rows < 5) ? 5 : rows ;
		
		this.columns = columns;
		this.rows = rows;
		boolean [][] board = new boolean[rows][columns];

		
		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.columns; j++) {
				board[i][j] = (rand.nextInt(100) >= threshold)? true : false;
			}
		}
		
		this.gameBoard = board;

	}
	
	public GameOfLifeModel(boolean[][] newGameBoard) {
		rows = newGameBoard.length;
		columns = newGameBoard[0].length;

		
		gameBoard = new boolean[rows][columns];
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				gameBoard[i][j] = newGameBoard[i][j];
			}
		}
		

	}
	
	/**
	 * Transitions the game state to that of the next generation
	 * by applying the rules of Conway's Game of Life
	 * 
	 */
	private void gotoNextGeneration() {
		boolean[][] gameBoardCopy = getGameBoard();
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				boolean currentState = gameBoard[i][j];
				int numNeighbors = getNumNeighbors(i, j);
				
				
				if(currentState) {
					
					if(numNeighbors < 2) {
						currentState = false;
					} else if (numNeighbors == 2 || numNeighbors == 3) {
						currentState = true;
					} else {
						currentState = false;
					}
					
				} else {
					if(numNeighbors == 3) {
						currentState = true;
					}
					
				}

				
				gameBoardCopy[i][j] = currentState;
				
			}
		}
		
		gameBoard = gameBoardCopy;
		
	}
	
	
	/**
	 * Gets the number of live neighbors for a given cell
	 * 
	 * @param row - the column in which the specified cell exists
	 * @param column - the row in which the specified cell exists
	 * @return the number of live neighbors for the specified cell
	 */
	private int getNumNeighbors(int row, int column) {
		int numNeighbors = 0;
		
		try {
			
			if(row == 0 && column == 0) {
				// cell is in the top left
				numNeighbors += (gameBoard[row + 1][column])? 1 : 0;
				numNeighbors += (gameBoard[row + 1][column + 1])? 1 : 0;
				numNeighbors += (gameBoard[row][column + 1])? 1 : 0;
				
			} else if (row == rows - 1 && column == columns - 1) {
				// cell is in the bottom right
				numNeighbors += (gameBoard[row][column - 1])? 1 : 0;
				numNeighbors += (gameBoard[row - 1][column - 1])? 1 : 0;
				numNeighbors += (gameBoard[row - 1][column])? 1 : 0;
				
			} else if (row == rows - 1 && column == 0) {
				// cell is in the bottom left corner
				numNeighbors += (gameBoard[row - 1][column])? 1 : 0;
				numNeighbors += (gameBoard[row - 1][column + 1])? 1 : 0;
				numNeighbors += (gameBoard[row][column + 1])? 1 : 0;
	
			} else if (row == 0 && column == columns - 1) {
				// cell is in the top right corner
				numNeighbors += (gameBoard[row][column - 1])? 1 : 0;
				numNeighbors += (gameBoard[row + 1][column - 1])? 1 : 0;
				numNeighbors += (gameBoard[row + 1][column])? 1 : 0;
				
			} else if (column == 0) {
				// cell borders the left side of the game board
				numNeighbors += (gameBoard[row - 1][column])? 1 : 0;
				numNeighbors += (gameBoard[row - 1][column + 1])? 1 : 0;
				numNeighbors += (gameBoard[row][column + 1])? 1 : 0;
				numNeighbors += (gameBoard[row + 1][column + 1])? 1 : 0;
				numNeighbors += (gameBoard[row + 1][column])? 1 : 0;
				
			} else if(row == 0) {
				// cell borders the top of the game board
				numNeighbors += (gameBoard[row][column + 1])? 1 : 0;
				numNeighbors += (gameBoard[row + 1][column + 1])? 1 : 0;
				numNeighbors += (gameBoard[row + 1][column])? 1 : 0;
				numNeighbors += (gameBoard[row + 1][column - 1])? 1 : 0;
				numNeighbors += (gameBoard[row][column - 1])? 1 : 0;
				
			} else if(column == columns - 1) {
				// cell borders the right side of the game board
				numNeighbors += (gameBoard[row - 1][column])? 1 : 0;
				numNeighbors += (gameBoard[row - 1][column - 1])? 1 : 0;
				numNeighbors += (gameBoard[row][column - 1])? 1 : 0;
				numNeighbors += (gameBoard[row + 1][column - 1])? 1 : 0;
				numNeighbors += (gameBoard[row + 1][column])? 1 : 0;
				
			} else if (row == rows - 1) {
				// cell borders the bottom of the game board
				numNeighbors += (gameBoard[row][column - 1])? 1 : 0;
				numNeighbors += (gameBoard[row - 1][column - 1])? 1 : 0;
				numNeighbors += (gameBoard[row - 1][column])? 1 : 0;
				numNeighbors += (gameBoard[row - 1][column + 1])? 1 : 0;
				numNeighbors += (gameBoard[row][column + 1])? 1 : 0;
	
				
			} else {
				// need to check all 8 sides
				numNeighbors += (gameBoard[row - 1][column])? 1 : 0;
				numNeighbors += (gameBoard[row - 1][column + 1])? 1 : 0;
				numNeighbors += (gameBoard[row][column + 1])? 1 : 0;
				numNeighbors += (gameBoard[row + 1][column + 1])? 1 : 0;
				numNeighbors += (gameBoard[row + 1][column])? 1 : 0;
				
				numNeighbors += (gameBoard[row + 1][column - 1])? 1 : 0;
				numNeighbors += (gameBoard[row][column - 1])? 1 : 0;
				numNeighbors += (gameBoard[row - 1][column - 1])? 1 : 0;
			
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return numNeighbors;
		
	}
	
	/**
	 * Runs the game of life simulation for the specified number of generations
	 * 
	 * @param numGenerations - the number of generations to advance the state of the game
	 */
	public void runSimulation(int numGenerations) {
		for(int i = 0; i < numGenerations; i++) {
				gotoNextGeneration();
			
		}
	}
	
	/**
	 * Returns a copy of the current state of the game
	 * 
	 * @return a copy of the current state of the game
	 */
	public boolean[][] getGameBoard() {
		boolean[][] gameBoardCopy = new boolean[rows][columns];
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				gameBoardCopy[i][j] = gameBoard[i][j];
			}
		}
		
		return gameBoardCopy;
	}
	
	/**
	 * returns the number of columns in the gameboard
	 * @return columns - the number of columns in the gameboard
	 */
	public int getColumns() {
		return columns;
	}
	
	/**
	 * returns the number of rows in the gameboard
	 * @return rows - the number of columns in the gameboard
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * Returns the current state of the game as a string
	 * 
	 * @return - The current state of the game as a string
	 */
	@Override
	public String toString() {
		String stringBoard = "";
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				stringBoard += (gameBoard[i][j]) ? "[X]" : "[ ]";
			}
			stringBoard += "\n";
		}
		
		return stringBoard;
	}
	
	

}

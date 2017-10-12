package application.controllers;

import application.components.GameOfLifeComponent;
import application.model.GameOfLifeModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 * A class to act as the main controller for the Game of Life Application
 * 
 * @author Tiffani Singley
 *
 */
public class GameOfLifeController{
	@FXML private GameOfLifeComponent gameOfLifeComponent;
	@FXML private Button play;
	@FXML private Button stop;
	@FXML private Button reset;
	@FXML private Button newRandom;
	
	private GameOfLifeModel model;
	private boolean[][] copy;
	private Timeline gameOfLifeTimeline;
	


	
	/**
	 * Provides the initial setup for the controller
	 */
	@FXML
	public void initialize() {
		model = new GameOfLifeModel();
		copy = model.getGameBoard();
		
		gameOfLifeComponent.setModel(model);
		gameOfLifeComponent.updateView();
		
		
		// Create the timeline to update the model and view every second
		gameOfLifeTimeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				model.runSimulation(1);
				gameOfLifeComponent.updateView();
				
			}
			
		}));
		
		gameOfLifeTimeline.setCycleCount(Timeline.INDEFINITE);
		
		initEventListeners();
		
	}
	
	/**
	 * Initializes Event Listeners
	 */
	private void initEventListeners() {
		// action for the play button to play the animation and update the view
		play.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				gameOfLifeTimeline.play();
				stop.setDisable(false);
				play.setDisable(true);
				reset.setDisable(true);
				newRandom.setDisable(true);
				
			}
			
		});
		
		// action for the stop button to stop the animation and update the view
		stop.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				gameOfLifeTimeline.stop();
				stop.setDisable(true);
				play.setDisable(false);
				reset.setDisable(false);
				newRandom.setDisable(false);
				
			}
			
		});
		
		// action for the reset button to reset the model to its starting state
		reset.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				model = new GameOfLifeModel(copy);
				gameOfLifeComponent.setModel(model);
				gameOfLifeComponent.updateView();
			}
		});
		
		// action for the new random game button to randomize the model and save the new starting state
		newRandom.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				model = new GameOfLifeModel();
				copy = model.getGameBoard();
				gameOfLifeComponent.setModel(model);
				gameOfLifeComponent.updateView();
			}
		});
	}
	
	/**
	 * Starts the game of life animation
	 */
	public void startTimeline() {
		gameOfLifeTimeline.play();
	}
	
}

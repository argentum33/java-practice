package application;
	
import application.controllers.GameOfLifeController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

/**
 * The main application class to run 
 * the Conway's Game of Life simulation
 * 
 * @author Tiffani Singley
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// Load the Game of Life View
			FXMLLoader golLoader = new FXMLLoader(getClass().getResource("views/GameOfLifeView.fxml"));
			VBox root = (VBox) golLoader.load();
			
			
			// initialize the main scene
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.setTitle("Conway's Game of Life in JavaFX");
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

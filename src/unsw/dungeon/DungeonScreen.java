package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {
	private Stage primaryStage;
	private String title;
	private Scene scene;
	private DungeonController controller;
	private String[] dungeons = {"maze.json", "advanced.json", "boulders.json"};
	private int cur_level = 0;
	
	
	public DungeonScreen(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		title = "Dungeon";

		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");

		controller = dungeonLoader.loadController();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		loader.setController(controller);
      
      
		Parent root = loader.load();
		scene = new Scene(root);
		root.requestFocus();
	}
	
	//Resetting board resets Dungeon Controller links to other screens 
	public void resetBoard(int level) throws IOException {
		cur_level = level;
		
		StartScreen startScreen = getController().getStartScreen();
		CompletedScreen completedScreen = getController().getCompletedScreen();
		GameOverScreen gameOverScreen = getController().getGameOverScreen();
		GameCompleteScreen gameCompleteScreen = getController().getGameCompleteScreen();
		PauseScreen pauseScreen = getController().getPauseScreen();
		
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(dungeons[level]);

		controller = dungeonLoader.loadController();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		loader.setController(controller);
      
		getController().setStartScreen(startScreen);
		getController().setCompletedScreen(completedScreen);
		getController().setGameOverScreen(gameOverScreen);
		getController().setDungeonScreen(this);
		getController().setGameCompleteScreen(gameCompleteScreen);
		getController().setPauseScreen(pauseScreen);
		
		Parent root = loader.load();
		scene = new Scene(root);
		root.requestFocus();
	}
	
	public void start() {
		primaryStage.setTitle(title);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public DungeonController getController() {
		return controller;
	}
	
	public int nextLevel() {
		if (cur_level < dungeons.length - 1) {
			cur_level++;
		}
		
		return cur_level;
	}
	
	public int getLevel() {
		return cur_level;
	}
	
	public int getNumLevels() {
		return dungeons.length;
	}
	
}

package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameCompleteScreen {
	private String title;
	private Scene scene;
	private Stage stage;
	private GameCompleteController controller;
	
	public GameCompleteScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Game Completed";
    	        
        controller = new GameCompleteController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameComplete.fxml"));
        loader.setController(controller);
        
        Parent root = loader.load();
        scene = new Scene(root);
    }
	
    public void start() {    	
    	stage.setTitle(title);
    	stage.setScene(scene);
    	stage.show();
    }
    
    public GameCompleteController getController() {
    	return controller;
    }
}

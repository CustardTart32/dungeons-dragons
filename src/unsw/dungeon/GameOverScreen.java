package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameOverScreen {
	private String title;
	private Scene scene;
	private Stage stage;
	private GameOverController controller;
	
	public GameOverScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Game Over";
    	        
        controller = new GameOverController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOverScreen.fxml"));
        loader.setController(controller);
        
        Parent root = loader.load();
        scene = new Scene(root);
    }
	
    public void start() {
    	stage.setTitle(title);
    	stage.setScene(scene);
    	stage.show();
    }
    
    public GameOverController getController() {
    	return controller;
    }
}

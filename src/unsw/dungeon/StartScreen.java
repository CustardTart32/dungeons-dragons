package unsw.dungeon;

import java.io.IOException;

//import example.counter.StartController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreen {

	private StartController controller;
	private String title;
	private Scene scene;
	private Stage stage;
	
 
    public StartScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Start Menu";
    	        
        controller = new StartController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startMenu.fxml"));
        loader.setController(controller);
        
        Parent root = loader.load();
        scene = new Scene(root);
    }
    
    public void start() {
    	stage.setTitle(title);
    	stage.setScene(scene);
    	stage.show();
    }
    
    public StartController getController() {
    	return controller;
    }
    	
}
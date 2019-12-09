package unsw.dungeon;

import java.io.IOException;

//import example.counter.StartController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PauseScreen {

	private PauseController controller;
	private String title;
	private Scene scene;
	private Stage stage;
	
 
    public PauseScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Pause Screen";
    	        
        controller = new PauseController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pauseScreen.fxml"));
        loader.setController(controller);
        
        Parent root = loader.load();
        scene = new Scene(root);
    }
    
    public void start(String str_keys, String str_sword, String str_potion, 
    		String str_treasure, String str_enemy, String str_switch) {
    	// System.out.println(str_keys);
    	controller.setKeysTextField(str_keys);
    	controller.setSwordTextField(str_sword);
    	controller.setPotionTextField(str_potion);
    	controller.setTreasureTextField(str_treasure);
    	controller.setEnemyTextField(str_enemy);
    	controller.setSwitchTextField(str_switch);
    	stage.setTitle(title);
    	stage.setScene(scene);
    	stage.show();
    }
    
    public PauseController getController() {
    	return controller;
    }
    	
}
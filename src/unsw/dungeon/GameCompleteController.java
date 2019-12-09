package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameCompleteController {
	private StartScreen startScreen;
	
	@FXML
	private Button mainMenuButton;
	
	@FXML
	private Button quitButton;
	
	@FXML
	public void handleMainMenu(ActionEvent event) {
		startScreen.start();
	}
	
	@FXML 
	public void handleQuit(ActionEvent event) {
		System.exit(0);
	}
	
	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}
}

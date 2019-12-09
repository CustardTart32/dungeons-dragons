package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {
	private DungeonScreen dungeonScreen;
	
	@FXML
	private Button startButton;
	
	@FXML
	private Button quitButton;
	
	@FXML
	public void handleStart(ActionEvent event) {
		try {
			dungeonScreen.resetBoard(0);
		}
		catch (IOException e){
			System.out.print(e);
		}

		dungeonScreen.start();
	}
	
	@FXML
	public void handleQuit(ActionEvent event) {
		System.exit(0);
	}
	
	public void setDungeonScreen(DungeonScreen screen) {
		this.dungeonScreen = screen;
	}
}

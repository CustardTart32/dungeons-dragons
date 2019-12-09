package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CompletedController {
	private DungeonScreen dungeonScreen;
	private StartScreen startScreen;
	
	@FXML
	public Button nextLevelButton;
	
	@FXML
	private Button mainMenuButton;
	
	@FXML
	public void handleNextLevel(ActionEvent event) {
		int next_level = dungeonScreen.nextLevel();
				
		try {
			dungeonScreen.resetBoard(next_level);
		}
		catch (IOException e) {
			System.out.print(e);
		}
		dungeonScreen.start();
	}
	
	@FXML
	public void handleMainMenu(ActionEvent event) {
		startScreen.start();
	}

	public DungeonScreen getDungeonScreen() {
		return dungeonScreen;
	}

	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}

	public StartScreen getStartScreen() {
		return startScreen;
	}

	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}
	
}

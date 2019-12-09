package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameOverController {
	private StartScreen startScreen;
	private DungeonScreen dungeonScreen;
	
	@FXML
	private Button mainMenuButton;
	
	@FXML
	private Button restartButton;
	
	@FXML
	public void handleMainMenu(ActionEvent event) {
		startScreen.start();
	}
	
	@FXML
	public void handleRestart(ActionEvent event) {
		try {
			dungeonScreen.resetBoard(dungeonScreen.getLevel());
		}
		catch (IOException e){
			System.out.print(e);
		}

		dungeonScreen.start();
	}
	
	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}
	
	public StartScreen getStartScreen() {
		return startScreen;
	}

	public DungeonScreen getDungeonScreen() {
		return dungeonScreen;
	}

	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}
}

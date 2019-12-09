package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PauseController {
	private DungeonScreen dungeonScreen;
	
	@FXML
	private TextField keysTextField;
	
	@FXML
	private TextField swordTextField;
	
	@FXML
	private TextField potionTextField;
	
	@FXML
	private TextField treasureTextField;
	
	@FXML
	private TextField enemyTextField;
	
	@FXML
	private TextField switchTextField;
	
	@FXML
	private Button restartButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	public void handleBack(ActionEvent event) {
		dungeonScreen.start();
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
	
	public void setKeysTextField(String keys) {
		keysTextField.setText(keys);
	}
	
	public void setSwordTextField(String sword) {
		swordTextField.setText(sword);
	}
	
	public void setPotionTextField(String potion) {
		potionTextField.setText(potion);
	}
	
	public void setTreasureTextField(String treasure) {
		treasureTextField.setText(treasure);
	}
	
	public void setEnemyTextField(String enemy) {
		enemyTextField.setText(enemy);
	}
	
	public void setSwitchTextField(String f_switch) {
		switchTextField.setText(f_switch);
	}
	
	public void setDungeonScreen(DungeonScreen screen) {
		this.dungeonScreen = screen;
	}
}
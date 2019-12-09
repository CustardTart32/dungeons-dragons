 package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;
    
    private StartScreen startScreen;
    private CompletedScreen completedScreen;
    private GameOverScreen gameOverScreen;
    private DungeonScreen dungeonScreen;
    private GameCompleteScreen gameCompleteScreen;
    private PauseScreen pauseScreen;
    
    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        dungeon.completed().addListener(new ChangeListener<Boolean>() {
        	@Override
        	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        		if (getDungeonScreen().getLevel() == getDungeonScreen().getNumLevels() - 1) {
        			gameCompleteScreen.start();
        		} else {
            		completedScreen.start();
        		}
        	}
        });
        
        dungeon.lost().addListener(new ChangeListener<Boolean>() {
        	@Override
        	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        		gameOverScreen.start();
        	}
        });
    }
    
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case E:
        	player.unlockDoor();
        	break;
        case P:
        	dungeon.printGoals();
        	String str_keys = player.showKeys();
        	String str_sword = player.showSwordDurability();
        	String str_potion = player.showPotionSteps();
        	dungeon.processGoals();
        	String str_treasure = dungeon.getTreasureProg();
        	String str_enemy = dungeon.getEnemyProg();
        	String str_switch = dungeon.getSwitchProg();
        	pauseScreen.start(str_keys, str_sword, str_potion, str_treasure, str_enemy, str_switch);
        	break;
        case ESCAPE:
        	startScreen.start();
        	break;
        default:
            break;
        }
    }

	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}
	
	public void setCompletedScreen(CompletedScreen completedScreen) {
		this.completedScreen = completedScreen;

	}
	
	public StartScreen getStartScreen() {
		return startScreen;
	}
	
	public CompletedScreen getCompletedScreen() {
		return completedScreen;
	}
	
	public void setPauseScreen(PauseScreen pauseScreen) {
		this.pauseScreen = pauseScreen;
	}
	
	public PauseScreen getPauseScreen() {
		return pauseScreen;
	}
	
	public GameOverScreen getGameOverScreen() {
		return gameOverScreen;
	}
	
	public void setGameOverScreen(GameOverScreen gameOverScreen) {
		this.gameOverScreen = gameOverScreen;
	}
	
	public DungeonScreen getDungeonScreen() {
		return dungeonScreen;
	}

	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}

	public BooleanProperty getCompleted() {
		return dungeon.completed();
	}
	
	public void setGameCompleteScreen(GameCompleteScreen gameCompleteScreen) {
		this.gameCompleteScreen = gameCompleteScreen;
	}

	public GameCompleteScreen getGameCompleteScreen() {
		return gameCompleteScreen;
	}
	
}


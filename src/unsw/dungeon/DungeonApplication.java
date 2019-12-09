package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {        
        StartScreen startScreen = new StartScreen(primaryStage);
        DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
        CompletedScreen completedScreen = new CompletedScreen(primaryStage);
        GameOverScreen gameOverScreen = new GameOverScreen(primaryStage);
        GameCompleteScreen gameCompleteScreen = new GameCompleteScreen(primaryStage);
        PauseScreen pauseScreen = new PauseScreen(primaryStage);
        
        startScreen.start();
        
        startScreen.getController().setDungeonScreen(dungeonScreen);
        
        dungeonScreen.getController().setStartScreen(startScreen);
        dungeonScreen.getController().setCompletedScreen(completedScreen);
        dungeonScreen.getController().setGameOverScreen(gameOverScreen);
        dungeonScreen.getController().setDungeonScreen(dungeonScreen);
        dungeonScreen.getController().setGameCompleteScreen(gameCompleteScreen);
        dungeonScreen.getController().setPauseScreen(pauseScreen);
        
        pauseScreen.getController().setDungeonScreen(dungeonScreen);
        
        completedScreen.getController().setDungeonScreen(dungeonScreen);
        completedScreen.getController().setStartScreen(startScreen);
        
        gameOverScreen.getController().setStartScreen(startScreen);
        gameOverScreen.getController().setDungeonScreen(dungeonScreen);
        
        gameCompleteScreen.getController().setStartScreen(startScreen);
    }

    public static void main(String[] args) {
        launch(args);
    }

}

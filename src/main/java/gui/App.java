package gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        var sceneAll = new SceneAll(primaryStage);
        sceneAll.goMenu(null);
    }
}
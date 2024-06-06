package org.example.batallanaval1;
/*
Color palette
191d32
fdfdff
957fef
9a031e
ba2d0b
 */
import org.example.batallanaval1.model.FileCRUD;
import org.example.batallanaval1.model.Logger;
import org.example.batallanaval1.view.StartStage;
import javafx.application.Application;
import javafx.stage.Stage;
import org.example.batallanaval1.view.WelcomeStage;

import java.io.IOException;


public class main extends Application {

    public static void main(String[] args) {
        Logger.initialize("src/main/resources/org/example/batallanaval1/logs.txt");
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {

        Logger.startNewGame();
        WelcomeStage welcomeStage = WelcomeStage.getInstance();
        welcomeStage.show();
    }
}

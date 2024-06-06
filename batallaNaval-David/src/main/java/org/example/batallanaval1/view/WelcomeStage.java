package org.example.batallanaval1.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.batallanaval1.controller.WelcomeController;

import java.io.IOException;

public class WelcomeStage extends Stage {
    private static WelcomeStage instance;

    private WelcomeStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/batallanaval1/welcome-naval-view.fxml"));
            loader.setController(new WelcomeController());
            Parent parent = loader.load();
            setTitle("Naval Battle");
            Scene scene = new Scene(parent);
            setScene(scene);
            setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WelcomeStage getInstance() {
        if (instance == null) {
            instance = new WelcomeStage();
        }
        return instance;
    }

    public static void deleteInstance() {
        if (instance != null) {
            instance.close();
            instance = null;
        }
    }
}
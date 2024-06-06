package org.example.batallanaval1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.batallanaval1.view.StartStage;
import org.example.batallanaval1.view.WelcomeStage;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;



public class WelcomeController {

    @FXML
    private Button startGameButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void initialize() {
        File file = new File("org/example/batallanaval1/Images/41DIvL1GFhL.jpg");
        Image image = new Image(file.toURI().toString());

        // Create a BackgroundImage
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );

        // Set the background image to the root pane
        Background background = new Background(backgroundImage);
        anchorPane.setBackground(background);
    }


    @FXML
    public void onStartGameButtonClicked(MouseEvent event) {
        // Delete the instance of WelcomeStage
        WelcomeStage.deleteInstance();


        // Show the StartStage
        try {
            StartStage.getInstance().show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the current stage (WelcomeStage)
        Stage currentStage = (Stage) startGameButton.getScene().getWindow();
        currentStage.close();
    }
}
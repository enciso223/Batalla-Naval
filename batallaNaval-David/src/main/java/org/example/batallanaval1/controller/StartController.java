package org.example.batallanaval1.controller;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.example.batallanaval1.model.*;
import org.example.batallanaval1.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

import javafx.scene.control.Alert;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import org.example.batallanaval1.model.Logger;

import static org.example.batallanaval1.model.Logger.gameCount;
import static org.example.batallanaval1.model.Logger.log;


public class StartController extends Stage {



    private int hitCountPortaAviones = 4;
    private int hitCountDestroyer1 = 2;
    private int hitCountDestroyer2 = 2;
    private int hitCountSubmarine1 = 3;
    private int hitCountSubmarine2 = 3;
    private int hitCountFrigate = 1;
    private int hitCountFrigate1 = 1;
    private int hitCountFrigate2 = 1;
    private int hitCountFrigate3 = 1;
    private int portaAvionesCounterEnemigo = 1;
    private int frigatesCounterEnemigo = 4;
    private int submarineCounterEnemigo = 2;
    private int destroyerCounterEnemigo = 2;



    private int a = 0;
    private int b = 0;
    private int winCount=0;

    LinkedList<String> array1 = new LinkedList<String>();
    ArrayList<String> array2 = new ArrayList<String>();
    String[][] array3 = new String[10][10];
    LinkedList<String> gridList = new LinkedList<>();




    @FXML
    private Pane basicPane;

    @FXML
    private Button btnStart;

    @FXML
    private TextField inputTextField;

    @FXML
    private Pane gameBoard, gameBoardOne;

    @FXML
    private HBox hbxHead;

    @FXML
    private Label lblTitle;
    @FXML
    private GridPane gridPlayer1;
    private Destroyer destructores1_boardOne, destructores2_boardOne;


    public GridPane getGridPlayer1() {
        return gridPlayer1;
    }

    private boolean checkAlready(String input) {
        if (array1.size() == 0) {
            return true;
        }
        for (int i = 0; i < array1.size(); i++) {
            if (input.equals(array1.get(i))) {
                return false;
            }
        }
        return true; // Add this line to ensure the method returns a boolean value
    }

    @FXML
    private void onHandleButtonStartGame(ActionEvent event) {
        a++;
        if (winCount==9){
            Logger.log("El jugador ha ganado");
            showPopupMessage("Has ganado!!");
            WelcomeStage.deleteInstance();
        }
        Logger.log("Juego: "+gameCount+"\n"+"Ronda: "+a+"\n"+" Estado actual:" +"\n"+
                "-Portaviones: "+portaAvionesCounterEnemigo+"\n"+
                "-Destructores: "+destroyerCounterEnemigo+"\n"+
                "-Submarinos: "+submarineCounterEnemigo+"\n"+
                "-Fragatas: "+frigatesCounterEnemigo+"\n"+
                "*Vida Portaviones: "+hitCountPortaAviones+"\n"+
                "*Vida Destructor 1: "+hitCountDestroyer1+"\n"+
                "*Vida Destructor 2: "+hitCountDestroyer2+"\n"+
                "*Vida Submarino 1: "+hitCountSubmarine1+"\n"+
                "*Vida Submarin0 2: "+hitCountSubmarine2+"\n"+
                "*Vida Frigata 1: "+hitCountFrigate+"\n"+
                "*Vida Frigata 2: "+hitCountFrigate1+"\n"+
                "*Vida Frigata 3: "+hitCountFrigate2+"\n"+
                "*Vida Frigata 4: "+hitCountFrigate3+"\n");
        String input = inputTextField.getText();

        boatDeleter(input);
        checkAlready(input);

        if (isValidInput(input) && array1.isEmpty()) {
            // Process the valid input
            Logger.log("El jugador ha ingresado la casilla: " + input);
            showPopupMessage("Valid input: " + input);
            array1.add(input);
            computerAttack();


        } else if(!isValidInput(input)) {
            showPopupMessage("Invalid input: Please enter a letter (a-j) followed by a number (1-9)." + input);
        } else if (isValidInput(input) && checkAlready(input)) {
            Logger.log("El jugador ha ingresado la casilla: " + input);

            showPopupMessage("Valid input: " + input);
            array1.add(input);
            computerAttack();

        } else if(isValidInput(input) && !checkAlready(input)) {
            showPopupMessage("Invalid input. You already picked this cell. " + input);

        }


    }
    private void airCraftHit() {
        hitCountPortaAviones--;
    }
    private void submarineHit() {
        hitCountSubmarine2--;
    }
    private void destroyerHit(){
        hitCountDestroyer2--;
    }
    private boolean isValidInput(String input) {
        if (input.length() != 2 && input.length() != 3) {
            return false; // Invalid input length
        }

        if (input.length() == 3) {
            char letter = input.charAt(0);
            char number = input.charAt(1);
            char number1 = input.charAt(2);

            // Check if all characters are within valid ranges
            return (letter >= 'a' && letter <= 'j') && (number >= '1' && number <= '9') && (number1 == '0');
        } else {
            char letter = input.charAt(0);
            char number = input.charAt(1);

            // Check if both characters are within valid ranges
            return (letter >= 'a' && letter <= 'j') && (number >= '1' && number <= '9');
        }
    }

    private void computerAttack() {
        // Initialize the LinkedList
        initializeGrid();


        b++;
        Random rand = new Random();
        int val1 = rand.nextInt(10);
        int val2 = rand.nextInt(10);
        int val3 = rand.nextInt(100);

        // Calculate the index for the LinkedList
        int index = val1 * 10 + val2;

        // Get the string key at the random index
        String key = gridList.get(index);
        Logger.log("Tiro oponente "+b+": "+ key);



        System.out.println();

        if (key.equals(gridList.get(val3))) {
            showPopupMessage("El oponente ha acertado");
            Logger.log("El oponente ha acertado");
            changePaneColor1("102",Color.RED);

        }
        else{
            Logger.log("El oponente ha fallado");
        }


    }
    private void initializeGrid() {
        char rowChar = 'a';

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                String position = rowChar + Integer.toString(j + 1);
                gridList.add(position);
            }
            rowChar++;
        }
    }

    private String[] separateString(String input) {
        String row = input.substring(0, 1); // Extract the row character
        String column = input.substring(1); // Extract the column characters
        return new String[]{row, column};
    }


    private void boatDeleter(String input) {
        checkAlready(input);
        if (checkAlready(input)){
            if (input.length() == 3) {
                char letter1 = input.charAt(0);
                char number1 = input.charAt(1);
                char number2 = input.charAt(2);

                if (number1 == '1' && number2=='0') {
                    System.out.println("Si entro");
                    switch (letter1) {
                        case 'a':
                            System.out.println("Si entro");
                            changePaneColor("102", Color.BLUE);

                            break;
                        case 'b':
                            double layoutZ5 = 115;
                            double layoutW5 = 390;
                            portaAvionesCounterEnemigo--;
                            System.out.println("Si entro");
                            System.out.println(hitCountPortaAviones);
                            portaAvion.addRedCircle(layoutZ5, layoutW5);
                            break;
                        case 'c':
                            System.out.println("Si entro");
                            changePaneColor("103", Color.BLUE);
                            break;
                        case 'd':
                            System.out.println("Si entro");
                            changePaneColor("104", Color.BLUE);
                            break;
                        case 'e':
                            System.out.println("Si entro");
                            changePaneColor("105", Color.BLUE);
                            break;
                        case 'f':
                            System.out.println("Si entro");
                            changePaneColor("106", Color.BLUE);
                            break;
                        case 'g':
                            System.out.println("Si entro");
                            changePaneColor("107", Color.BLUE);
                            break;
                        case 'h':
                            System.out.println("Si entro");
                            changePaneColor("108", Color.BLUE);
                            break;
                        case 'i':
                            System.out.println("Si entro");
                            changePaneColor("109", Color.BLUE);
                            break;
                        case 'j':
                            System.out.println("Si entro");
                            changePaneColor("1010", Color.BLUE);
                            break;
                    }

                    if (hitCountPortaAviones == 0) {
                        double layoutX = 115;
                        double layoutY = 290;
                        double layoutY1 = 325;
                        double layoutY2 = 355;
                        double layoutY3 = 390;
                        portaAvion.removeAircraftCarrier(layoutX, layoutY, layoutY1, layoutY2, layoutY3);
                    }
                }
            } else if (input.length() == 2) {
                char letter2 = input.charAt(0);
                char number3 = input.charAt(1);

                if (letter2 == 'b') {
                    System.out.println("Si entro");
                    switch (number3) {
                        case '1':

                            System.out.println("Si entro");
                            changePaneColor("12", Color.BLUE);

                            break;
                        case '2':

                            System.out.println("Si entro");
                            changePaneColor("22", Color.BLUE);

                            break;
                        case '3':

                            System.out.println("Si entro");
                            changePaneColor("32", Color.BLUE);

                            break;
                        case '4':

                            System.out.println("Si entro");
                            changePaneColor("42", Color.BLUE);

                            break;
                        case '5':

                            System.out.println("Si entro");
                            changePaneColor("52", Color.BLUE);

                            break;
                        case '6':

                            System.out.println("Si entro");
                            changePaneColor("62", Color.BLUE);

                            break;
                        case '7':
                            double layoutZ4 = 115;
                            double layoutW4 = 290;
                            hitCountPortaAviones--;
                            System.out.println("Si entro");
                            System.out.println(hitCountPortaAviones);
                            portaAvion.addRedCircle(layoutZ4, layoutW4);
                            break;
                        case '8':
                            double layoutZ5 = 115;
                            double layoutW5 = 325;
                            hitCountPortaAviones--;
                            System.out.println("Si entro");
                            System.out.println(hitCountPortaAviones);
                            portaAvion.addRedCircle(layoutZ5, layoutW5);
                            break;
                        case '9':
                            double layoutZ6 = 115;
                            double layoutW6 = 355;
                            hitCountPortaAviones--;
                            System.out.println("Si entro");
                            System.out.println(hitCountPortaAviones);
                            portaAvion.addRedCircle(layoutZ6, layoutW6);
                            break;
                    }

                    if (hitCountPortaAviones == 0) {
                        double layoutX = 115;
                        double layoutY = 290;
                        double layoutY1 = 325;
                        double layoutY2 = 355;
                        double layoutY3 = 390;
                        portaAvionesCounterEnemigo--;
                        portaAvion.removeAircraftCarrier(layoutX, layoutY, layoutY1, layoutY2, layoutY3);
                    }

                } else if (letter2 == 'd') {
                    switch (number3){
                        case '1':
                            hitCountFrigate--;
                            System.out.println("Si entro");
                            double layoutX = 179;
                            double layoutY = 100;
                            frigatesCounterEnemigo--;
                            fragata1.removeFrigate(layoutX, layoutY);
                            break;
                        case '2':
                            System.out.println("Si entro");
                            changePaneColor("24", Color.BLUE);
                            break;
                        case '3':
                            System.out.println("Si entro");
                            changePaneColor("34", Color.BLUE);
                            break;
                        case '4':
                            System.out.println("Si entro");
                            changePaneColor("44", Color.BLUE);
                            break;
                        case '5':
                            System.out.println("Si entro");
                            changePaneColor("54", Color.BLUE);
                            break;
                        case '6':
                            System.out.println("Si entro");
                            changePaneColor("64", Color.BLUE);
                            break;
                        case '7':
                            System.out.println("Si entro");
                            changePaneColor("74", Color.BLUE);
                            break;
                        case '8':
                            System.out.println("Si entro");
                            changePaneColor("84", Color.BLUE);
                            break;
                        case '9':
                            System.out.println("Si entro");
                            changePaneColor("94", Color.BLUE);
                            break;
                    }
                } else if (letter2 == 'i') {
                    switch (number3) {
                        case '1':
                            hitCountFrigate1--;
                            System.out.println("Si entro");
                            double layoutX = 339;
                            double layoutY = 100;
                            frigatesCounterEnemigo--;
                            fragata3.removeFrigate(layoutX, layoutY);
                            break;
                        case '2':
                            System.out.println("Si entro");
                            changePaneColor("29", Color.BLUE);
                            break;
                        case '3':
                            System.out.println("Si entro");
                            changePaneColor("39", Color.BLUE);
                            break;
                        case '4':
                            System.out.println("Si entro");
                            changePaneColor("49", Color.BLUE);
                            break;
                        case '5':
                            hitCountFrigate2--;
                            System.out.println("Si entro");
                            double layoutX1 = 339;
                            double layoutY1 = 230;
                            frigatesCounterEnemigo--;
                            fragata4.removeFrigate(layoutX1, layoutY1);
                            break;
                        case '6':

                            System.out.println("Si entro");
                            changePaneColor("69", Color.BLUE);

                            break;
                        case '7':

                            System.out.println("Si entro");
                            changePaneColor("79", Color.BLUE);

                            break;
                        case '8':

                            System.out.println("Si entro");
                            changePaneColor("89", Color.BLUE);

                            break;
                        case '9':

                            System.out.println("Si entro");
                            changePaneColor("99", Color.BLUE);

                            break;
                    }
                } else if (letter2 == 'f') {
                    switch (number3) {
                        case '1':
                            System.out.println("Si entro");
                            changePaneColor("16", Color.BLUE);
                            break;
                        case '2':
                            System.out.println("Si entro");
                            changePaneColor("26", Color.BLUE);
                            break;
                        case '3':
                            System.out.println("Si entro");
                            changePaneColor("36", Color.BLUE);
                            break;
                        case '4':
                            System.out.println("Si entro");
                            changePaneColor("46", Color.BLUE);
                            break;
                        case '5':
                            System.out.println("Si entro");
                            changePaneColor("56", Color.BLUE);
                            break;
                        case '6':
                            double layoutZ1 = 244;
                            double layoutW1 = 265;
                            hitCountSubmarine1--;
                            submarino2.addRedCircle(layoutZ1, layoutW1);
                            break;
                        case '7':
                            double layoutZ2 = 244;
                            double layoutW2 = 295;
                            hitCountSubmarine1--;
                            submarino2.addRedCircle(layoutZ2, layoutW2);
                            break;
                        case '8':
                            double layoutZ3 = 244;
                            double layoutW3 = 325;
                            hitCountSubmarine1--;
                            System.out.println("Si entro");
                            System.out.println(hitCountSubmarine1);
                            submarino2.addRedCircle(layoutZ3, layoutW3);
                            break;
                        case '9':
                            System.out.println("Si entro");
                            changePaneColor("96", Color.BLUE);
                            break;

                    }

                    if (hitCountSubmarine1 == 0) {
                        System.out.println("Total tiros: " + hitCountSubmarine1);
                        double layoutX3 = 244;
                        double layoutY3 = 265;
                        double layoutY4 = 295;
                        double layoutY5 = 325;
                        submarineCounterEnemigo--;
                        submarino2.removeSubmarine(layoutX3, layoutY3, layoutY4, layoutY5);
                    }
                } else if (letter2 == 'c') {
                    switch (number3) {
                        case '1':

                            System.out.println("Si entro");
                            changePaneColor("13", Color.BLUE);

                            break;
                        case '2':

                            System.out.println("Si entro");
                            changePaneColor("23", Color.BLUE);

                            break;
                        case '3':
                            double layoutZ4 = 149;
                            double layoutW4 = 165;
                            hitCountSubmarine2++;
                            System.out.println("Si entro");
                            System.out.println(hitCountSubmarine2);
                            submarino1.addRedCircle(layoutZ4, layoutW4);
                            break;
                        case '4':
                            double layoutZ5 = 149;
                            double layoutW5 = 195;
                            hitCountSubmarine2++;
                            System.out.println("Si entro");
                            System.out.println(hitCountSubmarine2);
                            submarino1.addRedCircle(layoutZ5, layoutW5);
                            break;
                        case '5':
                            double layoutZ6 = 149;
                            double layoutW6 = 225;
                            hitCountSubmarine2++;
                            System.out.println("Si entro");
                            System.out.println(hitCountSubmarine2);
                            submarino1.addRedCircle(layoutZ6, layoutW6);
                            break;
                        case '6':

                            System.out.println("Si entro");
                            changePaneColor("63", Color.BLUE);

                            break;
                        case '7':

                            System.out.println("Si entro");
                            changePaneColor("73", Color.BLUE);

                            break;
                        case '8':

                            System.out.println("Si entro");
                            changePaneColor("83", Color.BLUE);

                            break;
                        case '9':

                            System.out.println("Si entro");
                            changePaneColor("93", Color.BLUE);

                            break;
                    }

                    if (hitCountSubmarine2 == 3) {
                        System.out.println("Total tiros: " + hitCountSubmarine2);
                        double layoutX3 = 149;
                        double layoutY3 = 165;
                        double layoutY4 = 195;
                        double layoutY5 = 225;
                        submarineCounterEnemigo--;
                        submarino1.removeSubmarine(layoutX3, layoutY3, layoutY4, layoutY5);
                    }
                    if (hitCountSubmarine1 == 0) {
                        System.out.println("Total tiros: " + hitCountSubmarine1);
                        double layoutX3 = 244;
                        double layoutY3 = 265;
                        double layoutY4 = 295;
                        double layoutY5 = 325;
                        submarino2.removeSubmarine(layoutX3, layoutY3, layoutY4, layoutY5);
                    }
                } else if (letter2=='a') {
                    switch(number3){
                        case '1':
                            hitCountDestroyer1++;
                            System.out.println("Si entro");
                            double layoutZ7 = 85;
                            double layoutW7 = 100;
                            destructores1.addRedCircle(layoutZ7, layoutW7);
                            break;
                        case '2':
                            hitCountDestroyer1++;
                            System.out.println("Si entro");
                            double layoutZ8 = 85;
                            double layoutW8 = 130;
                            destructores1.addRedCircle(layoutZ8, layoutW8);
                            break;
                        case '3':

                            System.out.println("Si entro");
                            changePaneColor("31", Color.BLUE);

                            break;
                        case '4':

                            System.out.println("Si entro");
                            changePaneColor("41", Color.BLUE);

                            break;
                        case '5':

                            System.out.println("Si entro");
                            changePaneColor("51", Color.BLUE);

                            break;
                        case '6':

                            System.out.println("Si entro");
                            changePaneColor("61", Color.BLUE);

                            break;
                        case '7':

                            System.out.println("Si entro");
                            changePaneColor("71", Color.BLUE);

                            break;
                        case '8':

                            System.out.println("Si entro");
                            changePaneColor("81", Color.BLUE);

                            break;
                        case '9':

                            System.out.println("Si entro");
                            changePaneColor("91", Color.BLUE);

                            break;

                    }
                    if (hitCountDestroyer1 == 2) {
                        double layoutX4=85;
                        double layoutY5=100;
                        double layoutY6=130;
                        destroyerCounterEnemigo--;
                        destructores1.removeDestroyer(layoutX4,layoutY5,layoutY6);
                    }
                } else if (letter2 == 'h') {
                    switch(number3){
                        case '1':
                            System.out.println("Si entro");
                            changePaneColor("18", Color.BLUE);
                            break;
                        case '2':
                            hitCountDestroyer2++;
                            System.out.println("Vida Destroyer 2: "+hitCountDestroyer2);
                            double layoutZ9 = 309;
                            double layoutW9 = 130;
                            destructores2.addRedCircle(layoutZ9, layoutW9);
                            break;
                        case '3':
                            hitCountDestroyer2++;
                            System.out.println("Vida Destroyer 2: "+hitCountDestroyer2);
                            double layoutZ10 = 309;
                            double layoutW10 = 165;
                            destructores2.addRedCircle(layoutZ10, layoutW10);
                            break;
                        case '4':
                            System.out.println("Si entro");
                            changePaneColor("48", Color.BLUE);
                            break;
                        case '5':
                            System.out.println("Si entro");
                            changePaneColor("58", Color.BLUE);
                            break;
                        case '6':
                            System.out.println("Si entro");
                            changePaneColor("68", Color.BLUE);
                            break;
                        case '7':
                            System.out.println("Si entro");
                            changePaneColor("78", Color.BLUE);
                            break;
                        case '8':
                            System.out.println("Si entro");
                            changePaneColor("88", Color.BLUE);
                            break;
                        case '9':
                            hitCountFrigate++;
                            System.out.println("Si entro");
                            double layoutX2 = 308;
                            double layoutY2 = 356;
                            fragata2.removeFrigate(layoutX2, layoutY2);
                            break;
                    }
                    if (hitCountDestroyer2 == 2) {
                        double layoutX5 = 309;
                        double layoutY6 = 130;
                        double layoutY7 = 165;
                        destroyerCounterEnemigo--;
                        destructores2.removeDestroyer(layoutX5, layoutY6, layoutY7);
                    }
                } else if (letter2=='e') {
                    switch (number3){
                        case '1':
                            System.out.println("Si entro");
                            changePaneColor("15", Color.BLUE);
                            break;
                        case '2':
                            System.out.println("Si entro");
                            changePaneColor("25", Color.BLUE);
                            break;
                        case '3':
                            System.out.println("Si entro");
                            changePaneColor("35", Color.BLUE);
                            break;
                        case '4':
                            System.out.println("Si entro");
                            changePaneColor("45", Color.BLUE);
                            break;
                        case '5':
                            System.out.println("Si entro");
                            changePaneColor("55", Color.BLUE);
                            break;
                        case '6':
                            System.out.println("Si entro");
                            changePaneColor("65", Color.BLUE);
                            break;
                        case '7':
                            System.out.println("Si entro");
                            changePaneColor("75", Color.BLUE);
                            break;
                        case '8':
                            System.out.println("Si entro");
                            changePaneColor("85", Color.BLUE);
                            break;
                        case '9':
                            System.out.println("Si entro");
                            changePaneColor("95", Color.BLUE);
                            break;
                    }
                } else if (letter2=='g') {
                    switch (number3){
                        case '1':
                            System.out.println("Si entro");
                            changePaneColor("17", Color.BLUE);
                            break;
                        case '2':
                            System.out.println("Si entro");
                            changePaneColor("27", Color.BLUE);
                            break;
                        case '3':
                            System.out.println("Si entro");
                            changePaneColor("37", Color.BLUE);
                            break;
                        case '4':
                            System.out.println("Si entro");
                            changePaneColor("47", Color.BLUE);
                            break;
                        case '5':
                            System.out.println("Si entro");
                            changePaneColor("57", Color.BLUE);
                            break;
                        case '6':
                            System.out.println("Si entro");
                            changePaneColor("67", Color.BLUE);
                            break;
                        case '7':
                            System.out.println("Si entro");
                            changePaneColor("77", Color.BLUE);
                            break;
                        case '8':
                            System.out.println("Si entro");
                            changePaneColor("87", Color.BLUE);
                            break;
                        case '9':
                            System.out.println("Si entro");
                            changePaneColor("97", Color.BLUE);
                            break;
                    }
                } else if (letter2=='j') {
                    switch (number3){
                        case '1':
                            System.out.println("Si entro");
                            changePaneColor("110", Color.BLUE);
                            break;
                        case '2':
                            System.out.println("Si entro");
                            changePaneColor("210", Color.BLUE);
                            break;
                        case '3':
                            System.out.println("Si entro");
                            changePaneColor("310", Color.BLUE);
                            break;
                        case '4':
                            System.out.println("Si entro");
                            changePaneColor("410", Color.BLUE);
                            break;
                        case '5':
                            System.out.println("Si entro");
                            changePaneColor("510", Color.BLUE);
                            break;
                        case '6':
                            System.out.println("Si entro");
                            changePaneColor("610", Color.BLUE);
                            break;
                        case '7':
                            System.out.println("Si entro");
                            changePaneColor("710", Color.BLUE);
                            break;
                        case '8':
                            System.out.println("Si entro");
                            changePaneColor("810", Color.BLUE);
                            break;
                        case '9':
                            System.out.println("Si entro");
                            changePaneColor("910", Color.BLUE);
                            break;
                    }
                }
            }
        }
    }


    private void showPopupMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void changePaneColor(String paneId, Color color) {
        Pane parent = (Pane) gameBoard.getParent();
        Pane pane = (Pane) parent.lookup("#" + paneId);
        if (pane != null) {
            pane.setStyle("-fx-background-color: #" + color.toString().substring(2));
        }
    }
    private void changePaneColor1(String paneId, Color color) {
        Pane parent = (Pane) gameBoardOne.getParent();
        Pane pane = (Pane) parent.lookup("#" + paneId);
        if (pane != null) {
            pane.setStyle("-fx-background-color: #" + color.toString().substring(2));
        }
    }

    private void addCircleToPane(String paneId, Color color) {
        Pane parent = (Pane) gameBoardOne.getParent();
        Pane pane = (Pane) parent.lookup("#" + paneId);
        if (pane != null) {
            // Create a Circle
            Circle circle = new Circle();
            circle.setRadius(Math.min(pane.getWidth(), pane.getHeight()) / 4); // Set radius to a quarter of the smaller dimension
            circle.setFill(color);

            // Position the Circle in the center of the Pane
            circle.setLayoutX(pane.getWidth() / 2);
            circle.setLayoutY(pane.getHeight() / 2);

            // Add the Circle to the Pane
            pane.getChildren().add(circle);
        }
    }
    public Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;


            }
        }
        return null;

    }
    private DraggableMaker draggableMaker = new DraggableMaker();
    private Submarine submarino1,submarino2, submarino1_boardOne, submarino2_boardOne;
    private Destroyer destructores1,destructores2;
    private AircraftCarrier portaAvion, portaAvion_boardOne;
    private FiguresCreator base, base_boardOne;
    private Frigate fragata1,fragata2,fragata3,fragata4,fragata1_boardOne, fragata2_boardOne, fragata3_boardOne, fragata4_boardOne;

    private void addCircleToPane(GridPane gridPane, int col, int row) {
        Pane cellPane = (Pane) getNodeFromGridPane(gridPane, col, row);
        if (cellPane != null) {
            double centerX = cellPane.getPrefWidth() / 2;
            double centerY = cellPane.getPrefHeight() / 2;

            Circle redCircle = new Circle(centerX, centerY, 5); // radius 5, adjust as needed
            redCircle.setFill(Color.RED);

            cellPane.getChildren().add(redCircle);
        } else {
            System.out.println("No node found at column " + col + " and row " + row);
        }
    }


    public void initialize() {




        // Initialize boats for gameBoard
        destructores1 = new Destroyer();
        destructores1.setLayoutX(67);
        destructores1.setLayoutY(85);


        destructores2 = new Destroyer();
        destructores2.setLayoutX(291);
        destructores2.setLayoutY(117);

        portaAvion = new AircraftCarrier();
        portaAvion.setLayoutx(99);
        portaAvion.setLayouty(274);

        submarino1 = new Submarine();
        submarino1.setLayoutX(131);
        submarino1.setLayoutY(146);

        submarino2 = new Submarine();
        submarino2.setLayoutX(227);
        submarino2.setLayoutY(242);

        base = new FiguresCreator();

        fragata1 = new Frigate();
        fragata1.setLayoutX(163);
        fragata1.setLayoutY(85);
        fragata2 = new Frigate();
        fragata2.setLayoutX(291);
        fragata2.setLayoutY(341);
        fragata3 = new Frigate();
        fragata3.setLayoutX(323);
        fragata3.setLayoutY(85);
        fragata4 = new Frigate();
        fragata4.setLayoutX(323);
        fragata4.setLayoutY(213);


        // Add boats to basicPane (gameBoard)
        basicPane.getChildren().addAll(portaAvion.getAircraftCarrier(), fragata1.getFrigate(), fragata2.getFrigate(), fragata3.getFrigate(), fragata4.getFrigate(), submarino1.getSubmarine(), submarino2.getSubmarine(), destructores1.getDestroyer(), destructores2.getDestroyer());

        // Initialize boats for gameBoardOne
        destructores1_boardOne = new Destroyer();
        destructores1_boardOne.setLayoutX(795);
        destructores1_boardOne.setLayoutY(55);

        destructores2_boardOne = new Destroyer();
        destructores2_boardOne.setLayoutX(833);
        destructores2_boardOne.setLayoutY(55);

        portaAvion_boardOne = new AircraftCarrier();
        portaAvion_boardOne.setLayoutx(795);
        portaAvion_boardOne.setLayouty(135);

        submarino1_boardOne = new Submarine();
        submarino1_boardOne.setLayoutX(795);
        submarino1_boardOne.setLayoutY(275);

        submarino2_boardOne = new Submarine();
        submarino2_boardOne.setLayoutX(835);
        submarino2_boardOne.setLayoutY(290);

        base_boardOne = new FiguresCreator();

        fragata1_boardOne = new Frigate();
        fragata1_boardOne.setLayoutX(835);
        fragata1_boardOne.setLayoutY(140);
        fragata2_boardOne = new Frigate();
        fragata2_boardOne.setLayoutX(835);
        fragata2_boardOne.setLayoutY(175);
        fragata3_boardOne = new Frigate();
        fragata3_boardOne.setLayoutX(835);
        fragata3_boardOne.setLayoutY(210);
        fragata4_boardOne = new Frigate();
        fragata4_boardOne.setLayoutX(835);
        fragata4_boardOne.setLayoutY(245);

        // Add boats to gameBoardOne
        basicPane.getChildren().addAll(portaAvion_boardOne.getAircraftCarrier(), fragata1_boardOne.getFrigate(), fragata2_boardOne.getFrigate(), fragata3_boardOne.getFrigate(), fragata4_boardOne.getFrigate(), submarino1_boardOne.getSubmarine(), submarino2_boardOne.getSubmarine(), destructores1_boardOne.getDestroyer(), destructores2_boardOne.getDestroyer());

        // Create content for gameBoard


        int gridSize = 11; // Tamaño de la cuadrícula
        int paneSize = 352 / gridSize; // Tamaño de cada pane
        int offset = 10;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Pane pane = new Pane();
                pane.setId(String.valueOf(i).concat(String.valueOf(j)));
                pane.setPrefWidth(paneSize);
                pane.setPrefHeight(paneSize);
                pane.setStyle("-fx-background-color: rgb(177,194,255);" +
                        "-fx-border-color: rgba(0,0,0,0.7);" +
                        "-fx-stroke-type: inside;");

                double xPosition = j * paneSize;
                double yPosition = i * paneSize;

                pane.setLayoutX(xPosition);
                pane.setLayoutY(yPosition);

                gameBoard.getChildren().add(pane);

                if (i == 0 && j == 0) {
                    // Top-left corner, leave it empty
                    continue;
                } else if (i == 0) {
                    // First row, add letters 'A' to 'J'
                    Label label = new Label(String.valueOf((char) ('A' + j - 1)));
                    label.setStyle("-fx-text-fill: purple;" +
                            "-fx-alignment: center;" +
                            "-fx-font-weight: bold;");
                    pane.setStyle("-fx-background-color: rgb(150,120,180);" +
                            "-fx-border-color: rgb(5,19,75);" +
                            "-fx-stroke-type: inside;" +
                            "-fx-alignment: center");
                    pane.getChildren().add(label);
                } else if (j == 0) {
                    // First column, add numbers '1' to '9'
                    Label label = new Label(String.valueOf(i));
                    label.setStyle("-fx-text-fill: purple;" +
                            "-fx-alignment: center;" +
                            "-fx-font-weight: bold;");
                    pane.setStyle("-fx-background-color: rgb(150,120,180);" +
                            "-fx-border-color: rgb(5,19,75);" +
                            "-fx-stroke-type: inside;" +
                            "-fx-alignment: center");
                    pane.getChildren().add(label);
                } else {
                    pane.setOnMouseEntered(event -> {
                        pane.setStyle("-fx-background-color: rgb(173,173,173,0.66);" +
                                "-fx-border-color: rgba(0,0,0,0.7);" +
                                "-fx-stroke-type: inside;");
                    });

                    pane.setOnMouseExited(event -> {
                        pane.setStyle("-fx-background-color: rgb(177,194,255);" +
                                "-fx-border-color: rgba(0,0,0,0.7);" +
                                "-fx-stroke-type: inside;");
                    });

                    pane.setOnMouseClicked(event -> {
                        System.out.println("La posicion en x es: " + pane.getLayoutX() + "" +
                                ", y es: " + pane.getLayoutY() +
                                ". El id es " + pane.getId());
                    });

                }
            }
        }

        // Create content for gameBoardOne
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Pane pane = new Pane();
                pane.setId(String.valueOf(i).concat(String.valueOf(j))); // Unique ID for gameBoardOne
                pane.setPrefWidth(paneSize);
                pane.setPrefHeight(paneSize);
                pane.getStyleClass().add("game-board-pane");



                double xPosition = (j * paneSize);
                double yPosition = (i * paneSize);


                pane.setLayoutX(xPosition);
                pane.setLayoutY(yPosition);

                gameBoardOne.getChildren().add(pane);

                if (i == 0 && j == 0) {
                    // Top-left corner, leave it empty
                    continue;
                } else if (i == 0) {
                    // First row, add letters 'A' to 'J'
                    Label label = new Label(String.valueOf((char) ('A' + j - 1)));
                    label.setStyle("-fx-text-fill: purple;" +
                            "-fx-alignment: center;" +
                            "-fx-font-weight: bold;");
                    pane.setStyle("-fx-background-color: rgb(150,120,180);" +
                            "-fx-border-color: rgb(5,19,75);" +
                            "-fx-stroke-type: inside;" +
                            "-fx-alignment: center");
                    pane.getChildren().add(label);
                } else if (j == 0) {
                    // First column, add numbers '1' to '9'
                    Label label = new Label(String.valueOf(i));
                    label.setStyle("-fx-text-fill: purple;" +
                            "-fx-alignment: center;" +
                            "-fx-font-weight: bold;");
                    pane.setStyle("-fx-background-color: rgb(150,120,180);" +
                            "-fx-border-color: rgb(5,19,75);" +
                            "-fx-stroke-type: inside;" +
                            "-fx-alignment: center");
                    pane.getChildren().add(label);
                } else {
                    pane.setOnMouseEntered(event -> {
                        pane.setStyle("-fx-background-color: rgb(173,173,173,0.66);" +
                                "-fx-border-color: rgba(0,0,0,0.7);" +
                                "-fx-stroke-type: inside;");
                    });

                    pane.setOnMouseExited(event -> {
                        pane.setStyle("-fx-background-color: rgb(177,194,255);" +
                                "-fx-border-color: rgba(0,0,0,0.7);" +
                                "-fx-stroke-type: inside;");
                    });

                    pane.setOnMouseClicked(event -> {
                        System.out.println("La posicion en x es: " + pane.getLayoutX() + "" +
                                ", y es: " + pane.getLayoutY() +
                                ". El id es " + pane.getId());
                    });

                }
            }
        }


        // Make objects draggable in gameBoardOne
        draggableMaker.makeDraggable(portaAvion_boardOne.getAircraftCarrier());
        draggableMaker.makeDraggable(fragata1_boardOne.getFrigate());
        draggableMaker.makeDraggable(fragata2_boardOne.getFrigate());
        draggableMaker.makeDraggable(fragata3_boardOne.getFrigate());
        draggableMaker.makeDraggable(fragata4_boardOne.getFrigate());
        draggableMaker.makeDraggable(submarino1_boardOne.getSubmarine());
        draggableMaker.makeDraggable(submarino2_boardOne.getSubmarine());
        draggableMaker.makeDraggable(destructores1_boardOne.getDestroyer());
        draggableMaker.makeDraggable(destructores2_boardOne.getDestroyer());
        makeDraggableAndSetDropEvents(portaAvion_boardOne.getAircraftCarrier());
        makeDraggableAndSetDropEvents(fragata1_boardOne.getFrigate());
        makeDraggableAndSetDropEvents(fragata2_boardOne.getFrigate());
        makeDraggableAndSetDropEvents(fragata3_boardOne.getFrigate());
        makeDraggableAndSetDropEvents(fragata4_boardOne.getFrigate());
        makeDraggableAndSetDropEvents(submarino1_boardOne.getSubmarine());
        makeDraggableAndSetDropEvents(submarino2_boardOne.getSubmarine());
        makeDraggableAndSetDropEvents(destructores1_boardOne.getDestroyer());
        makeDraggableAndSetDropEvents(destructores2_boardOne.getDestroyer());

    }
    private void makeDraggableAndSetDropEvents(Node node) {
        // Set up onDragDetected event for draggable objects
        node.setOnDragDetected(event -> {
            Dragboard db = node.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(node.getId()); // Put the ID of the node on the clipboard
            db.setContent(content);
            System.out.println("La posicion en x es: " + node.getId());
            event.consume();

        });

        // Set up onDragOver event for target panes
        gameBoardOne.getChildren().forEach(pane -> {
            pane.setOnDragOver(event -> {
                if (event.getGestureSource() != pane && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    System.out.println("La posicion en x es: " + node.getId());
                }
                event.consume();
            });

            // Set up onDragDropped event for target panes
            pane.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    // Assign the dropped object's ID to the pane
                    pane.setId(db.getString());
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
                System.out.println("Dropped object with ID: " + db.getString() + " onto pane with ID: " + pane.getId());
            });
        });
    }





    public Pane getGameBoard() {
        return gameBoard;
    }






}
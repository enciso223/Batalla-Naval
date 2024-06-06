package org.example.batallanaval1.model;
import javafx.scene.layout.Pane;


public class FiguresCreator {

    private Pane pane;
    private int id;


    public FiguresCreator(){

        this.id = id;
        pane = new Pane();
        pane.setPrefWidth(32);
        pane.setPrefHeight(32);
        pane.setStyle("-fx-background-color: rgb(255,255,255);" +
                "-fx-border-color: rgba(0,0,0,0.7);" +
                "-fx-stroke-type: inside;");

    }

    public Pane getPane(){
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
    public void setLayoutX(double x){
        pane.setLayoutX(x);
    }
    public void setLayoutY(double y){
        pane.setLayoutY(y);
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}


}
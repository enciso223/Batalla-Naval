package org.example.batallanaval1.model;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Destroyer {
    private Polygon destroyer;

    private double layoutX,layoutY;
    private int destroyerHit=2;
    private int destroyerHitCount=2;

    public Destroyer(){
        destroyer = new Polygon(
                0,32,
                16,0,
                32,32,
                32,64,
                0, 64

        );
        destroyer.getStyleClass().add("boat");
        destroyer.getStyleClass().add("destroyer");

        // Agrega un evento de click al destructor para detectar clics del usuario
        destroyer.setOnMouseClicked(this::handleMouseClick);
    }
    public Polygon getDestroyer(){return destroyer;}

    public void setDestroyer(Polygon destroyer) {
        this.destroyer = destroyer;
    }

    public double getLayoutX(){return layoutX;}
    public double getLayoutY(){return layoutY;}

    public void setLayoutX(double x){this.layoutX = layoutX;
        destroyer.setLayoutX(x);}
    public void setLayoutY(double y){this.layoutY = layoutY;
        destroyer.setLayoutY(y);}

    private void handleMouseClick(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.SECONDARY) { // Right button
            handleRotarClick(mouseEvent);
        } else if (mouseEvent.getButton() == MouseButton.PRIMARY) { // Left button
            destroyerHit(mouseEvent);
            destroyerHit--;
            destroyerHitCount--;
        }
    }

    private void handleRotarClick(javafx.scene.input.MouseEvent mouseEvent) {
        destroyer.getTransforms().add(new javafx.scene.transform.Rotate(90, 16, 48));
        System.out.println("Destructor Rotado");

    }
    public int getDestroyerHit() {
        return destroyerHit;
    }
    private void destroyerHit(javafx.scene.input.MouseEvent mouseEvent) {


        System.out.println("Destroyer vida" + destroyerHit);

        if (destroyerHit == 0) {
            double layoutX = 307;
            double layoutY = 132;
            double layoutY1 = 162;


        }
    }

    public void removeDestroyer(double layoutX, double layoutY,double layoutY1)  {
        Pane parent = (Pane) destroyer.getParent();
        if (parent != null) {
            parent.getChildren().remove(destroyer);
            System.out.println("Destroyer destroyed");
            addBlackCircle(parent, layoutX, layoutY,layoutY1);
        }
    }

    private void addBlackCircle(Pane pane, double layoutX, double layoutY, double layoutY1) {
        // Create and configure the black circle
        Circle circle = new Circle(10);
        Circle circle1 = new Circle(10);

        circle.setFill(Color.BLACK);
        circle1.setFill(Color.BLACK);

        circle.setLayoutX(layoutX);
        circle.setLayoutY(layoutY);

        circle1.setLayoutX(layoutX);
        circle1.setLayoutY(layoutY1);

        System.out.println(layoutX);
        System.out.println(layoutY);


        // Add the circle to the pane
        pane.getChildren().add(circle);
        pane.getChildren().add(circle1);

    }
    public void addRedCircle(double layoutX, double layoutY) {
        // Create and configure the black circle
        Pane parent = (Pane) destroyer.getParent();
        Circle circle = new Circle(10);


        circle.setFill(Color.RED);


        circle.setLayoutX(layoutX);
        circle.setLayoutY(layoutY);


        System.out.println(layoutX);
        System.out.println(layoutY);


        // Add the circle to the pane
        parent.getChildren().add(circle);


    }

}
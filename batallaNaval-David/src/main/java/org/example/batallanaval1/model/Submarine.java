package org.example.batallanaval1.model;


import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class Submarine {
    private Polygon submarine;
    private double layoutX, layoutY;

    public Submarine() {
        submarine = new Polygon(
                0, 32,
                16, 0,
                32, 32,
                32, 96,
                0, 96

        );
        submarine.getStyleClass().add("boat");
        submarine.getStyleClass().add("submarine");

        // Agrega un evento de click al submarino para detectar clics del usuario
        submarine.setOnMouseClicked(this::handleSubmarineClick);
    }

    public Polygon getSubmarine() {
        return submarine;
    }

    public void setSubmarine(Polygon submarine) {
        this.submarine = submarine;
    }

    public double getLayoutX() {
        return layoutX;
    }

    public void setLayoutX(double layoutX) {
        this.layoutX = layoutX;
        submarine.setLayoutX(layoutX);
    }

    public double getLayoutY() {
        return layoutY;
    }

    public void setLayoutY(double layoutY) {
        this.layoutY = layoutY;
        submarine.setLayoutY(layoutY);
    }

    // Método para manejar el evento de click en el submarino
    private void handleSubmarineClick(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.SECONDARY) { // botón derecho
            // Rotar el submarino cuando se hace clic derecho
            rotateClockwise();
            System.out.println("Submarino rotado ");
        }
    }

    // Método para rotar el submarino
    private void rotateClockwise() {
        Rotate rotate = new Rotate(90, submarine.getBoundsInLocal().getWidth() / 2, submarine.getBoundsInLocal().getHeight() / 2);
        submarine.getTransforms().add(rotate);
    }

    public void removeSubmarine(double layoutX, double layoutY,double layoutY1, double layoutY2) {
        Pane parent = (Pane) submarine.getParent();
        if (parent != null) {
            // Remove the aircraft carrier
            parent.getChildren().remove(submarine);
            System.out.println("Submarine destroyed");

            // Add a black circle at the position where the aircraft carrier was located
            addBlackCircle(parent, layoutX, layoutY,layoutY1,layoutY2);
        }
    }


    private void addBlackCircle(Pane pane, double layoutX, double layoutY, double layoutY1, double layoutY2) {
        // Create and configure the black circle
        Circle circle = new Circle(10);
        Circle circle1 = new Circle(10);
        Circle circle2 = new Circle(10);

        circle.setFill(Color.BLACK);
        circle1.setFill(Color.BLACK);
        circle2.setFill(Color.BLACK);

        circle.setLayoutX(layoutX);
        circle.setLayoutY(layoutY);
        circle1.setLayoutX(layoutX);
        circle1.setLayoutY(layoutY1);
        circle2.setLayoutX(layoutX);
        circle2.setLayoutY(layoutY2);

        System.out.println(layoutX);
        System.out.println(layoutY);


        // Add the circle to the pane
        pane.getChildren().add(circle);
        pane.getChildren().add(circle1);
        pane.getChildren().add(circle2);

    }
    public void addRedCircle(double layoutX, double layoutY) {
        // Create and configure the black circle
        Pane parent = (Pane) submarine.getParent();
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

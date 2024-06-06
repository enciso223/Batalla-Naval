package org.example.batallanaval1.model;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;


public class AircraftCarrier {
    private Polygon aircraftCarrier, airCraftCarrier1;
    private DraggableMaker draggableMaker = new DraggableMaker();
    private double layouty;
    private double layoutx;
    private int hitCarrier = 4;
    private int id;


    public AircraftCarrier() {


        aircraftCarrier = new Polygon(
                0, 32,//A
                16, 0,//B
                32, 32,//C
                32, 128,
                0, 128

        );

        //layoutX="472.0" layoutY="87.0"
        aircraftCarrier.getStyleClass().add("boat");
        aircraftCarrier.getStyleClass().add("aircraft-carrier");

        // Agrega un evento de click al PortaAvion para detectar clics del usuario
        aircraftCarrier.setOnMouseClicked(this::handleRotarClick);


    }


    public Polygon getAircraftCarrier() {
        return aircraftCarrier;
    }

    public void setAircraftCarrier(Polygon aircraftCarrier) {
        this.aircraftCarrier = aircraftCarrier;
    }

    public double getLayouty() {
        return layouty;
    }

    public void setLayouty(double layouty) {
        this.layouty = layouty;
        aircraftCarrier.setLayoutY(layouty);
    }

    public double getLayoutx() {
        return layoutx;
    }

    public void setLayoutx(double layoutx) {
        this.layoutx = layoutx;
        aircraftCarrier.setLayoutX(layoutx);
    }

    public int getHitCarrier() {
        return hitCarrier;
    }

    // Método para manejar el evento de click en los portaaviones
    private void handleRotarClick(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.SECONDARY) { // botón derecho
            // Rotar el submarino cuando se hace clic derecho
            aircraftCarrier.getTransforms().add(new javafx.scene.transform.Rotate(90, 16, 48));
            System.out.println("PortaAviones rotado");
            getLayoutx();
            getLayouty();
            System.out.println("Layoutx: " + getLayoutx());
            System.out.println("Layouty: " + getLayouty());
        }
    }



    public void removeAircraftCarrier(double layoutX, double layoutY,double layoutY1, double layoutY2, double layoutY3) {
        Pane parent = (Pane) aircraftCarrier.getParent();
        if (parent != null) {
            // Remove the aircraft carrier
            parent.getChildren().remove(aircraftCarrier);
            System.out.println("Aircraft Carrier destroyed");

            // Add a black circle at the position where the aircraft carrier was located
            addBlackCircle(parent, layoutX, layoutY,layoutY1,layoutY2,layoutY3);
        }
    }


    private void addBlackCircle(Pane pane, double layoutX, double layoutY, double layoutY1, double layoutY2, double layoutY3) {
        // Create and configure the black circle
        Circle circle = new Circle(10);
        Circle circle1 = new Circle(10);
        Circle circle2 = new Circle(10);
        Circle circle3 = new Circle(10);
        circle.setFill(Color.BLACK);
        circle1.setFill(Color.BLACK);
        circle2.setFill(Color.BLACK);
        circle3.setFill(Color.BLACK);
        circle.setLayoutX(layoutX);
        circle.setLayoutY(layoutY);
        circle1.setLayoutX(layoutX);
        circle1.setLayoutY(layoutY1);
        circle2.setLayoutX(layoutX);
        circle2.setLayoutY(layoutY2);
        circle3.setLayoutX(layoutX);
        circle3.setLayoutY(layoutY3);
        System.out.println(layoutX);
        System.out.println(layoutY);


        // Add the circle to the pane
        pane.getChildren().add(circle);
        pane.getChildren().add(circle1);
        pane.getChildren().add(circle2);
        pane.getChildren().add(circle3);
    }
    public void addRedCircle(double layoutX, double layoutY) {
        // Create and configure the black circle
        Pane parent = (Pane) aircraftCarrier.getParent();
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


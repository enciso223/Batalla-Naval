package org.example.batallanaval1.model;

import javafx.scene.Node;

public class DraggableMaker {
    private double posMouseX = 0, posMouseY = 0;
    private int parentWidth = 900;  // Ancho del AnchorPane
    private int parentHeight = 585; // Alto del AnchorPane
    private final double POSITION_X1 = 69;
    private final double POSITION_X2 = 101;
    private final double POSITION_X3 = 133;
    private final double POSITION_X4 = 165;
    private final double POSITION_X5 = 197;
    private final double POSITION_X6 = 229;
    private final double POSITION_X7 = 261;
    private final double POSITION_X8 = 293;
    private final double POSITION_X9 = 325;
    private final double POSITION_X10 = 357;
    private final double POSITION_Y1 = 85;
    private final double POSITION_Y2 = 117;
    private final double POSITION_Y3 = 149;
    private final double POSITION_Y4 = 181;
    private final double POSITION_Y5 = 213;
    private final double POSITION_Y6 = 245;
    private final double POSITION_Y7 = 277;
    private final double POSITION_Y8 = 309;
    private final double POSITION_Y9 = 341;
    private final double POSITION_Y10 = 373;
    private final double POSITION_X11 = 470;
    private final double POSITION_X12 = 503;
    private final double POSITION_X13 = 530;
    private final double POSITION_X14 = 566;
    private final double POSITION_X15 = 598;
    private final double POSITION_X16 = 630;
    private final double POSITION_X17 = 663;
    private final double POSITION_X18 = 695;
    private final double POSITION_X19 = 727;
    private final double POSITION_X20 = 759;
    private final double POSITION_X21 = 789;
    private final double POSITION_Y11 = 85;
    private final double POSITION_Y12 = 85;
    private final double POSITION_Y13 = 85;
    private final double POSITION_Y14 = 85;
    private final double POSITION_Y15 = 85;
    private final double POSITION_Y16 = 85;
    private final double POSITION_Y17 = 85;
    private final double POSITION_Y18 = 85;
    private final double POSITION_Y19 = 85;
    private final double POSITION_Y20 = 85;
    private final double POSITION_Y21 = 405;
    private double closestX;
    private double closestY;

    public void makeDraggable(Node node) {
        node.setOnMousePressed(mouseEvent -> {
            // Guardar la posición del mouse cuando se presiona el botón
            posMouseX = mouseEvent.getSceneX() - node.getLayoutX();
            posMouseY = mouseEvent.getSceneY() - node.getLayoutY();
            System.out.println(posMouseX);
            System.out.println(posMouseY);
        });

        node.setOnMouseDragged(mouseEvent -> {
            // Calcular nuevas posiciones
            double newX = mouseEvent.getSceneX() - posMouseX;
            double newY = mouseEvent.getSceneY() - posMouseY;
            System.out.println(newX);
            System.out.println(newY);

            // Asegurarse de que el nodo no se salga del AnchorPane
            if (newX >= 0 && newX <= parentWidth - node.getBoundsInParent().getWidth()) {
                node.setLayoutX(newX);
            }
            if (newY >= 0 && newY <= parentHeight - node.getBoundsInParent().getHeight()) {
                node.setLayoutY(newY);
            }
        });

        node.setOnMouseReleased(mouseEvent -> {
            // Restablecer el desplazamiento cuando se suelta el mouse
            posMouseX = 0;
            posMouseY = 0;
            // Ajustar la posición a la más cercana
            adjustToClosestPosition(node);


        });

    }

    public double getPosMouseX() {
        return posMouseX;
    }

    public double getPosMouseY() {
        return posMouseY;
    }

    public void setPosMouseX(double posMouseX) {
        this.posMouseX = posMouseX;
    }

    public void setPosMouseY(double posMouseY) {
        this.posMouseY = posMouseY;
    }

    public double getClosestX() {
        return closestX;
    }

    public double getClosestY() {
        return closestY;
    }

    public void adjustToClosestPosition(Node node) {
        double currentY = node.getLayoutY();
        double[] positionsY = {POSITION_Y1, POSITION_Y2, POSITION_Y3,POSITION_Y4,POSITION_Y5,POSITION_Y6,POSITION_Y7,POSITION_Y8,POSITION_Y9,POSITION_Y10,POSITION_Y11,POSITION_Y12,POSITION_Y13,POSITION_Y14,POSITION_Y15,POSITION_Y16,POSITION_Y17,POSITION_Y18,POSITION_Y19,POSITION_Y20}; // Agrega aquí todas las posiciones Y fijas

        double closestY = positionsY[0]; // Inicializa closestY con la primera posición
        double minDifferenceY = Math.abs(currentY - positionsY[0]); // Calcula la diferencia absoluta con la primera posición

        for (int i = 1; i < positionsY.length; i++) {
            double difference = Math.abs(currentY - positionsY[i]); // Calcula la diferencia absoluta con la posición actual del bucle
            if (difference < minDifferenceY) { // Si la diferencia es menor que la mínima registrada hasta ahora
                closestY = positionsY[i]; // Actualiza closestY con la posición actual
                minDifferenceY = difference; // Actualiza la mínima diferencia registrada
            }
        }

        double currentX = node.getLayoutX();
        double[] positionsX = {POSITION_X1, POSITION_X2, POSITION_X3, POSITION_X4, POSITION_X5, POSITION_X6, POSITION_X7, POSITION_X8, POSITION_X9, POSITION_X10,POSITION_X11,POSITION_X12,POSITION_X13,POSITION_X14,POSITION_X15,POSITION_X16,POSITION_X17,POSITION_X18,POSITION_X19,POSITION_X20};
        double closestX = positionsX[0];
        double minDifferenceX = Math.abs(currentX - positionsX[0]);

        for (int i = 1; i < positionsX.length; i++) {
            double differenceX = Math.abs(currentX - positionsX[i]);
            if (differenceX < minDifferenceX) {
                closestX = positionsX[i];
                minDifferenceX = differenceX;
            }
        }
        node.setLayoutY(closestY);
        node.setLayoutX(closestX);
        System.out.println("posicion mas cercana designada x:"+closestX);
        System.out.println("posicion mas cercana designada y"+closestY);
    }


    }




package org.example.batallanaval1.model;

import org.example.batallanaval1.model.FileCRUD;

public class Logger {
    private static FileCRUD fileCRUD;
    public static int gameCount = 0;
    public static void initialize(String fileName) {
        fileCRUD = new FileCRUD(fileName);
    }

    public static void log(String message) {
        System.out.println(message);
        if (fileCRUD != null) {
            fileCRUD.writeFile(message);
        } else {
            throw new IllegalStateException("Logger not initialized. Call Logger.initialize() first.");
        }
    }
    public static void startNewGame() {
        gameCount++;

    }
}

package org.example.batallanaval1.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileCRUD {
    private final String fileName;

    public FileCRUD(String fileName){
        this.fileName = fileName;
    }
    public void createFile(String content){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(fileName,true))) {
            bf.write(content);
            bf.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String content) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(fileName, true))) {
            bf.write(content);
            bf.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
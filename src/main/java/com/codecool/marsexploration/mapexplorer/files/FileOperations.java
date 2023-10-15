package com.codecool.marsexploration.mapexplorer.files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {


    private final String fileName;

    public FileOperations(String fileName) {
        this.fileName = fileName;
    }

    public void writeUsingFileWriter(String text) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false))) {
            bw.write(text);
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
}



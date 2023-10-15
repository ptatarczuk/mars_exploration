package com.codecool.marsexploration.mapexplorer.logger;


import com.codecool.marsexploration.mapexplorer.files.FileOperations;

public class FileLogger extends LoggerBase{
   private final FileOperations fileOperations;

    public FileLogger(FileOperations fileOperations) {
        this.fileOperations = fileOperations;
    }

    @Override
    public void log(String message) {
        fileOperations.writeUsingFileWriter(createLogEntry(message));
    }



}

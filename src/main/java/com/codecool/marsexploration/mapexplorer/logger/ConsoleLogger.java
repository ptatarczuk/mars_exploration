package com.codecool.marsexploration.mapexplorer.logger;

public class ConsoleLogger extends LoggerBase {

    @Override
    public void log(String message) {
        System.out.println(createLogEntry(message));
    }

}

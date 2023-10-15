package com.codecool.marsexploration.mapexplorer.logger;

import java.time.LocalDateTime;

public abstract class LoggerBase implements Logger{
    protected static String createLogEntry(String message) {
        return String.format("%s", message);
    }

}

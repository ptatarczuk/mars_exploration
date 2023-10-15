package com.codecool.marsexploration.mapexplorer;

import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationParameters;
import com.codecool.marsexploration.mapexplorer.configuration.Symbol;
import com.codecool.marsexploration.mapexplorer.files.FileOperations;
import com.codecool.marsexploration.mapexplorer.logger.ConsoleLogger;
import com.codecool.marsexploration.mapexplorer.logger.FileLogger;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.simulator.ExplorationSimulator;

import java.util.Arrays;
import java.util.List;

public class Application {
    private static final String workDir = "src/main";

    public static void main(String[] args) {
        String mapFile = workDir + "/resources/exploration-0.map";
        Coordinate landingSpot = new Coordinate(6, 6);

        //  Add your code here
        MapLoader mapLoader = new MapLoaderImpl();

        ConfigurationParameters configurationParameters =
                new ConfigurationParameters(
                        mapFile,
                        landingSpot,
                        List.of(Symbol.WATER),
//                        Arrays.stream(Symbol.values()).toList(),
                        20
                );

        String fileName = workDir + "/resources/result.txt";
        ExplorationSimulator explorationSimulator =
                new ExplorationSimulator(
                        mapLoader,
                        "1", 1, configurationParameters,
                        new ConsoleLogger());
//                        new FileLogger(new FileOperations(fileName)));

        explorationSimulator.runSimulation();

    }
}


package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.List;

public record ConfigurationParameters(String filePath, Coordinate landingCoordinates, List<Symbol> symbols, int simulationSteps) {
}

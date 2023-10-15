package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

import java.util.Arrays;
import java.util.List;

public class ConfigurationValidatorImpl implements ConfigurationValidator {

    @Override
    public boolean validate(ConfigurationParameters configurationParameters, Map map) {
        return isLandingSpotNotOccupied(configurationParameters.landingCoordinates(), map) &&
                areAdjacentCoordinatesNotOccupied(configurationParameters.landingCoordinates(), map) &&
                !isFileMapEmpty(configurationParameters.filePath()) &&
                areResourcesSpecified(configurationParameters.symbols()) &&
                isTimeoutGreaterThanZero(configurationParameters.simulationSteps());
    }

    private boolean isLandingSpotNotOccupied(Coordinate landingCoordinates, Map map) {
        return map.isEmpty(landingCoordinates);
    }

    private boolean areAdjacentCoordinatesNotOccupied(Coordinate landingCoordinates, Map map) {
        return map.getEmptyAdjacentCoordinates(landingCoordinates).size() > 0;
    }

    private boolean isFileMapEmpty(String filePath) {
        return filePath.isEmpty();
    }

    private boolean areResourcesSpecified(List<Symbol> symbols) {
        return Arrays.stream(Symbol.values()).anyMatch(symbols::contains);
    }

    private boolean isTimeoutGreaterThanZero(int simulationSteps) {
        return simulationSteps > 0;
    }
}

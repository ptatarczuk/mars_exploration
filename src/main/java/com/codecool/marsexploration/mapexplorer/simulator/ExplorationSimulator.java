package com.codecool.marsexploration.mapexplorer.simulator;

import com.codecool.marsexploration.mapexplorer.command_center.CommandCenter;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationParameters;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidator;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidatorImpl;
import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.rovers.RoverPlacer;
import com.codecool.marsexploration.mapexplorer.rovers.RoverStatus;
import com.codecool.marsexploration.mapexplorer.simulation_context.SimulationContext;
import com.codecool.marsexploration.mapexplorer.exploration_step.Logging;

import java.util.ArrayList;
import java.util.Arrays;

public class ExplorationSimulator {
    private final MapLoader mapLoader;
    private final String roverId;
    private final int sightRange;
    private final ConfigurationParameters configurationParameters;
    private final ConfigurationValidator configurationValidator;
    private final RoverPlacer roverPlacer;
    private SimulationContext simulationContext;
    private final Logging logging;


    public ExplorationSimulator(MapLoader mapLoader, String roverId, int sightRange,
                                ConfigurationParameters configurationParameters, Logger logger) {
        this.mapLoader = mapLoader;
        this.roverId = roverId;
        this.sightRange = sightRange;
        this.configurationParameters = configurationParameters;
        configurationValidator = new ConfigurationValidatorImpl();
        roverPlacer = new RoverPlacer();
        logging = new Logging(logger);
    }

    public void runSimulation() {
        simulationContext = generateSimulationContext();
        if (simulationContext == null) {
            return;
        }

        while (
                simulationContext.getExplorationOutcome() != ExplorationOutcome.RETURN_TO_SHIP
                        && !(simulationContext.getCommandCenters().size() >= 2
                        && simulationContext.getCommandCenters().stream()
                        .allMatch(commandCenter -> commandCenter.getCoordinatesOfResource().size() == 0))
        ) {
            for (Rover rover : simulationContext.getRovers()) {
                rover.performExplorationStep(simulationContext);
                rover.performBuildingCommandCenterStep(simulationContext);
                rover.performExcavationStep();
            }
            for (CommandCenter commandCenter : simulationContext.getCommandCenters()) {
                commandCenter.performCommandCenterStep();
            }

            logging.performStep(simulationContext);
            simulationContext.decreaseStepsLeft();
        }
    }

    private SimulationContext generateSimulationContext() {
        Map map = mapLoader.load(configurationParameters.filePath());

        if (!map.isSuccessfullyGenerated()) {
            System.out.println("Failed to load the map.");
            return null;
        }

        if (!configurationValidator.validate(configurationParameters, map)) {
            System.out.println("Configuration validation failed");
            return null;
        }

        Rover rover = roverPlacer.placeRover(configurationParameters.landingCoordinates(), map,
                roverId, sightRange);
        rover.setRoverStatus(RoverStatus.EXPLORING);

        return new SimulationContext(
                configurationParameters.simulationSteps(),
                new ArrayList<>(Arrays.asList(rover)),
                configurationParameters.landingCoordinates(),
                map,
                configurationParameters.symbols()
        );
    }

}

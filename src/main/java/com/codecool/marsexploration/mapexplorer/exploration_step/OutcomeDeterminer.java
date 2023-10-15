package com.codecool.marsexploration.mapexplorer.exploration_step;

import com.codecool.marsexploration.mapexplorer.configuration.Symbol;
import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.rovers.RoverStatus;
import com.codecool.marsexploration.mapexplorer.simulation_context.SimulationContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OutcomeDeterminer implements ExplorationStep {

    @Override
    public void performStep(SimulationContext simulationContext, Rover rover) {
        verifyTimeoutReached(simulationContext);
        lookForMonitoredResources(simulationContext, rover);
    }

    private void verifyTimeoutReached(SimulationContext simulationContext) {
        if (simulationContext.getNumberOfStepsLeftToTimeout() == 1) {
            simulationContext.setExplorationOutcome(ExplorationOutcome.TIMEOUT);
        }
    }

    private void lookForMonitoredResources(SimulationContext simulationContext, Rover rover) {
        int sightRange = rover.getSightRange();
        List<String> resourcesToMonitor = simulationContext.getResourcesToMonitor().stream()
                .map(Symbol::getDisplaySymbol)
                .toList();

        Coordinate middlePosition = rover.getCurrentPosition();
        Set<Coordinate> coordinatesToCheck = new HashSet<>();
        for (int i = -sightRange; i <= sightRange; i++) {
            for (int j = -sightRange; j <= sightRange; j++) {
                coordinatesToCheck.add(new Coordinate(middlePosition.X() + i, middlePosition.Y() + j));
            }
        }

        for (Coordinate coordinate : coordinatesToCheck) {
            if (resourcesToMonitor.contains(simulationContext.getMap().getByCoordinate(coordinate))) {
                simulationContext.setExplorationOutcome(ExplorationOutcome.COLONIZABLE);
                rover.setRoverStatus(RoverStatus.CREATING_COMMAND_CENTER);
                rover.setCurrentPosition(coordinate);
                return;
            }
        }
    }

}

package com.codecool.marsexploration.mapexplorer.exploration_step.movement_behaviour;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.simulation_context.SimulationContext;

import java.util.Random;

public class ExploringBehaviour implements MovementBehaviour {

    Random random = new Random();
    @Override
    public void performMovement(SimulationContext simulationContext, Rover rover) {

            int lastIndex = simulationContext.getMap().getDimension() - 1;
            int sightRange = rover.getSightRange();

            int x = sightRange + random.nextInt(lastIndex - (2 * sightRange));
            int y = sightRange + random.nextInt(lastIndex - (2 * sightRange));

            rover.setCurrentPosition(new Coordinate(x, y));
//            simulationContext.addToVisitedCoordinates(simulationContext.getRovers().get(0).getCurrentPosition());
    }

    @Override
    public boolean isApplicable(SimulationContext simulationContext) {
        return simulationContext.getExplorationOutcome() == null;
    }
}

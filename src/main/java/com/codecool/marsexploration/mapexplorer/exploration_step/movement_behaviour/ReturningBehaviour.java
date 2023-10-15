package com.codecool.marsexploration.mapexplorer.exploration_step.movement_behaviour;

import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.simulation_context.SimulationContext;

public class ReturningBehaviour implements MovementBehaviour {
    @Override
    public void performMovement(SimulationContext simulationContext, Rover rover) {
        rover.setCurrentPosition(simulationContext.getSpaceshipLocation());
            simulationContext.setExplorationOutcome(ExplorationOutcome.RETURN_TO_SHIP);
    }

    @Override
    public boolean isApplicable(SimulationContext simulationContext) {
        return simulationContext.getExplorationOutcome() == ExplorationOutcome.TIMEOUT;
    }

}

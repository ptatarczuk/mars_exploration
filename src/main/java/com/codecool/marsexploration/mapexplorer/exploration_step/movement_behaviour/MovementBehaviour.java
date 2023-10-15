package com.codecool.marsexploration.mapexplorer.exploration_step.movement_behaviour;

import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.simulation_context.SimulationContext;

public interface MovementBehaviour {
        void performMovement(SimulationContext simulationContext, Rover rover);
        boolean isApplicable(SimulationContext simulationContext);

}

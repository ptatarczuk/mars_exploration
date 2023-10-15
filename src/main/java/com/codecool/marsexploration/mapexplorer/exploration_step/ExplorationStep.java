package com.codecool.marsexploration.mapexplorer.exploration_step;

import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.simulation_context.SimulationContext;

public interface ExplorationStep {
    void performStep(SimulationContext simulationContext, Rover rover);

}

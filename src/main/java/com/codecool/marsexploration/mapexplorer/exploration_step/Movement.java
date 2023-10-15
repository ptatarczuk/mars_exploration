package com.codecool.marsexploration.mapexplorer.exploration_step;

import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.simulation_context.SimulationContext;
import com.codecool.marsexploration.mapexplorer.exploration_step.movement_behaviour.MovementBehaviour;

import java.util.List;
import java.util.Optional;

public class Movement implements ExplorationStep {

    private final List<MovementBehaviour> behaviors;

    public Movement(List<MovementBehaviour> behaviors) {
        this.behaviors = behaviors;
    }

    @Override
    public void performStep(SimulationContext simulationContext, Rover rover) {
        Optional<MovementBehaviour> behaviourOptional = behaviors.stream()
                .filter(b -> b.isApplicable(simulationContext))
                .findFirst();

        behaviourOptional.ifPresent(movementBehaviour -> movementBehaviour.performMovement(simulationContext, rover));
    }

}

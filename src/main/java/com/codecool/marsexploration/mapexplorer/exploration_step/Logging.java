package com.codecool.marsexploration.mapexplorer.exploration_step;

import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.simulation_context.SimulationContext;

import java.util.ArrayList;
import java.util.List;

public class Logging {
    Logger logger;

    public Logging(Logger logger) {
        this.logger = logger;
    }

    List<String> result = new ArrayList<>();

    public void performStep(SimulationContext simulationContext) {
        result.clear();
        int stepNumber = simulationContext.getNumberOfSteps() - simulationContext.getNumberOfStepsLeftToTimeout();

        for (Rover rover : simulationContext.getRovers()) {
            String roverId = rover.getId();
            int positionX = rover.getCurrentPosition().X();
            int positionY = rover.getCurrentPosition().Y();

            result.add("STEP " + stepNumber + ";  EVENT position; UNIT Rover-" + roverId + " " + "POSITION [" +
                    positionX + "," + positionY + "] " + "\n");
        }

        if (simulationContext.getExplorationOutcome() != null
//                && simulationContext.getExplorationOutcome() == ExplorationOutcome.RETURN_TO_SHIP
        ) {
            result.add("STEP " + stepNumber + ";  EVENT outcome; OUTCOME " +
                    simulationContext.getExplorationOutcome().name() + "\n");
        }

        StringBuilder toPrint = new StringBuilder();
        result.forEach(toPrint::append);

        logger.log(toPrint.toString());


    }
}

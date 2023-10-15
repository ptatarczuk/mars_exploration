package com.codecool.marsexploration.mapexplorer.rovers;

import com.codecool.marsexploration.mapexplorer.command_center.CommandCenter;
import com.codecool.marsexploration.mapexplorer.configuration.Symbol;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.exploration_step.Movement;
import com.codecool.marsexploration.mapexplorer.exploration_step.OutcomeDeterminer;
import com.codecool.marsexploration.mapexplorer.exploration_step.ExplorationStep;
import com.codecool.marsexploration.mapexplorer.exploration_step.movement_behaviour.ExploringBehaviour;
import com.codecool.marsexploration.mapexplorer.exploration_step.movement_behaviour.ReturningBehaviour;
import com.codecool.marsexploration.mapexplorer.simulation_context.SimulationContext;

import java.util.List;

public class Rover {
    private final String id;
    private Coordinate currentPosition;
    private final int sightRange;
    private RoverStatus roverStatus;
    private RoverExcavationStatus roverExcavationStatus;
    private Coordinate excavationPosition;
    private int gatheredResources;
    private final List<ExplorationStep> explorationSteps;
    private CommandCenter commandCenter;

    public Rover(String id, Coordinate currentPosition, int sightRange) {
        this.id = id;
        this.currentPosition = currentPosition;
        this.sightRange = sightRange;

        explorationSteps = List.of(
                new Movement(List.of(new ExploringBehaviour(), new ReturningBehaviour())),
                new OutcomeDeterminer()
        );
    }

    public void performExcavationStep() {
        if (roverStatus == RoverStatus.GATHERING_MATERIALS) {
            if (roverExcavationStatus == RoverExcavationStatus.GO_TO_EXCAVATION) {
                goToExcavationPlace();
                return;
            }
            if (roverExcavationStatus == RoverExcavationStatus.EXCAVATE) {
                excavate();
                return;
            }
            if (roverExcavationStatus == RoverExcavationStatus.GO_TO_BASE) {
                goToCommandCenter();
                return;
            }
            if (roverExcavationStatus == RoverExcavationStatus.DUMP_RESOURCES) {
                dumpResources();
            }
        }
    }

    public void performBuildingCommandCenterStep(SimulationContext simulationContext) {
        if (roverStatus == RoverStatus.CREATING_COMMAND_CENTER) {
            createCommandCenter(simulationContext);
        }
    }

    public void goToExcavationPlace() {
        currentPosition = excavationPosition;
        roverExcavationStatus = RoverExcavationStatus.EXCAVATE;
    }

    public void excavate() {
        gatheredResources++;
        roverExcavationStatus = RoverExcavationStatus.GO_TO_BASE;
    }

    public void goToCommandCenter() {
        currentPosition = commandCenter.getLocation();
        roverExcavationStatus = RoverExcavationStatus.DUMP_RESOURCES;
    }

    public void dumpResources() {
        commandCenter.addResources(gatheredResources);
        gatheredResources = 0;
        roverExcavationStatus = RoverExcavationStatus.GO_TO_EXCAVATION;
    }

    public void createCommandCenter(SimulationContext simulationContext) {
        commandCenter = new CommandCenter(
                simulationContext.getCommandCenters().size() + 1,
                currentPosition,
                5,
                List.of(Symbol.PIT),
                simulationContext
        );
        excavationPosition = commandCenter.getExcavationPosition();
        roverStatus = RoverStatus.GATHERING_MATERIALS;
        roverExcavationStatus = RoverExcavationStatus.GO_TO_EXCAVATION;
        simulationContext.addCommandCenter(commandCenter);
        System.out.println("stworzyłem bazę " + id);
    }

    public void performExplorationStep(SimulationContext simulationContext) {
        if (roverStatus == RoverStatus.EXPLORING) {
            explorationSteps.forEach(explorationStep -> explorationStep.performStep(simulationContext, this));
        }
    }

    public void setRoverStatus(RoverStatus roverStatus) {
        this.roverStatus = roverStatus;
    }

    public int getSightRange() {
        return sightRange;
    }

    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Coordinate currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getId() {
        return id;
    }

    public void setRoverExcavationStatus(RoverExcavationStatus roverExcavationStatus) {
        this.roverExcavationStatus = roverExcavationStatus;
    }

    public void setExcavationPosition(Coordinate excavationPosition) {
        this.excavationPosition = excavationPosition;
    }

    public void setCommandCenter(CommandCenter commandCenter) {
        this.commandCenter = commandCenter;
    }
}

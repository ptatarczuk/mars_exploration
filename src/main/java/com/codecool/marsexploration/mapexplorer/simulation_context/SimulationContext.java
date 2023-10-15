package com.codecool.marsexploration.mapexplorer.simulation_context;

import com.codecool.marsexploration.mapexplorer.command_center.CommandCenter;
import com.codecool.marsexploration.mapexplorer.configuration.Symbol;
import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;

import java.util.ArrayList;
import java.util.List;

public class SimulationContext {
    private final int numberOfSteps;
    private int numberOfStepsLeftToTimeout;
    private List<Rover> rovers;
    private List<CommandCenter> commandCenters;
    private Coordinate spaceshipLocation;
    private Map map;
    private List<Symbol> resourcesToMonitor;
    private ExplorationOutcome explorationOutcome;

    public SimulationContext(int numberOfSteps, List<Rover> rovers, Coordinate spaceshipLocation, Map map, List<Symbol> resourcesToMonitor) {
        this.numberOfSteps = numberOfSteps;
        this.numberOfStepsLeftToTimeout = numberOfSteps;
        this.rovers = rovers;
        this.spaceshipLocation = spaceshipLocation;
        this.map = map;
        this.resourcesToMonitor = resourcesToMonitor;
        commandCenters = new ArrayList<>();
    }

    public void decreaseStepsLeft() {
        numberOfStepsLeftToTimeout--;
    }

    public void setExplorationOutcome(ExplorationOutcome explorationOutcome) {
        this.explorationOutcome = explorationOutcome;
    }
    public ExplorationOutcome getExplorationOutcome() {
        return explorationOutcome;
    }

    public List<Rover> getRovers() {
        return rovers;
    }

    public Map getMap() {
        return map;
    }

    public Coordinate getSpaceshipLocation() {
        return spaceshipLocation;
    }

    public int getNumberOfStepsLeftToTimeout() {
        return numberOfStepsLeftToTimeout;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public List<Symbol> getResourcesToMonitor() {
        return resourcesToMonitor;
    }

    public void addCommandCenter(CommandCenter commandCenter) {
        commandCenters.add(commandCenter);
    }

    public List<CommandCenter> getCommandCenters() {
        return commandCenters;
    }
}

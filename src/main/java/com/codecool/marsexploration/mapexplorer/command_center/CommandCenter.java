package com.codecool.marsexploration.mapexplorer.command_center;

import com.codecool.marsexploration.mapexplorer.configuration.Symbol;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.rovers.RoverExcavationStatus;
import com.codecool.marsexploration.mapexplorer.rovers.RoverStatus;
import com.codecool.marsexploration.mapexplorer.simulation_context.SimulationContext;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CommandCenter {

    private final int RESOURCES_REQUIRED_TO_BUILD_COMMAND_CENTER = 3;
    private final int RESOURCES_REQUIRED_TO_BUILD_ROVER = 2;

    private final int id;
    private final Coordinate location;
    private CommandCenterStatus commandCenterStatus;
    private final int explorationRange;
    private int resources;
    private final Queue<Coordinate> coordinatesOfResource;
    private SimulationContext simulationContext;

    public CommandCenter(int id, Coordinate location, int explorationRange, List<Symbol> resources, SimulationContext simulationContext) {
        this.id = id;
        this.location = location;
        this.commandCenterStatus = CommandCenterStatus.INITIATED;
        this.explorationRange = explorationRange;
        this.resources = 0;
        this.simulationContext = simulationContext;
        this.coordinatesOfResource = lookForResources(resources, simulationContext);
    }

    public void performCommandCenterStep() {
        if(coordinatesOfResource.size() > 0) {
            createNewExcavatingRover();
        } else {
            createNewExplorationRover();
        }
        updateStatus();
    }


    public CommandCenterStatus updateStatus() {

        if (commandCenterStatus == CommandCenterStatus.INITIATED && resources >= RESOURCES_REQUIRED_TO_BUILD_COMMAND_CENTER) {
            commandCenterStatus = CommandCenterStatus.BUILT;
            return commandCenterStatus;
        }
        return commandCenterStatus;
    }


    public void createNewExcavatingRover() {
        if ((commandCenterStatus == CommandCenterStatus.BUILT ) &&
                coordinatesOfResource.size() > 0 &&
                resources > RESOURCES_REQUIRED_TO_BUILD_COMMAND_CENTER + simulationContext.getRovers().size() * RESOURCES_REQUIRED_TO_BUILD_ROVER
        ) {
            Rover rover = new Rover(
                    String.valueOf(simulationContext.getRovers().size() + 1) + "-excavation" + id,
                    location,
                    2
            );
            rover.setRoverStatus(RoverStatus.GATHERING_MATERIALS);
            rover.setRoverExcavationStatus(RoverExcavationStatus.GO_TO_EXCAVATION);
            rover.setExcavationPosition(coordinatesOfResource.poll());
            rover.setCommandCenter(this);

            simulationContext.getRovers().add(rover);
        }
    }

    public void createNewExplorationRover() {
        if(commandCenterStatus != CommandCenterStatus.EXPLOITED &&
                coordinatesOfResource.size() == 0
        ) {
            Rover rover = new Rover(
                    String.valueOf(simulationContext.getRovers().size() + 1)  + "-exploration" + (id + 1),
                    new Coordinate(6, 6),
                    2
            );
            rover.setRoverStatus(RoverStatus.EXPLORING);

            simulationContext.getRovers().add(rover);
            commandCenterStatus = CommandCenterStatus.EXPLOITED;
            simulationContext.setExplorationOutcome(null);
        }
    }

    public void addResources(int quantity) {
        resources += quantity;
    }

    public Coordinate getLocation() {
        return location;
    }

    public Coordinate getExcavationPosition() {
        return coordinatesOfResource.poll();
    }

    public Queue<Coordinate> getCoordinatesOfResource() {
        return coordinatesOfResource;
    }

    private Queue<Coordinate> lookForResources(List<Symbol> resources, SimulationContext simulationContext) {
        List<String> resourcesToMonitor = resources.stream()
                .map(Symbol::getDisplaySymbol)
                .toList();

        int mapSizeX = simulationContext.getMap().getRepresentation().length;
        int mapSizeY = simulationContext.getMap().getRepresentation()[0].length;

        Queue<Coordinate> resourcesCoordinates = new LinkedList<>();

        for (int i = -CommandCenter.this.explorationRange; i <= CommandCenter.this.explorationRange; i++) {
            for (int j = -CommandCenter.this.explorationRange; j <= CommandCenter.this.explorationRange; j++) {
                Coordinate coordinate = new Coordinate(location.X() + i, location.Y() + j);
                if (coordinate.X() >= 0 && coordinate.X() < mapSizeX &&
                        coordinate.Y() >= 0 && coordinate.Y() < mapSizeY) {

                    if (resourcesToMonitor.contains(simulationContext.getMap().getByCoordinate(coordinate))) {
                        resourcesCoordinates.add(coordinate);
                    }
                }
            }
        }

        return resourcesCoordinates;
    }

}

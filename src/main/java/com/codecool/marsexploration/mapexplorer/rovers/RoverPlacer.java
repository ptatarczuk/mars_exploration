package com.codecool.marsexploration.mapexplorer.rovers;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

import java.util.List;
import java.util.Random;

public class RoverPlacer {
    Random random = new Random();

    public Rover placeRover(Coordinate coordinate, Map map, String id, int sightRange) {
        List<Coordinate> emptyAdjacentCoordinates = map.getEmptyAdjacentCoordinates(coordinate);
        int chosenCoordinateIndex = random.nextInt(emptyAdjacentCoordinates.size());

        return new Rover(
                id,
                emptyAdjacentCoordinates.get(chosenCoordinateIndex),
                sightRange
        );
    }

}

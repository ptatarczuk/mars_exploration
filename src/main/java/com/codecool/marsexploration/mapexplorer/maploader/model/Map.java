package com.codecool.marsexploration.mapexplorer.maploader.model;

import com.codecool.marsexploration.mapexplorer.configuration.Symbol;

import java.util.List;

public class Map {
    public boolean isSuccessfullyGenerated() {
        return successfullyGenerated;
    }

    private String[][] representation;
    private boolean successfullyGenerated;

    public Map(String[][] representation, boolean successfullyGenerated) {
        this.representation = representation;
        this.successfullyGenerated = successfullyGenerated;
    }

    public int getDimension() {
        return representation.length;
    }

    private static String createStringRepresentation(String[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (String[] strings : arr) {
            StringBuilder s = new StringBuilder();
            for (String string : strings) {
                s.append(string == null ? " " : string);
            }

            sb.append(s).append("\n");
        }

        return sb.toString();
    }

    public String getByCoordinate(Coordinate coordinate) {
        return representation[coordinate.X()][coordinate.Y()];
    }

    public boolean isEmpty(Coordinate coordinate) {
        return representation[coordinate.X()][coordinate.Y()] == null
                || representation[coordinate.X()][coordinate.Y()].isEmpty()
                || representation[coordinate.X()][coordinate.Y()].equals(Symbol.EMPTY.getDisplaySymbol());
    }

    public List<Coordinate> getEmptyAdjacentCoordinates(Coordinate coordinate) {
        Coordinate c1 = new Coordinate(coordinate.X() + 1, coordinate.Y());
        Coordinate c2 = new Coordinate(coordinate.X() - 1, coordinate.Y());
        Coordinate c3 = new Coordinate(coordinate.X(), coordinate.Y() + 1);
        Coordinate c4 = new Coordinate(coordinate.X(), coordinate.Y() - 1);

        List<Coordinate> coordinatesToCheck = List.of(c1, c2, c3, c4);

        return coordinatesToCheck.stream().filter(this::isEmpty).toList();
    }

    @Override
    public String toString() {
        return createStringRepresentation(representation);
    }

    public String[][] getRepresentation() {
        return representation;
    }
}

package com.codecool.marsexploration.mapexplorer.configuration;

public enum Symbol {
    EMPTY(" "),
    MOUNTAIN("#"),
    WATER("*"),
    PIT("&"),
    MINERAL("%");

    private String displaySymbol;

    Symbol(String displaySymbol) {
        this.displaySymbol = displaySymbol;
    }

    public String getDisplaySymbol() {
        return displaySymbol;
    }

    @Override
    public String toString() {
        return displaySymbol;
    }
}

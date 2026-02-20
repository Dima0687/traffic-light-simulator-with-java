package de.eisner.traffic.enums;

public enum RoadState {
    OPENED("open"), CLOSED("closed");

    private final String currentState;

    RoadState(String currentState) {
        this.currentState = currentState;
    }

    public String getCurrentState() {
        return currentState;
    }
}

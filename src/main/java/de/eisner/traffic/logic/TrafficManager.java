package de.eisner.traffic.logic;

import de.eisner.traffic.model.Road;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Manages the state of the traffic lights and the list of roads.
 * Handles the timing logic and ensures only one road is green at a time.
 */
public class TrafficManager {
    private final List<Road> roads = new CopyOnWriteArrayList<>();
    private final int maxRoads;
    private final int interval;
    private int currentTimeInPhase;
    private int greenIndex = 0;

    /**
     * Constructs a TrafficManager with specified limits and timing.
     * @param maxRoads Maximum number of roads allowed in the system.
     * @param interval Duration of each green light phase in seconds.
     */
    public TrafficManager(int maxRoads, int interval) {
        this.maxRoads = maxRoads;
        this.interval = interval;
        this.currentTimeInPhase = interval;
    }

    /**
     * Updates the internal timer. If a phase ends, it switches the green light
     * to the next road in the queue using modulo arithmetic for circular rotation.
     */
    public void tick() {
        if (roads.isEmpty()) {
            currentTimeInPhase = interval;
            return;
        }

        currentTimeInPhase--;

        if (currentTimeInPhase <= 0) {
            currentTimeInPhase = interval;

            if (roads.size() > 1) {
                greenIndex = (greenIndex + 1) % roads.size();
            } else {
                greenIndex = 0;
            }
        }
    }

    /**
     * Adds a new road to the system.
     * @param name The unique identifier for the road.
     */
    public void addRoad(String name) {
        roads.add(new Road(name));
    }

    /**
     * Removes the first road from the queue and adjusts the green index
     * to prevent index-out-of-bounds or unintended state shifts.
     * @return An Optional containing the removed Road, or empty if the queue was empty.
     */
    public Optional<Road> deleteRoad() {
        if (roads.isEmpty()) return Optional.empty();
        Road removed = roads.removeFirst();

        if (roads.isEmpty()) {
            greenIndex = 0;
            currentTimeInPhase = interval;
        } else {
            if (greenIndex > 0) {
                greenIndex--;
            }
            greenIndex %= roads.size();
        }
        return Optional.of(removed);
    }

    // Helper methods for renderer
    public int getMaxRoads() {
        return maxRoads;
    }
    public List<Road> getRoads() {
        return roads;
    }
    public int getGreenIndex() {
        return greenIndex;
    }
    public int getCurrentTimeInPhase() {
        return currentTimeInPhase;
    }
    public int getInterval() {
        return interval;
    }
}

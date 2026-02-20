package de.eisner.traffic.ui;

import de.eisner.traffic.enums.AnsiColor;
import de.eisner.traffic.enums.RoadState;
import de.eisner.traffic.logic.TrafficManager;
import de.eisner.traffic.model.Road;

/**
 * Visualizes the current state of the traffic system in the terminal.
 */
public class SystemRenderer {

    /**
     * Calculates and prints the status (open/closed) and remaining time for all roads.
     * @param secondsSinceStart Total runtime of the system.
     * @param maxRoads The maximum capacity of the queue.
     * @param manager The logic unit containing the current road states.
     */
    public static void render(int secondsSinceStart, int maxRoads, TrafficManager manager) {
        System.out.printf("""
                        ! %ds. have passed since system startup !
                        ! Number of roads: %d !
                        ! Interval: %d !
                        """,
                secondsSinceStart,
                maxRoads,
                manager.getInterval()
        );

        var roads = manager.getRoads();
        int n = roads.size();
        int g = manager.getGreenIndex();
        int t = manager.getCurrentTimeInPhase();

        for (int actualStreet = 0; actualStreet < n; actualStreet++) {
            int dist = (actualStreet - g + n) % n;
            Road road = roads.get(actualStreet);

            if (dist == 0) {
                printColored(road.name(), RoadState.OPENED, t, AnsiColor.ANSI_GREEN);
            } else {
                int waitTime = (dist - 1) * manager.getInterval() + t;
                if (dist == 1) {
                    printColored(road.name(), RoadState.CLOSED, waitTime, AnsiColor.ANSI_YELLOW);
                } else {
                    printColored(road.name(), RoadState.CLOSED, waitTime, AnsiColor.ANSI_RED);
                }
            }
        }
        System.out.println("! Press \"Enter\" to open menu !");
    }

    private static void printColored(String name, RoadState state, int time, AnsiColor ansiColor) {
        System.out.printf("Road \"%s\" will be %s%s for %ds.%s%n",
                name,
                ansiColor.get(),
                state.getCurrentState(),
                time,
                AnsiColor.ANSI_RESET.get()
        );
    }
}

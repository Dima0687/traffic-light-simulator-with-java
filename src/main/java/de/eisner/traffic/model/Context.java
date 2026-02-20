package de.eisner.traffic.model;

import de.eisner.traffic.logic.TrafficManager;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Global state container for the application.
 * Facilitates communication between the background thread, logic manager, and UI.
 * Uses volatile and atomic variables to ensure thread-safety.
 */
public class Context {
    private final Scanner scanner;
    private final AtomicInteger secondsSinceStart = new AtomicInteger(0);
    private TrafficManager trafficManager;
    private Thread queueThread;
    private volatile boolean systemState = false;
    private volatile boolean running = true;

    public Context(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Initializes the traffic manager with system parameters.
     * @param maxRoads The capacity limit for roads.
     * @param interval The phase duration for the traffic lights.
     */
    public void initManager(int maxRoads, int interval) {
        this.trafficManager = new TrafficManager(maxRoads, interval);
    }

    /**
     * Increments the total system runtime counter.
     */
    public void incrementSeconds() {
        secondsSinceStart.incrementAndGet();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public int getSecondsSinceStart() {
        return secondsSinceStart.get();
    }

    public TrafficManager getTrafficManager() {
        return trafficManager;
    }

    public Thread getQueueThread() {
        return queueThread;
    }

    public void setQueueThread(Thread queueThread) {
        this.queueThread = queueThread;
    }

    public boolean isSystemState() {
        return systemState;
    }

    public void setSystemState(boolean systemState) {
        this.systemState = systemState;
    }

    public boolean isRunning() {
        return running;
    }
}

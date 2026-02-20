package de.eisner.traffic.config;

import de.eisner.traffic.model.Context;
import de.eisner.traffic.ui.SystemRenderer;

import java.util.Scanner;

import static de.eisner.traffic.Main.clearTerminal;

/**
 * Utility class to handle the initial configuration and system startup.
 */
public class Configurer {

    /**
     * Prompts the user for initial settings and initializes the application context.
     * @param scanner The input source for configuration.
     * @return A fully initialized Context object.
     */
    public static Context configure(Scanner scanner) {

        Context ctx = new Context(scanner);

        System.out.print("Input the number of roads: ");
        int roads = getInput(scanner);

        System.out.print("Input the interval: ");
        int interval = getInput(scanner);

        ctx.initManager(roads, interval);

        startQueueThread(ctx);

        return ctx;
    }

    /**
     * Starts the background thread that handles the timing (tick) and
     * dynamic system display.
     * @param ctx The application context.
     */
    private static void startQueueThread(Context ctx) {
        Thread queueThread = new Thread(() -> {
            while (ctx.isRunning()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }

                ctx.incrementSeconds();

                if (ctx.isSystemState()) {
                    clearTerminal();
                    SystemRenderer.render(
                            ctx.getSecondsSinceStart(),
                            ctx.getTrafficManager().getMaxRoads(),
                            ctx.getTrafficManager()
                    );
                }

                ctx.getTrafficManager().tick();
            }
        });

        queueThread.setName("QueueThread");
        queueThread.start();
        ctx.setQueueThread(queueThread);
    }

    private static int getInput(Scanner scanner) {
        boolean correctInput = false;
        int input = 0;
        do {
            try {
                input = Integer.parseInt(scanner.nextLine());

                if (input <= 0) {
                    throw new IllegalArgumentException();
                }

                correctInput = true;
            } catch (IllegalArgumentException e) {
                System.out.print("Error! Incorrect Input. Try again: ");
            }
        } while (!correctInput);
        return input;
    }
}

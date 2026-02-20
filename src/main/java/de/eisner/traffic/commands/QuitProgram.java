package de.eisner.traffic.commands;

import de.eisner.traffic.enums.MenuChoice;
import de.eisner.traffic.model.Context;

import static de.eisner.traffic.enums.MenuChoice.EXIT;

/**
 * Command to terminate the application.
 * Responsible for performing a clean shutdown by closing system resources
 * and interrupting background processes.
 */
public class QuitProgram implements Command {

    /**
     * Executes the shutdown logic.
     * Closes the input scanner, stops the background queue thread,
     * and signals the main loop to terminate.
     * @param ctx The current application context containing the scanner and thread reference.
     * @return {@link MenuChoice#EXIT} to break the main application loop.
     */
    @Override
    public MenuChoice execute(Context ctx) {
        ctx.getScanner().close();
        ctx.getQueueThread().interrupt();

        System.out.println("Bye!");
        return EXIT;
    }
}

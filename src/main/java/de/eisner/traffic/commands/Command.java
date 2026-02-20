package de.eisner.traffic.commands;

import de.eisner.traffic.enums.MenuChoice;
import de.eisner.traffic.model.Context;

/**
 * Interface for application commands following the Command Pattern.
 */
public interface Command {
    /**
     * Executes the specific logic of a menu option.
     * @param ctx The current application context.
     * @return A MenuChoice indicating whether to continue or exit the program.
     */
    MenuChoice execute(Context ctx);
}

package de.eisner.traffic.commands;

import de.eisner.traffic.enums.MenuChoice;
import de.eisner.traffic.model.Context;

import static de.eisner.traffic.enums.MenuChoice.CONTINUE;

/**
 * Command to switch the UI to the live system monitoring mode.
 */
public class OpenSystem implements Command {
    @Override
    public MenuChoice execute(Context ctx) {
        ctx.setSystemState(true);
        ctx.getScanner().nextLine(); // Awaits Enter
        ctx.setSystemState(false);
        return CONTINUE;
    }
}

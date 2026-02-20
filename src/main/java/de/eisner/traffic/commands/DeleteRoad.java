package de.eisner.traffic.commands;

import de.eisner.traffic.enums.MenuChoice;
import de.eisner.traffic.model.Context;

import static de.eisner.traffic.enums.MenuChoice.*;

/**
 * Command to remove the oldest road from the system.
 */
public class DeleteRoad implements Command {
    @Override
    public MenuChoice execute(Context ctx) {
        var removed = ctx.getTrafficManager().deleteRoad();

        if (removed.isPresent()) {
            System.out.println(removed.get().name() + " deleted!");
        } else {
            System.out.println("Queue is empty");
        }

        ctx.getScanner().nextLine();
        return CONTINUE;
    }
}

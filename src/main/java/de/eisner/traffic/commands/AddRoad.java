package de.eisner.traffic.commands;

import de.eisner.traffic.enums.MenuChoice;
import de.eisner.traffic.model.Context;

import static de.eisner.traffic.enums.MenuChoice.*;

/**
 * Command to add a new road to the traffic management system.
 * Checks against the maximum road limit before adding.
 */
public class AddRoad implements Command{
    @Override
    public MenuChoice execute(Context ctx) {
        System.out.println("Input road name: ");
        String name = ctx.getScanner().nextLine();

        if (ctx.getTrafficManager().getRoads().size() < ctx.getTrafficManager().getMaxRoads()) {
            ctx.getTrafficManager().addRoad(name);
            System.out.println(name + " Added!");
        } else {
            System.out.println("Queue is full");
        }
        ctx.getScanner().nextLine();
        return CONTINUE;
    }
}

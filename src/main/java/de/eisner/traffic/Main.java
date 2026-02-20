package de.eisner.traffic;


import de.eisner.traffic.config.Configurer;
import de.eisner.traffic.enums.MenuChoice;
import de.eisner.traffic.model.Context;
import de.eisner.traffic.commands.*;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * Entry point of the Traffic Management System.
 * This class initializes the application context, manages the main menu loop,
 * and dispatches user choices to the corresponding command implementations.
 */
public class Main {

    /**
     * Mapping of menu option numbers to their respective command actions.
     * Implements a simplified Command Pattern for menu navigation.
     */
    private static final Map<Integer, Command> commands = Map.of(
            0, new QuitProgram(),
            1, new AddRoad(),
            2, new DeleteRoad(),
            3, new OpenSystem()
    );

    /**
     * Main application loop. Sets up the environment and processes user input
     * until the program is terminated via the exit command.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the traffic management system!");
        Context ctx = Configurer.configure(scanner);

        while (true) {
            printStartMenu();
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                clearTerminal();
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                if (commands.containsKey(choice)) {
                    if (commands.get(choice).execute(ctx) == MenuChoice.EXIT) {
                        break;
                    }
                } else {
                    System.out.println("Incorrect option.");
                    scanner.nextLine(); // if wrong choice
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect option.");
                scanner.nextLine();
            }
            clearTerminal();
        }
    }

    /**
     * Prints the primary user interface menu options to the console.
     */
    private static void printStartMenu() {
        System.out.println("""
                Menu:
                1. Add road
                2. Delete road
                3. Open System
                0. Quit""");
    }

    /**
     * Clears the console terminal screen based on the operating system.
     * Supports both Windows (via cmd /c cls) and Unix-like systems (via clear).
     */
    public static void clearTerminal() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException _) {
        }
    }
}

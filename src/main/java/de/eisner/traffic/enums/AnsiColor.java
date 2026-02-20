package de.eisner.traffic.enums;

public enum AnsiColor {
    ANSI_RED("\u001B[31m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_RESET("\u001B[0m");

    private final String code;

    AnsiColor(String code) {
        this.code = code;
    }

    public String get() {
        return code;
    }
}

package com.allfuzzer.util;

/**
 * Logger: A simple logging utility with configurable log levels.
 */
public class Logger {
    
    public enum LogLevel {
        ERROR,
        WARNING,
        INFO,
        DEBUG
    };

    private static LogLevel currentLogLevel;

    private static String getDateTime() {
        return java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS"));
    }
    
    /**
     * Set the logging level.
     * @param level The desired logging level (ERROR, WARNING, INFO, DEBUG).
     */
    public static void setLogLevel(LogLevel level) {
        currentLogLevel = level;
    }

    /**
     * Log a message if its level is at or above the current logging level.
     * @param level The level of the message.
     * @param message The message to log.
     */
    public static void log(LogLevel level, String message) {
        if (level.ordinal() <= currentLogLevel.ordinal()) {
            switch (level) {
                case ERROR:
                    error(message);
                    break;
                case WARNING:
                    warning(message);
                    break;
                case INFO:
                    info(message);
                    break;
                case DEBUG:
                    debug(message);
                    break;
                default:
                    info(message);
                    break;
            }
        }
    }

    /**
     * Log a debug message.
     * @param message The message to log.
     */
    public static void debug(String message) {
        if (currentLogLevel.ordinal() < LogLevel.DEBUG.ordinal()) {
            return;
        }
        System.out.println(String.format("[%s] DEBUG: %s", getDateTime(), message));
    }

    /**
     * Log an info message.
     * @param message The message to log.
     */
    public static void info(String message) {
        if (currentLogLevel.ordinal() < LogLevel.INFO.ordinal()) {
            return;
        }
        System.out.println(String.format("[%s] INFO: %s", getDateTime(), message));
    }

    /**
     * Log a warning message.
     * @param message The message to log.
     */
    public static void warning(String message) {
        if (currentLogLevel.ordinal() < LogLevel.WARNING.ordinal()) {
            return;
        }
        System.err.println(String.format("[%s] WARNING: %s", getDateTime(), message));
    }

    /**
     * Log an error message.
     * @param message The message to log.
     */
    public static void error(String message) {
        System.err.println(String.format("[%s] ERROR: %s", getDateTime(), message));
    }

    /**
     * Log an exception with its stack trace.
     * @param e The exception to log.
     */
    public static void error(Exception e) {
        StringBuilder errorMess = new StringBuilder();
        errorMess.append("Exception: ").append(e.getMessage()).append("\n");
        for (StackTraceElement s : e.getStackTrace()) {
            errorMess.append("\t").append(s.toString()).append("\n");
        }
        System.err.println(String.format("[%s] ERROR: %s", getDateTime(), errorMess.toString()));
    }

    static {
        // Default log level
        setLogLevel(LogLevel.INFO);
    }

}

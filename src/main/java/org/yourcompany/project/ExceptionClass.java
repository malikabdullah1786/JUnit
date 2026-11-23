package org.yourcompany.project;

public class ExceptionClass {

    // ✅ Static inner exception classes
    public static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }
    }

    public static class DatabaseConnectionException extends RuntimeException {
        public DatabaseConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // ✅ Checked exception
    public void registerUser(int age) throws InvalidAgeException {
        if (age < 18)
            throw new InvalidAgeException("User must be 18 or older to register");
        System.out.println("User registration successful!");
    }

    // ✅ Cause chain
    public void connectDatabase() {
        try {
            throw new RuntimeException("Network failure");
        } catch (RuntimeException e) {
            throw new DatabaseConnectionException("Database connection failed", e);
        }
    }

    // ✅ Null pointer example
    public String getUserName(String user) {
        if (user == null)
            throw new NullPointerException("User name cannot be null");
        return "Welcome, " + user;
    }
}

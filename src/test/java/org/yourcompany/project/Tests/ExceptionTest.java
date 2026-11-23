package org.yourcompany.project.Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.yourcompany.project.ExceptionClass;
import org.yourcompany.project.ExceptionClass.InvalidAgeException;
import org.yourcompany.project.ExceptionClass.DatabaseConnectionException;

public class ExceptionTest {

    ExceptionClass exceptionDemo = new ExceptionClass();

    // ✅ 1. Exception message verification
    @Test
    void testInvalidAgeMessage() {
        Exception exception = assertThrows(InvalidAgeException.class, () -> {
            exceptionDemo.registerUser(16);
        });
        assertEquals("User must be 18 or older to register", exception.getMessage());
        System.out.println("✔ Exception message verified successfully!");
    }

    // ✅ 2. Exception cause chain testing
    @Test
    void testExceptionCauseChain() {
        DatabaseConnectionException exception =
            assertThrows(DatabaseConnectionException.class, () -> {
                exceptionDemo.connectDatabase();
            });

        // Verify top-level message
        assertEquals("Database connection failed", exception.getMessage());
        // Verify cause message
        assertEquals("Network failure", exception.getCause().getMessage());
        System.out.println("✔ Exception cause chain verified successfully!");
    }

    // ✅ 3. Custom matcher — verify message content pattern
    @Test
    void testCustomMatcherForNullPointer() {
        NullPointerException exception =
            assertThrows(NullPointerException.class, () -> {
                exceptionDemo.getUserName(null);
            });

        // ✅ Custom message matcher logic
        assertTrue(exception.getMessage().toLowerCase().contains("cannot be null"),
                "Exception message should mention null");
        System.out.println("✔ Custom exception matcher verified!");
    }

    // ✅ 4. No exception test (for contrast)
    @Test
    void testRegisterUserSuccess() throws InvalidAgeException {
        assertDoesNotThrow(() -> exceptionDemo.registerUser(20));
        System.out.println("✔ No exception thrown for valid age!");
    }
}

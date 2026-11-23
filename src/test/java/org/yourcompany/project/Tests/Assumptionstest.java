package org.yourcompany.project.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.Test;
import org.yourcompany.project.AssumptionsClass;

public class Assumptionstest {

    AssumptionsClass checker = new AssumptionsClass();

    // ✅ 1. Basic assumeTrue
    @Test
    void testRunOnlyOnWindows() {
        assumeTrue(checker.getOperatingSystem().toLowerCase().contains("windows"),
                () -> "Skipping test: Not running on Windows");
        System.out.println("Test is running on Windows OS");
    }

    // ✅ 2. Basic assumeFalse
    @Test
    void testSkipIfJava8() {
        assumeFalse(checker.getJavaVersion().startsWith("1.8"),
                () -> "Skipping test: Java 8 is not supported");
        System.out.println("Java version is NOT 1.8, continuing test...");
    }

    // ✅ 3. Environment-based assumption
    @Test
    void testOnlyOnStagingEnvironment() {
        assumeTrue("staging".equalsIgnoreCase(checker.getEnvironment()),
                () -> "Skipping test: ENV is not staging");
        System.out.println("Running on staging server");
    }

    // ✅ 4. using assumingThat() → partially execute logic
    @Test
    void testDatabaseConditional() {
        assumingThat(checker.isDatabaseAvailable(), () -> {
            System.out.println("Database available: Running DB tests");
            assertEquals("staging", checker.getEnvironment()); 
        });
        System.out.println("This part runs in all environments");
    }

    // ✅ 5. Custom logic without junit pioneer
    @Test
    void customEnabled() {
        assumeTrue(checker.customCondition(), 
                () -> "Custom condition returned false, skipping test!");
        System.out.println("Custom condition returned true, executing test");
    }

    // ✅ 6. Multiple assumptions in one test
    @Test
    void multipleAssumptions() {
        assumeTrue(checker.getOperatingSystem().contains("Windows"), 
                "OS must be Windows");
        assumeTrue(checker.getJavaVersion().startsWith("17"), 
                "Java must be version 17");
        System.out.println("Both OS and Java version are valid, test continues!");
    }

    // ✅ 7. Fallback example: still verify core logic even if assumption fails
    @Test
    void partialAssumptionTest() {
        assumingThat("production".equalsIgnoreCase(checker.getEnvironment()), () -> {
            System.out.println("Extra checks for production environment");
        });
        System.out.println("Always executed: core functionality validated");
    }

    // ✅ 8. Test failure condition
    @Test
    void testFailedExample() {
    System.out.println("Running a failing test...");

    // ❌ Option 1: Logical failure (expected vs actual mismatch)
    int result = checker.divide(10, 2);
    assertEquals(4, result, "Expected 4 but got something else!");

    // OR
    // ❌ Option 2: Runtime failure (divide by zero)
    // int result = checker.divide(10, 0);
    }
}

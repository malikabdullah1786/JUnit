package org.yourcompany.project;

public class AssumptionsClass {

    public String getOperatingSystem() {
        return System.getProperty("os.name");
    }

    public String getJavaVersion() {
        return System.getProperty("java.version");
    }

    public String getEnvironment() {
        String env = System.getenv("ENV");
        return env != null ? env : "undefined";
    }

    public boolean isDatabaseAvailable() {
        // simulate condition check (change to true to test successful assumption)
        return false;
    }

    public boolean customCondition() {
        // your own logic
        return false; // change to true to allow test to continue
    }

   
    // 🧩 Add this method for failed test
    public int divide(int a, int b) {
        // simple method to test failure condition
        return a / b;
    }
}

package org.yourcompany.project;   


public class ConditionalClass {

    public String getOperatingSystem() {
        return System.getProperty("os.name");
    }

    public String getJavaVersion() {
        return System.getProperty("java.version");
    }

    public String getArchitecture() {
        return System.getProperty("os.arch");
    }

    public String getEnvironment() {
        String env = System.getenv("ENV");
        return env != null ? env : "undefined";
    }

    //required for @EnabledIf and @DisabledIf and junit pioneer
    // // Custom condition method for @EnabledIf and @DisabledIf
    // boolean customCondition() {
    //     // Example: enable test only if current OS contains "Windows"
    //     return getOperatingSystem().toLowerCase().contains("windows");
    // }

    // // Static version (for external referencing)
    // public static boolean staticCustomCondition() {
    //     // Example: enable test only if Java version starts with 17
    //     return System.getProperty("java.version").startsWith("17");
    // }

    public boolean customCondition() {
        // your own logic
        return false; // change to true if you want to run the test case
    }
}

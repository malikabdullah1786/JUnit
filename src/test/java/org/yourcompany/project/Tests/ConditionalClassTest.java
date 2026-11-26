package org.yourcompany.project.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledInNativeImage;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.yourcompany.project.ConditionalClass;

public class ConditionalClassTest {

    ConditionalClass checker = new ConditionalClass();

    // 1️Based on Operating System
    @Test
    @EnabledOnOs(OS.WINDOWS)

    void runOnlyOnWindows() {
        assertTrue(checker.getOperatingSystem().toLowerCase().contains("windows"));
    }

    @Test
    @DisabledOnOs(OS.MAC)
    void skipOnMac() {
        System.out.println("Skipped on macOS!");
    }

    @Test
    @EnabledOnOs({ OS.LINUX, OS.MAC })
    void runOnLinuxOrMac() {
        System.out.println("Running on Linux or Mac!");
    }

    // Based on Java Version
    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void runOnlyOnJava17() {
        assertTrue(checker.getJavaVersion().startsWith("17"));
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_17, max = JRE.JAVA_21)
    void runOnRange() {
        System.out.println("Java version between 17 and 21");
    }

    // Based on Native Image
    @Test
    @EnabledInNativeImage
    void runOnlyInNativeImage() {
        System.out.println("Running in GraalVM native image");
    }

    @Test
    @DisabledInNativeImage
    void skipInNativeImage() {
        System.out.println("Skipped inside native image");
    }

    // Based on System Properties
    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void runOnlyOn64BitMachines() {
        assertTrue(checker.getArchitecture().contains("64"));
    }

    @Test
    @DisabledIfSystemProperty(named = "ci-server", matches = "true")
    void skipOnCIServer() {
        System.out.println("Skipping test on CI server");
    }

    // Based on Environment Variables
    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
    void runOnlyOnStaging() {
        assertEquals("staging-server", checker.getEnvironment());
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "ENV", matches = ".*development.*")
    void skipOnDeveloperMachine() {
        System.out.println("Skipping on developer workstation");
    }

    // this requires use of junit pioneer which i tried to add but couldnt get to
    // work
    // Custom Condition — internal method
    // @Test
    // @EnabledIf("customCondition")
    // void customEnabled() {
    // System.out.println("Custom condition returned true, running test!");
    // }

    // @Test
    // @DisabledIf("customCondition")
    // void customDisabled() {
    // System.out.println("Custom condition returned true, skipping test!");
    // }

    // // Custom Condition — external class
    // @Test
    // @EnabledIf("example.ConditionalClass#staticCustomCondition")
    // void externalCustomCondition() {
    // System.out.println("Static custom condition enabled this test!");
    // }

    // other way to do custom condition without junit pioneer
    @Test
    void customEnabled() {
        if (checker.customCondition()) {
            System.out.println("Custom condition returned true, running test!");
        } else {
            System.out.println("Custom condition false, skipping test!");
            return; // skip
        }
        // rest of your test code
    }
}

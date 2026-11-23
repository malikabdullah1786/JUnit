package org.yourcompany.project.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;


public class RepeatedDemoTest {

    @BeforeEach
    void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        int currentRepetition = repetitionInfo.getCurrentRepetition();
        int totalRepetitions = repetitionInfo.getTotalRepetitions();
        String methodName = testInfo.getDisplayName();
        System.out.println(methodName + " is currently on the iteration " + currentRepetition + "/" + totalRepetitions);

    }
    @RepeatedTest(value=10 ,name="{displayName}")
    @DisplayName("test-1")
    void repeatedTest() {
        // ...
    }
  @RepeatedTest(value=5 ,name="{displayName}")
    @DisplayName("test-2")
    void repeatedTestWithRepetitionInfo(RepetitionInfo repetitionInfo) {
        assertEquals(5, repetitionInfo.getTotalRepetitions());
    }

    // @RepeatedTest(value = 8, failureThreshold = 2)
    // void repeatedTestWithFailureThreshold(RepetitionInfo repetitionInfo) {
    //     // Simulate unexpected failure every second repetition
    //     if (repetitionInfo.getCurrentRepetition() <= 2  ) {
    //         fail("Boom!");
    //     }
    // }

    @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("Repeat!")
    void customDisplayName(TestInfo testInfo) {
        assertEquals("Repeat! 1/1", testInfo.getDisplayName());
    }

    // LONG_DISPLAY_NAME = "{displayName} :: repetition {currentRepetition} of {totalRepetitions}";
    @RepeatedTest(value = 1, name = RepeatedTest.LONG_DISPLAY_NAME) //
    @DisplayName("Details...")
    void customDisplayNameWithLongPattern(TestInfo testInfo) {
        assertEquals("Details... :: repetition 1 of 1", testInfo.getDisplayName());
    }
 

}
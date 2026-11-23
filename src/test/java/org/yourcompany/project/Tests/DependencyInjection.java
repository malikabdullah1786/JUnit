package org.yourcompany.project.Tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;//this import is important for built-in object TestInfo
import org.junit.jupiter.api.TestReporter;//this import is important for built-in object TestReporter
//----@RepeatedInfo is also a part of it 


/**
 * {@link ParameterResolver} that injects a {@link TestReporter}.
 *
 * @since 5.0
 */


import java.util.HashMap;
import java.util.Map;
//-----------TestInfo Demo-----------------
@DisplayName("TestInfo Demo")
@Tag("integration")
class DependencyInjection {

    @BeforeAll
    static void beforeAll(TestInfo testInfo) {
        assertEquals("TestInfo Demo", testInfo.getDisplayName());
        assertEquals("integration", testInfo.getTags().iterator().next());
    }

    DependencyInjection(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        assertTrue(displayName.equals("TEST 1") || displayName.equals("test2()"));
        System.out.println("Constructor called for: " + displayName + " with tags: " + testInfo.getTags());
    }

    @BeforeEach
    void init(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        assertTrue(displayName.equals("TEST 1") || displayName.equals("test2()"));
    }

    @Test
    @DisplayName("TEST 1")
    @Tag("my-tag")
    void test1(TestInfo testInfo) {
        assertEquals("TEST 1", testInfo.getDisplayName());
        assertTrue(testInfo.getTags().contains("my-tag"));
    }

    @Test
    void test2() {
    }

}
//-----------test reporter demo-----------------
class TestReporterDemo {//GitLab CI, Allure Report, and Surefire Report Plugin use that XML.

    @Test
    void reportSingleValue(TestReporter testReporter) {
        testReporter.publishEntry("a status message");
        System.out.println("a status message");

    }

    @Test
    void reportKeyValuePair(TestReporter testReporter) {
        testReporter.publishEntry("a key", "a value");
        System.out.println("key = value");
    }

    @Test
    void reportMultipleKeyValuePairs(TestReporter testReporter) {
        Map<String, String> values = new HashMap<>();
        values.put("user name", "dk38");
        values.put("award year", "1974");
        System.out.println("user name : dk38");
        System.out.println("award year : 1974");
        testReporter.publishEntry(values);
    }
}

// Think of an extension like a plugin in a video game:
// The game (JUnit) runs fine on its own.
// But you can add mods (extensions) to add extra powers and features.
//@ExtendWith is how you turn on that mod in your test.
// @ExtendWith(RandomParametersExtension.class)
// class MyRandomParametersTest {

//     @Test
//     void injectsInteger(@Random int i, @Random int j) {
//         assertNotEquals(i, j);
//     }

//     @Test
//     void injectsDouble(@Random double d) {
//         assertEquals(0.0, d, 1.0);
//     }

// }

package org.yourcompany.project.Tests;

import org.junit.jupiter.api.BeforeAll;
import org.yourcompany.project.*;
import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

@ParameterizedClass
@ValueSource(strings = { "apple", "orange" })
class FruitTests {

    @Parameter
    String fruit;

    @Nested
    @ParameterizedClass
    @ValueSource(ints = { 1, 2 })
    class QuantityTests {

        @Parameter
        int quantity;

        @ParameterizedTest
        @ValueSource(strings = { "duration-1", "duration-2" })
        void test(String duration) {
            System.out.println(fruit + " " + quantity + " " + duration);
        }
    }
}

class nestingTest {

    @Test
    void isInstantiatedWithNew() {
        new nesting<>();
    }

    @Nested
    class WhenNew {

        nesting<Object> stack;

        @BeforeEach
        void createNewStack() {
            System.out.println("before each called");
            stack = new nesting<>();
        }

        @Test
        void isEmpty() {
            assertTrue(stack.isEmpty());
        }

        @Test
        void throwsExceptionWhenPopped() {
            assertThrows(EmptyStackException.class, () -> stack.pop());
        }

        @Test
        void throwsExceptionWhenPeeked() {
            assertThrows(EmptyStackException.class, () -> stack.peek());
        }

        @Nested
        class AfterPushing {

            String anElement = "an element";

            @BeforeEach
            void pushAnElement() {
                System.out.println("Inner before each called");
                stack.push(anElement);
            }

            @Test
            void isNotEmpty() {
                System.out.println("Test-1 of inner");
                assertFalse(stack.isEmpty());
            }

            @Test
            void returnElementWhenPopped() {
                System.out.println("Test-2 of inner");
                assertEquals(anElement, stack.pop());
                assertTrue(stack.isEmpty());
            }

            @Test
            void returnElementWhenPeeked() {
                System.out.println("Test-3 of inner");
                assertEquals(anElement, stack.peek());
                assertFalse(stack.isEmpty());
            }
        }
    }
}

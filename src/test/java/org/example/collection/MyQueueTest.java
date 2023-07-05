package org.example.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MyQueue test")
class MyQueueTest {
    private MyQueue<Integer> myQueue;

    @BeforeEach
    void init() {
        myQueue = new MyQueue<>();
    }

    @Nested
    @DisplayName("add method")
    class AddMethodTests {
        @Test
        @DisplayName("one element increases size by one")
        void add_OneElement_ReturnSizeEqualOne() {
            myQueue.add(1);

            assertEquals(1, myQueue.size());
        }

        @Test
        @DisplayName("null throws NullPointerException")
        void add_Null_ThrowsNullPointerException() {
            assertThrows(
                    NullPointerException.class,
                    () -> myQueue.add(null)
            );
        }
    }

    @Nested
    @DisplayName("poll method")
    class PollMethodTests {
        @Test
        @DisplayName("one element in queue")
        void poll_OneElementInQueue() {
            myQueue.add(1);

            Integer value = myQueue.poll();

            assertEquals(1, value);
            assertEquals(0, myQueue.size());
        }

        @Test
        @DisplayName("from empty queue returns null")
        void poll_FromEmptyQueue_ReturnsNull() {
            Integer value = myQueue.poll();

            assertNull(value);
        }
    }

    @Nested
    @DisplayName("peek method")
    class PeekMethodTests {
        @Test
        @DisplayName("returns first element in queue")
        void peek_ReturnsFirstElementInQueue() {
            myQueue.add(1);

            Integer value = myQueue.peek();

            assertEquals(1, value);
        }

        @Test
        @DisplayName("from empty queue returns null")
        void peek_FromEmptyQueue_ReturnsNull() {
            Integer value = myQueue.peek();

            assertNull(value);
        }
    }

    @ParameterizedTest
    @DisplayName("add elements and check their order")
    @MethodSource("testDataForCheckingRightOrder")
    void checkElementsOrder(int[] arr) {
        for (Integer element: arr) {
            myQueue.add(element);
        }

        assertEquals(arr.length, myQueue.size());

        for (Integer element: arr) {
            assertEquals(element, myQueue.poll());
        }
    }

    static Stream<int[]> testDataForCheckingRightOrder() {
        return Stream.of(
                new int[]{1, 2},
                new int[]{1, 2, 3},
                new int[]{1, 2, 3, 4},
                new int[]{1, 2, 3, 4, 5}
        );
    }

    @Test
    @DisplayName("empty queue returns 0 size")
    void size_ReturnZeroIfQueueIsEmpty() {
        assertEquals(0, myQueue.size());
    }

    @Test
    @DisplayName("isEmpty on empty queue returns true")
    void isEmpty_OnEmptyQueue_ReturnsTrue() {
        assertEquals(0, myQueue.size());
        assertTrue(myQueue.isEmpty());
    }
}
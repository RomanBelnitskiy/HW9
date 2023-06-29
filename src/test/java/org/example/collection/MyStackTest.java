package org.example.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MyStack test")
class MyStackTest {
    private MyStack<Integer> myStack;

    @BeforeEach
    void init() {
        myStack = new MyStack<>();
    }

    @Nested
    @DisplayName("push method")
    class PushMethodTests {
        @Test
        @DisplayName("push an element increases size by one")
        void push_Element_IncreasesSize() {
            myStack.push(1);

            assertEquals(1, myStack.size());
        }

        @Test
        @DisplayName("push an element returns same value")
        void push_Element_ReturnsSAmeValue() {
            Integer value = myStack.push(1);

            assertEquals(1, value);
        }


    }

    @Nested
    @DisplayName("peek method")
    class PeekMethodTests {
        @Test
        @DisplayName("peek returns top element")
        void peek_ReturnsTopElement() {
            myStack.push(1);

            Integer value = myStack.peek();

            assertEquals(1, value);
        }

        @Test
        @DisplayName("peek after pushing two element returns last element")
        void peek_AfterPushingTwoElements_ReturnsLastElement() {
            myStack.push(1);
            myStack.push(2);

            Integer value = myStack.peek();

            assertEquals(2, value);
        }

        @Test
        @DisplayName("peek on empty stack throws EmptyStackException")
        void peek_OnEmptyStack_ThrowsEmptyStackException() {
            assertThrows(
                    EmptyStackException.class,
                    () -> myStack.peek()
            );
        }
    }

    @Nested
    @DisplayName("pop method")
    class PopMethodTests {
        @Test
        @DisplayName("pop decrements size by one")
        void pop_DecrementsSizeByOne() {
            myStack.push(1);

            myStack.pop();

            assertEquals(0, myStack.size());
        }

        @Test
        @DisplayName("pop returns last element")
        void pop_ReturnsLastElement() {
            myStack.push(1);

            Integer value = myStack.pop();

            assertEquals(1, value);
        }

        @Test
        @DisplayName("pop returns pushed elements in reverse order")
        void pop_ReturnsElementsInReverseOrder() {
            myStack.push(1);
            myStack.push(2);

            Integer secondValue = myStack.pop();
            Integer firstValue = myStack.pop();

            assertEquals(1, firstValue);
            assertEquals(2, secondValue);
        }

        @Test
        @DisplayName("pop on empty stack throws EmptyStackException")
        void pop_OnEmptyStack_ThrowsEmptyStackException() {
            assertThrows(
                    EmptyStackException.class,
                    () -> myStack.pop()
            );
        }
    }

    @Test
    @DisplayName("get elements in right order")
    void pop_GetsElementsInRightOrder() {
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);

        Integer third = myStack.pop();
        Integer second = myStack.pop();
        Integer first = myStack.pop();

        assertAll(
                "stack get elements in right order",
                () -> assertEquals(1, first),
                () -> assertEquals(2, second),
                () -> assertEquals(3, third),
                () -> assertTrue(myStack.isEmpty())
        );
    }

    @Nested
    @DisplayName("remove method")
    class RemoveMethodTests {
        @BeforeEach
        void init() {
            myStack.push(1);
            myStack.push(2);
            myStack.push(3);
            myStack.push(4);
        }

        @Test
        @DisplayName("remove with incorrect index throws IndexOutOfBoundException")
        void remove_IncorrectIndex_ThrowsIndexOutOfBoundException() {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> myStack.remove(6)
            );
        }

        @Test
        @DisplayName("remove decrements size by one")
        void remove_DecrementsSizeByOne() {
            myStack.remove(0);

            assertEquals(3, myStack.size());
        }

        @Test
        @DisplayName("remove first index")
        void remove_FirstIndex() {
            myStack.remove(0);

            assertAll(
                    "data after removing first index",
                    () -> assertEquals(4, myStack.pop()),
                    () -> assertEquals(3, myStack.pop()),
                    () -> assertEquals(2, myStack.pop())
            );
        }

        @Test
        @DisplayName("remove last index")
        void remove_LastIndex() {
            myStack.remove(myStack.size() - 1);

            assertAll(
                    "data after removing first index",
                    () -> assertEquals(3, myStack.pop()),
                    () -> assertEquals(2, myStack.pop()),
                    () -> assertEquals(1, myStack.pop())
            );
        }

        @Test
        @DisplayName("remove middle index")
        void remove_MiddleIndex() {
            myStack.remove(2);

            assertAll(
                    "data after removing first index",
                    () -> assertEquals(4, myStack.pop()),
                    () -> assertEquals(2, myStack.pop()),
                    () -> assertEquals(1, myStack.pop())
            );
        }
    }

    @Test
    @DisplayName("clear stack")
    void clear_Stack() {
        myStack.push(1);

        myStack.clear();

        assertEquals(0, myStack.size());
    }
}
package org.example.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MyLinkedList test")
class MyLinkedListTest {
    private MyLinkedList<String> myLinkedList;

    @BeforeEach
    void setUp() {
        myLinkedList = new MyLinkedList<>();
    }

    @Test
    @DisplayName("a new list's size should be zero")
    void size_WithEmptyList_ReturnZero() {
        assertEquals(0, myLinkedList.size());
    }

    @Nested
    @DisplayName("add method")
    class AddMethodTests {
        @Test
        @DisplayName("when add one element size should be equal 1")
        void add_OneElement_ReturnSizeEqualOne() {
            myLinkedList.add("java");
            assertEquals(1, myLinkedList.size());
        }

        @Test
        @DisplayName("when add two elements size should be equal 2")
        void add_TwoElements_ReturnSizeEqualTwo() {
            myLinkedList.add("java");
            myLinkedList.add("core");
            assertEquals(2, myLinkedList.size());
        }

        @Test
        @DisplayName("add two elements and check their indexes")
        void add_TwoElements_SaveElementsInRightIndexes() {
            myLinkedList.add("java");
            myLinkedList.add("core");
            assertEquals("java", myLinkedList.get(0));
            assertEquals("core", myLinkedList.get(1));
        }
    }


    @Nested
    @DisplayName("get method")
    class GetMethodTests {
        @Test
        @DisplayName("returns right element at first position")
        void get_ReturnRightElement_AtFirstPosition() {
            myLinkedList.add("java");
            assertEquals("java", myLinkedList.get(0));
        }

        @Test
        @DisplayName("returns right element at second position")
        void get_ReturnRightElement_AtSecondPosition() {
            myLinkedList.add("java");
            myLinkedList.add("core");
            assertEquals("core", myLinkedList.get(1));
        }

        @Test
        @DisplayName("returns right element at third position")
        void get_ReturnRightElement_AtThirdPosition() {
            myLinkedList.add("java");
            myLinkedList.add("core");
            myLinkedList.add("test");
            assertEquals("test", myLinkedList.get(2));
        }

        @Test
        @DisplayName("try to get non-existent element throws IndexOutOfBoundException")
        void get_NonExistentElement_ThrowsIndexOutOfBoundException() {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> myLinkedList.get(0)
            );
        }
    }

    @Nested
    @DisplayName("remove method")
    class RemoveMethodTests {
        @BeforeEach
        void init() {
            myLinkedList.add("java");
            myLinkedList.add("is");
            myLinkedList.add("the");
            myLinkedList.add("best");
            myLinkedList.add("programming");
            myLinkedList.add("language");
        }
        @Test
        @DisplayName("an element removing decrements size by 1")
        void remove_OneElement_DecrementsSizeByOne() {
            int sizeBeforeRemoving = myLinkedList.size();
            myLinkedList.remove(1);
            int sizeAfterRemoving = myLinkedList.size();

            assertEquals(sizeBeforeRemoving - 1, sizeAfterRemoving);
        }

        @Test
        @DisplayName("when last element has removed the list contains right data")
        void remove_LastElement_ListContainsRightData() {
            myLinkedList.remove(myLinkedList.size() - 1);

            assertAll("right data",
                    () -> assertEquals("java", myLinkedList.get(0)),
                    () -> assertEquals("is", myLinkedList.get(1)),
                    () -> assertEquals("the", myLinkedList.get(2)),
                    () -> assertEquals("best", myLinkedList.get(3)),
                    () -> assertEquals("programming", myLinkedList.get(4))
            );
        }

        @Test
        @DisplayName("when first element has removed the list contains right data")
        void remove_FirstElement_ListContainsRightData() {
            myLinkedList.remove(0);

            assertAll("right data",
                    () -> assertEquals("is", myLinkedList.get(0)),
                    () -> assertEquals("the", myLinkedList.get(1)),
                    () -> assertEquals("best", myLinkedList.get(2)),
                    () -> assertEquals("programming", myLinkedList.get(3)),
                    () -> assertEquals("language", myLinkedList.get(4))
            );
        }

        @Test
        @DisplayName("when an middle element has removed the list contains right data")
        void remove_MiddleElement_ListContainsRightData() {
            myLinkedList.remove(2);

            assertAll("right data",
                    () -> assertEquals("java", myLinkedList.get(0)),
                    () -> assertEquals("is", myLinkedList.get(1)),
                    () -> assertEquals("best", myLinkedList.get(2)),
                    () -> assertEquals("programming", myLinkedList.get(3)),
                    () -> assertEquals("language", myLinkedList.get(4))
            );
        }

        @Test
        @DisplayName("remove last element from the list")
        void remove_LastElementFromList() {
            myLinkedList.remove(myLinkedList.size() - 1);
            myLinkedList.remove(myLinkedList.size() - 1);
            myLinkedList.remove(myLinkedList.size() - 1);
            myLinkedList.remove(myLinkedList.size() - 1);
            myLinkedList.remove(myLinkedList.size() - 1);
            myLinkedList.remove(myLinkedList.size() - 1);

            assertEquals(0, myLinkedList.size());
        }

        @Test
        @DisplayName("try to remove non-existent element throws IndexOutOfBoundException")
        void remove_NonExistentElement_ThrowsIndexOutOfBoundException() {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> myLinkedList.remove(6)
            );
        }
    }

    @Nested
    @DisplayName("clear method")
    class ClearMethodTests {
        @Test
        @DisplayName("call clear method on empty list does nothing")
        void clear_OnEmptyList_DoesNothing() {
            myLinkedList.clear();
        }

        @Test
        @DisplayName("when clear method has called list's size should be zero")
        void clear_OnNonEmptyList_ClearAllElements() {
            myLinkedList.add("java");
            myLinkedList.add("core");

            myLinkedList.clear();

            assertEquals(0, myLinkedList.size());
        }
    }

}
package org.example.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MyArrayList test")
class MyArrayListTest {
    private MyArrayList<String> myArrayList;

    @BeforeEach
    void setUp() {
        myArrayList = new MyArrayList<>();
    }

    @Test
    @DisplayName("a new list's size should be zero")
    void size_WithEmptyList_ReturnZero() {
        assertEquals(0, myArrayList.size());
    }

    @Nested
    @DisplayName("add method")
    class AddMethodTests {
        @Test
        @DisplayName("when add one element size should be equal 1")
        void add_OneElement_ReturnSizeEqualOne() {
            myArrayList.add("java");
            assertEquals(1, myArrayList.size());
        }

        @Test
        @DisplayName("when add two elements size should be equal 2")
        void add_TwoElements_ReturnSizeEqualTwo() {
            myArrayList.add("java");
            myArrayList.add("core");
            assertEquals(2, myArrayList.size());
        }

        @Test
        @DisplayName("add two elements and check their indexes")
        void add_TwoElements_SaveElementsInRightIndexes() {
            myArrayList.add("java");
            myArrayList.add("core");
            assertEquals("java", myArrayList.get(0));
            assertEquals("core", myArrayList.get(1));
        }

        @Test
        @DisplayName("add eleven elements increases list's capacity")
        void add_ElevenElements_IncreasesCapacity() {
            int capacity = myArrayList.getCapacity();

            for (int i = 0; i < 11; i++) {
                myArrayList.add("java");
            }

            assertEquals((int) (capacity * 1.5), myArrayList.getCapacity());
        }
    }


    @Nested
    @DisplayName("get method")
    class GetMethodTests {
        @Test
        @DisplayName("returns right element at the specified position")
        void get_ReturnRightElement() {
            myArrayList.add("java");
            assertEquals("java", myArrayList.get(0));
        }

        @Test
        @DisplayName("try to get non-existent element throws IndexOutOfBoundException")
        void get_NonExistentElement_ThrowsIndexOutOfBoundException() {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> myArrayList.get(0)
            );
        }
    }

    @Nested
    @DisplayName("remove method")
    class RemoveMethodTests {
        @BeforeEach
        void init() {
            myArrayList.add("java");
            myArrayList.add("is");
            myArrayList.add("the");
            myArrayList.add("best");
            myArrayList.add("programming");
            myArrayList.add("language");
        }
        @Test
        @DisplayName("an element removing decrements size by 1")
        void remove_OneElement_DecrementsSizeByOne() {
            int sizeBeforeRemoving = myArrayList.size();
            myArrayList.remove(1);
            int sizeAfterRemoving = myArrayList.size();

            assertEquals(sizeBeforeRemoving - 1, sizeAfterRemoving);
        }

        @Test
        @DisplayName("when last element has removed the list contains right data")
        void remove_LastElement_ListContainsRightData() {
            myArrayList.remove(myArrayList.size() - 1);

            assertAll("right data",
                    () -> assertEquals("java", myArrayList.get(0)),
                    () -> assertEquals("is", myArrayList.get(1)),
                    () -> assertEquals("the", myArrayList.get(2)),
                    () -> assertEquals("best", myArrayList.get(3)),
                    () -> assertEquals("programming", myArrayList.get(4))
            );
        }

        @Test
        @DisplayName("when first element has removed the list contains right data")
        void remove_FirstElement_ListContainsRightData() {
            myArrayList.remove(0);

            assertAll("right data",
                    () -> assertEquals("is", myArrayList.get(0)),
                    () -> assertEquals("the", myArrayList.get(1)),
                    () -> assertEquals("best", myArrayList.get(2)),
                    () -> assertEquals("programming", myArrayList.get(3)),
                    () -> assertEquals("language", myArrayList.get(4))
            );
        }

        @Test
        @DisplayName("when an middle element has removed the list contains right data")
        void remove_MiddleElement_ListContainsRightData() {
            myArrayList.remove(2);

            assertAll("right data",
                    () -> assertEquals("java", myArrayList.get(0)),
                    () -> assertEquals("is", myArrayList.get(1)),
                    () -> assertEquals("best", myArrayList.get(2)),
                    () -> assertEquals("programming", myArrayList.get(3)),
                    () -> assertEquals("language", myArrayList.get(4))
            );
        }

        @Test
        @DisplayName("try to remove non-existent element throws IndexOutOfBoundException")
        void remove_NonExistentElement_ThrowsIndexOutOfBoundException() {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> myArrayList.remove(6)
            );
        }
    }

    @Nested
    @DisplayName("clear method")
    class ClearMethodTests {
        @Test
        @DisplayName("call clear method on empty list does nothing")
        void clear_OnEmptyList_DoesNothing() {
            myArrayList.clear();
        }

        @Test
        @DisplayName("when clear method has called list's size should be zero")
        void clear_OnNonEmptyList_ClearAllElements() {
            myArrayList.add("java");
            myArrayList.add("core");

            myArrayList.clear();

            assertEquals(0, myArrayList.size());
        }
    }

}
package org.example.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("MyHashMap Test")
class MyHashMapTest {
    private MyHashMap<String, Integer> myHashMap;

    @BeforeEach
    void init() {
        myHashMap = new MyHashMap();
    }


    @Nested
    @DisplayName("put method")
    class PutMethodTests {
        @Test
        @DisplayName("put one element increase size")
        void put_IncreaseSize() {
            myHashMap.put("AB", 1);

            assertEquals(1, myHashMap.size());
        }

        @Test
        @DisplayName("put null key throws NPE")
        void put_NullKey_ThrowsNPE() {
            assertThrows(
                    NullPointerException.class,
                    () -> myHashMap.put(null, 1)
            );
        }

        @Test
        @DisplayName("put null value throws NPE")
        void put_NullValue_ThrowsNPE() {
            assertThrows(
                    NullPointerException.class,
                    () -> myHashMap.put("key", null)
            );
        }
    }

    @Nested
    @DisplayName("get method")
    class GetMethodTests {
        @ParameterizedTest
        @DisplayName("get right value")
        @MethodSource("keyValuePairs")
        void get_ReturnsRightValue(String key, Integer value) {
            myHashMap.put(key, value);

            assertEquals(value, myHashMap.get(key));
        }

        static Stream<Arguments> keyValuePairs() {
            return Stream.of(
                    arguments("A", 1),
                    arguments("AB", 2),
                    arguments("ABC", 3),
                    arguments("ABCD", 4),
                    arguments("ABCDE", 5)
            );
        }
    }

    @Test
    @DisplayName("containsKey return true if key exists")
    void containsKey_ReturnsTrueIfKeyExists() {
        myHashMap.put("A", 1);

        assertTrue(myHashMap.containsKey("A"));
    }

    @Test
    @DisplayName("containsKey return false if key doesn't exist")
    void containsKey_ReturnsFalseIfKeyDoesNotExist() {
        assertFalse(myHashMap.containsKey("A"));
    }

    @Test
    @DisplayName("containsValue return true if value exists")
    void containsValue_ReturnsTrueIfValueExists() {
        myHashMap.put("A", 1);

        assertTrue(myHashMap.containsValue(1));
    }

    @Test
    @DisplayName("containsValue return false if value doesn't exist")
    void containsValue_ReturnsFalseIfValueDoesNotExist() {
        assertFalse(myHashMap.containsValue(1));
    }

    @Test
    @DisplayName("size returns 0 when map is empty")
    void size_ReturnsZero() {
        assertEquals(0, myHashMap.size());
    }

    @Test
    @DisplayName("isEmpty returns true when map is empty")
    void isEmpty_ReturnsTrue() {
        assertTrue(myHashMap.isEmpty());
    }

    @Test
    @DisplayName("isEmpty returns false when map isn't empty")
    void isEmpty_ReturnsFalse() {
        myHashMap.put("A", 1);

        assertFalse(myHashMap.isEmpty());
    }

    @Nested
    @DisplayName("remove method")
    class RemoveMethodTests {
        @Test
        @DisplayName("returns value when element has removed")
        void remove_ReturnsRemovedElementValue() {
            myHashMap.put("A", 1);

            assertEquals(1, myHashMap.remove("A"));
        }

        @Test
        @DisplayName("decrease size if elements was removed")
        void remove_DecreaseSize() {
            myHashMap.put("A", 1);

            myHashMap.remove("A");

            assertEquals(0, myHashMap.size());
        }

        @Test
        @DisplayName("returns null when key was not found")
        void remove_ReturnsNull_WhenKeyWasNotFound() {
            assertNull(myHashMap.remove("A"));
        }

        @Test
        @DisplayName("throws NPE when key is null")
        void remove_ThrowsNPE_WhenKeyIsNull() {
            assertThrows(
                    NullPointerException.class,
                    () -> myHashMap.remove(null)
            );
        }
    }

    @Test
    @DisplayName("clear method removes all elements from map")
    void clear_RemovesAllElements() {
        myHashMap.put("A", 1);

        myHashMap.clear();

        assertTrue(myHashMap.isEmpty());
    }

    @ParameterizedTest
    @DisplayName("inspect indexes returning by calculateIndex")
    @MethodSource("testDataForCheckingIndex")
    void calculateIndex_CheckIndexes(String key, int rightIndex) {

        int index = MyHashMap.calculateIndex(key, 8);

        assertEquals(rightIndex, index);
    }

    static Stream<Arguments> testDataForCheckingIndex() {
        return Stream.of(
                arguments("A", 1),
                arguments("B", 2),
                arguments("C", 3),
                arguments("D", 4),
                arguments("E", 5),
                arguments("F", 6),
                arguments("G", 7),
                arguments("H", 0)
        );
    }
}
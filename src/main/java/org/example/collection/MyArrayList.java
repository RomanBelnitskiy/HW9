package org.example.collection;


import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<T> {
    private static final int INITIAL_CAPACITY = 10;

    private Object[] array;
    private int size;

    public MyArrayList() {
        array = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public void add(T value) {
        if (size == array.length) {
            increaseArraySize();
        }

        array[size] = value;
        size++;
    }

    private void increaseArraySize() {
        int newCapacity = array.length + (array.length >> 1);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);

        if (index != size - 1) {
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
        }

        size--;
    }

    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    public int size() {                     // повертає розмір колекції
        return size;
    }

    public T get(int index) {          // повертає елемент за індексом
        Objects.checkIndex(index, size);
        return (T) array[index];
    }

    public int getCapacity() {
        return array.length;
    }
}

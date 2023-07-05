package org.example.collection;

import java.util.Objects;

public class MyQueue<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public void add(T value) {
        Objects.requireNonNull(value);
        Node<T> node = Node.nodeOf(value);

        if (size == 0) {
            first = last = node;
        } else if (size == 1) {
            last = node;
            Node.link(first, last);
        } else {
            Node.link(last, node);
            last = node;
        }
        size++;
    }

    public void clear() {
        first = last = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return first.element;
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }

        T element = first.element;

        if (size == 1) {
            first = last = null;
        } else if (size == 2) {
            first = last;
            first.next = null;
        } else {
            Node<T> nodeToRemove = first;
            first = first.prev;
            first.next = null;
            nodeToRemove.prev = null;
        }

        size--;
        return element;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

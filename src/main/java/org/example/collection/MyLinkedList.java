package org.example.collection;

import java.util.Objects;

public class MyLinkedList<T> {
    private Node<T> first, last;
    private int size;

    public MyLinkedList() {
    }

    public void add(T value) {
        Node<T> node = Node.nodeOf(value);

        if (first == null && last == null) {
            first = last = node;
        } else if (last != null) {
            node.prev = last;
            last.next = node;
            last = node;
        }

        size++;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);

        if (index == 0) {
            if (size == 1) {
                last = first = null;
            } else {
                first = first.next;
                first.prev = null;
            }
        } else if (index == size - 1) {
            last = last.prev;
            last.next = null;
        } else {
            int i = 1;
            Node<T> nodeToRemove = first.next;
            while (i < index) {
                nodeToRemove = nodeToRemove.next;
                i++;
            }

            nodeToRemove.prev.next = nodeToRemove.next;
            nodeToRemove.next.prev = nodeToRemove.prev;
            nodeToRemove.next = null;
            nodeToRemove.prev = null;
        }

        size--;
    }

    public void clear() {
        first = last = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);

        if (index == 0) return first.element;
        if (index == size - 1) return last.element;

        int i = 0;
        Node<T> node = first;

        do {
            node = node.next;
            i++;
        } while (i < index);

        return node.element;
    }
}

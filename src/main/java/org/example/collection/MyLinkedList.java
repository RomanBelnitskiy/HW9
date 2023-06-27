package org.example.collection;

/*
    Напишіть свій клас MyLinkedList як аналог класу LinkedList.

    Не можна використовувати масив. Кожний елемент повинен бути окремим
    об'єктом-посередником (Node - нода), що зберігає посилання на попередній
    та наступний елемент колекції (двозв'язний список).
 */

import java.util.Objects;

public class MyLinkedList<T> {
    private Node<T> first, last;
    private int size;

    public MyLinkedList() {
        first = last = null;
        size = 0;
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

    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> prev;

        Node(T element, Node<T> next, Node<T> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        static <T> Node<T> nodeOf(T element) {
            return new Node<>(element, null, null);
        }

        static <T> Node<T> nodeOf(T element, Node<T> next) {
            return new Node<>(element, next, null);
        }

        static <T> Node<T> nodeOf(T element, Node<T> next, Node<T> prev) {
            return new Node<>(element, next, prev);
        }
    }
}

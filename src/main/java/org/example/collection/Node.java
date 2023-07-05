package org.example.collection;

public class Node<T> {
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

    static <T> Node<T> link(Node<T> first, Node<T> second) {
        first.prev = second;
        second.next = first;

        return first;
    }
}

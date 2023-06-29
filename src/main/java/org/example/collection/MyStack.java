package org.example.collection;

import java.util.EmptyStackException;
import java.util.Objects;

public class MyStack<T> {
    private Node<T> top;
    private Node<T> bottom;
    private int size;

    public MyStack() {

    }

    public T push(T value) {
        Node<T> newNode = Node.nodeOf(value);

        if (size == 0) {
            top = bottom = newNode;
        } else {
            if (size == 1) {
                top = Node.link(newNode, bottom);
            } else {
                top = Node.link(newNode, top);
            }
        }
        size++;
        return value;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        Node<T> nodeToRemove = null;

        if (index == 0) {
            nodeToRemove = bottom;
            if (size == 1) {
                top = bottom = null;
            } else {
                bottom = bottom.next;
                bottom.prev = null;
            }
        } else {
            if (index == size - 1) {
                nodeToRemove = top;
                top = top.prev;
                top.next = null;
            } else {
                nodeToRemove = bottom;
                int i = 0;

                do {
                    nodeToRemove = nodeToRemove.next;
                    i++;
                } while (i != index);

                Node.link(nodeToRemove.next, nodeToRemove.prev);
            }
        }

        nodeToRemove.prev = null;
        nodeToRemove.next = null;

        size--;
    }

    public void clear() {
        top = bottom = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public T peek() {
        ifEmptyThrowException();

        return top.element;
    }

    public T pop() {
        ifEmptyThrowException();
        Node<T> node = top;

        if (size == 1) {
            top = bottom = null;
        } else {
            if (size == 2) {
                top = bottom;
            } else {
                top = top.prev;
            }
            top.next = null;
        }
        node.prev = null;

        size--;
        return node.element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ifEmptyThrowException() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
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

        static <T> Node<T> link(Node<T> first, Node<T> second) {
            first.prev = second;
            second.next = first;

            return first;
        }
    }
}

package org.example.collection;

import static java.util.Objects.requireNonNull;


public class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 8;
    private static final float RESIZE_THRESHOLD = 1.0f;

    private Node<K, V>[] table;
    private int size;

    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashMap(int capacity) {
        verifyCapacity(capacity);
        table = new Node[capacity];
    }

    public static int calculateIndex(Object key, int capacity) {
        int hash = key.hashCode() ^ (key.hashCode() >> 16);
        return hash & (capacity - 1);
    }

    public V put(K key, V value) {
        resizeIfNeeded();
        return putOnTable(table, key, value);
    }

    private void resizeIfNeeded() {
        if (size / (float) table.length > RESIZE_THRESHOLD) {
            resizeTable(2 * table.length);
        }
    }

    private V putOnTable(Node<K, V>[] table, K key, V value) {
        Node<K, V> newNode = Node.of(key, value);
        int index = calculateIndex(key, table.length);

        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node<K, V> node = table[index];
            while (node.next != null) {
                if (node.key.equals(key)) {
                    V oldValue = node.value;
                    node.value = value;
                    return oldValue;
                }
                node = node.next;
            }

            if (node.key.equals(key)) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }

            node.next = newNode;
        }

        size++;

        return null;
    }

    public V remove(K key) {
        int index = calculateIndex(requireNonNull(key), table.length);
        Node<K, V> node = table[index];
        if (node != null) {
            if (node.key.equals(key)) {
                V value = node.value;
                table[index] = node.next;
                size--;
                return value;
            } else {
                while (node.next != null) {
                    if (node.next.key.equals(key)) {
                        V value = node.next.value;
                        node.next = node.next.next;
                        size--;
                        return value;
                    }
                    node = node.next;
                }
            }
        }

        return null;
    }


    public void clear() {
        table = new Node[table.length];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(K key) {
        requireNonNull(key);
        int index = calculateIndex(key, table.length);
        Node<K, V> node = table[index];

        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }

        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public boolean containsValue(V value) {
        for (Node<K, V> head : table) {
            Node<K, V> current = head;

            while (current != null) {
                if (current.value.equals(value)) {
                    return true;
                }
                current = current.next;
            }
        }

        return false;
    }

    private void verifyCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
    }

    public void resizeTable(int newCapacity) {
        verifyCapacity(newCapacity);

        Node<K, V>[] newTable = new Node[newCapacity];
        size = 0;

        for (Node<K, V> head : table) {
            Node<K, V> current = head;

            while (current != null) {
                putOnTable(newTable, current.key, current.value);
                current = current.next;
            }
        }

        table = newTable;
    }

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public static <K, V> Node<K, V> of(K key, V value) {
            return new Node<>(requireNonNull(key), requireNonNull(value));
        }
    }
}

package by.it.group251052.ramazanov.lesson11;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet<E> implements Set<E> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Node<E>[] table;
    private int size;
    private int threshold;

    private static class Node<E> {
        final E key;
        Node<E> next;

        Node(E key, Node<E> next) {
            this.key = key;
            this.next = next;
        }
    }

    @SuppressWarnings("unchecked")
    public MyHashSet() {
        table = (Node<E>[]) new Node[DEFAULT_CAPACITY];
        threshold = (int) (DEFAULT_CAPACITY * LOAD_FACTOR);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        boolean first = true;
        for (Node<E> node : table) {
            while (node != null) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(node.key);
                first = false;
                node = node.next;
            }
        }
        sb.append(']');
        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        @SuppressWarnings("unchecked")
        Node<E>[] newTable = (Node<E>[]) new Node[table.length];
        table = newTable;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E key) {
        int index = indexFor(hash(key));
        Node<E> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return false;
            }
            node = node.next;
        }
        addNode(key, index);
        return true;
    }

    @Override
    public boolean remove(Object key) {
        int index = indexFor(hash(key));
        Node<E> prev = null;
        Node<E> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    table[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return true;
            }
            prev = node;
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean contains(Object key) {
        int index = indexFor(hash(key));
        Node<E> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    private void addNode(E key, int index) {
        Node<E> node = new Node<>(key, table[index]);
        table[index] = node;
        size++;
        if (size >= threshold) {
            resize(2 * table.length);
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Node<E>[] oldTable = table;
        Node<E>[] newTable = (Node<E>[]) new Node[newCapacity];
        threshold = (int) (newCapacity * LOAD_FACTOR);
        table = newTable;
        for (Node<E> node : oldTable) {
            while (node != null) {
                Node<E> next = node.next;
                int index = indexFor(hash(node.key), newCapacity);
                node.next = newTable[index];
                newTable[index] = node;
                node = next;
            }
        }
    }

    private int hash(Object key) {
        int h = key.hashCode();
        return (h ^ (h >>> 16)) & 0x7fffffff;
    }

    private int indexFor(int hash) {
        return hash % table.length;
    }

    private int indexFor(int hash, int length) {
        return hash % length;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E element = it.next();
            if (!c.contains(element)) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            if (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }
}

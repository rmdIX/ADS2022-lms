package by.it.group151051.senko.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class ListA<T> implements List<T> {
    private Object[] data;

    private int size;
    private int count;

    public ListA() {
        size = 20;
        data = new Object[size];
        count = 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public boolean add(T e) {
        data[count++] = e;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    public T remove(int index) {
        T result = (T)data[index];

        Object[] new_data = new Object[size];
        for (int i = 0; i < index; i++) {
            new_data[i] = data[i];
        }
        for (int i = index + 1; i < count; i++) {
            new_data[i-1] = data[i];
        }

        data = new_data;
        count--;

        return result;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    public T get(int index) {
        return (T)data[index];
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append('[');

        if (count > 0) {
            for (int i = 0; i < count - 1; i++) {
                result.append(data[i].toString());
                result.append(',').append(' ');
            }
            result.append(data[count - 1].toString());
        }

        result.append(']');
        return result.toString();
    }
}
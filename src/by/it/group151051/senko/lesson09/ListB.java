package by.it.group151051.senko.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class ListB<T> implements List<T> {
    private Object[] data;

    private int size;
    private int count;

    public ListB() {
        size = 20;
        data = new Object[size];
        count = 0;
    }

    public boolean add(T e) {
        data[count++] = e;

        return true;
    }

    public void add(int index, T element) {
        Object[] new_data = new Object[size];
        for (int i = 0; i < index; i++) {
            new_data[i] = data[i];
        }
        new_data[index] = element;
        for (int i = index; i < count; i++) {
            new_data[i + 1] = data[i];
        }

        data = new_data;
        count++;
    }

    public boolean addAll(Collection<? extends T> c) {
        for (T item : c) {
            add(item);
        }

        return true;
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

    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index < 0) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public T get(int index) {
        return (T)data[index];
    }

    public T set(int index, T element) {
        T result = (T)data[index];

        data[index] = element;

        return result;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < count; i++) {
            if (o == null && data[i] == null) {
                return true;
            }

            if (o.equals(data[i])) {
                return true;
            }
        }

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

    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < count; i++) {
            if (o == null && data[i] == null) {
                return i;
            }

            if (o.equals(data[i])) {
                return i;
            }
        }

        return -1;
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

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append('[');

        if (count > 0) {
            for (int i = 0; i < count - 1; i++) {
                result.append(data[i]);
                result.append(',').append(' ');
            }
            result.append(data[count - 1]);
        }

        result.append(']');
        return result.toString();
    }
}
package by.it.group151051.senko.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


public class SetC<T> implements Set<T> {
    private Object[] data;

    private int size;
    private int count;

    public SetC() {
        size = 20;
        data = new Object[size];
        count = 0;
    }

    public boolean add(T e) {
        if (contains(e)) {
            return false;
        } else {
            data[count++] = e;
            return true;
        }
    }

    public boolean addAll(Collection<? extends T> c) {
        for (T item : c) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
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

    public boolean removeAll(Collection<?> c) {
        for (Object item : c) {
            remove(item);
        }

        return true;
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
            if (o == null) {
                if (data[i] == null) {
                    return true;
                }
            } else {
                if (o.equals(data[i])) {
                    return true;
                }
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

    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    public int size() {
        return count;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < count; i++) {
            if (o == null) {
                if (data[i] == null) {
                    return i;
                }
            } else {
                if (o.equals(data[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    public boolean isEmpty() {
        return !(count > 0);
    }

    public void clear() {
        data = new Object[size];
        count = 0;
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
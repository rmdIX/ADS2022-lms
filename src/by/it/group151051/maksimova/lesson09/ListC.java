package by.it.group151051.maksimova.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ListC<T> implements Set<T> {
    public int size1 = 10;
    public Object[] array = new Object[size1];
    public int size2 = 0;

    @Override
    public int size() {
        return size2;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for (Object element : array) {
            if (element == o) {
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

    @Override
    public boolean add(T t) {
        if (!contains(t)) {
            if (size2 == array.length-1) {
                Object[] newArray = new Object[array.length * 2];
                System.arraycopy(array, 0, newArray, 0, size2);
                array = newArray;
            }
            array[size2++] = t;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        int ind = -1;
        for (int i = 0; i < size2; i++)
            if (array[i] == o)
                ind= i;
        if (ind == -1)
            return false;
        for (int i = ind; i < size2; i++)
            array[i] = array[i + 1];
        array[size2--] = null;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        for (Object o : c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] modifiedArray = new Object[size2 + c.size()];
        System.arraycopy(array, 0, modifiedArray, 0, size2);
        array = modifiedArray;
        for (Object element : c) {
            if (!contains(element))
                array[size2++] = element;
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = true;
        for (Object element: c)
            isRemoved = remove(element);
        return isRemoved;
    }

    @Override
    public void clear() {
        array = new Object[0];
        size2 = 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < size2; i++) {
            str.append(array[i]);
            str.append(", ");
        }
        if (str.length() > 3) {
            str.delete(str.length() - 2, str.length());
        }
        str.append("]");

        return str.toString();
    }
}

package by.it.group151051.goron.lesson09;

import java.util.*;


public class SetC<T> implements Set<T> {
    private final HashMap<T, String> hMap;

    public SetC() {
        hMap = new HashMap<>();
    }

    public String toString() {
        if (size() == 0) {
            return "";
        }

        Object[] hMapArr = hMap.keySet().toArray();

        StringBuilder str = new StringBuilder();
        str.append('[');
        for (int i = 0; i < size() - 1; ++i) {
            str.append(hMapArr[i]).append(',').append(' ');
        }
        str.append(hMapArr[size() - 1]).append(']');

        return str.toString();
    }


    @Override
    public boolean add(T t) {
        String PRES = "PRES";
        return hMap.put(t, PRES) == null;
    }

    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            hMap.remove(o);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean contains(Object o) {
        return hMap.containsKey((T) o);
    }

    @Override
    public int size() {
        return hMap.size();
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T elem : c) {
            add(elem);
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object elem : c) {
            if (!contains(elem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object elem : c) {
            remove(elem);
        }
        return true;
    }

    @Override
    public void clear() {
        hMap.clear();
    }


    /////////////////////////////////////////////////////////////////////////
    //////////         Необязательные к реализации методы          //////////
    /////////////////////////////////////////////////////////////////////////


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
    public boolean retainAll(Collection<?> c) {
        return false;
    }
}

package by.it.group151051.k_maliuk.lesson09;

import java.util.*;

public class ListB<T> implements List<T>
{
    private static final int initSize = 20;
    private int elementsCounter;
    private int sizeOfElements;
    private Object[] objects;

    public ListB()
    {
        elementsCounter = 0;
        sizeOfElements = initSize;
        objects = new Object[sizeOfElements];
    }

    private void realloc()
    {
        sizeOfElements = sizeOfElements * 3 / 2;
        objects = Arrays.copyOf(objects, sizeOfElements);
    }

    @Override
    public int size() {
        return elementsCounter;
    }

    @Override
    public boolean isEmpty() {
        return elementsCounter == 0;
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
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(T element) {
        if (sizeOfElements == elementsCounter)
            realloc();

        objects[elementsCounter++] = element;
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
    public boolean addAll(Collection<? extends T> list) {
        for (T element : list)
            add(element);

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

    @Override
    public T get(int index) {
        return (T)(objects[index]);
    }

    @Override
    public T set(int index, T element)
    {
        T res = (T)(objects[index]);
        objects[index] = element;

        return res;
    }

    @Override
    public void add(int index, T element)
    {
        if (elementsCounter == sizeOfElements)
            realloc();

        int length = elementsCounter - index;

        System.arraycopy(objects, index, objects, index + 1, length);
        objects[index] = element;
        elementsCounter++;
    }

    @Override
    public T remove(int index)
    {
        T result = (T)objects[index];

        int len = elementsCounter - index + 1;

        elementsCounter--;
        System.arraycopy(objects, index + 1, objects, index, len);

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

    @Override
    public String toString()
    {
        StringBuilder temp = new StringBuilder();
        temp.append("[");

        if (elementsCounter != 0)
        {
            for (int i = 0; i < elementsCounter - 1; i++)
            {
                temp.append(objects[i]);
                temp.append(", ");
            }
            temp.append(objects[elementsCounter - 1]);
        }

        temp.append("]");
        return temp.toString();
    }
}

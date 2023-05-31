package by.it.group151051.gorovik.lesson09;

import java.util.Arrays;

public class ListA <T>{
    int size = 0;
    final int capacity = 10;
    Object[] elements;

    public ListA() {
        elements = new Object [capacity];
    }

    public void add(T e) {
        if(size == elements.length) {
            int newSize = elements.length * 2;
            elements = Arrays.copyOf(elements, newSize);
        }
        elements[size++] = e;
    }

    public Object remove(int index) {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        Object t = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i+1];
        }
        elements[size-1] = null;
        size--;
        return t;
    }

    public Object get(int index) {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        return elements[index];
    }

    public String toString(){
        String result = "[ ";
        for (int i = 0; i < size - 1; i++) {
            result += elements[i] + ", ";
        }
        result += elements[size-1] + " ]";
        return result;
    }
}

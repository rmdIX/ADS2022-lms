package by.it.group151051.eremeev.lesson09;

import java.util.List;


public class ListA<T> implements List<T> {
    private Object[] data;

    private int size;
    private int count;

    public ListA() {
        size = 20;
        data = new Object[size];
        count = 0;
    }

    public boolean add(T e) {
        data[count++] = e;

        return true;
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

    public T get(int index) {
        return (T)data[index];
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

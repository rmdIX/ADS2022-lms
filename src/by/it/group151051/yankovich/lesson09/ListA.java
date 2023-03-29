package by.it.group151051.yankovich.lesson09;

import java.util.Arrays;

public class ListA<T> {
    private int size = 2;
    private T[] list;
    private int count = 0;

    public ListA(){
        list = (T[]) new Object[size];
    }

    public void add(T value){
        resize(count);
        list[count++] = value;
    }

    public void resize(int cnt){
        if (cnt < size){
            return;
        }
        size *= 2;
        list = Arrays.copyOf(list, size);
    }
}

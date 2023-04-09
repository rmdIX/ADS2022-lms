package by.it.group151051.yankovich.lesson09;

import java.util.List;

public class ListB<T> {
    private int size = 10;
    private T[] list;
    private int count = 0;

    public ListB(){
        list = (T[]) new Object[size];
    }

    public void add(T value){
        resize(count);
        list[count++] = value;
    }

    public void add(int index, T e){
        size += 1;
        T[] newList = (T[]) new Object[size];
        System.arraycopy(list, 0, newList, 0, index);
        newList[index] = e;
        System.arraycopy(list, index, newList, index+1, list.length-index);
        count++;
        list = newList;
    }

    public boolean addAll(List<?> c){
        return false;
    }

    public T remove(int index){
        if (index < 0 || index > count){
            throw new IndexOutOfBoundsException();
        }
        T removed = list[index];
        count--;
        for (int i=index; i<count; i++){
            list[i] = list[i+1];
        }
        list[count] = null;
        return removed;
    }

    public T get(int index){
        if (index < 0 || index >= count){
            throw new IndexOutOfBoundsException();
        }
        return list[index];
    }

    public T set(int index, T e){
        if (index < 0 || index > count){
            throw new IndexOutOfBoundsException();
        }
        T result = list[index];
        list[index] = e;
        return result;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i=0; i<count-1; i++){
            result.append(get(i) + ", ");
        }
        result.append(get(count-1) + "]");
        return result.toString();
    }

    public void resize(int cnt){
        if (cnt < size){
            return;
        }
        size *= 1.5;
        T[] newList = (T[]) new Object[size];
        System.arraycopy(list, 0, newList, 0, count);
        list = newList;
    }
}

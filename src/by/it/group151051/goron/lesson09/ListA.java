package by.it.group151051.goron.lesson09;

import java.util.List;

public class ListA<T> implements List<T> {
    private int currCapacity = 10;
    private Object[] currList;

    public ListA() {
        currList = new Object[currCapacity];
    }

    public boolean add(T element) {
        if (currList.length == currCapacity) {
            currCapacity = ((3 * currCapacity) / 2) + 1;
            Object[] newList = new Object[currCapacity];
            System.arraycopy(currList, 0, newList, 0, currList.length);
            currList = newList;
        }

        currList[sizeOfList()] = element;
        return true;
    }

    public T remove(int index) {
        T remElem = (T) currList[index];

        if (index == 0) {
            Object[] newList = new Object[currCapacity];
            System.arraycopy(currList, 1, newList, 0, currList.length - 1);
            currList = newList;
        }
        else if (index == sizeOfList() - 1) {
            currList[index] = null;
        }
        else {
            for (int i = index; i < sizeOfList() - 1; ++i) {
                currList[i] = currList[i + 1];
            }
            currList[sizeOfList() - 1] = null;
        }

        return remElem;
    }

    public T get(int index) {
        return (T) currList[index];
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append('[');
        for (int i = 0; i < sizeOfList() - 1; ++i) {
            str.append(get(i)).append(',').append(' ');
        }
        str.append(get(sizeOfList() - 1)).append(']');

        return str.toString();
    }

    private int sizeOfList() {
        int elemCount = 0;
        for (int i = 0; i <= currList.length; ++i) {
            if (i == currList.length || currList[i] == null) {
               elemCount = i;
               break;
            }
        }

        return elemCount;
    }
}

package by.it.group151051.padabied.lesson09;


public class ListA<T>  {
    public Object[] list = new Object[10];
    private int currCapacity = 10;

    public void add(T e) {
        if (this.size() == currCapacity) {
            reform();
        }
        list[this.size()] = e;
    }

    public void remove(int index) {
        if (index < 0 || index > this.size()-1) {
            return;
        }
        Object[] newList = new Object[currCapacity];
        if (index == 0) {
            if (this.size() - 1 >= 0) System.arraycopy(list, 1, newList, 0, this.size() - 1);
        }
        else if (index == this.size()-1) {
            if (this.size() - 1 >= 0) System.arraycopy(list, 0, newList, 0, this.size() - 1);
        }
        else {
            int currIndex = 0;
            for (int i = 0; i < this.size(); i++) {
                if (i != index) {
                    newList[currIndex] = list[i];
                    currIndex++;
                }
            }
        }
        list = newList;
    }

    public T get(int index) {
        if (index < 0 || index > this.size()-1) {
            return null;
        }
        return (T) list[index];
    }

    public int size() {
        int result = 0;
        for (Object o : list) {
            if (o != null) result++;
        }
        return result;
    }

    public void reform() {
        currCapacity *= 1.5;
        Object[] newList = new Object[currCapacity];
        System.arraycopy(list, 0, newList, 0, list.length);
        list = newList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < this.size(); i++) {
            builder.append(this.get(i));
            if (i != this.size()-1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}

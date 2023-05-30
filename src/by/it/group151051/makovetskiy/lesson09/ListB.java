package by.it.group151051.padabied.lesson09;

import java.util.List;

public class ListB<T> extends ListA<T> {


    public void set(int index, T e) {
        if (index < 0 || index > this.size() - 1) {
            return;
        } else {
            this.list[index] = e;
        }
    }

    public void add(int index, T e) {
        if (index < 0 || index > this.size() - 1) {
            return;
        }
        else {
            Object[] tmp = new Object[this.size() - index];
            System.arraycopy(this.list, index, tmp, 0, (this.size() - index));
            this.set(index, e);
            int sizeTmp = this.size();
            for (int i = index+1; i < sizeTmp; i++) {
                this.list[i] = null;
            }
            System.out.println(this.toString());
            for (Object element : tmp) {
                this.add((T) element);
            }
        }
    }

    public void addAll(List<T> c) {
        for (T element : c) {
            add(element);
        }
    }
// TEST uncomment
//    public static void main(String[] args) {
//        ListB<String> list1 = new ListB<>();
//        list1.add("1");
//        list1.add("2");
//        list1.add("3");
//        list1.add("4");
//        list1.add("5");
//        list1.add("6");
//        list1.add("7");
//        list1.add("8");
//        List<String> list2 = new ArrayList<>();
//        list2.add("A");
//        list2.add("B");
//        list2.add("C");
//        list2.add("D");
//        list2.add("E");
//
//        list1.set(2, "DONE");
//        list1.add(2, "--->");
//        list1.addAll(list2);
//
//        System.out.println(list1);
//    }
}

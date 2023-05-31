package by.it.group151051.krupovich.lesson09;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<T> implements List{
    int capacity = 1;
    int size;
    private T[] elements;

    public ListB(){
        elements = (T[])new Object[capacity];
    }
    public boolean add(Object a){
        if(size+1 > capacity) extend(1);
        elements[size] = (T) a;
        size++;
        return true;
    }
    public Object remove(int index){
        if(index < size) {
            capacity--;
            T[] elements_copy = (T[])new Object[capacity];
            T removedElement = null;
            for(int i = 0, j = 0; i < size; i++, j++) {
                if(i == index) {
                    j--;
                    removedElement = elements[i];
                    continue;
                };
                elements_copy[j] = elements[i];
            }
            elements = elements_copy;
            size--;
            return removedElement;
        }
        return null;
    }

    public int indexOf(Object o) {
        for(int i = 0; i < size; i++) {
            if(elements[i] == o) return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator listIterator() {
        return null;
    }

    public ListIterator listIterator(int index) {
        return null;
    }

    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    public int size() {
        int count = 0;
        for(var item : elements) {
            count++;
        }
        return count;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean contains(Object o) {
        for(var item : elements){
            if(item == (T)o) return true;
        }
        return false;
    }

    public Iterator iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public boolean remove(Object o) {
        if(this.contains(o)) {
            capacity--;
            T[] elements_copy = (T[]) new Object[capacity];
            for (int i = 0, j = 0; i < size; i++, j++) {
                if (elements[i] == o) {
                    j--;
                    continue;
                }
                ;
                elements_copy[j] = elements[i];
            }
            elements = elements_copy;
            size--;
            return true;
        }
        return false;
    }

    public boolean addAll(Collection c) {
        System.out.println("Before addall "+ this);
        extend(c.size());
        var arr = c.toArray();
        for(int i = size, j = 0; j < c.size(); i++, j++){
            elements[i] = (T)arr[j];
            size++;
        }
        return true;
    }

    public boolean addAll(int index, Collection c) {
        return false;
    }

    public void clear() {

    }

    public boolean equals(Object o) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public boolean retainAll(Collection c) {
        return false;
    }

    public boolean removeAll(Collection c) {
        return false;
    }

    public boolean containsAll(Collection c) {
        return false;
    }

    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    public T get(int index){
        return elements[index];
    }

    public Object set(int index, Object element) {
        var element_at_index = elements[index];
        elements[index] = (T)element;
        return element_at_index;
    }

    public void add(int index, Object element) {
        extend(1);
        for(int i = size; i > index; i--){
            elements[i] = elements[i-1];
        }
        elements[index] = (T)element;
        size++;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(T item : elements) {

            if(!sb.toString().equals("[")) sb.append(", ");
            sb.append(item);

        }
        sb.append(']');
        return sb.toString();
    }
    private void extend(int added_capacity) {
        for(int i = 0; i < added_capacity; i++) capacity++;
        T[] elements_copy = (T[])new Object[capacity];
        for(int i = 0; i < size; i++) {
            elements_copy[i] = elements[i];
        }
        elements = elements_copy;
    }

    private void reduce() {
        capacity--;
        T[] elements_copy = (T[])new Object[capacity];
        for(int i = 0; i < size-1; i++) {
            elements_copy[i] = elements[i];
        }
        elements = elements_copy;
    }
}


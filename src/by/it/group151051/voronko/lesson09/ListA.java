package by.it.group151051.voronko.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<T> implements List{
    int capacity = 16;
    int size;
    private T[] elements;

    public ListA(){
        elements = (T[])new Object[capacity];
    }
    public boolean add(Object a){
        if(++size > capacity) extend();
        elements[size-1] = (T) a;
        return false;
    }
    public Object remove(int index){
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
        return removedElement;
    }

    public int indexOf(Object o) {
        return 0;
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
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean contains(Object o) {
        return false;
    }

    public Iterator iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean addAll(Collection c) {
        return false;
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
        elements[index] = (T)element;
        return element;
    }

    public void add(int index, Object element) {
        if(elements[index] != null){
            for(int i = size; i > index; i--){
                elements[i] = elements[i-1];
            }
            elements[index] = (T)element;
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(T item : elements) {
            if(item != null) {
                if(!sb.toString().equals("[")) sb.append(", ");
                sb.append(item);
            }
        }
        sb.append(']');
        return sb.toString();
    }
    private void extend() {
        capacity *= 1.5;
        T[] elements_copy = (T[])new Object[capacity];
        for(int i = 0; i < size; i++) {
            elements_copy[i] = elements[i];
        }
        elements = elements_copy;
    }
}

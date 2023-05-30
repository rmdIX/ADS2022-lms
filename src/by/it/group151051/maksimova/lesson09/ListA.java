package by.it.group151051.maksimova.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<A> implements List<A> {
    private class Node {
        Node next;
        A data;
        Node(A data, Node next){
            this.data = data;
            this.next = next;
        }
    }
    private Node head = new Node(null, null);

    public String toString() {
        StringBuilder res = new StringBuilder();
        Node indNode = head.next;
        if (indNode == null){
            res.append("Пусто");
        }
        else{
            res.append("[");
            while (indNode.next != null){
                res.append(indNode.data + ", ");
                indNode = indNode.next;
            }
        }
        res.append(indNode.data + "]");
        return res.toString();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<A> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(A t) {
        Node newNode = new Node(t, null);
        if (head.next == null)
            head.next = newNode;
        else{
            Node prevNode = head.next;
            while (prevNode.next != null)
                prevNode = prevNode.next;
            prevNode.next = newNode;
        }
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
    public boolean addAll(Collection<? extends A> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends A> c) {
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
    public A get(int index) {
        int ind = 0;
        Node indNode = head.next;
        while (ind < index && indNode != null){
            ind++;
            indNode = indNode.next;
        }
        if (indNode != null)
            return indNode.data;
        else
            return null;
    }

    @Override
    public A set(int index, A element) {
        return null;
    }

    @Override
    public void add(int index, A element) {

    }

    @Override
    public A remove(int index) {
        int ind = 0;
        Node indNode = head.next;
        Node prev = indNode;
        while (ind < index && indNode != null){
            prev = indNode;
            indNode = indNode.next;
            ind++;
        }
        if (indNode != null){
            if (indNode == head.next) {
                head.next = indNode.next;
            }
            else {
                prev.next = prev.next.next;
            }
            return indNode.data;
        }
        return null;
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
    public ListIterator<A> listIterator() {
        return null;
    }

    @Override
    public ListIterator<A> listIterator(int index) {
        return null;
    }

    @Override
    public List<A> subList(int fromIndex, int toIndex) {
        return null;
    }
}

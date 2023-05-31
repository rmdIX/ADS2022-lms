package by.it.group151051.gorovik.lesson10;

import java.util.*;

public class TaskA<E extends Comparable<E>>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы

    Node root;
    class Node {
        E e;
        Node left;
        Node right;

        Node(E e) {
            this.e = e;
            right = null;
            left = null;
        }
    }

    public TaskA() {
    }

    private Node addRecursive(Node current, E e) {
        if (current == null) {
            return new Node(e);
        }
        if (e.compareTo(current.e) < 0) {
            current.left = addRecursive(current.left, e);
        } else if (e.compareTo(current.e) > 0) {
            current.right = addRecursive(current.right, e);
        } else {
            return current;
        }

        return current;
    }

    @Override
    public boolean add(E e) {
        root = addRecursive(root, e);
        return true;
    }

    private E findSmallestValue(Node root) {
        return root.left == null ? root.e : findSmallestValue(root.left);
    }
    private Node deleteRecursive(Node current, E e) {
        if (current == null) {
            return null;
        }

        if (e.compareTo(current.e) == 0) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            E smallestValue = findSmallestValue(current.right);
            current.e = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;

        }
        if (e.compareTo(current.e) < 0) {
            current.left = deleteRecursive(current.left, e);
            return current;
        }
        current.right = deleteRecursive(current.right, e);
        return current;
    }

    @Override
    public boolean remove(Object o) {
        root = deleteRecursive(root, (E)o);
        return true;
    }

    public String traverse(Node current) {
        String res = "";
        if (current != null) {
            traverse(current.left);
            res += current.e + ", ";
            traverse(current.right);
        }
        return res;
    }

    @Override
    public String toString() {
        String result = "[ ";
        result += traverse(root) + "]";
        return result;
    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public E lower(E e) {
        return null;
    }

    @Override
    public E floor(E e) {
        return null;
    }

    @Override
    public E ceiling(E e) {
        return null;
    }

    @Override
    public E higher(E e) {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public NavigableSet<E> descendingSet() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super E> comparator() {
        return null;
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return null;
    }

    @Override
    public E first() {
        return null;
    }

    @Override
    public E last() {
        return null;
    }
}

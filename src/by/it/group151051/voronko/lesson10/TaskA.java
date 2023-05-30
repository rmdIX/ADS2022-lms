package by.it.group151051.voronko.lesson10;

import java.util.*;

public class TaskA<E extends Comparable<E>> implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    private Node root;

    //Обязательные к реализации методы и конструкторы
    public TaskA() {
    }

    @Override
    public boolean add(E e) {
        Node node = new Node(e);
        if (root == null) {
            root = node;
            return true;
        } else {
            Node current = root;
            Node parent;
            while(true) {
                parent = current;
                if (e.compareTo((E) current.value) == 0) {
                    return false;
                } else if (e.compareTo((E) current.value) < 0) {
                    current = current.left;
                    if (current == null) {
                        parent.left = node;
                        return true;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = node;
                        return true;
                    }
                }
            }
        }
    }

    @Override
    public boolean remove(Object o) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        while (current.value.compareTo((E)o) != 0) {
            parent = current;
            if (((Comparable<E>) o).compareTo((E) current.value) < 0) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }

        if (current.right == null && current.left == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            Node heir = getHeir(current);
            if (current == root) {
                root = heir;
            } else if (isLeftChild) {
                parent.left = heir;
            } else {
                parent.right = heir;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder("");
        getString(root, text);
        return "[" + text + "]";
    }

    private Node getHeir(Node node) {
        Node parent = node;
        Node heir = node;
        Node current = node.right;
        while (current != null) {
            parent = heir;
            heir = current;
            current = current.left;
        }
        if (heir != node.right) {
            parent.left = heir.right;
            heir.right = node.right;
        }
        heir.left = node.left;
        return heir;
    }

    private void getString(Node node, StringBuilder text) {
        if (node != null) {
            getString(node.left, text);
            if (text.length() > 0) {
                text.append(", ");
            }
            text.append(node.value);
            getString(node.right, text);
        }
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

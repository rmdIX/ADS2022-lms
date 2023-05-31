package by.it.group151051.senko.lesson10;

import java.util.*;

public class TaskB<E extends Comparable<E>>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы

    public class Node {
        public Node left;
        public Node right;
        public Node parent;
        public E value;

        public boolean isLeaf() {
            return (
                    left == null &&
                            right == null
            );
        }

        public boolean hasOnlyLeft() {
            return (
                    left != null &&
                            right == null
            );
        }

        public boolean hasOnlyRight() {
            return (
                    left == null &&
                            right != null
            );
        }

        public Node next() {
            Node current;

            if (right != null) {
                current = right;
                while (current.left != null) {
                    current = current.left;
                }
                return current;
            }

            if (parent != null) {
                if (parent.left == this) {
                    return parent;
                }
                current = parent;
                while (
                        current.parent != null &&
                                current.parent.right == current
                ) {
                    current = current.parent;
                }
                return current.parent;
            }

            return null;
        }
    }
    Node head;
    int size;
    public TaskB() {
        head = new Node();
        size = 0;
    }

    @Override
    public boolean add(E e) {
        size++;

        if (head.value == null) {
            head.value = e;
            return true;
        }

        Node current = head;

        while (current != null) {
            if (e.compareTo(current.value) == 0) {
                size--;
                return false;
            }

            if (e.compareTo(current.value) < 0) {
                if (current.left == null) {
                    current.left = new Node();
                    current.left.value = e;
                    current.left.parent = current;
                    return true;
                } else {
                    current = current.left;
                }
            }

            if (e.compareTo(current.value) > 0) {
                if (current.right == null) {
                    current.right = new Node();
                    current.right.value = e;
                    current.right.parent = current;
                    return true;
                } else {
                    current = current.right;
                }
            }
        }

        size--;
        return false;
    }

    @Override
    public boolean remove(Object o) {
        Node current = head;

        while (current != null) {
            if (current.value == o) {
                removeNode(current);
                return true;
            }

            if (current.value.compareTo((E)o) < 0) {
                current = current.right;
            }

            if (current.value.compareTo((E)o) > 0) {
                current = current.left;
            }
        }

        return false;
    }

    private void removeNode(Node node) {
        size--;

        if (node.isLeaf()) {
            if (node == head) {
                head = new Node();
                return;
            }

            if (node.parent.left == node) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }

            return;
        }

        if (node.hasOnlyLeft()) {
            if (node == head) {
                head = node.left;
                head.parent = null;
                return;
            }

            if (node.parent.left == node) {
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }
            node.left.parent = node.parent;

            return;
        }

        if (node.hasOnlyRight()) {
            if (node == head) {
                head = node.right;
                head.parent = null;
                return;
            }

            if (node.parent.left == node) {
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;

            return;
        }

        Node closestValueNode = getFirst(node.right);
        node.value = closestValueNode.value;

        if (closestValueNode.parent.left == closestValueNode) {
            closestValueNode.parent.left = closestValueNode.right;
        } else {
            closestValueNode.parent.right = closestValueNode.right;
        }

        if (closestValueNode.right != null) {
            closestValueNode.right.parent = closestValueNode.parent;
        }
    }

    @Override
    public boolean contains(Object o) {
        Node current = head;

        while (current != null) {
            if (current.value.compareTo((E)o) == 0) {
                return true;
            }

            if (current.value.compareTo((E)o) < 0) {
                current = current.right;
            }

            if (current.value.compareTo((E)o) > 0) {
                current = current.left;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {
        head = new Node();
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E first() {
        return getFirst(head).value;
    }

    @Override
    public E last() {
        return getLast(head).value;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append('[');
        if (head.value != null) {
            fillResultString(getFirst(head), result);
            result.delete(result.length() - 2, result.length());
        }
        result.append(']');
        return result.toString();
    }

    private void fillResultString(Node node, StringBuilder str) {
        if (node == null) {
            return;
        }

        str.append(node.value).append(',').append(' ');
        fillResultString(node.next(), str);
    }

    private Node getFirst(Node current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node getLast(Node current) {
        while (current.right != null) {
            current = current.right;
        }

        return current;
    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
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


}

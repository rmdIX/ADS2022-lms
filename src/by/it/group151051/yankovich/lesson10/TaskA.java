package by.it.group151051.yankovich.lesson10;

import java.util.*;

public class TaskA<E extends Comparable<E>>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы
        public E value;
        public TaskA left;
        public TaskA right;

        public TaskA(E value) {
            this.value = value;
        }

        public TaskA() {
            this(null);
        }


//    public TaskA() {
//    }

        @Override
        public boolean add(E e) {
            if (value == null){
                value = e;
                return true;
            }
            int cmp = e.compareTo(value);

            if (cmp < 0) {
                if (left == null) {
                    left = new TaskA<>(e);
                    return true;
                } else {
                    return left.add(e);
                }
            } else if (cmp > 0) {
                if (right == null) {
                    right = new TaskA<>(e);
                    return true;
                } else {
                    return right.add(e);
                }
            } else {
                return false;
            }
        }


        @Override
        public boolean remove(Object o) {
            if (!(o instanceof Comparable)) {
                return false;
            }
            E value = (E) o;
            TaskA<E> node = findNode(value, this);

            if (node == null) {
                return false;
            }

            if (node.left == null && node.right == null) {
                replaceNode(node, null);
            } else if (node.left == null) {
                replaceNode(node, node.right);
            } else if (node.right == null) {
                replaceNode(node, node.left);
            } else {
                TaskA<E> minRightNode = findMinNode(node.right);
                node.value = minRightNode.value;
                replaceNode(minRightNode, minRightNode.right);
            }

            return true;
        }

    private TaskA<E> findNode(E value, TaskA<E> node) {
        if (node == null) {
            return null;
        }

        int cmp = value.compareTo(node.value);

        if (cmp < 0) {
            return findNode(value, node.left);
        } else if (cmp > 0) {
            return findNode(value, node.right);
        } else {
            return node;
        }
    }

    private TaskA<E> findMinNode(TaskA<E> node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            return node;
        }

        return findMinNode(node.left);
    }

    private void replaceNode(TaskA<E> node, TaskA<E> replacement) {
        if (node == this) {
            this.value = replacement.value;
            this.left = replacement.left;
            this.right = replacement.right;
            return;
        }

        TaskA<E> parent = findParent(node, this);

        if (node == parent.left) {
            parent.left = replacement;
        } else {
            parent.right = replacement;
        }
    }

    private TaskA<E> findParent(TaskA<E> node, TaskA<E> parent) {
        if (parent == null) {
            return null;
        }

        if (node == parent.left || node == parent.right) {
            return parent;
        }

        int cmp = node.value.compareTo(parent.value);

        if (cmp < 0) {
            return findParent(node, parent.left);
        } else {
            return findParent(node, parent.right);
        }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            toStringHelper(this, sb);
            if (sb.length() > 1) {
                sb.setLength(sb.length() - 2);
            }
            sb.append("]");
            return sb.toString();
        }

    private void toStringHelper(TaskA<E> node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        toStringHelper(node.left, sb);
        sb.append(node.value.toString());
        sb.append(", ");
        toStringHelper(node.right, sb);
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

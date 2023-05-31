package by.it.group151051.voronko.lesson10;

import java.util.*;

public class TaskC<E extends Comparable<E>>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы

    private Node root;
    public TaskC() {
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
    public boolean contains(Object o) {
        Node current = root;
        while (current != null) {
            int cmp = current.value.compareTo((E)o);
            if (cmp == 0) {
                return true;
            } else if (cmp < 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private Node last = null;
            private Node current = null;

            @Override
            public boolean hasNext() {
                return findNext() != null;
            }

            @Override
            public E next() {
                Node node = findNext();
                if (node != null) {
                    current = node;
                }
                return node != null ? (E) node.value : null;
            }

            private Node findNext() {
                if (root == null || (current != null && current == last)) {
                    return null;
                }
                Node node = current;
                if (current == null) {
                    node = root;
                    while (node.left != null) {
                        node = node.left;
                    }
                    last = root;
                    while (last.right != null) {
                        last = last.right;
                    }
                } else if (node.right == null) {
                    if (node.parent == null) {
                        return null;
                    } else {
                        while (node.parent.right == node) {
                            node = node.parent;
                        }
                        node = node.parent;
                    }
                } else {
                    node = node.right;
                    while (node.left != null) {
                        node = node.left;
                    }
                }
                return node;
            }
        };
    }

    @Override
    public void clear() {
        doClear(root);
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return getSize(root);
    }

    @Override
    public E first() {
        if (root == null) {
            return null;
        }
        Node parent = root;
        Node current = root;
        while(current != null) {
            parent = current;
            current = current.left;
        }
        return (E) parent.value;
    }

    @Override
    public E last() {
        if (root == null) {
            return null;
        }
        Node parent = root;
        Node current = root;
        while(current != null) {
            parent = current;
            current = current.right;
        }
        return (E) parent.value;
    }

    @Override
    public E lower(E e) {
        Node node = root;
        while (node != null) {
            int cmp = e.compareTo((E) node.value);
            if (cmp > 0) {
                if (node.right != null) {
                    node = node.right;
                } else {
                    return (E) node.value;
                }
            } else {
                if (node.left != null) {
                    node = node.left;
                } else {
                    Node parent = node.parent;
                    Node tmp = node;
                    while (parent != null && tmp == parent.left) {
                        tmp = parent;
                        parent = parent.parent;
                    }
                    return parent != null ? (E) parent.value : null;
                }
            }
        }
        return null;
    }

    @Override
    public E floor(E e) {
        if (contains(e)) {
            return e;
        }
        return lower(e);
    }

    @Override
    public E ceiling(E e) {
        if (contains(e)) {
            return e;
        }
        return higher(e);
    }

    @Override
    public E higher(E e) {
        Node node = root;
        while (node != null) {
            int cmp = e.compareTo((E) node.value);
            if (cmp < 0) {
                if (node.left != null) {
                    node = node.left;
                } else {
                    return (E) node.value;
                }
            } else {
                if (node.right != null) {
                    node = node.right;
                } else {
                    Node parent = node.parent;
                    Node tmp = node;
                    while (parent != null && tmp == parent.right) {
                        tmp = parent;
                        parent = parent.parent;
                    }
                    return parent != null ? (E) parent.value : null;
                }
            }
        }
        return null;
    }

    @Override
    public E pollFirst() {
        E value = first();
        if (value != null) {
            remove(value);
        }
        return value;
    }

    @Override
    public E pollLast() {
        E value = last();
        if (value != null) {
            remove(value);
        }
        return value;
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

    private void doClear(Node node) {
        if (node == null) {
            return;
        }
        doClear(node.left);
        doClear(node.right);
        node.left = null;
        node.right = null;
    }

    private int getSize(Node node) {
        if(node == null) {
            return 0;
        }
        return 1 + getSize(node.left) + getSize(node.right);
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

package by.it.group151051.padabied.lesson10;

import java.util.*;

public class TaskA<E extends Comparable<E>>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы
    protected Node head = null;
    protected ArrayList<E> list = new ArrayList<>();
    private Node search = null;

    private class Node {
        E element;
        Node left = null;
        Node right = null;
        Node parent = null;

        public Node(E element) {
            this.element = element;
        }
    }
    @Override
    public boolean add(E e) {
        return addNode(e, head);
    }

    private boolean addNode(E element, Node head) {
        if (list.contains(element)) return false;

        if (head == null) {
            this.head = new Node(element);
            list.add(element);
            return true;
        }
        else {
            if (element.compareTo(head.element) < 0) {
                if (head.left == null) {
                    head.left = new Node(element);
                    list.add(element);
                }
                else {
                    addNode(element, head.left);
                }
                initParent(this.head);
                return true;
            }
            else if (element.compareTo(head.element) > 0) {
                if (head.right == null) {
                    head.right = new Node(element);
                    list.add(element);
                }
                else {
                    addNode(element, head.right);
                }
                initParent(this.head);
                return true;
            }
            return false;
        }
    }
    //Инициализация поля parent у Node
    private void initParent(Node head) {
        if (head == null) return;

        if (head.left != null) {
            head.left.parent = head;
            initParent(head.left);
        }
        if (head.right != null) {
            head.right.parent = head;
            initParent(head.right);
        }
    }
    @Override
    public boolean remove(Object o) {
        try {
            if (list.contains(o)) {
                searchNode((E)o, head);
                System.out.printf("search = %s\n", search);
                if (search != null) {
                    return deleteNode(search);
                }
            }
            else return false;
        }
        catch (Exception e) {
            return false;
        }
        return false;
    }
    //ищет узел с заданным элементом
    private boolean deleteNode(Node node) {
        System.out.println(search);
        if (search != null) {
            list.remove(node.element);
            //Если узел- лист
            if (node.left == null && node.right == null) {
                if (node.parent.left == node) node.parent.left = null;
                else node.parent.right = null;
            }
            //Если из узла выходит две ветви
            else if (node.left != null && node.right != null) {
                Node change = node.left;
                while (change.right != null) {
                    change = change.right;
                }
                if (node.parent.left == node) {
                    node.parent.left = change;
                    change.parent = node.parent;
                    change.left = node.left;
                    change.right = node.right;
                }
                else if (node.parent.right == node) {
                    node.parent.right = change;
                    change.parent = node.parent;
                    change.left = node.left;
                    change.right = node.right;
                }
            }
            //Из удаляемого узла выходит одна ветвь
            else {
                if (node.left != null) {
                    if (node.parent.left == node) {
                        node.parent.left = node.left;
                    }
                    else {
                        node.parent.right = node.left;
                    }
                    node.left.parent = node.parent;
                }
                else {
                    if (node.parent.left == node) {
                        node.parent.left = node.right;
                    }
                    else {
                        node.parent.right = node.right;
                    }
                    node.right.parent = node.parent;
                }
            }
            search = null;    //Вернуть search в null для дальнейшего использования
            return true;
        }
        else return false;
    }
    //После работы этого метода поле search станет != null т.е хранит в себе найденный элемент
    private void searchNode(E element, Node head) {
        if (head == null) {
            return;
        }
        else if(head.element.compareTo(element) == 0) {
            search = head;
        }
        else {
            searchNode(element, head.left);
            searchNode(element, head.right);
        }
    }


    @Override
    public String toString() {
        Collections.sort(list);
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < list.size(); i++) {
            builder.append(list.get(i));
            if (i != list.size()-1) builder.append(", ");
        }
        builder.append("]");
        return builder.toString();
    }

//    public static void main(String[] args) {
//        TaskA<String> task = new TaskA<>();
//        task.add("D");
//        task.add("C");
//        task.add("B");
//        task.add("E");
//        task.add("A");
//        task.add("G");
//        task.add("F");
//        task.add("H");
//        task.remove("D");
//        System.out.println(task);
//        System.out.println(task.size());
//    }
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
        return list.size();
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

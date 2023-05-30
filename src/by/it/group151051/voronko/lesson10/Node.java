package by.it.group151051.voronko.lesson10;

class Node<E extends Comparable<E>> extends Object{
    E value;
    Node<E> left;
    Node<E> right;
    Node<E> parent;

    public Node(E value) {
        this.value = value;
        right = null;
        left = null;
    }
}
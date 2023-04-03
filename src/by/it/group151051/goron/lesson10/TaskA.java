package by.it.group151051.goron.lesson10;

import java.util.*;

public class TaskA<E extends Comparable<E>> implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    private static final boolean BLACK = false;
    private static final boolean RED = true;


    private class Node {
        Node left, right, parent;
        boolean color;
        E data;
    }

    // В случае удаления узла, у которого потомки = NIL,
    // один из них занимает место удаляемого узла, поэтому для перехода к родителю этого NIL-узла
    // необходим специальный узел-заглушка
    private class NilNode extends Node {
        private NilNode() {
            super();
            this.color = BLACK;
        }
    }

    //Обязательные к реализации методы и конструкторы

    private Node root;
    private int treeSize;

    public TaskA() {
        root = null;
        treeSize = 0;
    }

    @Override
    public boolean add(E e) {
        Node currentNode = root;
        Node parent = null;

        while (currentNode != null) {
            parent = currentNode;
            if (e.compareTo(currentNode.data) < 0) {
                currentNode = currentNode.left;
            }
            else if (e.compareTo(currentNode.data) > 0) {
                currentNode = currentNode.right;
            }
            else {
                return false;
            }
        }

        Node newNode = new Node();
        newNode.data = e;
        newNode.color = RED;

        if (parent == null) {
            root = newNode;
        }
        else if (e.compareTo(parent.data) < 0) {
            parent.left = newNode;
        }
        else {
            parent.right = newNode;
        }
        newNode.parent = parent;

        fixPropsAfterAddingNode(newNode);
        ++treeSize;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node currNode = root;
        E oCast = (E) o;
        while (currNode != null && !oCast.equals(currNode.data)) {
            if (oCast.compareTo(currNode.data) < 0) {
                currNode = currNode.left;
            }
            else {
                currNode = currNode.right;
            }
        }

        if (currNode == null) {
            return false;
        }

        Node replacerNode;
        boolean deletedNodeColor;

        // У узла нет потомков или есть только один
        if (currNode.left == null || currNode.right == null) {
            replacerNode = deleteNode(currNode);
            deletedNodeColor = currNode.color;
        }

        // Есть два потомка
        else {
            Node inorderSuccessor = findMin(currNode);
            currNode.data = inorderSuccessor.data;

            replacerNode = deleteNode(inorderSuccessor);
            deletedNodeColor = inorderSuccessor.color;
        }

        if (deletedNodeColor == BLACK) {
            fixPropsAfterDeletingNode(replacerNode);

            // Remove the temporary NIL node
            if (replacerNode.getClass() == NilNode.class) {
                changeParentChild(replacerNode.parent, replacerNode, null);
            }
        }

        --treeSize;
        return true;
    }

    @Override
    public String toString() {
        if (root == null)
            return "";

        StringBuilder str = new StringBuilder();
        str.append("[");

        // Симметричный обход полученного дерева для вывода значений в порядке возрастания
        Stack<Node> stack = new Stack<>();
        Node currentNode = root;
        while (currentNode != null || stack.size() > 0) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            currentNode = stack.pop();

            str.append(currentNode.data).append(", ");
            currentNode = currentNode.right;
        }

        str.delete(str.length() - 2, str.length()).append("]");

        return str.toString();
    }

    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node rightChild = node.right;

        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.left = node;
        node.parent = rightChild;

        changeParentChild(parent, node, rightChild);
    }

    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node leftChild = node.left;

        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.right = node;
        node.parent = leftChild;

        changeParentChild(parent, node, leftChild);
    }

    private void changeParentChild(Node parent, Node oldChild, Node newChild) {
        if (parent == null) {
            root = newChild;
        }
        else if (parent.left == oldChild) {
            parent.left = newChild;
        }
        else if (parent.right == oldChild) {
            parent.right = newChild;
        }
        else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (newChild != null) {
            newChild.parent = parent;
        }
    }

    private void fixPropsAfterAddingNode (Node node) {
        Node parent = node.parent;

        // Нарушение 2-го правила КЧД - корень всегда черный
        if (parent == null) {
            node.color = BLACK;
            return;
        }

        // Если родитель черный, то никаких исправлений не делаем
        if (parent.color == BLACK) {
            return;
        }

        Node grandparent = parent.parent;
        Node uncle = getUncle(parent);

        // Нарушение 4-го правила (Красный родитель вставляемого узла), красный родитель и дядя
        if (uncle != null && uncle.color == RED) {
            // Перекрашиваем родителя, прародителя и дядю
            parent.color = BLACK;
            grandparent.color = RED;
            uncle.color = BLACK;

            // Т.к. прародитель красный, необходимо проверить его родителя
            fixPropsAfterAddingNode(grandparent);
        }

        // Нарушение 4-го правила (Красный родитель вставляемого узла), родитель - левый потомок прародителя
        else if (parent == grandparent.left) {
            // Дядя - черный, и путь к вставляемому узлу от прародителя = left->right
            if (node == parent.right) {
                rotateLeft(parent);

                // Указатель родителя устанавливаем на новый корневой элемент повернутого поддерева
                parent = node;
            }

            // Дядя - черный, и путь к вставляемому узлу от прародителя = left->left
            rotateRight(grandparent);

            // Перекрашиваем родителя и прародителя
            parent.color = BLACK;
            grandparent.color = RED;
        }

        // Нарушение 4-го правила (Красный родитель вставляемого узла), родитель - правый потомок прародителя
        else {
            // Дядя - черный, и путь к вставляемому узлу от прародителя = right->left
            if (node == parent.left) {
                rotateRight(parent);

                // Указатель родителя устанавливаем на новый корневой элемент повернутого поддерева
                parent = node;
            }

            // Дядя - черный, и путь к вставляемому узлу от прародителя = right->right
            rotateLeft(grandparent);

            // Перекрашиваем родителя и прародителя
            parent.color = BLACK;
            grandparent.color = RED;
        }
    }

    private Node getUncle (Node parent) {
        Node grandparent = parent.parent;

        if (grandparent.left == parent) {
            return grandparent.right;
        }
        else if (grandparent.right == parent) {
            return grandparent.left;
        }
        else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }

    private void fixPropsAfterDeletingNode (Node node) {
        // Нарушение 2-го правила КЧД - корень всегда черный
        if (node == root) {
            node.color = BLACK;
            return;
        }

        // Далее следуют исправления если нарушено 5 правило КЧД
        // (неодинаковое число черных узлов на пути к листам дерева)

        Node sibling = getSibling(node);
        // Случай если брат - красный
        if (sibling.color == RED) {
            handleRedSibling(node, sibling);
            sibling = getSibling(node);
        }

        // Случай если брат черный и у него два черных потомка
        if(isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.color = RED;

            // Если родитель красный
            if (node.parent.color == RED) {
                node.parent.color = BLACK;
            }
            // Если родитель черный
            else {
                fixPropsAfterDeletingNode(node.parent);
            }
        }

        // Если брат черный и у него хотя бы один черный потомок
        else {
            handleBlackSiblings(node, sibling);
        }
    }

    private void handleRedSibling (Node node, Node sibling) {
        // Меняем цвет брата и родителя
        sibling.color = BLACK;
        node.parent.color = RED;

        // И вращаем поддерево вокруг родителя
        if (node == node.parent.right) {
            rotateRight(node.parent);
        }
        else {
            rotateLeft(node.parent);
        }
    }

    // Нарушено 5 правило КЧД (неодинаковое число черных узлов на пути к листам дерева)
    private void handleBlackSiblings (Node node, Node sibling) {
        boolean nodeIsLeftChld = (node == node.parent.left);

        // Случай если брат черный и он имеет хотя бы одного потомка, а также его племянник черный
        if (nodeIsLeftChld && isBlack(sibling.right)) {
            // Меняем цвет брата и его потомков
            sibling.left.color = BLACK;
            sibling.color = RED;
            rotateRight(sibling);       // И вращаем поддерево в обратном направлении от удаляемого узла
            sibling = node.parent.right;
        }
        // Если переданный узел правый потомок
        else if (!nodeIsLeftChld && isBlack(sibling.left)){
            sibling.right.color = BLACK;
            sibling.color = RED;
            rotateLeft(sibling);
            sibling = node.parent.left;
        }

        // Случай если брат черный и он имеет хотя бы одного потомка, а также его племянник красный
        // Меняем цвета брата и родителя
        sibling.color = node.parent.color;
        node.parent.color = BLACK;
        if (nodeIsLeftChld) {
            sibling.right.color = BLACK;
            rotateLeft(node.parent);    // И вращаем поддерево в направлении удаляемого узла
        }
        else {
            sibling.left.color = BLACK;
            rotateRight(node.parent);
        }
    }

    private Node getSibling (Node node) {
        Node parent = node.parent;
        if (node == parent.left) {
            return parent.right;
        }
        else if (node == parent.right) {
            return parent.left;
        }
        else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }

    private boolean isBlack(Node node) {
        return node == null || node.color == BLACK;
    }

    private Node deleteNode (Node node) {
        // У узла есть только левый потомок
        if (node.left != null) {
            changeParentChild(node.parent, node, node.left);
            return node.left;
        }
        // Только правый
        else if (node.right != null) {
            changeParentChild(node.parent, node, node.right);
            return node.right;
        }
        // Нет потомков
        else {
            Node newChild;
            if (node.color == BLACK) {
                newChild = new NilNode();
            }
            else {
                newChild = null;
            }
            changeParentChild(node.parent, node, newChild);
            return newChild;
        }
    }

    private Node findMin(Node node) {
        node = node.right;
        while (node.left != null) {
            node = node.left;
        }
        return node;
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

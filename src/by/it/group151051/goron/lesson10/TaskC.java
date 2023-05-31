package by.it.group151051.goron.lesson10;

import java.util.*;

public class TaskC<E extends Comparable<E>> implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    //Обязательные к реализации методы и конструкторы

    private Node root;
    private int treeSize;

    public TaskC() {
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

        newNode.fixPropsAfterAddingNode();
        ++treeSize;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node currentNode = root;
        E oCast = (E) o;
        while (currentNode != null && !oCast.equals(currentNode.data)) {
            if (oCast.compareTo(currentNode.data) < 0) {
                currentNode = currentNode.left;
            }
            else {
                currentNode = currentNode.right;
            }
        }

        if (currentNode == null) {
            return false;
        }

        Node replacerNode;
        boolean deletedNodeColor;

        // У узла нет потомков или есть только один
        if (currentNode.left == null || currentNode.right == null) {
            replacerNode = currentNode.deleteNode();
            deletedNodeColor = currentNode.color;
        }

        // Есть два потомка
        else {
            Node inorderSuccessor = currentNode.right.findMin();
            currentNode.data = inorderSuccessor.data;

            replacerNode = inorderSuccessor.deleteNode();
            deletedNodeColor = inorderSuccessor.color;
        }

        if (deletedNodeColor == BLACK) {
            assert replacerNode != null;
            replacerNode.fixPropsAfterDeletingNode();

            // Remove the temporary NIL node
            if (replacerNode.getClass() == NilNode.class) {
                replacerNode.changeParentChild(replacerNode.parent, null);
            }
        }

        --treeSize;
        return true;
    }

    @Override
    public String toString() {
        if (root == null)
            return "[]";

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

    public boolean contains(Object o) {
        Node currentNode = root;
        E oCast = (E) o;

        while (currentNode != null) {
            if (oCast.compareTo(currentNode.data) < 0) {
                currentNode = currentNode.left;
            }
            else if (oCast.compareTo(currentNode.data) > 0) {
                currentNode = currentNode.right;
            }
            else {
                return true;
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
        root = null;
        treeSize = 0;
    }

    @Override
    public boolean isEmpty() {
        return treeSize == 0;
    }

    @Override
    public int size() {
        return treeSize;
    }

    @Override
    public E first() {
        if (treeSize == 0) {
            return null;
        }

        Node currentNode = root;
        while(currentNode.left != null) {
            currentNode = currentNode.left;
        }

        return currentNode.data;
    }

    @Override
    public E last() {
        if (treeSize == 0) {
            return null;
        }

        Node currentNode = root;
        while(currentNode.right != null) {
            currentNode = currentNode.right;
        }

        return currentNode.data;
    }

    @Override
    public E lower(E e) {
        E maxLower = first();

        if (maxLower == null) {
            return null;
        }

        Node currentNode = root;
        while(currentNode != null) {
            if (currentNode.data.compareTo(e) >= 0) {
                currentNode = currentNode.left;
            }
            else {
                if (currentNode.data.compareTo(maxLower) > 0) {
                    maxLower = currentNode.data;
                }

                currentNode = currentNode.right;
            }
        }

        return e.compareTo(maxLower) > 0 ? maxLower : null;
    }

    @Override
    public E floor(E e) {
        E maxLower = first();

        if (maxLower == null) {
            return null;
        }

        Node currentNode = root;
        while(currentNode != null) {
            if (currentNode.data.compareTo(e) > 0) {
                currentNode = currentNode.left;
            }
            else {
                if (currentNode.data.compareTo(maxLower) > 0) {
                    maxLower = currentNode.data;
                }

                currentNode = currentNode.right;
            }
        }

        return e.compareTo(maxLower) >= 0 ? maxLower : null;
    }

    @Override
    public E ceiling(E e) {
        E minGreater = last();

        if (minGreater == null) {
            return null;
        }

        Node currentNode = root;
        while(currentNode != null) {
            if (currentNode.data.compareTo(e) >= 0) {
                if (currentNode.data.compareTo(minGreater) < 0) {
                    minGreater = currentNode.data;
                }

                currentNode = currentNode.left;
            }
            else {
                currentNode = currentNode.right;
            }
        }

        return e.compareTo(minGreater) <= 0 ? minGreater : null;
    }

    @Override
    public E higher(E e) {
        E minGreater = last();

        if (minGreater == null) {
            return null;
        }

        Node currentNode = root;
        while(currentNode != null) {
            if (currentNode.data.compareTo(e) > 0) {
                if (currentNode.data.compareTo(minGreater) < 0) {
                    minGreater = currentNode.data;
                }

                currentNode = currentNode.left;
            }
            else {
                currentNode = currentNode.right;
            }
        }

        return e.compareTo(minGreater) < 0 ? minGreater : null;
    }

    @Override
    public E pollFirst() {
        if (treeSize == 0) {
            return null;
        }

        Node currentNode = root;
        while(currentNode.left != null) {
            currentNode = currentNode.left;
        }

        E data = currentNode.data;
        remove(currentNode.data);

        return data;
    }

    @Override
    public E pollLast() {
        if (treeSize == 0) {
            return null;
        }

        Node currentNode = root;
        while(currentNode.right != null) {
            currentNode = currentNode.right;
        }

        E data = currentNode.data;
        remove(currentNode.data);

        return data;
    }


    /////////////////////////////////////////////////////////
    ////////    Классы узла и методы работы с ним    ////////
    /////////////////////////////////////////////////////////


    private class Node {
        Node left, right, parent;
        boolean color;
        E data;

        private void rotateLeft() {
            Node parent = this.parent;
            Node rightChild = this.right;

            this.right = rightChild.left;
            if (rightChild.left != null) {
                rightChild.left.parent = this;
            }

            rightChild.left = this;
            this.parent = rightChild;

            this.changeParentChild(parent, rightChild);
        }

        private void rotateRight() {
            Node parent = this.parent;
            Node leftChild = this.left;

            this.left = leftChild.right;
            if (leftChild.right != null) {
                leftChild.right.parent = this;
            }

            leftChild.right = this;
            this.parent = leftChild;

            this.changeParentChild(parent, leftChild);
        }

        private void changeParentChild(Node parent, Node newChild) {
            if (parent == null) {
                root = newChild;
            }
            else if (parent.left == this) {
                parent.left = newChild;
            }
            else if (parent.right == this) {
                parent.right = newChild;
            }
            else {
                throw new IllegalStateException("Node is not a child of its parent");
            }

            if (newChild != null) {
                newChild.parent = parent;
            }
        }

        private void fixPropsAfterAddingNode() {
            Node parent = this.parent;

            // Нарушение 2-го правила КЧД - корень всегда черный
            if (parent == null) {
                this.color = BLACK;
                return;
            }

            // Если родитель черный, то никаких исправлений не делаем
            if (parent.color == BLACK) {
                return;
            }

            Node grandparent = parent.parent;
            Node uncle = this.getUncle();

            // Нарушение 4-го правила (Красный родитель вставляемого узла), красный родитель и дядя
            if (uncle != null && uncle.color == RED) {
                // Перекрашиваем родителя, прародителя и дядю
                parent.color = BLACK;
                grandparent.color = RED;
                uncle.color = BLACK;

                // Т.к. прародитель красный, необходимо проверить его родителя
                grandparent.fixPropsAfterAddingNode();
            }

            // Нарушение 4-го правила (Красный родитель вставляемого узла), родитель - левый потомок прародителя
            else if (parent == grandparent.left) {
                // Дядя - черный, и путь к вставляемому узлу от прародителя = left->right
                if (this == parent.right) {
                    parent.rotateLeft();

                    // Указатель родителя устанавливаем на новый корневой элемент повернутого поддерева
                    parent = this;
                }

                // Дядя - черный, и путь к вставляемому узлу от прародителя = left->left
                grandparent.rotateRight();

                // Перекрашиваем родителя и прародителя
                parent.color = BLACK;
                grandparent.color = RED;
            }

            // Нарушение 4-го правила (Красный родитель вставляемого узла), родитель - правый потомок прародителя
            else {
                // Дядя - черный, и путь к вставляемому узлу от прародителя = right->left
                if (this == parent.left) {
                    parent.rotateRight();

                    // Указатель родителя устанавливаем на новый корневой элемент повернутого поддерева
                    parent = this;
                }

                // Дядя - черный, и путь к вставляемому узлу от прародителя = right->right
                grandparent.rotateLeft();

                // Перекрашиваем родителя и прародителя
                parent.color = BLACK;
                grandparent.color = RED;
            }
        }

        private Node getUncle() {
            Node grandparent = this.parent.parent;

            if (grandparent.left == this.parent) {
                return grandparent.right;
            }
            else if (grandparent.right == this.parent) {
                return grandparent.left;
            }
            else {
                throw new IllegalStateException("Parent is not a child of its grandparent");
            }
        }

        private void fixPropsAfterDeletingNode() {
            // Нарушение 2-го правила КЧД - корень всегда черный
            if (this == root) {
                this.color = BLACK;
                return;
            }

            // Далее следуют исправления если нарушено 5 правило КЧД
            // (неодинаковое число черных узлов на пути к листам дерева)

            Node sibling = this.getSibling();
            // Случай если брат - красный
            if (sibling.color == RED) {
                this.handleRedSibling(sibling);
                sibling = this.getSibling();
            }

            // Случай если брат черный и у него два черных потомка
            if(isBlack(sibling.left) && isBlack(sibling.right)) {
                sibling.color = RED;

                // Если родитель красный
                if (this.parent.color == RED) {
                    this.parent.color = BLACK;
                }
                // Если родитель черный
                else {
                    this.parent.fixPropsAfterDeletingNode();
                }
            }

            // Если брат черный и у него хотя бы один черный потомок
            else {
                this.handleBlackSiblings(sibling);
            }
        }

        private void handleRedSibling (Node sibling) {
            // Меняем цвет брата и родителя
            sibling.color = BLACK;
            this.parent.color = RED;

            // И вращаем поддерево вокруг родителя
            if (this == this.parent.right) {
                this.parent.rotateRight();
            }
            else {
                this.parent.rotateLeft();
            }
        }

        // Нарушено 5 правило КЧД (неодинаковое число черных узлов на пути к листам дерева)
        private void handleBlackSiblings (Node sibling) {
            boolean nodeIsLeftChld = (this == this.parent.left);

            // Случай если брат черный и он имеет хотя бы одного потомка, а также его племянник черный
            if (nodeIsLeftChld && isBlack(sibling.right)) {
                // Меняем цвет брата и его потомков
                sibling.left.color = BLACK;
                sibling.color = RED;
                sibling.rotateRight();       // И вращаем поддерево в обратном направлении от удаляемого узла
                sibling = this.parent.right;
            }
            // Если переданный узел правый потомок
            else if (!nodeIsLeftChld && isBlack(sibling.left)){
                sibling.right.color = BLACK;
                sibling.color = RED;
                sibling.rotateLeft();
                sibling = this.parent.left;
            }

            // Случай если брат черный и он имеет хотя бы одного потомка, а также его племянник красный
            // Меняем цвета брата и родителя
            sibling.color = this.parent.color;
            this.parent.color = BLACK;
            if (nodeIsLeftChld) {
                sibling.right.color = BLACK;
                this.parent.rotateLeft();    // И вращаем поддерево в направлении удаляемого узла
            }
            else {
                sibling.left.color = BLACK;
                this.parent.rotateRight();
            }
        }

        private Node getSibling () {
            Node parent = this.parent;
            if (this == parent.left) {
                return parent.right;
            }
            else if (this == parent.right) {
                return parent.left;
            }
            else {
                throw new IllegalStateException("Parent is not a child of its grandparent");
            }
        }

        private boolean isBlack(Node node) {
            return node == null || node.color == BLACK;
        }

        private Node deleteNode () {
            // У узла есть только левый потомок
            if (this.left != null) {
                this.changeParentChild(this.parent, this.left);
                return this.left;
            }
            // Только правый
            else if (this.right != null) {
                this.changeParentChild(this.parent, this.right);
                return this.right;
            }
            // Нет потомков
            else {
                Node newChild;
                if (this.color == BLACK) {
                    newChild = new NilNode();
                }
                else {
                    newChild = null;
                }
                this.changeParentChild(this.parent, newChild);
                return newChild;
            }
        }

        private Node findMin() {
            Node currentNode = this;
            while (currentNode.left != null) {
                currentNode = currentNode.left;
            }
            return currentNode;
        }
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

package by.it.group151051.maksimova.lesson10;

import java.util.*;

public class TaskB<E>  implements NavigableSet<E> {
    public int count = 0;
    private int i=0;
    private Node<E> root;
    public int compareTo(E o, E o1) {
        return Integer.compare((int)o, (int)o1);
    }
    public class Node<E> {
        private E data;
        private Node<E> leftChild;
        private Node<E> rightChild;
        private Node<E> parent;

        public void setLeftChild(Node<E> newNode) {
            leftChild = newNode;
        }

        public void setRightChild(Node<E> newNode) {
            rightChild = newNode;
        }

        public Node(E data) {
            this.data = data;
        }
        public Node<E> getLeftChild() {
            return leftChild;
        }

        public Node<E> getRightChild() {
            return rightChild;
        }

        public E getvalue() {
            return data;
        }

        public Node<E> getparent() {
            return parent;
        }
    }
    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet

    //Обязательные к реализации методы и конструкторы
    public TaskB() {
    }

    @Override
    public boolean add(E e) {
        Node<E> curr = root;
        Node<E> prev;
        Node<E> newNode = new Node<>(e);
        if(!contains(e)) {
            if (root == null) {
                root = newNode;
                root.parent = null;
                count++;
                return true;
            } else {
                while (true) {
                    prev = curr;
                    if (compareTo(curr.getvalue(), e) > 0) {
                        if (curr.leftChild == null) {
                            curr.setLeftChild(newNode);
                            curr.leftChild.parent = curr;
                            count++;
                            return true;
                        }
                        curr = curr.getLeftChild();
                    } else {

                        if (curr.rightChild == null) {
                            curr.setRightChild(newNode);
                            curr.rightChild.parent = curr;
                            count++;
                            return true;
                        }
                        curr = curr.getRightChild();
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> curr = root;
        boolean flag=false;
        while(!flag && count >0){
            if(curr==null) return false;
            if(curr!=null && compareTo(curr.getvalue(),(E)o)>0){
                curr=curr.getLeftChild();
            } else
            if(curr!=null && compareTo(curr.getvalue(),(E)o)<0){
                curr=curr.getRightChild();
            }
            if(curr!=null && compareTo(curr.getvalue(),(E)o)==0){
                flag = true;
            }
        }
        if(curr.leftChild==null && curr.rightChild==null){
            if(curr.parent==null){
                root=null;
            } else
            if(curr.parent.rightChild==curr){
                curr.parent.rightChild=null;
            }else {
                curr.parent.leftChild = null;
            }
            count--;
            return true;
        }
        if(curr.leftChild==null || curr.rightChild==null){
            if(curr.leftChild==null){
                if(curr.parent==null){
                    root=curr.getRightChild();
                } else
                if(curr.parent.leftChild==curr){
                    curr.rightChild.parent=curr.parent;
                    curr.parent.leftChild=curr.rightChild;
                } else
                if(curr.parent.rightChild==curr){
                    curr.rightChild.parent=curr.parent;
                    curr.parent.rightChild = curr.rightChild;
                }
            }
            if(curr.rightChild==null){
                if(curr.parent==null){
                    root=curr.getLeftChild();
                } else
                if(curr.parent!=null && curr.parent.rightChild==curr){
                    curr.leftChild.parent=curr.parent;
                    curr.parent.rightChild=curr.leftChild;
                } else
                if(curr.parent!=null && curr.parent.leftChild==curr){
                    curr.leftChild.parent=curr.parent;
                    curr.parent.leftChild = curr.leftChild;
                }
            }
            count--;
            return true;
        }
        if(curr.leftChild!=null && curr.rightChild!=null){
            Node<E> temp;
            temp=curr.getRightChild();
            boolean flag1=false;
            while(temp.leftChild!=null){
                temp=temp.getLeftChild();
                flag1=true;
            }
            if(flag1){
                if(temp.rightChild!=null){
                    temp.rightChild.parent=temp.parent;
                    temp.parent.leftChild=temp.rightChild;
                    curr.data=temp.data;
                }
                if(temp.rightChild==null){
                    temp.parent.leftChild=null;
                    curr.data=temp.data;
                }
                count--;
                return true;
            }
            else if(!flag1){
                if(temp.rightChild!=null) {
                    temp.rightChild.parent = temp.parent;
                    temp.parent.rightChild = temp.rightChild;
                    curr.data = temp.data;
                }
                if(temp.rightChild==null){
                    temp.parent.rightChild=null;
                    curr.data=temp.data;
                }
                count--;
                return true;
            }
            count--;
            return true;
        }
        return false;
    }

    void inOrder(Node root, StringBuilder str){
        if(root==null){return;}
        inOrder(root.leftChild,str);
        str.append(root.data);
        str.append(", ");
        i++;
        inOrder(root.rightChild,str);
    }

    @Override
    public String toString() {
        i=0;
        StringBuilder s = new StringBuilder();
        s.append('[');
        inOrder(root,s);
        if (s.length() < 2) {
            s.append("]");
        } else {
            s.delete(s.length()-2, s.length());
            s.append("]");
        }
        return s.toString();
    }

    @Override
    public boolean contains(Object o) {
        Node<E> current = root;
        boolean flag=false;
        while(!flag && count>0){
            if(current==null) return false;
            if(current!=null && compareTo(current.getvalue(),(E)o)>0){
                current=current.getLeftChild();
            }
            if(current!=null && compareTo(current.getvalue(),(E)o)<0){
                current=current.getRightChild();
            }
            if(current!=null && compareTo(current.getvalue(),(E)o)==0){
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
        count=0;
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return (count==0);
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public E first() {
        if(count>0){
            Node<E> current = root;
            while(current.leftChild!=null){current= current.getLeftChild();}
            return current.data;
        }
        else return null;
    }

    @Override
    public E last() {
        if(count>0){
            Node<E> current = root;
            while(current.rightChild!=null){current= current.getRightChild();}
            return current.data;
        }
        else return null;
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

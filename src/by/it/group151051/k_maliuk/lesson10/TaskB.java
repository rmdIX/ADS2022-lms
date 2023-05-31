package by.it.group151051.k_maliuk.lesson10;

import java.util.*;

public class TaskB<E>  implements NavigableSet<E> {
    public int count = 0;
    private int i=0;
    private by.it.group151051.maksimova.lesson10.TaskB.Node<E> root;
    public int compareTo(E o, E o1) {
        return Integer.compare((int)o, (int)o1);
    }
    public class Node<E> {
        private E data;
        private by.it.group151051.maksimova.lesson10.TaskB.Node<E> leftChild;
        private by.it.group151051.maksimova.lesson10.TaskB.Node<E> rightChild;
        private by.it.group151051.maksimova.lesson10.TaskB.Node<E> parent;

        public void setLeftChild(by.it.group151051.maksimova.lesson10.TaskB.Node<E> newNode) {
            leftChild = newNode;
        }

        public void setRightChild(by.it.group151051.maksimova.lesson10.TaskB.Node<E> newNode) {
            rightChild = newNode;
        }

        public Node(E data) {
            this.data = data;
        }
        public by.it.group151051.maksimova.lesson10.TaskB.Node<E> getLeftChild() {
            return leftChild;
        }

        public by.it.group151051.maksimova.lesson10.TaskB.Node<E> getRightChild() {
            return rightChild;
        }

        public E getvalue() {
            return data;
        }

        public by.it.group151051.maksimova.lesson10.TaskB.Node<E> getparent() {
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
        by.it.group151051.maksimova.lesson10.TaskB.Node<E> curr = root;
        by.it.group151051.maksimova.lesson10.TaskB.Node<E> prev;
        by.it.group151051.maksimova.lesson10.TaskB.Node<E> newNode = new by.it.group151051.maksimova.lesson10.TaskB.Node<>(e);
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
        by.it.group151051.maksimova.lesson10.TaskB.Node<E> curr = root;
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
            by.it.group151051.maksimova.lesson10.TaskB.Node<E> temp;
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

    void inOrder(by.it.group151051.maksimova.lesson10.TaskB.Node root, StringBuilder str){
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
        by.it.group151051.maksimova.lesson10.TaskB.Node<E> current = root;
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
            by.it.group151051.maksimova.lesson10.TaskB.Node<E> current = root;
            while(current.leftChild!=null){current= current.getLeftChild();}
            return current.data;
        }
        else return null;
    }

    @Override
    public E last() {
        if(count>0){
            by.it.group151051.maksimova.lesson10.TaskB.Node<E> current = root;
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

    public boolean remove(Object o)
    {
        TaskB<E> root = deleteNode(this,(E)o);
        this.SetKey( root);

        return root.value == this.value;
    }

    @Override
    public boolean contains(Object o)
    {
        if (this.value == null)
            return false;

        TaskB<E> curr = this;

        while (curr.value != null)
        {
            if (curr.value.equals(o))
                return true;

            if (curr.value.hashCode() > o.hashCode()) curr = curr.left;
            else curr = curr.right;
        }

        return false;
    }

    @Override
    public Iterator<E> iterator()
    {
        Stack<E> stack = new Stack<>();
        final TaskB<E>[] curr = new TaskB[] {this};

        Iterator<E> iterator = new Iterator<E>()
        {
            @Override
            public boolean hasNext() {
                return (!stack.isEmpty() || curr[0].value != null);
            }

            @Override
            public E next()
            {
                while (curr[0].value != null)
                {
                    stack.push(curr[0].value);
                    curr[0] = curr[0].left;
                }

                curr[0] = (TaskB<E>) stack.pop();
                TaskB<E> node = curr[0];
                curr[0] = curr[0].right;

                return node.value;
            }
        };

        return iterator;
    }

    @Override
    public void clear()
    {
        if (this.value != null)
        {
            this.left.clear();
            this.right.clear();
            this.value = null;
            this.left = null;
            this.right = null;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.value == null;
    }

    @Override
    public int size()
    {
        int temp = 0;
        if (this.value == null) return temp;

        TaskB<E> l = this.left;
        TaskB<E> r = this.right;
        temp++;

        if (l.value != null) temp += l.size();
        if (r.value != null) temp += r.size();

        return temp;
    }

    @Override
    public E first()
    {
        TaskB<E> curr = this;

        while(curr.left.value != null)
            curr = curr.left;

        return curr.value;
    }

    @Override
    public E last()
    {
        TaskB<E> curr = this;

        while(curr.right.value != null)
            curr = curr.right;

        return curr.value;
    }

    private void subString(StringBuilder sb)
    {
        if(this.value == null) return;

        this.left.subString(sb);
        sb.append(this.value);
        sb.append(", ");

        this.right.subString(sb);

        return;
    }
    @Override
    public String toString()
    {
        if (this.value == null)
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        left.subString(sb);
        sb.append(this.value);
        sb.append(", ");

        right.subString(sb);
        sb.delete(sb.length() - 2,sb.length());
        sb.append("]");

        return sb.toString();
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
package by.it.group151051.maksimova.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<B> implements List<B> {
    private class ListNode {
        B data;
        ListNode next;

        ListNode(B data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }

    private ListNode head = new ListNode(null, null);

    @Override
    public int size() {
        int res = 0;
        ListNode currentNode = head.next;
        while (currentNode != null)
        {
            res++;
            currentNode = currentNode.next;
        }

        return res;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        ListNode currentNode = head.next;
        boolean isFound = false;
        while (currentNode != null && currentNode.data != o)
        {
            currentNode = currentNode.next;
        }

        if (currentNode != null)
        {
            return true;
        }

        return false;
    }

    @Override
    public Iterator<B> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(B t) {
        ListNode currentNode = head.next;
        ListNode elem = new ListNode(t, null);
        if (head.next == null)
        {
            head.next = elem;
        }
        else
        {
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }

            currentNode.next = elem;
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1)
        {
            B data = remove(index);
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends B> c) {
        ListNode currentNode = head;
        while (currentNode.next != null)
        {
            currentNode = currentNode.next;
        }

        for (B item : c)
        {
            ListNode elem = new ListNode(item, null);
            currentNode.next = elem;
            currentNode = currentNode.next;
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends B> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public B get(int index) {
        int count = 0;
        ListNode currentNode = head.next;
        while (currentNode != null && count < index)
        {
            currentNode = currentNode.next;
            count++;
        }

        if (currentNode == null)
        {
            return null;
        }
        else
        {
            return currentNode.data;
        }
    }

    @Override
    public B set(int index, B element) {
        int count = 0;
        ListNode currentNode = head.next;
        while (currentNode != null && count < index)
        {
            currentNode = currentNode.next;
            count++;
        }
        if (currentNode == null)
        {
            return null;
        }
        else
        {
            B res = currentNode.data;
            currentNode.data = element;
            return res;
        }
    }

    @Override
    public void add(int index, B element) {
        ListNode currentNode = head.next;
        int count = 0;
        while (currentNode != null && count < index - 1)
        {
            currentNode = currentNode.next;
            count++;
        }

        if (currentNode != null)
        {
            if (currentNode != head.next)
            {
                ListNode temp = currentNode.next;
                currentNode.next = new ListNode(element, temp);
            }
            else
            {
                head.next = new ListNode(element, head.next);
            }

        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode currentNode = head.next;
        res.append("[");
        while (currentNode.next != null) {
            res.append(currentNode.data).append(", ");
            currentNode = currentNode.next;
        }
        res.append(currentNode.data).append("]");
        return res.toString();
    }

    @Override
    public B remove(int index) {
        ListNode currentNode = head.next;
        int counter = 0;
        while (currentNode != null && counter < index - 1)
        {
            currentNode = currentNode.next;
            counter++;
        }

        if (currentNode == null)
        {
            return null;
        }
        else
        {
            ListNode deleted;
            if (currentNode == head.next)
            {
                deleted = currentNode;
                head.next = currentNode.next;
            }
            else
            {
                deleted = currentNode.next;
                currentNode.next = currentNode.next.next;
            }

            return deleted.data;
        }
    }

    @Override
    public int indexOf(Object o) {
        int res = 0;
        ListNode currentNode = head.next;
        while (currentNode != null && currentNode.data != o)
        {
            res++;
            currentNode = currentNode.next;
        }
        if (currentNode!= null)
        {
            return res;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<B> listIterator() {
        return null;
    }

    @Override
    public ListIterator<B> listIterator(int index) {
        return null;
    }

    @Override
    public List<B> subList(int fromIndex, int toIndex) {
        return null;
    }
}

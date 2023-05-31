package by.it.group151051.k_maliuk.lesson10;

import java.util.*;

public class TaskC<E extends Comparable<E>> extends TaskB<E> implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы

    @Override
    public E lower(E e) {
        E curr = this.list.get(0);
        for(int i = 1; i < this.size(); i++) {
            E next = this.list.get(i);
            if (next.compareTo(e) < 0) {
                if (next.compareTo(curr) > 0) {
                    curr = next;
                }
            }
        }
        if (curr.compareTo(this.list.get(0)) == 0) {
            if (curr.compareTo(e) < 0) return curr;
            else return null;
        }
        else {
            return curr;
        }
    }

    @Override
    public E floor(E e) {
        E curr = this.list.get(0);
        for(int i = 1; i < this.size(); i++) {
            E next = this.list.get(i);
            if (next.compareTo(e) <= 0) {
                if (next.compareTo(curr) > 0) {
                    curr = next;
                }
            }
        }
        if (curr.compareTo(this.list.get(0)) == 0) {
            if (curr.compareTo(e) <= 0) return curr;
            else return null;
        }
        else {
            return curr;
        }
    }

    @Override
    public E ceiling(E e) {
        E curr = this.list.get(0);
        for(int i = 1; i < this.size(); i++) {
            E next = this.list.get(i);
            if (next.compareTo(e) >= 0) {
                if (next.compareTo(curr) < 0) {
                    curr = next;
                }
            }
        }
        if (curr.compareTo(this.list.get(0)) == 0) {
            if (curr.compareTo(e) >= 0) return curr;
            else return null;
        }
        else {
            return curr;
        }
    }

    @Override
    public E higher(E e) {
        E curr = this.list.get(0);
        for(int i = 1; i < this.size(); i++) {
            E next = this.list.get(i);
            if (next.compareTo(e) > 0) {
                if (next.compareTo(curr) < 0) {
                    curr = next;
                }
            }
        }
        if (curr.compareTo(this.list.get(0)) == 0) {
            if (curr.compareTo(e) > 0) return curr;
            else return null;
        }
        else {
            return curr;
        }
    }

    @Override
    public E pollFirst() {
        if (this.isEmpty()) return null;
        else {
            Collections.sort(this.list);
            E toReturn = this.list.get(0);
            remove(toReturn);
            return toReturn;
        }
    }

    @Override
    public E pollLast() {
        if (this.isEmpty()) return null;
        else {
            Collections.sort(this.list);
            E toReturn = this.list.get(this.size()-1);
            remove(toReturn);
            return toReturn;
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

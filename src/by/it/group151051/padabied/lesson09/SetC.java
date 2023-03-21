package by.it.group151051.padabied.lesson09;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {
    private HashMap<T, String> map = new HashMap<>();
    private String plug = "PLUG";


    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        T cast = (T) o;
        return map.containsKey(cast);
    }

    @Override
    public Iterator<T> iterator() {
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
    public boolean add(T t) {
        try {
            map.put(t, plug);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        T cast = (T) o;
        try {
            map.remove(cast);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (T element : map.keySet()) {
            if (!map.containsKey(element)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        try {
            for (T element : c) {
                map.put(element, plug);
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (!this.containsAll(c)) return false;
        else {
            try{
                for (Object element : c.toArray()) {
                    T cast = (T) element;
                    map.remove(cast);
                }
            }
            catch (Exception e) {
                return false;
            }
            return true;
        }
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Object[] arr = map.keySet().toArray();
        for (int i = 0; i < map.size(); i++) {
            builder.append(arr[i].toString());
            if (i != map.size()-1) builder.append(", ");
        }
        builder.append("]");
        return builder.toString();
    }
//        Test uncomment
//    public static void main(String[] args) {
//        SetC<String> set = new SetC<>();
//        set.add("first");
//        set.add("second");
//        set.add("third");
//        set.add("fourth");
//        System.out.println(set);
//        set.remove("second");
//        System.out.println(set);
//    }
}

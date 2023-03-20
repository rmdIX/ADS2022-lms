package by.it.group151051.artem_lakatun.lesson09;

class ListEl<T> {
    T data;
    ListEl<T> next;

    public ListEl() {
        this.data = null;
        this.next = null;
    }

    public ListEl(T data) {
        this.data = data;
        this.next = null;
    }

    public ListEl(T data, ListEl<T> next) {
        this.data = data;
        this.next = next;
    }
}
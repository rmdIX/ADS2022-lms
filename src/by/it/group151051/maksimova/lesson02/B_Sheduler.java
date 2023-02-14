package by.it.group151051.maksimova.lesson02;

import java.util.*;
/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event implements Comparable<Event>{
        int start,stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
    @Override
    public int compareTo(Event o) {
        if (this.start == o.start) {
            return Integer.compare(this.stop, o.stop);
        }
        else {
            return Integer.compare(this.start, o.start);
        }
    }
}

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };
        List<Event> starts = instance.calcStartTimes(events);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events) {
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.

        List<Event> result;
        result = new ArrayList<>();
        Arrays.sort(events);
        Event first = events[0];
        int n= events.length;

        int seg = first.stop;
        int  ind = 0;

        result.add(first);

        while (ind < n) {
            for (int i = 0; i < n; i++) {
                if (events[i].start == seg) {
                    result.add(events[i]);
                    first = events[i];
                    seg = first.stop;
                    ind = i;
                }
            }
            ind++;
            seg++;
        }
        return result;
    }
}
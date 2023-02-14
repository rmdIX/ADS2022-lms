package by.it.group151051.k_maliuk.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.
        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсивные вызовы должны проводиться на основе 3-разбиения
        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска
        для первого отрезка решения, а затем найдите оставшуюся часть решения
        (т.е. отрезков, подходящих для точки, может быть много)
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0
*/


public class C_QSortOptimized {

    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
        }
        private int differeceCalc(Segment o){
            return o.stop-o.start-this.stop+this.start;
        }
        @Override
        public int compareTo(Segment o) {
            //подумайте, что должен возвращать компаратор отрезков
            if(o.start-this.start>0) return 1;
            if(o.start-this.start<0) return -1;
            //добавляем сортировку по длине отрезка
            if(o.start-this.start==0){
                if(this.differeceCalc(o)>0) return -1;
                if(this.differeceCalc(o)<0) return 1;
            }
            return 0;
        }
    }

    public static void Q_Sort(Segment[] segments, int first, int last ){

        if(first>=last) return;

        int middle=first+(last-first)/2;
        Segment pivot=segments[middle];

        int leftInd=first;
        int rightInd=last;

        while(leftInd<=rightInd){

            while (segments[leftInd].compareTo(pivot)>0)
                leftInd++;

            while (segments[rightInd].compareTo(pivot)<0)
                rightInd--;

            if(leftInd<=rightInd){
                swap(segments, rightInd, leftInd);
                leftInd++;
                rightInd--;
            }

        }
        Q_Sort(segments,first,leftInd-1);
        Q_Sort(segments,leftInd,last);
    }
    private static void swap(Segment[] segments, int ind, int ind1) {
        Segment temp  = segments[ind];
        segments[ind] = segments[ind1];
        segments[ind1] = temp;
    }
    public static int binarySearch(Segment[] seg, int points, int first, int last){
        int count=0;
        int i=first;
        int j=last;
        while( i <= j ){
            int middle=(i+j)/2;
            if ( points<seg[middle].start){
                j = j - 1;

            }else if(points>=seg[middle].start&&points<=seg[middle].stop){
                count++;
                count+=binarySearch(seg,points,first,middle-1);
                count+=binarySearch(seg,points,middle+1,last);
                break;
            }else{
                i = middle + 1;
            }
        }
        return count;
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор


        Q_Sort(segments,0,n-1);
        Arrays.fill(result,0);
        for(int i=0;i<m;i++) {
            result[i]=binarySearch(segments,points[i],0,n-1);
        }



        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result=instance.getAccessory2(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}

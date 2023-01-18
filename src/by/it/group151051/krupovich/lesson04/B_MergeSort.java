package by.it.group151051.krupovich.lesson04;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    public int[] mergeSort(int[] array){

        if(array.length<2) return array;

        int size1= array.length/2;
        int size2= array.length-size1;
        int[] array1=new int[size1];
        int[] array2=new int[size2];
        System.arraycopy(array,0,array1,0,size1);
        System.arraycopy(array,size1,array2,0,size2);

        array1=mergeSort(array1);
        array2=mergeSort(array2);

        return sort(array1,array2);
    }
    int[] sort(int[] array1,int[] array2){

        int i=0;
        int j=0;
        int ind=0;
        int[] result=new int[array1.length+ array2.length];

        while(i<array1.length&&j<array2.length){
            if(array1[i]<array2[j]){
                result[ind]=array1[i];
                i++;
            }
            else {result[ind]=array2[j]; j++;}
            ind++;
        }
        System.arraycopy(array1,i,result,ind,array1.length-i);
        System.arraycopy(array2,j,result,ind,array2.length-j);
        return result;
    }
    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        // тут ваше решение (реализуйте сортировку слиянием)
        // https://ru.wikipedia.org/wiki/Сортировка_слиянием




        a=mergeSort(a);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return a;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}


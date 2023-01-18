package by.it.group151051.krupovich.lesson08;

import by.it.group151051.krupovich.lesson05.C_QSortOptimized;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

/*
Задача на программирование: рюкзак с повторами

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        сколько есть вариантов золотых слитков
                     (каждый можно использовать множество раз).
Следующая строка содержит n целых чисел, задающих веса слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.


Sample Input:
10 3
1 4 8
Sample Output:
10

Sample Input 2:

15 3
2 8 16
Sample Output 2:
14

*/

public class A_Knapsack {

    void Sort(int[] gold, int instance, int indexStop){

        int j=(indexStop)/2;
        while ((j>=0) && (gold[j] > instance)) j=(indexStop+j)/2;

        while ((j>=0) && (gold[j] < instance)) j--;//j=(indexStop-j)/2-1;


        int temp;
        for(int k=j+1; k<=indexStop; k++){
                temp=gold[k];
                gold[k]=instance;
                instance=gold[k+1];
                gold[k+1]=temp;
                k++;
            }

    }

    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int w=scanner.nextInt();
        int n=scanner.nextInt();
        int gold[]=new int[n];

        gold[0]=scanner.nextInt();
        for (int i = 1; i < n; i++) {
            int instance=scanner.nextInt();

            if(instance>w) {
                i--;
                n--;
                continue;
            }
            if(instance<=gold[i-1]) gold[i]=instance;

            else{Sort(gold, instance, i);}

        }
        int result = 0;
        int i=0;
        while(i<n){
            if(result+gold[i]>w) i++;
            else result+=gold[i];
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
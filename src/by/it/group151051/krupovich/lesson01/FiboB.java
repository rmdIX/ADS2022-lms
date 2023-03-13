package by.it.group151051.krupovich.lesson01;

 /* Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
  * без ограничений на размер результата (BigInteger)
  */
import java.math.BigInteger;
public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {

        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        BigInteger[] array=new BigInteger[3];

        array[0]=BigInteger.ZERO;
        if(n==0) return array[n];
        array[1]=BigInteger.ONE;

        for(int i =2; i<n+1;i++){
            array[2]=array[1].add(array[0]);
            array[0]=array[1];
            array[1]=array[2];
         }
        return array[2];
    }

}
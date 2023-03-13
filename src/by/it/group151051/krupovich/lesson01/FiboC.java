package by.it.group151051.krupovich.lesson01;


/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        int period=1;
        long[] arrayModFibo=new long[(m*6)+1];
        arrayModFibo[0]=0;
        arrayModFibo[1]=1;
        for(int i=2;i<=m*6;i++){
            arrayModFibo[i]=(arrayModFibo[i-1]+arrayModFibo[i-2])%(m);
            if(arrayModFibo[i]==1&&arrayModFibo[i-1]==0) break;
            period++;
          }
        int x=(int)n%period;

        return arrayModFibo[x];
    }


}
package by.it.group151051.maksimova.lesson01;

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
        if (m == 1) {
            return 0;
        }
        if (m == 2) {
            if (n % 3 == 0) {
                return 0;
            } else {
                return 1;
            }
        }
        int a = 6;
        int max = m * m + 1;
        long[] mas = new long[max];
        mas[0] = 1;
        mas[1] = 1;
        for (int i = 2; i < max; i++) {
            mas[i] = (mas[i - 1] + mas[i - 2]) % m;
        }
        int index = 0;
        while(true) {
            if (mas[index] != mas[index + a]) {
                index = 0;
                a += 2;
            }
            index++;
            if (index > a) {
                break;
            }
            if (index > max - a) {
                break;
            }
        }
        int indOst = ((int)n % a) - 1;
        return mas[indOst];
    }
    }


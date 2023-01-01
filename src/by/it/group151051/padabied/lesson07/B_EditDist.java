package by.it.group151051.padabied.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class B_EditDist {

    public int minOfThree (int a, int b, int c) {
        return Integer.min(a, Integer.min(b, c));
    }

    int getDistanceEdinting(String one, String two) {
        int m = one.length();
        int n = two.length();

        if (m == 0) return n;
        if (n == 0) return m;

        int cost = (one.charAt(m - 1) == two.charAt(n - 1)) ? 0: 1;

        return minOfThree(
                //Удаление
                getDistanceEdinting(one.substring(0, one.length()-1), two) + 1,
                //Вставка
                getDistanceEdinting(one,two.substring(0, two.length()-1)) + 1,
                //Замена
                getDistanceEdinting(one.substring(0, one.length()-1),  two.substring(0, two.length()-1)) + cost);
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
package by.it.group151051.k_maliuk.lesson07;

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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки
    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,
    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,
    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,
    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    String getDistanceEdinting(String two, String one) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


        String result = "";

        int[][] matrix = new int[two.length() + 1][one.length()+1];

        for(int i=0;i<=two.length();i++)
            matrix[i][0]=i;
        for(int j=0;j<=one.length();j++)
            matrix[0][j]=j;

        for (int i = 1; i <= two.length(); i++) {
            for (int j = 1; j <= one.length(); j++) {
                int cost = (one.charAt(one.length()- j) != two.charAt(two.length() - i)) ? 1 : 0;
                matrix[i][j] = Math.min((Math.min( matrix[i-1][j] + 1, matrix[i][j - 1] + 1)),matrix[i-1][j - 1] + cost);
            }
        }
        int i=two.length();
        int j=one.length();
        while(i>0&&j>0){

            if(one.charAt(one.length() - j) == two.charAt(two.length() - i) ){result+="#,";i--;j--;}

            else if(matrix[i][j]==matrix[i][j-1] + 1){result+="+"+one.charAt(one.length()-j)+",";j--;}
            else if(matrix[i][j]==matrix[i-1][j] + 1){result+="-"+two.charAt(two.length()-i)+",";i--;}
            else if(matrix[i][j]==matrix[i-1][j-1] + 1){result+="~"+one.charAt(one.length()-j)+",";j--;i--;}


        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
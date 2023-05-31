package by.it.group151051.k_maliuk.lesson11;

import java.util.Arrays;

public class TaskA
{
    public boolean[][] mas;
    public boolean[] isVisited;

    public int[] pre;
    public int[] post;

    public int size = 0;
    int counter = 0;

    public String result = "";

    public void add(int x, int y)
    {
        mas[x][y] = true;
        mas[y][x] = true;
    }

    public void init()
    {
        isVisited = new boolean[size];
        pre = new int[size];
        post = new int[size];

        for (int i = 0; i < size; i++)
        {
            if (!isVisited[i])
                explore(i);

            System.out.println("Pre " + i + " = "+ Arrays.toString(pre));
            System.out.println("Post " + i + " = "+ Arrays.toString(post));
        }
    }

    public void explore(int a)
    {
        isVisited[a] = true;
        pre[a] = counter++;
        result = result + a + " ";

        for (int i = 0; i < size; i++)
            if (mas[a][i] && !isVisited[i])
                explore(i);

        post[a] = counter++;
    }

    public static void main(String[] args)
    {
        by.it.group151051.k_maliuk.lesson11.TaskA graph = new by.it.group151051.k_maliuk.lesson11.TaskA();

        graph.size = 9;
        graph.mas = new boolean[graph.size][graph.size];

        graph.add(0,1);
        graph.add(0,4);
        graph.add(1,2);
        graph.add(1,4);
        graph.add(2,5);
        graph.add(4,5);
        graph.add(6,7);
        graph.add(3,7);
        graph.add(3,6);
        graph.add(5,8);

        graph.init();

        System.out.println(graph.result);
    }
}
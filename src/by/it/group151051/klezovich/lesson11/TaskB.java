package by.it.group151051.klezovich.lesson11;

import java.util.Arrays;

public class TaskB
{
    public int[][] mas;
    int[] topologic;
    public int[] pre;
    public int[] post;
    public boolean[] visited;

    public int size;
    int counter = 0;
    int counterT = 0;

    void add(int x, int y)
    {
        mas[x][y] = 1;
        mas[y][x] = 0;
    }

    public void sort()
    {
        visited = new boolean[size];
        pre = new int[size];
        post = new int[size];
        topologic = new int[size];

        for (int i = 0; i < size; i++)
            if (!visited[i])
                explore(i);

        topologic = reverse(topologic);
    }

    public int[] reverse(int[] mas)
    {
        int[] res = new int[size];

        for (int i = size - 1; i > -1; i--)
            res[size - i - 1] = mas[i];

        return res;
    }

    public void explore(int a)
    {
        visited[a] = true;
        pre[a] = counter++;

        for (int i = 0; i < a; i++)
            if (mas[a][i] == 1 && !visited[i])
                explore(i);

        post[a] = counter++;
        topologic[counterT++] = a;
    }

    public static void main(String[] args)
    {
        TaskB graph = new TaskB();

        graph.size = 8;
        graph.mas = new int[graph.size][graph.size];

        graph.add(0, 2);
        graph.add(1, 2);
        graph.add(2, 3);
        graph.add(2, 4);
        graph.add(3, 5);
        graph.add(4, 5);
        graph.add(5, 6);
        graph.add(5, 7);

        graph.sort();

        System.out.println(Arrays.toString(graph.topologic));
    }
}
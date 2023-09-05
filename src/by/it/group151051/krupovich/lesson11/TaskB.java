package by.it.group151051.krupovich.lesson11;

import java.util.Arrays;

public class TaskB {
    int[] b;
    int c = 0;
    public boolean[] vis;
    public int m;

    public int[][] a;
    public int[] pre;
    public int[] post;
    public int start = 0;
    int cl = 0;

    void addEdge(int x, int y)
    {
        a[x][y] = 1;
        a[y][x] = 0;
    }

    public void sort()
    {
        vis = new boolean[m];
        pre = new int[m];
        post = new int[m];
        b = new int[m];
        for (int i = 0; i < m; i++)
        {
            if (!vis[i])
            {
                explore(i);
            }
        }

        b = reverse(b);
    }

    public int[] reverse(int[] arr)
    {
        int[] res = new int[m];
        for (int i = m - 1; i > -1; i--)
        {
            res[m - i - 1] = arr[i];
        }

        return res;
    }

    public void explore(int vertex)
    {
        vis[vertex] = true;
        pre[vertex] = cl++;
        for (int i = 0; i < m; i++)
        {
            if (a[vertex][i] == 1 && !vis[i])
            {
                explore(i);
            }
        }

        post[vertex] = cl++;
        b[c++] = vertex;
    }

    public static void main(String[] args)
    {
       TaskB graph = new TaskB();
        int m = 8;
        graph.m = m;
        graph.a = new int[m][m];
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.sort();
        System.out.println(Arrays.toString(graph.b));
    }
}

package by.it.group151051.maksimova.lesson11;

public class TaskA {
    public int len;
    public boolean[][] matrix;
    public String res = "";
    public int[] pre;
    public int[] post;
    public boolean[] visited;
    public int start = 0;
    int clock = 0;
    void addEdge(int src, int dest){
        matrix[src][dest] = true;
        matrix[dest][src] = true;
    }
    public void initDFS()
    {
        visited = new boolean[len];
        pre = new int[len];
        post = new int[len];
        for (int i = 0; i < len; i++)
        {
            if (!visited[i])
            {
                explore(i);
            }
            System.out.println("Pre " + i + " = "+ pre.toString());

            System.out.println("Post " + i + " = "+ post.toString());
        }

    }
    public void explore(int vertex)
    {
        visited[vertex] = true;
        pre[vertex] = clock++;
        res = res + vertex + " ";
        for (int i = 0; i < len; i++)
        {
            if (matrix[vertex][i] && !visited[i])
            {
                explore(i);
            }
        }

        post[vertex] = clock++;
    }
    public static void main(String[] args) {
        TaskA graphA = new TaskA();
        graphA.len = 9;
        graphA.matrix = new boolean[graphA .len][graphA .len];
        graphA.addEdge(0,1);
        graphA.addEdge(0,4);
        graphA.addEdge(1,2);
        graphA.addEdge(1,4);
        graphA.addEdge(2,5);
        graphA.addEdge(4,5);
        graphA.addEdge(6,7);
        graphA.addEdge(3,7);
        graphA.addEdge(3,6);
        graphA.addEdge(5,8);
        graphA.initDFS();
        System.out.println(graphA.res);
    }
}

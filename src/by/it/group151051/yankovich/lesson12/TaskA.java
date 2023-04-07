package by.it.group151051.yankovich.lesson12;

public class TaskA {
    public int[][] initGraph(){
        int[][] graph = {{0, 1, 0, 0, 4, 8, 0, 0},
                         {0, 0, 2, 0, 0, 6, 6, 0},
                         {0, 0, 0, 1, 0, 0, 2, 0},
                         {0, 0, 0, 0, 0, 0, 2, 4},
                         {0, 0, 0, 0, 0, 5, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0, 1, 0, 1},
                         {0, 0, 0, 0, 0, 0, 0, 0},};

        return graph;
    }

    public int minDist(){
        int idxMin;


        return idxMin;
    }

    public void dijkstra(char start, int[][] graph){
        int index = start - 65;
        int[] dist = new int[8]; // distance from source to other vertices
        for (int value: dist) {
            value = Integer.MAX_VALUE;
        }
        dist[index] = 0;
        boolean[] visited = new boolean[8]; // marking visited vertices
        visited[index] = true;
        for

    }

    public void run(){
        int[][] graph = initGraph();
        int[] distance = new int[8];
        //dijkstra('A', graph);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new TaskA().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + "ms");
    }
}

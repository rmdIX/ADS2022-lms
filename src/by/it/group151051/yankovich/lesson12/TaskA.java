package by.it.group151051.yankovich.lesson12;

public class TaskA {
    static final int size = 8;
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


    public int minDistance(int[] dist, boolean[] visited){
        int indexMin = -1;
        int min = Integer.MAX_VALUE;
        for (int i=0; i<size; i++){
            if (dist[i] <= min && visited[i] == false){
                min = dist[i];
                indexMin = i;
            }
        }

        return indexMin;
    }

    public void dijkstra(char start, int[][] graph){
        int index = start - 65;
        int[] dist = new int[size]; // distance from source to other vertices
        for (int i=0; i<size; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[index] = 0;
        boolean[] visited = new boolean[size]; // marking visited vertices
        for (int i=0; i<size-1; i++){
            int idx = minDistance(dist, visited);
            visited[idx] = true;
            for (int j=0; j<size; j++){
                if     (visited[j] == false
                        && graph[idx][j] != 0
                        && dist[idx] != Integer.MAX_VALUE
                        && dist[idx] + graph[idx][j] < dist[j]){
                    dist[j] = dist[idx] + graph[idx][j];
                }
            }
        }
        printDistance(dist);
    }

    public void printDistance(int[] distance){
        System.out.println(
                "Vertex \t\t Distance from Source");
        for (int i = 0; i < size; i++)
            System.out.println((char) (i+65) + " \t\t " + distance[i]);
    }

    public void run(){
        int[][] graph = initGraph();
        int[] distance = new int[size];
        boolean[] visited = new boolean[size];
        dijkstra('A', graph);
        //System.out.println(minDist(0, graph, visited));
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new TaskA().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + "ms");
    }
}

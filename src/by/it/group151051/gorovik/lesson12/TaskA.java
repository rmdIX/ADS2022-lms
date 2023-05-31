package by.it.group151051.gorovik.lesson12;

import java.util.Arrays;

public class TaskA {
    public static void main(String[] args) {
        Graph1 theGraph = new Graph1(8);
        theGraph.addEdge(0, 1, 1);
        theGraph.addEdge(0, 4, 4);
        theGraph.addEdge(0, 5, 8);

        theGraph.addEdge(1, 2, 2);
        theGraph.addEdge(1, 5, 6);
        theGraph.addEdge(0, 6, 6);

        theGraph.addEdge(2, 3, 1);
        theGraph.addEdge(2, 6, 2);

        theGraph.addEdge(3, 6, 1);
        theGraph.addEdge(3, 7, 4);

        theGraph.addEdge(4, 5, 5);

        theGraph.addEdge(6, 7, 1);

        int[] dist = theGraph.shortestPath(0);
        System.out.print(Arrays.toString(dist));
    }
}

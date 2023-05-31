package by.it.group151051.gorovik.lesson11;

public class TaskA {
    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');
        theGraph.addVertex('F');
        theGraph.addVertex('G');
        theGraph.addVertex('H');
        theGraph.addVertex('I');

        theGraph.addEdge(0, 1);
        theGraph.addEdge(0, 4);
        theGraph.addEdge(1, 2);
        theGraph.addEdge(1, 4);
        theGraph.addEdge(2, 5);
        theGraph.addEdge(5, 8);

        theGraph.addEdge(3, 6);
        theGraph.addEdge(3, 7);
        theGraph.addEdge(6, 7);

        System.out.print("DFS result: ");
        theGraph.dfs();
        System.out.println();
        theGraph.displayPrePost();
    }
}

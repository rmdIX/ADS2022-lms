package by.it.group151051.yankovich.lesson11;

public class TaskA {
    public static void main(String[] args) {
        Graph graph = new Graph();
        // add vertices
        char symbol = 'A';
        for (int i=0; i<9; i++){
            graph.addVertex(symbol++);
        }
        graph.showVertices();
        // add edges
        graph.addEdge('A', 'B');
        graph.addEdge('A', 'E');
        graph.addEdge('B', 'C');
        graph.addEdge('B', 'E');
        graph.addEdge('C', 'F');
        graph.addEdge('E', 'F');
        graph.addEdge('F', 'I');
        graph.addEdge('D', 'G');
        graph.addEdge('D', 'H');
        graph.addEdge('G', 'H');
        graph.showEdges();
        graph.showNeighbors();
        // run main algorithm
        DFS dfs = new DFS('A');
        StringBuilder result = new StringBuilder(); // save result
        dfs.runDFS(graph.vertices.get(0), graph , result);
        result = result.deleteCharAt(result.length()-1);
        System.out.println(result);
        System.out.println();


    }
}

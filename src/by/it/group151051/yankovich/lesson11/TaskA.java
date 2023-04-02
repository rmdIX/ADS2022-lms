package by.it.group151051.yankovich.lesson11;

import java.util.ArrayList;
import java.util.List;

public class TaskA {
    public static void main(String[] args) {
        Graph graph = new Graph();
        char symbol = 'A';
        for (int i=0; i<9; i++){
            graph.addVertex(symbol++);
        }
        graph.showVertices();
        graph.addEdge('A', 'B');
        graph.addEdge('C', 'F');
        graph.showEdges();

    }
}

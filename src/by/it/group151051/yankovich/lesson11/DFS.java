package by.it.group151051.yankovich.lesson11;

import java.util.HashMap;
import java.util.Map;

public class DFS {
    public char firstVertex;

    public DFS(char firstVertex){
        this.firstVertex = firstVertex;
    }
    public void runDFS(Vertex start, Graph graph, StringBuilder result){
        start.visited = true;
        result.append(start.data+"-");
        for (char neighbor: graph.adjVertices.get(start.data)) {
            int pos = neighbor - 65;
            if (!graph.vertices.get(pos).visited){
                runDFS(graph.vertices.get(pos), graph, result);
            }
            if (start.data == firstVertex){
                if (findUnvisited(graph) != null){
                    runDFS(findUnvisited(graph), graph, result);
                }
            }
        }
    }
    public Vertex findUnvisited(Graph graph){
        for (Vertex vertex: graph.vertices) {
            if (!vertex.visited) return vertex;
        }
        return null;
    }
}

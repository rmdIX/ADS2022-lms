package by.it.group151051.yankovich.lesson11;

public class DFS {
    public char firstVertex;

    public DFS(char firstVertex){
        this.firstVertex = firstVertex;
    }
    public void runDFS(Vertex start, Graph graph){
        start.visited = true;
        System.out.print(start.data + "->");
        for (char neighbor: graph.adjVertices.get(start.data)) {
            int pos = neighbor - 65;
            if (!graph.vertices.get(pos).visited){
                runDFS(graph.vertices.get(pos), graph);
            }
            if (start.data == firstVertex){
                if (findUnvisited(graph) != null){
                    runDFS(findUnvisited(graph), graph);
                }
            }
        }

//        if (checkNeighbors(start, graph) != null){
//            runDFS(checkNeighbors(start, graph), graph);
//        }


    }

    public Vertex checkNeighbors(Vertex vertex, Graph graph){
        for (char neighbor: graph.adjVertices.get(vertex.data)){
            int pos = neighbor - 65;
            if (!graph.vertices.get(pos).visited){
                return graph.vertices.get(pos);
            }
        }
        return null;
    }

    public Vertex findUnvisited(Graph graph){
        for (Vertex vertex: graph.vertices) {
            if (!vertex.visited) return vertex;
        }
        return null;
    }
}

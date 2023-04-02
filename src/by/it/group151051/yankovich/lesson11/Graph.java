package by.it.group151051.yankovich.lesson11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private List<Vertex> vertices;
    private Map<Vertex, List<Vertex>> adjVertices;
    private int size;

    public Graph(){
        vertices = new ArrayList<>();
        adjVertices = new HashMap<>();
    }

    public void addVertex(char symbol){
        vertices.add(new Vertex(symbol));
        adjVertices.put(new Vertex(symbol), new ArrayList<>());
    }

    public void addEdge(char source, char destination){
        adjVertices.get(new Vertex(source)).add(new Vertex(destination));
    }

    public void showVertices(){
        for (Vertex vertex: vertices) {
            System.out.println(vertex.data);
        }
    }

    public void showEdges(){
        //int size = vertices.size();
        for (Vertex vertex: adjVertices.keySet()) {
            char name = vertex.data;
            List<Vertex> neighbors = adjVertices.get(vertex);
            System.out.print(name + ": ");
            for (Vertex neighbor: neighbors) {
                System.out.print(neighbor.data + " ");
            }
            System.out.println();
        }
    }
}

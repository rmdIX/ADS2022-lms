package by.it.group151051.yankovich.lesson11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    public List<Vertex> vertices;
    public Map<Character, List<Character>> adjVertices;
    private int size;

    public Graph(){
        vertices = new ArrayList<>();
        adjVertices = new HashMap<>();
    }

    public void addVertex(char symbol){
        vertices.add(new Vertex(symbol));
        adjVertices.put(symbol, new ArrayList<>());
    }

    public void addEdge(char source, char destination){
        adjVertices.get(source).add(destination);
        adjVertices.get(destination).add(source);
    }

    public void showVertices(){
        for (Vertex vertex: vertices) {
            System.out.print(vertex.data + " ");
        }
        System.out.println();
    }

    public void showEdges(){
        //int size = vertices.size();
        for (char vertex: adjVertices.keySet()) {
            List<Character> neighbors = adjVertices.get(vertex);
            System.out.print(vertex + ": ");
            for (char neighbor: neighbors) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

}

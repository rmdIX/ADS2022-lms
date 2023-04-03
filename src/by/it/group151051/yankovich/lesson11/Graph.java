package by.it.group151051.yankovich.lesson11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    public List<Vertex> vertices;
    public Map<Character, List<Character>> adjVertices;
    public List<List<Character>> edges;


    public Graph(){
        vertices = new ArrayList<>();
        adjVertices = new HashMap<>();
        edges = new ArrayList<>();

    }

    public void addVertex(char symbol){
        vertices.add(new Vertex(symbol));
        adjVertices.put(symbol, new ArrayList<>());
    }

    public void addEdge(char source, char destination){
        adjVertices.get(source).add(destination);
        adjVertices.get(destination).add(source);
        saveEdge(source, destination);
    }

    public void saveEdge(char source, char destination){
        List<Character> edge = new ArrayList<>();
        edge.add(source);
        edge.add(destination);
        edges.add(edge);
    }

    public void showVertices(){
        System.out.println("Vertices:");
        for (Vertex vertex: vertices) {
            System.out.print(vertex.data + " ");
        }
        System.out.println();
    }

    public void showEdges(){
        System.out.println("Edges:");
        for (List edge: edges) {
            System.out.println(edge.get(0) + " " + edge.get(1));
        }
    }

    public void showNeighbors(){
        System.out.println("Neighbors:");
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

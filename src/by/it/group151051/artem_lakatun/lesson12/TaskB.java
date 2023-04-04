package by.it.group151051.artem_lakatun.lesson12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class TaskB {
    public static HashMap<Vertex, Float> getRoutes(String relFilePath, String startVertexName) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + relFilePath);
        Graph graph = Graph.readGraphFromFile(stream);


        HashMap<Vertex, Float> map = new HashMap<>();
        for (Vertex v : graph.getVertices().values()) {
            map.put(v, Float.POSITIVE_INFINITY);
        }
        map.put(graph.getVertex(startVertexName), 0.0f); //A

        for (int i = 0;i < map.size()-1;i++) {
            for (Edge e : graph.getEdges()) {
                if (map.get(e.getFrom())+e.getWeight() < map.get(e.getTo())) {
                    map.put(e.getTo(), map.get(e.getFrom())+e.getWeight());
                }
            }
        }

        return map;
    }
}
package by.it.group251052.ramazanov.lesson13;

import java.util.*;

class GraphB {
    private Map<Integer, List<Integer>> adjacencyList;

    public GraphB() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(int source, int destination) {
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
    }

    public boolean hasCycle() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recursionStack = new HashSet<>();

        for (int vertex : adjacencyList.keySet()) {
            if (hasCycleUtil(vertex, visited, recursionStack)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycleUtil(int vertex, Set<Integer> visited, Set<Integer> recursionStack) {
        if (recursionStack.contains(vertex)) {
            return true; // Cycle detected
        }

        if (visited.contains(vertex)) {
            return false; // Already visited
        }

        visited.add(vertex);
        recursionStack.add(vertex);

        List<Integer> neighbors = adjacencyList.getOrDefault(vertex, new ArrayList<>());
        for (int neighbor : neighbors) {
            if (hasCycleUtil(neighbor, visited, recursionStack)) {
                return true;
            }
        }

        recursionStack.remove(vertex);

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the graph structure:");
        String input = scanner.nextLine();

        GraphB graph = new GraphB();

        String[] edges = input.split(", ");
        for (String edge : edges) {
            String[] vertices = edge.split(" -> ");
            int source = Integer.parseInt(vertices[0]);
            int destination = Integer.parseInt(vertices[1]);
            graph.addEdge(source, destination);
        }

        boolean hasCycle = graph.hasCycle();
        if (hasCycle) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}

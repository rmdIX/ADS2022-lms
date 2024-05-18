package by.it.group251052.ramazanov.lesson13;

import java.util.*;

public class GraphA {

    private static void DFS(String node, Map<String, List<String>> graph, Set<String> visited, Stack<String> stack) {
        visited.add(node);
        List<String> neighbors = graph.get(node);
        if (neighbors != null) {
            for (String nextNode : neighbors) {
                if (!visited.contains(nextNode)) {
                    DFS(nextNode, graph, visited, stack);
                }
            }
        }
        stack.push(node);
    }

    static class CompareString implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s2.compareTo(s1);
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        Scanner scanner = new Scanner(System.in);

        boolean isEnd = false;

        while (!isEnd) {
            String a = scanner.next();
            String s = scanner.next();
            String b = scanner.next();

            if (b.charAt(b.length() - 1) == ',')
                b = b.substring(0, s.length() - 1);
            else
                isEnd = true;

            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
        }

        for (List<String> neighbors : graph.values()) {
            neighbors.sort(new CompareString());
        }

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                DFS(node, graph, visited, stack);
            }
        }

        while (!stack.empty()) {
            System.out.print(stack.pop());
            System.out.print(' ');
        }

        scanner.close();
    }
}

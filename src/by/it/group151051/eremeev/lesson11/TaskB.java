package by.it.group151051.eremeev.lesson11;

import java.util.Stack;

import org.junit.Test;

public class TaskB {
    public class Node {
        public Node[] edges;
        public int count;
        public String name;
        public boolean visited;

        public Node(String name) {
            edges = new Node[10];
            count = 0;
            visited = false;
            this.name = name;
        }

        public void addEdge(Node n) {
            edges[count] = n;
            count++;
        }

        public String toString() {
            StringBuilder result = new StringBuilder();

            result.append(name);
            result.append(" --> ");
            result.append("[");
            if (count > 0) {
                for (int i = 0; i < count - 1; i++) {
                    result.append(edges[i].name);
                    result.append("; ");
                }
                result.append(edges[count - 1].name);
            }
            result.append("]");

            return result.toString();
        }
    }

    public class GraphList {
        public Node[] nodes;
        public int count;

        public GraphList() {
            nodes = new Node[100];
            count = 0;
        }

        public void addNode(Node n) {
            nodes[count] = n;
            count++;
        }

        public void dropVisited() {
            for (int i = 0; i < count; i++) {
                nodes[i].visited = false;
            }
        }

        public Node find(String name) {
            for (int i = 0; i < count; i++) {
                if (nodes[i].name == name) {
                    return nodes[i];
                }
            }

            return null;
        }

        public String toString() {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < count; i++) {
                result.append(nodes[i].toString());
                result.append("\n");
            }

            return result.toString();
        }

        public Stack<String> topologicalSort() {
            dropVisited();
            Stack<String> result = new Stack<String>();

            for (int i = 0; i < count; i++) {
                dfs(nodes[i], result);
            }

            return result;
        }

        private void dfs(Node n, Stack<String> s) {
            if (n.visited == false) {
                n.visited = true;
                for (int i = 0; i < n.count; i++) {
                    dfs(n.edges[i], s);
                }
                s.push(n.name);
            }
        }
    }

    @Test(timeout = 5000)
    public void Test() {
        GraphList graph = new GraphList();
        graph.addNode(new Node("A"));
        graph.addNode(new Node("B"));
        graph.addNode(new Node("C"));
        graph.addNode(new Node("D"));
        graph.addNode(new Node("E"));
        graph.addNode(new Node("F"));
        graph.addNode(new Node("G"));
        graph.addNode(new Node("H"));

        Node n;
        n = graph.find("A");
        n.addEdge(graph.find("C"));

        n = graph.find("B");
        n.addEdge(graph.find("C"));

        n = graph.find("C");
        n.addEdge(graph.find("D"));
        n.addEdge(graph.find("E"));

        n = graph.find("D");
        n.addEdge(graph.find("F"));

        n = graph.find("E");
        n.addEdge(graph.find("F"));

        n = graph.find("F");
        n.addEdge(graph.find("G"));
        n.addEdge(graph.find("H"));

        System.out.println("Исходный граф.");
        System.out.println(graph.toString());

        System.out.println("Топологическая сортировка.");
        Stack<String> sortResult = graph.topologicalSort();
        while (!sortResult.empty()) {
            System.out.printf("%s  ", sortResult.pop());
        }
        System.out.println("");
    }
}


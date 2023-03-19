package by.it.group151051.eremeev.lesson11;

import org.junit.Test;

public class TaskA {
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

        public GraphList depthFirstSearch() {
            dropVisited();
            GraphList result = new GraphList();

            for (int i = 0; i < count; i++) {
                dfs(nodes[i], result);
            }

            return result;
        }

        private void dfs(Node n, GraphList gl) {
            n.visited = true;
            Node node = new Node(n.name);
            for (int i = 0; i < n.count; i++) {
                if (n.edges[i].visited == false) {
                    n.edges[i].visited = true;
                    node.addEdge(
                        new Node(n.edges[i].name)
                    );
                }
            }
            gl.addNode(node);
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
        graph.addNode(new Node("I"));

        Node n;
        n = graph.find("A");
        n.addEdge(graph.find("B"));
        n.addEdge(graph.find("E"));

        n = graph.find("B");
        n.addEdge(graph.find("A"));
        n.addEdge(graph.find("C"));
        n.addEdge(graph.find("E"));

        n = graph.find("C");
        n.addEdge(graph.find("B"));
        n.addEdge(graph.find("F"));

        n = graph.find("D");
        n.addEdge(graph.find("G"));
        n.addEdge(graph.find("H"));

        n = graph.find("E");
        n.addEdge(graph.find("A"));
        n.addEdge(graph.find("B"));
        n.addEdge(graph.find("F"));

        n = graph.find("F");
        n.addEdge(graph.find("C"));
        n.addEdge(graph.find("E"));
        n.addEdge(graph.find("I"));

        n = graph.find("G");
        n.addEdge(graph.find("D"));
        n.addEdge(graph.find("H"));

        n = graph.find("H");
        n.addEdge(graph.find("D"));
        n.addEdge(graph.find("G"));

        n = graph.find("I");
        n.addEdge(graph.find("F"));

        System.out.println("Исходный граф.");
        System.out.println(graph.toString());

        System.out.println("Глубинный остовный лес.");
        System.out.println(
            graph.depthFirstSearch().toString()
        );
    }
}

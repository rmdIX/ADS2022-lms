package by.it.group151051.komar.lesson11;

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
}

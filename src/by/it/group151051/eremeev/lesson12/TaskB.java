package by.it.group151051.eremeev.lesson12;

import org.junit.Test;

public class TaskB {
    public class Node {
        public String name;
        public Edge[] edges;
        public int count;

        // analytics
        public boolean visited;
        public int cost;
        public Node closest;

        public Node(String name) {
            edges = new Edge[10];
            count = 0;
            visited = false;
            this.name = name;
        }

        public void addEdge(Node n, Integer weight) {
            edges[count] = new Edge(n, weight);
            count++;
        }

        public String toString() {
            StringBuilder result = new StringBuilder();

            result.append(name);
            if (count > 0) {
                result.append(" --> ");
                for (int i = 0; i < count; i++) {
                    result.append(edges[i].toString());
                    result.append("  ");
                }
            }

            return result.toString();
        }
    }

    public class Edge {
        public Node target;
        public Integer weight;

        public Edge(Node n, Integer w) {
            target = n;
            weight = w;
        }

        public String toString() {
            return String.format("[%s, %d]", target.name, weight);
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

        public void bellmanFordTest() {
            dropAnalytics();
            nodes[0].cost = 0;
            nodes[0].closest = nodes[0];

            for (int i = 1; i < count; i++) {
                bellmanFord();
            }

            System.out.print("\t");
            for (int i = 0; i < count; i++) {
                System.out.printf("%s\t", nodes[i].name);
            }
            System.out.println();
            System.out.print("Цена:\t");
            for (int i = 0; i < count; i++) {
                System.out.printf("%d\t", nodes[i].cost);
            }
            System.out.println();
            System.out.print("Через:\t");
            for (int i = 0; i < count; i++) {
                System.out.printf("%s\t", nodes[i].closest.name);
            }
            System.out.println();
        }

        private void bellmanFord() {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < nodes[i].count; j++) {
                    int cost = nodes[i].cost + nodes[i].edges[j].weight;
                    if (cost < nodes[i].edges[j].target.cost) {
                        nodes[i].edges[j].target.cost = cost;
                        nodes[i].edges[j].target.closest = nodes[i];
                    }
                }
            }
        }

        public void dropAnalytics() {
            for (int i = 0; i < count; i++) {
                nodes[i].visited = false;
                nodes[i].closest = null;
                nodes[i].cost = Integer.MAX_VALUE;
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
    }

    @Test(timeout = 5000)
    public void Test() {
        GraphList graph = new GraphList();
        graph.addNode(new Node("S"));
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
        n = graph.find("S");
        n.addEdge(graph.find("A"), 7);
        n.addEdge(graph.find("C"), 6);
        n.addEdge(graph.find("F"), 5);
        n.addEdge(graph.find("E"), 6);

        n = graph.find("A");
        n.addEdge(graph.find("B"), 4);
        n.addEdge(graph.find("C"), -2);

        n = graph.find("B");
        n.addEdge(graph.find("G"), -2);
        n.addEdge(graph.find("H"), -4);

        n = graph.find("C");
        n.addEdge(graph.find("D"), 2);
        n.addEdge(graph.find("F"), 1);

        n = graph.find("E");
        n.addEdge(graph.find("F"), -2);
        n.addEdge(graph.find("H"), 3);

        n = graph.find("F");
        n.addEdge(graph.find("D"), 3);

        n = graph.find("G");
        n.addEdge(graph.find("I"), -1);

        n = graph.find("H");
        n.addEdge(graph.find("G"), 1);

        n = graph.find("I");
        n.addEdge(graph.find("H"), 1);

        System.out.println("Исходный граф.");
        System.out.println(graph.toString());

        graph.bellmanFordTest();
    }
}

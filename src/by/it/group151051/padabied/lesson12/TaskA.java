package by.it.group151051.padabied.lesson12;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TaskA {

    static class Node {
        static int count = 0;
        String name;
        private int number;
        int mark = Integer.MAX_VALUE;     //Длина кратчайшего пути в эту вершину
        boolean visited = false;
        private ArrayList<Node> connections = new ArrayList<>();
        public Node() {
            this.number = count++;
        }
    }

    static class Arc {
        private Node start;
        private Node end;
        private int dist;
        public Arc(Node st, Node end, int dist) {
            this.start=st;
            this.end = end;
            this.dist = dist;
        }

        public Node getStart() {
            return start;
        }
        public Node getEnd() {
            return end;
        }
        public int getDist() {
            return dist;
        }
    }

    static class Graph {
        public Map<Integer, Node> nodes = new HashMap<>();
        int[][] matrix;
        Node start;   //Вершина-источник
        ArrayList<Arc> arcList = new ArrayList<>();

        //Сгенерировать граф на 8 вершин согласно задания
        public static Graph getGraph() {
            Graph result = new Graph();
            for (int i = 0; i < 8; i++) {
                Node newNode = new Node();
                newNode.name = String.valueOf((char)(65+i));
                result.nodes.put(i, newNode);
            }
            //Создается граф
            result.nodes.get(0).connections.add(result.nodes.get(1));
            result.nodes.get(0).connections.add(result.nodes.get(4));
            result.nodes.get(0).connections.add(result.nodes.get(5));

            result.nodes.get(1).connections.add(result.nodes.get(2));
            result.nodes.get(1).connections.add(result.nodes.get(5));
            result.nodes.get(1).connections.add(result.nodes.get(6));

            result.nodes.get(2).connections.add(result.nodes.get(3));
            result.nodes.get(2).connections.add(result.nodes.get(6));

            result.nodes.get(3).connections.add(result.nodes.get(6));
            result.nodes.get(3).connections.add(result.nodes.get(7));

            result.nodes.get(4).connections.add(result.nodes.get(5));

            result.nodes.get(6).connections.add(result.nodes.get(5));
            result.nodes.get(6).connections.add(result.nodes.get(7));
            //Создаются дуги между вершинами графа
            result.arcList.add(new Arc(result.nodes.get(0), result.nodes.get(1), 1 ));
            result.arcList.add(new Arc(result.nodes.get(0), result.nodes.get(4), 4 ));
            result.arcList.add(new Arc(result.nodes.get(0), result.nodes.get(5), 8 ));
            result.arcList.add(new Arc(result.nodes.get(1), result.nodes.get(2), 2 ));
            result.arcList.add(new Arc(result.nodes.get(1), result.nodes.get(5), 6 ));
            result.arcList.add(new Arc(result.nodes.get(1), result.nodes.get(6), 6 ));
            result.arcList.add(new Arc(result.nodes.get(2), result.nodes.get(3), 1 ));
            result.arcList.add(new Arc(result.nodes.get(2), result.nodes.get(6), 2 ));
            result.arcList.add(new Arc(result.nodes.get(3), result.nodes.get(6), 1 ));
            result.arcList.add(new Arc(result.nodes.get(3), result.nodes.get(7), 4 ));
            result.arcList.add(new Arc(result.nodes.get(4), result.nodes.get(5), 5 ));
            result.arcList.add(new Arc(result.nodes.get(6), result.nodes.get(5), 1 ));
            result.arcList.add(new Arc(result.nodes.get(6), result.nodes.get(7), 1 ));

            result.start = result.nodes.get(0);
            result.start.mark = 0;
            getMatrix(result);
            return result;
        }
        //Инициализация матрицы смежности
        private static void getMatrix(Graph graph) {
            int[][] result = new int[graph.nodes.size()][graph.nodes.size()];

            for (int i = 0; i < graph.nodes.size(); i++) {
                for (int j = 0; j < graph.nodes.size(); j++) {
                    if (graph.nodes.get(i).connections.contains(graph.nodes.get(j))) {
                        result[i][j] = 1;
                    }
                    else {
                        result[i][j] = 0;
                    }
                }
            }
            graph.matrix = result;
        }
        //Печать матрицы смежности
        public void printMatrix () {
            for (int[] ints : matrix) {
                for (int anInt : ints) {
                    System.out.print(anInt + " ");
                }
                System.out.print("\n");
            }
        }
    }

    public static void dijkstra(Graph graph) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.push(graph.start);

        while (queue.peek() != null) {
            Node curr = queue.poll();
            ArrayList<Node> connections = curr.connections;
            for (int i = 0; i < connections.size(); i++) {
                for (int j = 0; j < graph.arcList.size(); j++) {
                    Arc arc = graph.arcList.get(j);
                    if (arc.start.number == curr.number && arc.end.number == connections.get(i).number && !arc.end.visited) {
                        queue.push(arc.end);
                        if (arc.dist + arc.start.mark < arc.end.mark) {
                            arc.end.mark = arc.dist + arc.start.mark;
                        }
                    }
                }
            }
            curr.visited = true;
        }

        for (int i = 0; i < graph.nodes.size(); i++) {
            System.out.println(graph.nodes.get(i).name + "---> " + graph.nodes.get(i).mark);
        }
    }

    public static void main(String[] args) {
        Graph graph = Graph.getGraph();
        //graph.printMatrix();
        System.out.println("Кратчайшие пути из вершины А в другие вершины:");
        dijkstra(graph);
    }
}

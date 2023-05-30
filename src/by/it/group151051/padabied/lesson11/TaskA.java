package by.it.group151051.padabied.lesson11;

import java.util.ArrayList;
import java.util.LinkedList;

public class TaskA {
    protected ArrayList<Node<String>> graph = new ArrayList<>();

    protected class Node<T> {
        protected ArrayList<Node<T>> connections = new ArrayList<>();
        protected T element;
        protected boolean isVisited = false;

        public Node(T el) {
            this.element = el;
        }
    }

    public void add(String el) {
        this.graph.add(new Node(el));
    }

    public Node<String> get(String el) {
        for (Node<String> stringNode : this.graph) {
            if (stringNode.element.equals(el)) {
                return stringNode;
            }
        }
        return null;
    }

    private static TaskA createGraph() {
        TaskA graph = new TaskA();
        for (int i = 0; i < 9; i++) {
            graph.add(String.valueOf((char)(65+i)));
        }
        graph.get("A").connections.add(graph.get("B"));
        graph.get("A").connections.add(graph.get("E"));
        graph.get("B").connections.add(graph.get("A"));
        graph.get("B").connections.add(graph.get("C"));
        graph.get("B").connections.add(graph.get("E"));
        graph.get("C").connections.add(graph.get("B"));
        graph.get("C").connections.add(graph.get("F"));
        graph.get("D").connections.add(graph.get("G"));
        graph.get("D").connections.add(graph.get("H"));
        graph.get("E").connections.add(graph.get("A"));
        graph.get("E").connections.add(graph.get("B"));
        graph.get("E").connections.add(graph.get("F"));
        graph.get("F").connections.add(graph.get("C"));
        graph.get("F").connections.add(graph.get("E"));
        graph.get("F").connections.add(graph.get("I"));
        graph.get("G").connections.add(graph.get("D"));
        graph.get("G").connections.add(graph.get("H"));
        graph.get("H").connections.add(graph.get("D"));
        graph.get("H").connections.add(graph.get("G"));
        graph.get("I").connections.add(graph.get("F"));
        return graph;
    }

    private static void deepSearch(TaskA graph) {

        for (int i = 0; i < graph.graph.size(); i++) {
            if (!graph.graph.get(i).isVisited) {
                LinkedList<Node<String>> queue = new LinkedList<>();
                queue.add(graph.graph.get(i));
                while (queue.size() != 0) {
                    Node<String> curr = queue.poll();
                    if (!curr.isVisited) {
                        System.out.println(curr.element);
                        curr.isVisited = true;
                        for (int j = 0; j < curr.connections.size(); j++) {
                            if (!curr.connections.get(j).isVisited) {
                                queue.offerFirst(curr.connections.get(j));
                            }
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        TaskA graph = createGraph();
        deepSearch(graph);
    }
}

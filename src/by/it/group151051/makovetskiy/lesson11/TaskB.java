package by.it.group151051.padabied.lesson11;

public class TaskB extends TaskA{

    protected static StringBuilder builder = new StringBuilder();

    protected static TaskB createGraph() {
        TaskB graph = new TaskB();
        for (int i = 0; i < 8; i++) {
            graph.add(String.valueOf((char)(65+i)));
        }
        graph.get("A").connections.add(graph.get("C"));
        graph.get("B").connections.add(graph.get("C"));
        graph.get("C").connections.add(graph.get("D"));
        graph.get("C").connections.add(graph.get("E"));
        graph.get("D").connections.add(graph.get("F"));
        graph.get("E").connections.add(graph.get("F"));
        graph.get("F").connections.add(graph.get("G"));
        graph.get("F").connections.add(graph.get("H"));
        return graph;
    }

    private static void topographicalSort(Node<String> start) {
        if (!start.isVisited) {
            switch (start.connections.size()) {
                case 0:
                    break;
                case 1:
                    topographicalSort(start.connections.get(0));
                    break;
                case 2:
                    if (start.connections.get(0).element.compareTo(start.connections.get(1).element) < 0) {
                        topographicalSort(start.connections.get(0));
                        topographicalSort(start.connections.get(1));
                    }
                    else {
                        topographicalSort(start.connections.get(1));
                        topographicalSort(start.connections.get(0));
                    }
                    break;
            }
            start.isVisited = true;
            builder.append(start.element);
        }
    }

    public static void goSort(TaskB taskB) {
        topographicalSort(taskB.graph.get(0));
        for (int i = 0; i < taskB.graph.size(); i++) {
            if (!taskB.graph.get(i).isVisited) {
                builder.append(taskB.graph.get(i).element);
            }
        }
        System.out.println(builder.toString());
    }

    public static void main(String[] args) {
        TaskB taskB = createGraph();
        goSort(taskB);
    }
}

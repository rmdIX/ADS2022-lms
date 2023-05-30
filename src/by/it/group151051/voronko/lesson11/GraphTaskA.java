package by.it.group151051.voronko.lesson11;

import static java.lang.System.out;
public class GraphTaskA {
    public static void main(String [] args) {
        Graph graph = new Graph();
        if (!graph.load("L11G1.txt")) {
            out.println("Неверный формат файла!");
            return;
        }
        graph.calculate();
        out.println("Узел Pre Post");
        for (int i = 1; i <= graph.getNodeCount(); i++) {
            out.printf("%4s %3d %3d\n", graph.getNodeName(i), graph.getTimeIn(i), graph.getTimeOut(i));
        }
        out.print("\nДревесные ребра: ");
        for (Graph.Edge edge : graph.getTreeEdges()) {
            out.printf("%s%s ", graph.getNodeName(edge.getSrcNode()), graph.getNodeName(edge.getDstNode()));
        }

        out.print("\nОбратные ребра: ");
        for (Graph.Edge edge : graph.getBackEdges()) {
            out.printf("%s%s ", graph.getNodeName(edge.getSrcNode()), graph.getNodeName(edge.getDstNode()));
        }
    }
}

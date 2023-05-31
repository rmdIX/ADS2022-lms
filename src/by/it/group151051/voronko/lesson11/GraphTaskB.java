package by.it.group151051.voronko.lesson11;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class GraphTaskB {

    public static void main(String [] args) {
        Graph graph = new Graph();
        if (!graph.load("L11G2.txt")) {
            out.println("Неверный формат файла!");
            return;
        }
        graph.calculate();

        out.print("Топологическая сортировка: ");
        for (int i = 1; i <= graph.getNodeCount(); i++) {
            out.printf("%s:%d ", graph.getNodeName(i), graph.getTopologyNumber(i));
        }

        out.println("\n\nУзел Pre Post");
        for (int i = 1; i <= graph.getNodeCount(); i++) {
            out.printf("%4s %3d %3d\n", graph.getNodeName(i), graph.getTimeIn(i), graph.getTimeOut(i));
        }

        List<Integer> sources = new ArrayList<>();
        List<Integer> destinations = new ArrayList<>();
        graph.findSourcesAndDestinations(sources, destinations);
        out.print("\nИстоки графа: ");
        for (Integer source: sources) {
            out.printf("%s ", graph.getNodeName(source));
        }
        out.print("\nCтоки графа: ");
        for (Integer destination: destinations) {
            out.printf("%s ", graph.getNodeName(destination));
        }
        out.println();
    }
}
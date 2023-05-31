package by.it.group151051.padabied.lesson11;

import java.util.ArrayList;

public class TaskC extends TaskB{
    //Найти истоки графа
    public static ArrayList<Node<String>> findSource (TaskB taskB) {
        ArrayList<Node<String>> result = new ArrayList<>();
        for (int i = 0; i < taskB.graph.size(); i++) {
            Node<String> curr = taskB.graph.get(i);
            boolean found = false;
            for (int j = 0; j < taskB.graph.size(); j++) {
                if (i != j) {
                    Node<String> next = taskB.graph.get(j);
                    if (next.connections.contains(curr)){
                        found = true;
                        break;
                    }
                }
            }
            if (!found) result.add(curr);
        }
        return result;
    }
    //Найти стоки графа
    public static ArrayList<Node<String>> findDrain(TaskB taskB) {
        ArrayList<Node<String>> result = new ArrayList<>();
        for (int i = 0; i < taskB.graph.size(); i++) {
            Node<String> curr = taskB.graph.get(i);
            if (curr.connections.isEmpty()) {
                result.add(curr);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TaskB taskB = createGraph();
        //Вывести все истоки графа из примера TaskB
        System.out.println("ИСТОКИ:");
        for (Node<String> el : findSource(taskB)) {
            System.out.println(el.element);
        }
        System.out.println("СТОКИ:");
        for (Node<String> el : findDrain(taskB)) {
            System.out.println(el.element);
        }
    }
}

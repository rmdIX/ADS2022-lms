package by.it.group151051.klezovich.lesson11;

import java.util.*;

public class TaskC
{
    public Map<Character, List<Character>> graph;
    public Map<Character, Integer> pre;
    public Map<Character, Integer> post;
    public List<Character> topologicalOrder;
    public boolean[] isVisited;
    public int origins;
    public int counter = 1;

    public TaskC(Map<Character, List<Character>> graph)
    {
        this.graph = graph;
        this.pre = new HashMap<>();
        this.post = new HashMap<>();
        topologicalOrder = new ArrayList<>();
        isVisited = new boolean[graph.size()];
    }

    public void sort()
    {
        for (char top : graph.keySet())
            if (!isVisited[top - 'A'])
                dfs(top);

        Collections.reverse(topologicalOrder);
    }

    public void dfs(Character top)
    {
        pre.put(top, counter++);
        isVisited[top - 'A'] = true;

        for (char neighbour : graph.get(top))
            if (!isVisited[neighbour - 'A'])
                dfs(neighbour);

        topologicalOrder.add(top);
        post.put(top, counter++);
    }

    public Map<Character, Integer> getPre() {
        return pre;
    }

    public Map<Character, Integer> getPost() {
        return post;
    }

    public List<Character> getTopologicalOrder() {
        return topologicalOrder;
    }

    public void findOriginsAndDrains()
    {
        List<Character> origins = new ArrayList<>();
        List<Character> drains = new ArrayList<>();

        for (char top : graph.keySet())
        {
            if (graph.get(top).isEmpty())
                drains.add(top);

            boolean isSource = true;

            for (char neighbour : graph.keySet())
                if (graph.get(neighbour).contains(top))
                {
                    isSource = false;
                    break;
                }

            if (isSource)
                origins.add(top);
        }
        this.origins = origins.size();
        System.out.println("b) Origins: " + origins);
        System.out.println("b) Drains: " + drains);
    }

    public static void main(String[] args)
    {
        Map<Character, List<Character>> graph = new HashMap<>();
        TaskC topologicalSort = new TaskC(graph);

        graph.put('A', List.of('C'));
        graph.put('B', List.of('C'));
        graph.put('C', List.of('D', 'E'));
        graph.put('D', List.of('F'));
        graph.put('E', List.of('F'));
        graph.put('F', List.of('G', 'H'));
        graph.put('G', List.of());
        graph.put('H', List.of());

        topologicalSort.sort();
        topologicalSort.findOriginsAndDrains();

        System.out.println("a) Pre values: " + topologicalSort.getPre());
        System.out.println("a) Post values: " + topologicalSort.getPost());
        System.out.println("c) Linearization of graph: " + topologicalSort.getTopologicalOrder());
        System.out.println("d) Amount of all linearizations: " + topologicalSort.origins * 2 * 2);
    }
}
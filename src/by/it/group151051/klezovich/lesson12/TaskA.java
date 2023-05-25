package by.it.group151051.klezovich.lesson12;

import java.util.*;

public class TaskA {
    public static int INF = Integer.MAX_VALUE;
    public static List<List<Node>> mas = new ArrayList<>();
    public static int[] dist;
    public static boolean[] visited;

    public static void Dijkstras_algorithm(int start)
    {
        dist = new int[mas.size()];
        Arrays.fill(dist, INF);
        visited = new boolean[mas.size()];

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>()
        {
            @Override
            public int compare(Node o1, Node o2)
            {
                return o1.cost - o2.cost;
            }
        });

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty())
        {
            Node p = pq.poll();
            int here = p.here;

            if (visited[here])
                continue;

            visited[here] = true;
            for (int i = 0; i < mas.get(here).size(); i++)
            {
                int there = mas.get(here).get(i).here;
                int cost = mas.get(here).get(i).cost;

                if (dist[there] > dist[here] + cost)
                {
                    dist[there] = dist[here] + cost;
                    pq.offer(new Node(there, dist[there]));
                }
            }
        }
    }

    public static class Node
    {
        int here;
        int cost;

        Node(int here, int cost)
        {
            this.here = here;
            this.cost = cost;
        }
    }

    public static void main(String[] args)
    {
        for (int i = 0; i < 8; i++)
            mas.add(new ArrayList<Node>());

        mas.get(0).add(new Node(4, 4));
        mas.get(0).add(new Node(5, 8));
        mas.get(0).add(new Node(1, 1));
        mas.get(1).add(new Node(5, 6));
        mas.get(1).add(new Node(6, 6));
        mas.get(1).add(new Node(2, 2));
        mas.get(2).add(new Node(6, 2));
        mas.get(2).add(new Node(3, 1));
        mas.get(3).add(new Node(6, 1));
        mas.get(3).add(new Node(7, 4));
        mas.get(6).add(new Node(5, 1));
        mas.get(6).add(new Node(7, 1));
        mas.get(4).add(new Node(5, 5));

        Dijkstras_algorithm(0);

        for (int i = 0; i < dist.length; i++)
            System.out.println("Shortest path from 0 to " + i + ": " + dist[i]);
    }
}
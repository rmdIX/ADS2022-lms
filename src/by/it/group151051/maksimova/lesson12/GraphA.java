package by.it.group151051.maksimova.lesson12;

import java.util.*;

public class GraphA {
    public int maxLength = 1000000;
    public List<List<Edge>> list = new ArrayList<>();
    public int[] len;
    public boolean[] visit;

    public void GetPath(int start) {
        len = new int[list.size()];
        Arrays.fill(len, maxLength);
        visit = new boolean[list.size()];

        PriorityQueue<Edge> a = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge c, Edge d) {
                return c.cost - d.cost;
            }
        });
        a.offer(new Edge(start, 0));
        len[start] = 0;

        while (!a.isEmpty()) {
            Edge b = a.poll();
            int here = b.dest;
            if (visit[here])
                continue;

            visit[here] = true;
            for (int i = 0; i < list.get(here).size(); i++) {
                int there = list.get(here).get(i).dest;
                int cost = list.get(here).get(i).cost;

                if (len[there] > len[here] + cost) {
                    len[there] = len[here] + cost;
                    a.offer(new Edge(there, len[there]));
                }
            }
        }
    }

    public static class Edge {
        int dest;
        int cost;
        Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        GraphA graphA = new GraphA();
        for (int i = 0; i < 8; i++) {
            graphA.list.add(new ArrayList<Edge>());
        }
        graphA.list.get(0).add(new Edge(4, 4));
        graphA.list.get(0).add(new Edge(5, 8));
        graphA.list.get(0).add(new Edge(1, 1));
        graphA.list.get(1).add(new Edge(5, 6));
        graphA.list.get(1).add(new Edge(6, 6));
        graphA.list.get(1).add(new Edge(2, 2));
        graphA.list.get(2).add(new Edge(6, 2));
        graphA.list.get(2).add(new Edge(3, 1));
        graphA.list.get(3).add(new Edge(6, 1));
        graphA.list.get(3).add(new Edge(7, 4));
        graphA.list.get(6).add(new Edge(5, 1));
        graphA.list.get(6).add(new Edge(7, 1));
        graphA.list.get(4).add(new Edge(5, 5));
        graphA.GetPath(0);
        for (int i = 0; i < graphA.len.length; i++) {
            System.out.println("Самый короткий путь от A до " + (char) ('A' + i) + ": " + graphA.len[i]);
        }
    }
}

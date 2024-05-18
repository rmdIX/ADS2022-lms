package by.it.group251052.ramazanov.lesson14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PointsA {
    private static class DSU {
        private int[] parent;
        private int[] size;

        public DSU(int n){
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int v){
            if (v != parent[v])
                parent[v] = find(parent[v]);
            return parent[v];
        }

        public void union(int p, int q){
            int rootP = find(p);
            int rootQ = find(q);

            if (rootP == rootQ) { return; }

            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
        }
    }

    private record Point(int x, int y, int z) { }

    private static boolean check(Point p1, Point p2, int distance) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2) + Math.pow(p2.z - p1.z, 2)) < distance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int distanceThreshold = scanner.nextInt();
        int pointsAmount = scanner.nextInt();

        Point[] points = new Point[pointsAmount];
        for (int i = 0; i < pointsAmount; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            points[i] = new Point(x, y, z);
        }

        DSU dsu = new DSU(pointsAmount);
        for (int i = 0; i < pointsAmount; i++)
            for (int j = i + 1; j < pointsAmount; j++)
                if (check(points[i], points[j], distanceThreshold))
                    dsu.union(i, j);

        List<Integer> sizes = new ArrayList<>();
        boolean[] visited = new boolean[pointsAmount];
        for (int i = 0; i < pointsAmount; i++) {
            int root = dsu.find(i);
            if (!visited[root]){
                visited[root] = true;
                sizes.add(dsu.size[root]);
            }
        }

        sizes.sort(Collections.reverseOrder());
        for (int size : sizes) {
            System.out.print(size);
            System.out.print(" ");
        }
    }
}

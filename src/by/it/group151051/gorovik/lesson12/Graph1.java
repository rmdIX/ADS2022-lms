package by.it.group151051.gorovik.lesson12;

public class Graph1 {
    int adjMatrix[][];
    int numVerts;

    public Graph1(int v){
        adjMatrix = new int [v][v];
        numVerts = v;
    }

    public void addEdge(int start, int end, int weight) {
        adjMatrix[start][end] = weight;
    }

    public static int getClosestVertex(int[] dist, boolean[] visited)
    {
        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        for(int i = 0; i < dist.length; i++)
        {
            if(dist[i] < min)
                if(visited[i] == false)
                {
                    min = dist[i];
                    minIdx = i;
                }
        }
        return minIdx;
    }

    public int[] shortestPath(int start)
    {
        int[] distance = new int[numVerts];
        boolean[] visited = new boolean[numVerts];

        //initializing the arrays
        for(int i = 0; i < numVerts; i++)
        {
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        distance[start] = 0;

        for(int i = 0; i < numVerts; i++)
        {
            int closestVertex = getClosestVertex(distance, visited);
            if(closestVertex == Integer.MAX_VALUE)
                return distance;

            visited[closestVertex] = true;
            for(int j = 0; j < numVerts; j++)
            {
                if(visited[j] == false)
                {
                    if(adjMatrix[closestVertex][j] != 0)
                    {
                        int d = distance[closestVertex] + adjMatrix[closestVertex][j];
                        if(d < distance[j])
                            distance[j] = d;
                    }
                }
            }
        }
        return distance;
    }
}

package by.it.group151051.gorovik.lesson11;

import java.util.Stack;

public class Graph {
    final int maxVerts = 10;
    Vertex vertexList[];
    int adjMatrix[][];
    int numVerts;

    Stack<Integer> theStack = new Stack<>();

    int[] pre;
    int[] post;
    int clock = 0;

    public Graph() {
        vertexList = new Vertex[maxVerts];
        adjMatrix = new int [maxVerts][maxVerts];
        numVerts = 0;
        for (int i = 0; i < maxVerts; i++) {
            for (int j = 0; j < maxVerts; j++) {
                adjMatrix[i][j] = 0;
            }
        }
    }

    public void addVertex(char lab) {
        vertexList[numVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }

    public void displayVer(int v){
        System.out.print(vertexList[v].label);
    }

    public int getAdjUnvisVertex(int v) {
        for(int j = 0; j < numVerts; j++) {
            if(adjMatrix[v][j]==1 && vertexList[j].wasVisited==false) {
                return j;
            }
        }
        return -1;
    }

    public void dfs(){
        pre = new int [maxVerts];
        post = new int [maxVerts];

        vertexList[0].wasVisited = true;
        displayVer(0);
        theStack.push(0);

        while(!theStack.empty()) {
            int v = getAdjUnvisVertex( theStack.peek());
            if (v == -1) {
                v = theStack.pop();
                post[v] = clock++;
            }
            else {
                vertexList[v].wasVisited = true;
                pre[v] = clock++;
                displayVer(v);
                theStack.push(v);
            }
        }

        for(int j = 0; j < numVerts; j++) {
            vertexList[j].wasVisited = false;
        }
        clock = 0;
    }

    public void displayPrePost() {
        for(int i = 0; i < numVerts; i++) {
            System.out.println(vertexList[i].label + ": " + pre[i] + ", " +post[i]);
        }
    }
}

package by.it.group151051.maksimova.lesson12;
import java.util.Arrays;
public class GraphB {
    public int[] dest = new int[10];
    public int[] path = new int[10];
    static final int INF = 10000;
    public int[][] matrix = {
            { INF, 4,   -2,  INF, INF, INF, INF, INF, INF, INF },
            { INF, INF, INF, INF, INF, INF, -2 , -4 , INF, INF },
            { INF, INF, INF, 2  , INF, 1  , INF, INF, INF, INF },
            { INF, INF, INF, INF, INF, INF, INF, INF, INF, INF },
            { INF, INF, INF, INF, INF, -2 , INF, 3  , INF, INF },
            { INF, INF, INF, 3  , INF, INF, INF, INF, INF, INF },
            { INF, INF, INF, INF, INF, INF, INF, INF, -1 , INF },
            { INF, INF, INF, INF, INF, INF, 1  , INF, INF, INF },
            { INF, INF, INF, INF, INF, INF, INF, 1  , INF, INF },
            { 7  , INF, 6  , INF, 6  , 5  , INF, INF, INF, INF }
    };

    public static void main(String[] args)
    {
        GraphB graphB = new GraphB();
        graphB.Dest(0);
        for (int i = 0; i < 10; i++)
        {
            System.out.println("Самый короткий путь от 0 до " + i + ": " );
            graphB.output(0, i);
            System.out.println("\nРасстояние: " + graphB.dest[i]);
        }
    }
    public void Dest(int a) {
        Arrays.fill(dest, INF);
        Arrays.fill(path, -1);
        dest[a] = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if ((dest[k] > dest[j] + matrix[j][k]) && matrix[j][k] != INF) {
                        dest[k] = dest[j] + matrix[j][k];
                        path[k] = j;
                    }
                }
            }
        }
    }
    public void output(int a, int b) {
        if (a == b) {
            System.out.print( (a) + " ");
        } else if (path[b] == -1) {
            System.out.println("Нет пути от " + (a) + " до " + (b));
        } else {
            output(a, path[b]);
            System.out.print(b + " ");
        }
    }
}

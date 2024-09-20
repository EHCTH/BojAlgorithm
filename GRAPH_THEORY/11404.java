import java.util.*;


public class Main {
    static int n, m;
    static int[][] graph = new int[102][102];
    static Scanner sc = new Scanner(System.in);

    static void init() {
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], 0x3f3f3f3f);
            graph[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            int v, e, w;
            v = sc.nextInt() - 1;
            e = sc.nextInt() - 1;
            w = sc.nextInt();
            if (graph[v][e] > w) {
                graph[v][e] = w;
            }
        }
    }

    static void solutions() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
            }
        }
    }

}

    static void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int ret = graph[i][j] == 0x3f3f3f3f ? 0 : graph[i][j];
                System.out.print(ret + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        init();
        solutions();
        print();

    }
}

class Coordinate implements Comparable<Coordinate> {
    int first;
    int second;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Coordinate(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first + " " + second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) obj;
            return first == coordinate.first && second == coordinate.second;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public int compareTo(Coordinate o) {
        return second - o.second;
    }
}
/*
6 8
1
1 3 2
1 2 3
1 4 5
2 3 2
2 5 8
3 4 2
4 5 6
5 6 1
 */
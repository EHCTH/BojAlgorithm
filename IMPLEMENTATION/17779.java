import java.util.*;

        /*

          +---+
         /   /|
        +---+ |
        |   | +
        |   |/
        +---+

        */


public class Main {
    static int n;
    static int[][] graph = new int[22][22];
    static Scanner sc = new Scanner(System.in);
    static Coordinate[] coordinates = new Coordinate[4];
    static int answer = Integer.MAX_VALUE;

    static void init() {
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < 4; i++) {
            coordinates[i] = new Coordinate();
        }
    }
    static boolean isConnect(int i, int j, int d1, int d2) {
        /*

         1 번 방향  x + d1, y - d1
         2 번 방향  x + d2, y + d2
         3 || 4 번 방향은 x + d1 + d2, y - d1 + d2

         */
        int x, y, nx, ny;
        x = i; y = j;
        nx = x + d1 + d2;
        ny = y - d1 + d2;
        if (x + d1 >= n || y - d1 < 0) return false;
        if (x + d2 >= n || y + d2 >= n) return false;
        if (nx >= n || ny >= n || ny < 0) return false;
        return true;
    }
    static void calc(int[][] numberGraph) {
        int[] numberSum = new int[5];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                numberSum[numberGraph[i][j] - 1] += graph[i][j];
            }
        }
        int max = Arrays.stream(numberSum).max().getAsInt();
        int min = Arrays.stream(numberSum).min().getAsInt();
        answer = Math.min(answer, max - min);
    }
    static void numberingGraph() {

        int[][] numberGraph = new int[22][22];
        for (int i = 0; i < n; i++) Arrays.fill(numberGraph[i], 5);

        int idx = 0;
        for (int i = 0; i < coordinates[1].first; i++) {
            if (i >= coordinates[0].first) idx++;
            for (int j = 0; j <= coordinates[0].second - idx; j++) {
                numberGraph[i][j] = 1;
            }
        }
        idx = 0;
        for (int i = 0; i <= coordinates[2].first; i++) {
            if (i > coordinates[0].first) idx++;
            for (int j = coordinates[0].second + 1 + idx; j < n; j++) {
                numberGraph[i][j] = 2;
            }
        }
        idx = 0;
        for (int i = n - 1; i >= coordinates[1].first; i--) {
            if (i < coordinates[3].first) idx++;
            for (int j = 0; j < coordinates[3].second - idx; j++) {
                numberGraph[i][j] = 3;
            }
        }
        idx = 0;
        for (int i = n - 1; i > coordinates[2].first; i--) {
            if (i <= coordinates[3].first) idx++;
            for (int j = coordinates[3].second + idx; j < n; j++) {
                numberGraph[i][j] = 4;
            }
        }
        calc(numberGraph);
    }
    static void run() {
        for (int i = 0; i < n - 2; i++) {
            for (int j = 1; j < n - 1; j++) {
                for (int d1 = 1; d1 < n; d1++) {
                    for (int d2 = 1; d2 < n; d2++) {
                        if (isConnect(i, j, d1, d2)) {
                            coordinates[0].first = i; coordinates[0].second = j;
                            coordinates[1].first = i + d1; coordinates[1].second = j - d1;
                            coordinates[2].first = i + d2; coordinates[2].second = j + d2;
                            coordinates[3].first = i + d1 + d2; coordinates[3].second = j - d1 + d2;
                            numberingGraph();
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        init();
        run();
        System.out.println(answer);
    }

}

class Node {
    char root;
    Node left = null;
    Node right = null;

    Node(char root) {
        this.root = root;
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

    public Coordinate() {
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
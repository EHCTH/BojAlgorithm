import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(bf.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    static long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    static int N, w, b;
    static int[][] graph = new int[128][128];
    static final int BLUE = 1;
    static final int WHITE = 0;
    static void init() throws IOException {
        N = nextInt();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = nextInt();
            }
        }
    }
    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void solution(int x, int y, int n) {
        boolean isOtherColor = false;
        int color = graph[x][y];
        for (int i = x; i < x + n; i++) {
            if (isOtherColor) break;
            for (int j = y; j < y + n; j++) {
                if (color != graph[i][j]) {
                    isOtherColor = true;
                    break;
                }
            }
        }
        if (isOtherColor) {
            solution(x, y, n / 2);
            solution(x, y + n / 2, n / 2);
            solution(x + n / 2, y, n / 2);
            solution(x + n / 2, y + n / 2, n / 2 );
        }
        else {
            if (color == WHITE) w++;
            else b++;
        }
        /*
        0 to 4, 0 to 4
        0 to 4, 4 to 8
        4 to 8, 0 to 4
        4 to 8, 4,to 8
         */
    }
   public static void main(String[] args) throws IOException {
        init();
        solution(0, 0, N);
       System.out.println(w + "\n" + b);

   }
}

/*
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0

11111
11111
11111
11111
11111


 */
class Node implements Comparable<Node> {
    int u;
    int v;
    int w;

    Node(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    void swap() {
        int tmp = u;
        u = v;
        v = tmp;
    }

    boolean isConnect() {
        return u == v;
    }

    @Override
    public int hashCode() {
        return Objects.hash(u, v, w);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(u).append(" ").append(v).append(" ").append(w);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Node) {
            Node node = (Node) obj;
            return u == node.u && v == node.v && w == node.w;
        }
        return false;
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(w, node.w);
    }
}

class Coordinate implements Comparable<Coordinate> {
    int x;
    int y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) obj;
            return x == coordinate.x && y == coordinate.y;
        }
        return false;
    }

    @Override
    public int compareTo(Coordinate coordinate) {
        return Integer.compare(x, coordinate.x);
    }

}
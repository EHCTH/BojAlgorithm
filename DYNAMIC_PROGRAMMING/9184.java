import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    static void init() throws IOException {
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j <21; j++) {
                Arrays.fill(cache[i][j], -1);
            }
        }
    }

    static boolean exit(int a, int b, int c) {
        return a == -1 && b == -1 && c == -1;
    }
    static int recursion(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) return cache[0][0][0] = 1;
        if (a > 20 || b > 20 || c > 20) return cache[20][20][20] = recursion(20, 20, 20);
        if (cache[a][b][c] != -1) return cache[a][b][c];
        if (a < b && b < c) return cache[a][b][c] = recursion(a, b, c -1)
                + recursion(a, b - 1, c - 1) - recursion(a, b - 1, c);
        return cache[a][b][c] = recursion(a - 1, b, c) + recursion(a -1 , b - 1, c)
                + recursion(a - 1, b, c - 1) - recursion(a - 1, b - 1, c - 1);

    }
    static int[][][] cache = new int[21][21][21];
    public static void main(String[] args) throws IOException {
        init();
        int a, b, c;
        while (true) {
            a = nextInt();
            b = nextInt();
            c = nextInt();
            if (exit(a, b, c)) break;
            System.out.printf("w(%d, %d, %d) = %d\n",a,b,c,recursion(a, b, c));
        }
    }
}


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

    boolean isConnected() {
        return u == v;
    }

    @Override
    public int hashCode() {
        return Objects.hash(u, v, w);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node node = (Node) obj;
            return u == node.u && v == node.v && w == node.w;
        }
        return false;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(w, o.w);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(u).append(" ").append(v).append(" ").append(w);
        return sb.toString();
    }
}

class Coordinate implements Comparable<Coordinate> {
    int first;
    int second;
    int third;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Coordinate(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
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


    /*

     왼쪽이 클 경우 양수
     오른 쪽이 클 경우 음수
     같을 경우 0

     */

    //     first 오름차순 정렬 후 값이 같을경우 second 오름 차 순 정렬
    @Override
    public int compareTo(Coordinate o) {
        int firstSort = Integer.compare(first, o.first);
        if (firstSort == 0) {
            return Integer.compare(second, o.second);
        }
        return firstSort;
    }
}
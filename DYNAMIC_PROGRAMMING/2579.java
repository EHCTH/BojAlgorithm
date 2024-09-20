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

    static int N;
    static StringBuilder sb = new StringBuilder();
    static int[][] graph = new int[2_190][2_190];
    static int[] answer = new int[3];

    static void init() throws IOException {
        N = nextInt();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = nextInt();
            }
        }
    }

    static void recursion(int x, int y, int n) {
        boolean isPaper = true;
        int paper = graph[x][y];
        for (int i = x; i < x + n; i++) {
            if (!isPaper) break;
            for (int j = y; j < y + n; j++) {
                if (paper != graph[i][j]) {
                    isPaper = false;
                    break;
                }
            }
        }
        if (isPaper) {
            answer[1 + paper]++;
            return;
        }
        int divN = n / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                recursion(x + (divN * i), y + (divN * j), divN);
            }
        }
    }

    static int[] arr;
    static int[][] cache;
    static int stair(int n, int serial) {
        if (n > N) return -987654321;
        if (n == N) return arr[n];
        if (cache[n][serial] != -1) return cache[n][serial];
        if (serial == 2) return cache[n][serial] = arr[n] + stair(n + 2, 1);
        return cache[n][serial] = Math.max(stair(n + 1, serial + 1),
                stair(n + 2, 1)) + arr[n];
    }
    public static void main(String[] args) throws IOException {
//        init();
//        recursion(0, 0, N);
//        Arrays.stream(answer).forEach(System.out::println);
        N = nextInt();
        cache = new int[N + 1][3];
        for (int i = 0; i <= N; i++) Arrays.fill(cache[i], -1);
        arr = new int[N  + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = nextInt();
        }
        System.out.println(stair(0, 0));
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
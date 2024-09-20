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
    static int n, m;
    static int[][] graph = new int[1024][1024];
    static int[][] ruleA = new int[64][64];
    static int[][] ruleB = new int[64][64];
    static int r, c, d;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][][] visited = new int[64][64][4];
    static final int DUST = 0;
    static final int CLEAN = 1;


    static void init() throws IOException {
//        n = nextInt();
//        m = nextInt();
//        r = nextInt();
//        c = nextInt();
//        d = nextInt();
//        for (int i = 0; i < n; i++) {
//            String inString = nextToken();
//            for (int j = 0; j < m; j++) {
//                ruleA[i][j] = inString.charAt(j) - '0';
//            }
//        }
//        for (int i = 0; i < n; i++) {
//            String inString = nextToken();
//            for (int j = 0; j < m; j++) {
//                ruleB[i][j] = inString.charAt(j) - '0';
//            }
//        }
        N = nextInt();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = nextInt();
            }
        }
    }
    static int recursion(int x, int y, int n) {
        if (n == 2) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = x; i < x + n; i++) {
                for (int j = y; j < y + n; j++) {
                    tmp.add(graph[i][j]);
                }
            }
            Collections.sort(tmp, Comparator.reverseOrder());
            return tmp.get(1);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                list.add(recursion(x + (n / 2) * i, y + (n / 2) * j, n / 2));
            }
        }
        Collections.sort(list, Comparator.reverseOrder());
        return list.get(1);

    }
    static boolean isRange() {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
    static int solution() {
        int ret = 0;
        while (true) {
            if (!isRange()) break;
            if (graph[r][c] == DUST) {
                graph[r][c] = CLEAN;
                d = ruleA[r][c];
                d = (d + ruleA[r][c]) % 4;
                r += dx[d];
                c += dy[d];

            }
            else {
                d = ruleB[r][c];
                d = (d + ruleB[r][c]) % 4;
                r += dx[d];
                c += dy[d];
                if (visited[d][r][c] != 0) break;
                visited[d][r][c] = 1;
            }
            r += dx[d];
            c += dy[d];
            ret++;
        }
        return ret;
    }
    static int boggle() {
        return 0;
    }
    public static void main(String[] args) throws IOException {
        init();
//        System.out.println(solution());
        System.out.println(recursion(0, 0, N));
//         9 9 17 21
//        for (int i = 0; i < )
//        recursion(0, 0 , 4);
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
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

    static int n;
    static int[] arr = new int[10_002];
    static String answer = "";
    static void init() throws IOException {
        n = nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
        }
    }

    static boolean nextPermutation() {
        int N = n - 1;
        int i = N - 1;
        while (i >= 0 && arr[i + 1] <= arr[i]) i--;
        if (i < 0) {
            reverse(0, N);
            return false;
        }
        int j = N;
        while (arr[j] <= arr[i]) j--;
        swap(i, j);
        reverse(i + 1, N);
        return true;
    }
    static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    static void reverse(int i, int j) {
        while (i < j) {
            swap(i, j);
            i++;
            j--;
        }
    }
    public static void main(String[] args) throws IOException {
        init();
        StringBuilder sb = new StringBuilder();
        long beforeTime = System.currentTimeMillis();
        while (nextPermutation()) {
            Arrays.stream(arr).limit(n).forEach((x) -> sb.append(x).append(" "));
            break;
        }
        if (sb.length() == 0) System.out.println(-1);
        else System.out.println(sb);



//        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
//        long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
//        System.out.println("시간차이(ms) : "+secDiffTime);
    }
}

/*
1
URLPM
XPRET
GIAET
XTNZY
XOQRS
1
GIRL
PRETTY
REPEAT
KARA
PANDORA
GIAZAPX

 */
/*
    2
  1   3

    3
  1   2

  output  =
    1
  2   3
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
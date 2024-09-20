import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

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

    static String sentence;
    static void init() throws IOException {
        sentence = nextToken();
    }


    static String solution() {
        String ret = "";
        for (char c : sentence.toCharArray()) {
            if (Character.isLowerCase(c)) ret += Character.toUpperCase(c);
            else ret += Character.toLowerCase(c);
        }
        return ret;
    }
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
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
        return first + " " + second + " " + third;
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
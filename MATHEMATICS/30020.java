import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//    static int[] arr = new int[11];
//    static int[] parent = new int[11];
//    static int[] rank = new int[11];
/*
    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(bf.readLine());
    }

    static int func(int n) {
        if (n == 1) {
            arr[n] = n;
            return arr[n];
        }
        arr[n] = n + func(n - 1);
        return arr[n];
    }
    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(x);
    }

    static boolean merge(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return false;
        if (rank[u] > rank[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        parent[u] = v;
        if (rank[u] == rank[v]) rank[v]++;
        return true;

    }

 */
    public static void main(String[] args) throws Exception {
        int a, b;
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        if (a <= b || b * 2 < a) {
            System.out.println("NO");
            System.exit(0);
        }
        sb.append("YES")
                .append("\n")
                .append(a - b)
                .append("\n");
        while (b + 1 < a) {
            sb.append("aba").append("\n");
            a -= 2;
            b -= 1;
        }
        IntStream.range(0, b)
                .boxed()
                .forEach((x) -> sb.append("ab"));
        sb.append("a");
        System.out.println(sb);


//        System.out.println(func(10));
//        System.out.println(Arrays.toString(arr));
//        init();
//        List<Integer> s = IntStream.range(0, 5)
//                .boxed()
//                .flatMap((x) -> IntStream.range(0, 5)
//                        .boxed()
//                        .map((y) -> x + y))
//                .collect(Collectors.toList());
//        System.out.println(s);
//
//        List<Stream<Integer>> s1 = IntStream.range(0, 5)
//                .boxed()
//                .map((x) -> IntStream.range(0, 5)
//                        .boxed()
//                        .map((y) -> x + y))
//                .collect(Collectors.toList());
//        System.out.println(s1);
    }



}

class Node {
    int data;
    Node next = null;
    Node(int data) {
        this.data = data;
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
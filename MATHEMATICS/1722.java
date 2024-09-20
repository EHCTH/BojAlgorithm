import java.util.*;

public class Main {
    static int n, k;
    static long[] arr = new long[22];
    static boolean[] visited = new boolean[22];
    static StringBuilder sb = new StringBuilder();

    static void init() {
        arr[0] = 1;
        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i - 1] * i;
        }
    }

    static void permutation(Scanner sc) {
        long N = sc.nextLong();
        for (int i = 1; i <= n; i++) {
            int cnt = 1;
            for (int j = 1; j <= n; j++) {
                if (visited[j]) continue;
                if (N <= arr[n - i] * cnt) {
                    N -= (arr[n - i] * --cnt);
                    sb.append(j).append(" ");
                    visited[j] = true;
                    break;
                }
                cnt++;
            }
        }
    }
    static void permutationOrder(Scanner sc) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < n; i++) linkedList.add(sc.nextInt());
        long order = 1;
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            int idx = linkedList.get(i - 1);
            for (int j = 1; j < idx; j++) {
                if (!visited[j]) {
                    cnt++;
                }
            }
            order += cnt * arr[n - i];
            visited[idx] = true;
        }
        sb.append(order);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        init();
        k = sc.nextInt();
        if (k == 1) permutation(sc);
        else permutationOrder(sc);
        System.out.println(sb);


    }
}
class Coordinate {
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
}
import java.util.*;
public class Main {
    static int n, answer;
    static int[][] graph = new int[52][9];
    static boolean[] visited = new boolean[9];
    static int[] order = new int[9];

    static void run() {
        int score = 0;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            boolean[] bases = new boolean[4];
            int out = 0;
            while (out < 3) {
                bases[0] = true;
                int move = graph[i][order[idx++]];
                if (idx == 9) idx = 0;
                if (move == 0) {
                    out++;
                    continue;
                }
                for (int j = 3; j > -1; j--) {
                    if (!bases[j]) continue;
                    if (j + move >= 4) score++;
                    else bases[j + move] = true;
                    bases[j] = false;
                }
            }
        }
        answer = Math.max(answer, score);
    }

    static void permutation(int N) {
        if (N == 9) {
            run();
            return;
        }
        if (N == 3) {
            permutation(N + 1);
            return;
        }
        for (int i = 1; i < 9; i++) {
            if (visited[i]) continue;
            order[N] = i;
            visited[i] = true;
            permutation(N + 1);
            visited[i] = false;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 9; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        permutation(0);
        System.out.println(answer);
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
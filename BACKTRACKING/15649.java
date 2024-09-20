import java.util.*;

public class Main {
    static int n, m;
    static boolean[] visited = new boolean[8];
    static void permutation(int N, int[] answer) {
        if (N == m) {
            Arrays.stream(answer).limit(m).forEach((x) -> System.out.print(x + " "));
            System.out.println();
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                answer[N] = i + 1;
                permutation(N + 1, answer);
                visited[i] = false;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[] answer = new int[8];
        permutation(0, answer);
    }
}
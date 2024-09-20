import java.util.*;

public class Main {
    static int n, m;
    static void combination(int N, int next, int[] answer) {
        if (N == m) {
            Arrays.stream(answer).limit(m).forEach((x) -> System.out.print(x + " "));
            System.out.println();
            return;
        }

        for (int i = next; i < n; i++) {
            answer[N] = i + 1;
            combination(N + 1, i + 1, answer);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[] answer = new int[8];
        combination(0, 0, answer);
    }
}
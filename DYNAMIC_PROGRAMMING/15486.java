import java.util.Scanner;

public class Main {
    static int n;
    static int[][] arr = new int[2][1500002];
    static int[] dp = new int[1500002];

    static int solution() {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n + 1; i++) {
            max = Math.max(max, dp[i]);
            int next = i + arr[0][i];
            if (next < n + 2) dp[next] = Math.max(dp[next], max + arr[1][i]);
        }
        return dp[n + 1];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            int t = sc.nextInt();
            int p = sc.nextInt();
            arr[0][i] = t;
            arr[1][i] = p;
        }
        System.out.println(solution());
    }
}
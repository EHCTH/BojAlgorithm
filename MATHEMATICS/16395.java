import java.util.*;

public class Main {
    static int n, k;
    static int[][] dp = new int[32][32];
    static void init() {
        for (int i = 0; i < 32; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
            for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        init();
        System.out.println(dp[n - 1][k - 1]);
    }
}
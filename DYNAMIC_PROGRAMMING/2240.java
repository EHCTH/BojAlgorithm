import java.util.*;

public class Main {
    static int t, w;
    static int[] arr = new int[1002];
    static int[][][] dp = new int[3][32][1002];
    static int solution() {
        for (int i = 1; i <= t; i++) {
            dp[1][0][i] = dp[1][0][i - 1] + (arr[i] == 1 ? 1 : 0);
            for (int j = 1; j <= w; j++) {
                if (i < j) break;
                if (arr[i] == 1) {
                    dp[1][j][i] = Math.max(dp[2][j - 1][i - 1], dp[1][j][i - 1]) + 1;
                    dp[2][j][i] = Math.max(dp[1][j - 1][i - 1], dp[2][j][i - 1]);
                }
                else {
                    dp[1][j][i] = Math.max(dp[2][j - 1][i - 1], dp[1][j][i - 1]);
                    dp[2][j][i] = Math.max(dp[1][j - 1][i - 1], dp[2][j][i - 1]) + 1;
                }
            }
        }
        int ans = 0;
        for (int j = 0; j <= w; j++) ans = Math.max(ans, Math.max(dp[1][j][t], dp[2][j][t]));
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        w = sc.nextInt();
        for (int i = 1; i <= t; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(solution());
    }
}
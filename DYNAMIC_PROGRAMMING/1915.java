import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] graph = new int[1002][1002];
    static int[][] dp = new int[1002][1002];

    static int solutions() {
        int ret = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (graph[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    ret = Math.max(ret, dp[i][j]);
                }
            }
        }
        return ret * ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            String inString = sc.next();
            for (int j = 1; j <= m; j++) {
                graph[i][j] = inString.charAt(j - 1) - '0';
            }
        }
        System.out.println(solutions());



    }

}
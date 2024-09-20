import java.util.Scanner;

public class Main {
    static int n;
    static long[] dp = new long[1000002];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        dp[1] = 1;
        n = sc.nextInt();
        if (n >= 0) {
            for (int i = 2; i <= n; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000000;
            }
        } else {
            n = Math.abs(n);
            for (int i = 2; i <= n; i++) {
                dp[i] = (dp[i - 2] - dp[i - 1]) % 1000000000;
            }
        }
        if (dp[n] > 0) System.out.println(1);
        else if (dp[n] < 0) System.out.println(-1);
        else System.out.println(0);
        System.out.println(Math.abs(dp[n]));
    }
}
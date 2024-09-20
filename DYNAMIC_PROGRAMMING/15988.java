import java.util.Scanner;
public class Main {
    static int n, t;
    static long[] dp = new long[1000002];
    static void init() {
        for (int i = 4; i <= 1000000; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        init();
        for (int i = 0; i < t; i++) {
            n = sc.nextInt();
            System.out.println(dp[n]);
        }
    }
}
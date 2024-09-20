import java.util.Scanner;
public class Main {
    static int n;
    static int[] dp = new int[1002];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dp[0] = 1;
        dp[1] = 3;
        for (int i = 2; i < n; i++)
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
        System.out.println(dp[n - 1]);
    }
}
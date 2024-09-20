import java.util.*;
public class Main {
    static int n;
    static int[] dp = new int[32];

    static int solution() {
        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 3;
        for (int i = 3; i <= n; i++) {
            dp[i] += dp[i - 2] * 3;
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += dp[j] * 2;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        System.out.println(solution());

    }
}
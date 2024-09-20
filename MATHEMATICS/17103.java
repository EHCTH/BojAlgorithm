import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int T;
    static final int MAX_NUM = 1_000_002;
    static boolean[] isPrime = new boolean[1_000_002];
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    static void checkPrime(int prime) {
        for (int i = prime * prime; i < MAX_NUM; i += prime) {
            isPrime[i] = false;
        }
    }

    static void sieveOfEratosthenes() {
        Arrays.fill(isPrime, 2, MAX_NUM, true);
        for (int i = 2; i <= Math.sqrt(MAX_NUM); i++) {
            if (isPrime[i]) {
                checkPrime(i);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        sieveOfEratosthenes();
        T = Integer.parseInt(bf.readLine());
        for (int i = 0; i < T; i++) {
            int answer = 0;
            int n = Integer.parseInt(bf.readLine());
            for (int j = 2; j <= n; j++) {
                if (!isPrime[j] || !isPrime[n - j]) continue;
                if (n - j >= j) answer++;
            }
            System.out.println(answer);
        }
    }
}
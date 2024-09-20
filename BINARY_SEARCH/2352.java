import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n;
    static int[] arr = new int[40002];
    static int[] dp = new int[40002];

    static int idx = 0;
    static Scanner sc = new Scanner(System.in);

    static void init() {
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
    }
    static int lowerIndexBinarySearch(int choiceNumber) {
        int s = 0;
        int e = idx;
        while (s < e) {
            int mid = (s + e) / 2;
            if (dp[mid] >= choiceNumber) e = mid;
            else s = mid + 1;
        }
        return s;
    }
    static int solution() {
        for (int i = 0; i < n; i++) {
            if (dp[idx] < arr[i]) dp[++idx] = arr[i];
            else dp[lowerIndexBinarySearch(arr[i])] = arr[i];
        }
        return idx;
    }
    public static void main(String[] args) {
        init();
        System.out.println(solution());
    }
}
import java.util.Scanner;
public class Main {
    static int n;
    static int idx = 0;
    static int[] dp = new int[100002];
    static int[] arr = new int[100002];

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

    static int solutions() {
        for (int i = 0; i < n; i++) {
            if (arr[i] > dp[idx]) dp[++idx] = arr[i];
            else dp[lowerIndexBinarySearch(arr[i])] = arr[i];
        }
        return n - idx;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(solutions());

    }
}
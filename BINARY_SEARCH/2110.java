import java.util.*;


public class Main {
    static int n, m;
    static int[] arr = new int[200_002];
    static Scanner sc = new Scanner(System.in);
    static void init() {
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr, 0, n);
    }
    static int upperIndexBinarySearch()  {
        int s = 1;
        int e = arr[n - 1] - arr[0] + 1;
        while (s < e) {
            int mid = (s + e) >> 1;
            if (distanceCal(mid) >= m) s = mid + 1;
            else e = mid;
        }
        return s - 1;
    }
    static int distanceCal(int choiceDistance) {
        int cnt = 1;
        int curStep = arr[0];
        for (int i = 1; i < n; i++) {
            int nextStep = arr[i];
            if (nextStep - curStep >= choiceDistance) {
                curStep = nextStep;
                cnt++;
            }
        }
        return cnt;
    }
    static int run() {
        return upperIndexBinarySearch();
    }
    public static void main(String[] args) {
        init();
        System.out.println(run());

    }
}
import java.util.*;

public class Main {
    static int n;
    static int[] arr = new int[10002];
    static int upperIndex(int st, int end, int choiceValue) {
        while (st < end) {
            int mid = (st + end) / 2;
            if (arr[mid] <= choiceValue) st = mid + 1;
            else end = mid;
        }

        return st;
    }
    static int lowerIndex(int st, int end, int choiceValue) {
        while (st < end) {
            int mid = (st + end) / 2;
            if (arr[mid] >= choiceValue) end = mid;
            else st = mid + 1;
        }
        return st;
    }
    static long permutation(int N, int next, int[] cho, long answer) {
        if (N == 2) {
            int ub = upperIndex(cho[1] + 1, n, -arr[cho[0]] - arr[cho[1]]);
            int lb = lowerIndex(cho[1]+ 1, n,-arr[cho[0]] - arr[cho[1]]);
            return answer + (ub - lb);
        }
        else {
            for (int i = next; i < n; i++) {
                    cho[N] = i;
                    answer = permutation(N + 1, i + 1, cho, answer);
                }
        }
        return answer;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr, 0, n);
        int[] tmp = new int[2];
        System.out.println(permutation(0, 0, tmp, 0));
    }
}
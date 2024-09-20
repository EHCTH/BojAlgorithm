import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n;
    static int[] arr = new int[100002];
    static int solution() {
        int answer = Integer.MAX_VALUE;
        int st = 0;
        int end = n - 1;
        while (st < end) {
            int sum = arr[st] + arr[end];
            if (Math.abs(sum) < Math.abs(answer)) answer = sum;
            if (sum < 0) st++;
            else end--;
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(solution());
    }
}
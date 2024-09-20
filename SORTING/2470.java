import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // BufferReader 공부하자
    static int n;
    static int[] arr = new int[100002];
    static int[] answer = new int[2];

    static void solutions() {
        int minValue = Integer.MAX_VALUE;
        int st = 0;
        int end = n - 1;
        while (st < end) {
            int mid = arr[st] + arr[end];
            int absMid = Math.abs(mid);
            if (absMid < minValue) {
                answer[0] = arr[st];
                answer[1] = arr[end];
                minValue = absMid;
            }
            if (mid < 0) st++;
            else end--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr, 0, n);
        solutions();
        Arrays.stream(answer).forEach((x) -> System.out.print(x + " "));

    }
}
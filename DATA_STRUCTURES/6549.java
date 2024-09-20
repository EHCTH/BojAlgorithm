import java.util.Scanner;
import java.util.Stack;

public class Main {
    static int n;
    static long[] arr = new long[100002];
    static long[] ml = new long[100002];
    static long[] mr = new long[100002];
    static void solution() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= n; i++) {
            while (!stack.isEmpty()) {
                int top = stack.peek();
                if (arr[top] < arr[i]) break;
                stack.pop();
            }
            if (stack.isEmpty()) ml[i] = 0;
            else ml[i] = stack.peek();
            stack.push(i);
        }
        stack = new Stack<>();
        for (int i = n; i >= 1; i--) {
            while (!stack.isEmpty()) {
                int top = stack.peek();
                if (arr[top] < arr[i]) break;
                stack.pop();
            }
            if (stack.isEmpty()) mr[i] = n;
            else mr[i] = stack.peek() - 1;
            stack.push(i);
        }

        long answer = 0;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, (mr[i] - ml[i]) * arr[i]);
        }
        System.out.println(answer);
    }


    static void init() {
        arr = new long[100002];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            init();
            String inString = sc.next();
            if (inString.equals("0")) break;
            n = Integer.valueOf(inString);
            for (int i = 1; i < n + 1; i++) {
                arr[i] = sc.nextInt();
            }
            solution();
        }
    }
}
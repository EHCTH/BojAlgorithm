import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static String solve(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            }

            else if (c == ')') {
                if (stack.isEmpty()) {
                    return "NO";
                }
                else {
                    stack.pop();
                }
            }
        }
        if (stack.isEmpty()) {
            return "YES";
        }
        return "NO";
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();
        for (int i = 0; i < cnt; i++) {
            System.out.println(solve(sc.next()));
        }
    }
}
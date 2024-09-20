import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static String solve(String sentence) {
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == '(' || sentence.charAt(i) == '[') {
                stack.push(sentence.charAt(i));
            } else if (sentence.charAt(i) == ')' || sentence.charAt(i) == ']') {


                if (!stack.isEmpty() && stack.peek() == '(' && sentence.charAt(i) == ')') {
                    stack.pop();
                } else if (!stack.isEmpty() && stack.peek() == '[' && sentence.charAt(i) == ']') {
                    stack.pop();
                } else {
                    return "no";
                }
            }
        }
        if(stack.isEmpty()) {
            return "yes";
        }
        return "no";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sentence;
        while (true) {
            sentence = sc.nextLine();
            if (sentence.equals(".")) {
                break;
            }
            System.out.println(solve(sentence));
        }
    }
}
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int n;

    static int[] arr = new int[100002];
    static void printArr() {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static int solution() {
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        stack.add(0);
        for (int i = 1; i < n + 2; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int top = stack.pop();
                if (stack.isEmpty()) break;
                int distance = i - stack.peek() - 1;
                answer = Math.max(answer, arr[top] * distance);
            }
            stack.add(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = n - i; j > 0; j--) {
                System.out.print("*" + "");
            }
            System.out.println();
        }

    }
}


class Coordinate {
    int first;
    int second;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Coordinate(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first + " " + second;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) obj;
            return first == coordinate.first && second == coordinate.second;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

}
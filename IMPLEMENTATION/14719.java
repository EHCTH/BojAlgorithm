import java.util.*;

public class Main {
    static int h, w;
    static int[] array = new int[500];

    public static int solutions() {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < w; i++) {
            while (!stack.isEmpty() && array[i] > array[stack.peek()]) {
                int top = stack.pop();

                if (stack.isEmpty()) {
                    break;
                }
                int distance = i - 1 - stack.peek();
                int water = Math.min(array[i], array[stack.peek()]) - array[top];
                answer += water * distance;
            }
            stack.add(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        h = sc.nextInt(); w = sc.nextInt();
        for (int i = 0; i < w; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println(solutions());
    }
}

class Coordinate {
    int first;
    int second;

    public Coordinate(int first, int second) {
        this.first = first;
        this.second = second;
    }


    public String toString() {
        return this.first + " " + this.second;
    }
}
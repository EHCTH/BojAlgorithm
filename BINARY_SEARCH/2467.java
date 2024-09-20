import java.util.*;
public class Main {
    static int n;
    static int[] arr = new int[100002];

    static void solution() {
        int[] answer = new int[2];
        int start = 0;
        int end = n - 1;
        int minValue = Integer.MAX_VALUE;
        while (start < end) {
            int curSum = arr[start] + arr[end];
            int absSum = Math.abs(curSum);
            if (absSum <= minValue) {
                minValue = absSum;
                answer[0] = arr[start];
                answer[1] = arr[end];

            }
            if (curSum < 0) start++;
            else end--;
        }
        Arrays.stream(answer).forEach((x) -> System.out.print(x + " "));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr, 0, n);
        solution();



    }
}

class Coordinate {
    int first;
    int second;
    int cnt;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Coordinate(int first, int second, int cnt) {
        this.first = first;
        this.second = second;
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return first + " " + second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
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

enum Pos {
    UP('U', 0),
    DOWN('D', 1),
    FRONT('F', 2),
    BACK('B', 3),
    LEFT('L', 4),
    RIGHT('R', 5);

    private final int value;
    private final char word;
    private static final Pos[] POS_ARR = Pos.values();

    Pos(char word, int value) {
        this.word = word;
        this.value = value;
    }

    static int equalsWord(char word) {
        for (Pos pos : POS_ARR) {
            if (pos.word == word) {
                return pos.value;
            }
        }
        return -1;
    }

    int getValue() {
        return value;
    }
}
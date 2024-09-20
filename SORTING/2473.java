import java.util.*;

public class Main {
    static int n;
    static long[] arr;
    static long[] answer = new long[3];

    static void solution() {
        long minAnswer = Long.MAX_VALUE;
        for (int start = 0; start < n - 2; start++) {
            int left = start + 1;
            int right = n - 1;
            while (left < right) {
                long curSum = arr[start] + arr[left] + arr[right];
                long absSum = Math.abs(curSum);
                if (absSum < minAnswer) {
                    minAnswer = absSum;
                    answer[0] = arr[start];
                    answer[1] = arr[left];
                    answer[2] = arr[right];

                }
                if (curSum < 0) left++;
                else right--;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        solution();
        Arrays.stream(answer).forEach((x) -> System.out.print(x + " "));

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
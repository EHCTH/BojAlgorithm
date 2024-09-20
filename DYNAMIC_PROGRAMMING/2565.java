import java.util.*;

public class Main {
    static int[][] array = new int[500][2];
    static int[] dp = new int[500];
    static int maxValue = Integer.MIN_VALUE;
    static int n;

    interface sort<T> {
         int compare(T t1, T t2);
    }
    static <T> void sortMethod(T[] array, sort<T> t) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (t.compare(array[j], array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
    static int solution(int n) {
        if (dp[n] == 0) {
            dp[n] = 1;

            for (int i = n + 1; i < dp.length; i++) {
                if (array[n][1] < array[i][1]) dp[n] = Math.max(dp[n], solution(i) + 1);
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int a, b;
        for (int i = 0; i < n; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            array[i][0] = a;
            array[i][1] = b;
        }
        sortMethod(array, (x1, x2) -> x1[0] - x2[0]);
        for (int i = 0; i < n; i++) {
            maxValue = Math.max(maxValue, solution(i));
        }
        System.out.println(n - maxValue + 1);
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
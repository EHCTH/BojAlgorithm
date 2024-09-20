import java.util.*;

        /*

          +---+
         /   /|
        +---+ |
        |   | +
        |   |/
        +---+

        */


public class Main {
    static int n;
    static int[] arr = new int[1_000_001];
    static int[] dp = new int[1_000_001];
    static int index = 0;
    static Scanner sc = new Scanner(System.in);

    static void init() {
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        dp[0] = Integer.MIN_VALUE;
    }
    static int lowerIndexBinarySearch(int choiceNumber) {
        int s = 0;
        int e = index;
        while (s < e) {
            int mid = (s + e) / 2;
            if (dp[mid] >= choiceNumber) e = mid;
            else s = mid + 1;
        }
        return s;
    }

    static void solution() {
        for (int i = 0; i < n; i++) {
            if (dp[index] < arr[i]) dp[++index] = arr[i];
            else dp[lowerIndexBinarySearch(arr[i])] = arr[i];
        }
    }
    public static void main(String[] args) {
        init();
        solution();
        System.out.println(index);

    }
}

class Node {
    char root;
    Node left = null;
    Node right = null;

    Node(char root) {
        this.root = root;
    }
}

class Coordinate implements Comparable<Coordinate> {
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

    @Override
    public int compareTo(Coordinate o) {
        return second - o.second;
    }
}
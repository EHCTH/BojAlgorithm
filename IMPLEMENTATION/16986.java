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
    static Scanner sc = new Scanner(System.in);
    static int N, K;
    static int[][] information = new int[15][15];
    static int[][] handSequence = new int[3][25];

    static void init() {
        N = sc.nextInt();
        K = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                information[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < 20; i++) handSequence[1][i] = sc.nextInt();
        for (int i = 0; i < 20; i++) handSequence[2][i] = sc.nextInt();
        for (int i = 0; i < N; i++) handSequence[0][i] = i + 1;
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }
    static boolean prePermutation(int[] arr, int end) {
        int n = end - 1;
        int i = n - 1;
        while (i >= 0 && arr[i + 1] <= arr[i]) i--;
        if (i < 0) {
            reverse(arr, 0, n);
            return false;
        }
        int j = n;
        while (arr[j] <= arr[i]) j--;
        swap(arr, i, j);
        reverse(arr, i + 1, n);
        return true;
    }
    static int run() {
        do {
            int[] winningPlayer = new int[3];
            int[] playerIndex = new int[3];
            int p1 = 0;
            int p2 = 1;
            while (true) {
                if (p1 > p2) {
                    int tmp = p1;
                    p1 = p2;
                    p2 = tmp;
                }
                if (p1 == 0 && playerIndex[p1] >= N) break;
                int hand1 = handSequence[p1][playerIndex[p1]++];
                int hand2 = handSequence[p2][playerIndex[p2]++];
                if (information[hand1][hand2] == 2) {
                    p2 = 3 - p1 - p2;
                    winningPlayer[p1]++;
                }
                else {
                    p1 = 3 - p1 - p2;
                    winningPlayer[p2]++;
                }
                if (winningPlayer[p1] >= K || winningPlayer[p2] >= K) break;
            }
            if (winningPlayer[0] >= K) return 1;
        }while (prePermutation(handSequence[0], N));
        return 0;
    }
    public static void main(String[] args) {
        init();
        System.out.println(run());
    }
}

class Horse {
    Coordinate coordinate;
    int dir, horseNumber;

    Horse(int x, int y, int dir, int horseNumber) {
        this.coordinate = new Coordinate(x, y);
        this.dir = dir;
        this.horseNumber = horseNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Horse) {
            Horse horse = (Horse) obj;
            return dir == horse.dir && horseNumber == horse.horseNumber;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, dir, horseNumber);
    }

    @Override
    public String toString() {
        return "(" + coordinate + ", " + dir + ", " + horseNumber + ")";
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
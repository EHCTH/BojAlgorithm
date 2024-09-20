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
    static int n;
    static int idx = 0;
    static int[] arr = new int[1002];
    static int[] dp = new int[1002];
    static int[] path = new int[1002];
    static List<Integer> answer = new ArrayList<>();


    static void init() {
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.fill(path, -1);

    }
    static int lowerIndexBinarySearch(int choiceNumber) {
        int s = 0;
        int e = idx;
        while (s < e) {
            int mid = (s + e) >> 1;
            if (dp[mid] >= choiceNumber) e = mid;
            else s = mid + 1;
        }
        return s;

    }
    static int solution() {
        for (int i = 0; i < n; i++) {
            if (dp[idx] < arr[i]) {
                dp[++idx] = arr[i];
                path[i] = idx;
            }
            else {
                int index = lowerIndexBinarySearch(arr[i]);
                dp[index] = arr[i];
                path[i] = index;
            }
        }
        return idx;
    }
    static void pathSearch() {
        for (int i = n - 1; i >= 0; i--) {
            if (path[i] == idx) {
                answer.add(arr[i]);
                idx--;
            }
        }
        Collections.sort(answer);
    }
    public static void main(String[] args) {
        init();
        System.out.println(solution());
        pathSearch();
        answer.forEach((x) -> System.out.print(x + " "));

    }
}

/*
5
999 1000 1 2 3
 */
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
            return coordinate == horse.coordinate && dir == horse.dir && horseNumber == horse.horseNumber;
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
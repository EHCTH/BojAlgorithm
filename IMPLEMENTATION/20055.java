import java.util.*;
import java.util.stream.IntStream;

        /*

          +---+
         /   /|
        +---+ |
        |   | +
        |   |/
        +---+

        */

public class Main {
    static final int ROBOT = 1;
    static Scanner sc = new Scanner(System.in);
    static int n, k, N;
    static int cnt = 0;
    static int[] arr = new int[202];
    static List<Integer> conveyorBeltRobotInfo = new LinkedList<>();

    static void init() {
        n = sc.nextInt();
        k = sc.nextInt();
        N = n << 1;
        for (int i = 0; i < 2 * n; i++) {
            arr[i] = sc.nextInt();
            if (i < n) conveyorBeltRobotInfo.add(0);
        }
    }

    static boolean isPut() {
        if (arr[0] != 0 && conveyorBeltRobotInfo.get(0) != ROBOT) {
            return true;
        }
        return false;
    }

    static boolean isEndGame() {
        return cnt >= k;
    }

    static void robotMove() {
        for (int i = n - 2; i > 0; i--) {
            int rotateSequence = (i + 1) % n;
            if (conveyorBeltRobotInfo.get(i) != ROBOT) continue;
            if (arr[rotateSequence] != 0 && conveyorBeltRobotInfo.get(rotateSequence) != ROBOT) {
                Collections.swap(conveyorBeltRobotInfo, i, rotateSequence);
                arr[rotateSequence]--;
                if (arr[rotateSequence] == 0) cnt++;
            }
        }
    }

    static void arriveRobot() {
        conveyorBeltRobotInfo.remove(n - 1);
        conveyorBeltRobotInfo.add(n - 1, 0);
    }

    static void rotate() {
        int rotateCnt = 1;
        int[] tmp = new int[202];
        for (int i = 0; i < N; i++) {
            tmp[i] = arr[(N + i - rotateCnt) % N];
        }
        arr = tmp;
        conveyorBeltRobotInfo.remove(n - 1);
        conveyorBeltRobotInfo.add(0, 0);


    }

    static void put() {
        arr[0]--;
        if (arr[0] == 0) cnt++;
        conveyorBeltRobotInfo.remove(0);
        conveyorBeltRobotInfo.add(0, ROBOT);
    }
    static int putTheRobotOnConveyorBelt() {
        int time = 0;
        while (true) {
            time++;
            rotate();
            arriveRobot();
            robotMove();
            if (isPut()) put();
            if (isEndGame()) return time;
        }
    }

    static int run() {
        return putTheRobotOnConveyorBelt();
    }

    public static void main(String[] args) {
        init();
        System.out.println(run());
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
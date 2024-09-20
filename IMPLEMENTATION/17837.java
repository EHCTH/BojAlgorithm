import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    static int n, k;
    static int t = 1000;
    static int[][] graph = new int[15][15];
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static final int WHITE = 0;
    static final int RED = 1;
    static final int BLUE = 2;
    static Map<Integer, Integer> reveresDir = new HashMap<>();

    static Map<Coordinate, List<Horse>> horseMap = new HashMap<>();
    static List<Horse> horses = new ArrayList<>();

    static void init() {
        reveresDir.put(1, 2);
        reveresDir.put(2, 1);
        reveresDir.put(3, 4);
        reveresDir.put(4, 3);


        n = sc.nextInt();
        k = sc.nextInt();
        for (int i = 0; i < 15; i++) {
            Arrays.fill(graph[i], BLUE);
            for (int j = 0; j < 15; j++) {
                horseMap.put(new Coordinate(i, j), new ArrayList<>());
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        for (int num = 1; num <= k; num++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int dir = sc.nextInt();
            Horse horse = new Horse(x, y, dir, num);
            horseMap.get(new Coordinate(x, y)).add(horse);
            horses.add(horse);
        }

    }

    static int run() {
        while (t--> 0) {
            for (Horse horse : horses) {
                Coordinate coordinate = horse.coordinate;
                int dir = horse.dir;
                int nx = coordinate.first + dx[dir];
                int ny = coordinate.second + dy[dir];
                if (graph[nx][ny] == BLUE) {
                    int rDir = reveresDir.get(dir);
                    nx = coordinate.first + dx[rDir];
                    ny = coordinate.second + dy[rDir];
                    horse.dir = rDir;
                    if (graph[nx][ny] == BLUE) continue;
                }

                List<Horse> takeHorse = new ArrayList<>();
                List<Horse> dropHorse = new ArrayList<>();
                int size = horseMap.get(coordinate).size();
                Coordinate nCoordinate = new Coordinate(nx, ny);
                for (int i = 0; i < size; i++) {
                    if (horseMap.get(coordinate).get(i) == horse) {
                        takeHorse.addAll(horseMap.get(coordinate).subList(i, size));
                        dropHorse.addAll(horseMap.get(coordinate).subList(0, i));
                        horseMap.put(coordinate, dropHorse);

                        if (graph[nx][ny] == RED) Collections.reverse(takeHorse);
                        horseMap.get(nCoordinate).addAll(takeHorse);
                        for (Horse otherHorse : takeHorse) {
                            otherHorse.coordinate = nCoordinate;
                        }
                        if (horseMap.get(nCoordinate).size() >= 4) {
                            return 1000 - t;
                        }
                        break;

                    }
                }
            }
        }
        return -1;
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
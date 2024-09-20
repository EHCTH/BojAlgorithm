import java.util.*;


public class Main {
    static int n, m;
    static char[][] graph = new char[1002][1002];
    static int[][] earthquakeResistant = new int[1002][1002];
    static Scanner sc = new Scanner(System.in);
    static int breakBuilding = 0;
    static int safeBuilding = 0;
    static int[] dx = {-1, 0, 1, 0, -2, 0, 2, 0};
    static int[] dy = {0, 1, 0, -1, 0, 2, 0, -2};
    static int startX, startY;
    static void buildingCount() {
        breakBuilding++;
        safeBuilding--;
    }
    static void init() {
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String inString = sc.next();
            for (int j = 0; j < inString.length(); j++) {
                char c = inString.charAt(j);
                graph[i][j] = c;
                if (c == '@') {
                    startX = i;
                    startY = j;
                } else if (c == '*') {
                    earthquakeResistant[i][j] = 1;
                    safeBuilding++;
                } else if (c == '#') {
                    earthquakeResistant[i][j] = 2;
                    safeBuilding++;
                }
            }
        }
    }

    static boolean checkMakeAfterShocks(int nx, int ny) {
        earthquakeResistant[nx][ny]--;
        if (earthquakeResistant[nx][ny] == 0) return true;
        return false;
    }

    static Deque<Coordinate> startEarthquake() {
        Deque<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(startX, startY));
        Deque<Coordinate> ret = new LinkedList<>();
        boolean[] checkSafeBuilding = new boolean[4];
        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            int x = coordinate.first;
            int y = coordinate.second;
            for (int i = 0; i < 8; i++) {
                if (i >= 4) {
                    if (checkSafeBuilding[i - 4]) continue;
                }
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (graph[nx][ny] == '|') {
                        if (i < 4) checkSafeBuilding[i] = true;
                        continue;
                    }
                    if (graph[nx][ny] == '*') {
                        earthquakeResistant[nx][ny]--;
                        buildingCount();
                        ret.add(new Coordinate(nx, ny));
                    } else if (graph[nx][ny] == '#') {
                        earthquakeResistant[nx][ny]--;
                    }
                }
            }
        }
        return ret;

    }

    static void solution() {
        Deque<Coordinate> queue = startEarthquake();
        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            int x = coordinate.first;
            int y = coordinate.second;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (graph[nx][ny] == '|') continue;
                    if (graph[nx][ny] == '*' || graph[nx][ny] == '#') {
                        if (checkMakeAfterShocks(nx, ny)) {
                            buildingCount();
                            queue.add(new Coordinate(nx, ny));
                        }
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        init();
        solution();
        System.out.println(breakBuilding + " " + safeBuilding);
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
/*
6 8
1
1 3 2
1 2 3
1 4 5
2 3 2
2 5 8
3 4 2
4 5 6
5 6 1
 */
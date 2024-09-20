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
    static int n, m, t;
    static int[][] graph = new int[52][52];
    static List<int[]> turn = new ArrayList<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static void init() {
        n = sc.nextInt();
        m = sc.nextInt();
        t = sc.nextInt();

        /*
          원판이 예를 들어 이렇게 되어 있을 경우 그래프로 행과 열로 따져보면

                    2
                    3
                    5
                    1
         2  5  2  3   1  2  1  1
                    2
                    4
                    3
                    3

           int[][] graph = { {1, 1, 2, 3},
                             {5, 2, 4, 2},
                             {3, 1, 3, 2},
                             {2, 1, 3 ,2}
                           }

           이렇게 표현 가능

         */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < t; i++) {
            int x, d, k;
            x = sc.nextInt();
            d = sc.nextInt();
            k = sc.nextInt();
            turn.add(new int[] {x, d, k});
        }
    }

    static void reverseRotate(int[][] graph, int rotateCnt, int index) {
        int[] tmp = new int[52];
        for (int i = 0; i < m; i++) {
            tmp[i] = graph[index][(i + rotateCnt) % m];
        }
//        1 2 3 0
//        i == 3  0 이 나와야함
        graph[index] = tmp;
    }
    static void rotate(int[][] graph, int rotateCnt, int index) {
        int[] tmp = new int[52];
        for (int i = 0; i < m; i++) {
            tmp[i] = graph[index][(i + m - rotateCnt) % m];
        }
        graph[index] = tmp;


        /*
        4 + 0 - 1
        4 + 1 - 1

        3 0 1 2

         5 2 4 2
         1 rotate ->
         2 5 2 4

         1 reverseRotate
         2 4 2 5


         */
    }
    static void bfs(int i, int j, boolean[][] visited) {
        Deque<Coordinate> queue = new LinkedList<>();
        queue.offer(new Coordinate(i, j));
        boolean isNotFound = false;
        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            int x = coordinate.first;
            int y = coordinate.second;
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = ((y + dy[dir]) % m) < 0 ? (((y + dy[dir]) % m) + m) : ((y + dy[dir]) % m);
                if (0 <= nx && nx < n && graph[x][y] == graph[nx][ny] && !visited[nx][ny]) {
                    queue.offer(new Coordinate(nx, ny));
                    visited[nx][ny] = true;
                    isNotFound = true;
                }
            }

        }
        if (!isNotFound) {
            visited[i][j] = false;
        }

    }
    static void adjeValidate() {
        boolean[][] visited = new boolean[52][52];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && graph[i][j] > 0) {
                    visited[i][j] = true;
                    bfs(i, j, visited);
                }
            }
        }
        avgAndDelete(visited);
    }
    static void avgAndDelete(boolean[][] visited) {
        double sum = 0.0;
        int cnt = 0;
        boolean isFlag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    graph[i][j] = 0;
                    isFlag = true;
                }
                else {
                    if (graph[i][j] > 0) {
                        sum += graph[i][j];
                        cnt++;
                    }
                }
            }
        }
        if (!isFlag && cnt > 0) {
            double avg = sum / cnt;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (avg < graph[i][j]) graph[i][j]--;
                    else if (0 < graph[i][j] && graph[i][j] < avg) graph[i][j]++;
                }
            }
        }
    }
    static int run() {
        for (int[] s : turn) {
            int x = s[0];
            int d = s[1];
            int k = s[2];
            for (int i = 0; i < n; i++) {
                if ((i + 1) % x == 0) {
                    if (d == 0) rotate(graph, k, i);
                    else reverseRotate(graph, k, i);
                }
            }
            adjeValidate();

        }
        return Arrays.stream(graph).flatMapToInt(Arrays::stream).sum();
    }

    static void print() {
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void printVisited(boolean[][] visited) {
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(visited[i][j] + " ");
            }
            System.out.println();
        }
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
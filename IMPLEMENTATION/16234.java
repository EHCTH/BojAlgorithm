import java.util.*;

public class Main {
    static int n, l, r;
    static int answer = 0;

    static int[][] graph = new int[50][50];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] visited = new int[50][50];

    static void solve(Deque<Coordinate> deque, int cx, int cy) {
        int sum = 0;
        int cnt = 0;
        deque.add(new Coordinate(cx, cy));
        Deque<Coordinate> united = new LinkedList<>();
        while (!deque.isEmpty()) {
            Coordinate coordinate = deque.poll();
            int x = coordinate.first, y = coordinate.second;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                int curPopulation = graph[x][y];
                if (0 <= nx && nx < n && 0 <= ny && ny < n && visited[nx][ny] == 0) {
                    int nextPopulation = graph[nx][ny];
                    int diff = Math.abs(curPopulation - nextPopulation);
                    if (l <= diff && diff <= r) {
                        visited[nx][ny] = 1;
                        sum += nextPopulation;
                        cnt++;
                        deque.add(new Coordinate(nx, ny));
                        united.add(new Coordinate(nx, ny));
                    }
                }
            }
        }
        for (Coordinate coordinate : united) {
            graph[coordinate.first][coordinate.second] = sum / cnt;
        }
    }

    static void init() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], 0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();
        r = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        do {
            int cnt = 0;
            Deque<Coordinate> deque = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0) {
                        solve(deque, i, j);
                        cnt++;
                    }
                }
            }
            if (cnt == n * n) break;
            answer++;
            init();
        } while (true) ;
        System.out.println(answer);
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
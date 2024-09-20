import java.util.*;


public class Main {
    static int n, m;
    static int[][] graph = new int[52][52];
    static final int SPACE = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy  ={0, 1, 0, -1};
    static int minValue = Integer.MAX_VALUE;
    static List<Coordinate> virusList = new ArrayList<>();
    static int spaceCnt = 0;

    static int run(Coordinate[] coordinates, int spaceCnt) {
        boolean[][] visited = new boolean[n][n];
        Deque<Coordinate> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            Coordinate c = coordinates[i];
            visited[c.first][c.second] = true;
            queue.add(c);
        }

        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            int x = coordinate.first;
            int y = coordinate.second;
            int cnt = coordinate.cnt;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < n && graph[nx][ny] != WALL) {
                    if (!visited[nx][ny]) {
                        if (graph[nx][ny] == SPACE) {
                            spaceCnt--;
                        }
                        if (spaceCnt == 0) {
                            return cnt + 1;
                        }
                        visited[nx][ny] = true;
                        queue.add(new Coordinate(nx, ny, cnt + 1));
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    static void solution(int N, int cnt, Coordinate[] coordinates) {
        if (N == virusList.size()) {
            if (cnt == m) {
                minValue = Math.min(minValue, run(coordinates, spaceCnt));
            }
            return;
        }
        coordinates[cnt] = virusList.get(N);
        solution(N + 1, cnt + 1, coordinates);
        solution(N + 1, cnt, coordinates);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
                if (graph[i][j] == 0) {
                    spaceCnt++;
                }
                else if (graph[i][j] == VIRUS) {
                    virusList.add(new Coordinate(i, j, 0));
                }
            }
        }
        Coordinate[] coordinates = new Coordinate[12];
        if (spaceCnt == 0) {
            System.out.println(0);
        }

        else {
            solution(0, 0, coordinates);
            if (minValue == Integer.MAX_VALUE) System.out.println(-1);
            else System.out.println(minValue);
        }

    }
}


class Coordinate {
    int first;
    int second;
    int cnt;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Coordinate(int first, int second, int cnt) {
        this.first = first;
        this.second = second;
        this.cnt = cnt;
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

}
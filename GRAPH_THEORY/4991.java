import java.util.*;

public class Main {
    static int n, m;
    static int x, y;
    static int[][] graph = new int[20][20];
    static int[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int bfs(int oriX, int oriY, int subSetCnt) {
        visited[oriX][oriY][0] = 1;
        Deque<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(oriX, oriY, 0, 0));
        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            int x = coordinate.first;
            int y = coordinate.second;
            int cnt = coordinate.third;
            int subset = coordinate.fourth;
            if (subset == (1 << subSetCnt) - 1) return cnt;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nSubSet = subset;
                if(ny < 0 || nx< 0 || ny >= m || nx >= n || graph[nx][ny] == -1) continue;
                if(graph[nx][ny] > 0) nSubSet |= (1 << (graph[nx][ny] - 1));
                if(visited[nx][ny][nSubSet] == 1) continue;
                queue.add(new Coordinate(nx, ny, cnt + 1, nSubSet));
                visited[nx][ny][nSubSet] = 1;
            }

        }
        return -1;
    }
    static void init() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], -1);
        }
        visited = new int[20][20][1<<10];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            m = sc.nextInt();
            n = sc.nextInt();
            if (n == 0 && m == 0) break;
            init();
            int subsetCnt = 1;
            for (int i = 0; i < n; i++) {
                String inString = sc.next();
                for (int j = 0; j < m; j++) {
                    char c = inString.charAt(j);
                    if (c == 'o') {
                        x = i;
                        y = j;
                        graph[i][j] = 0;
                    }
                    else if (c == '*') graph[i][j] = subsetCnt++;
                    else if(c == '.') graph[i][j] = 0;
                }
            }
            System.out.println(bfs(x, y, subsetCnt - 1));
        }
    }
}



class Coordinate {
    int first;
    int second;
    int third;
    int fourth;

    public Coordinate(int first, int second, int third, int fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    @Override
    public String toString() {
        return this.first + " " + this.second;
    }

    @Override
    public boolean equals(Object obj) {
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
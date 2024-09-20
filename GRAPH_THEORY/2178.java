import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 백준 미로탐색
    static int n;
    static int m;
    static boolean[][] visited;
    static int[][] graph;
    static int[] dirX = {1, 0, -1, 0};
    static int[] dirY = {0, 1, 0, -1};

    public static int solvePoint(int startX, int startY) {
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(0, 0));
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            if (x == n - 1 && y == m - 1) {
                return graph[x][y];
            }
            for (int i = 0; i < dirX.length; i++) {
                int nx = x + dirX[i];
                int ny = y + dirY[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (!visited[nx][ny] && graph[nx][ny] == 1) {
                        queue.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                        graph[nx][ny] = graph[x][y] + 1;

                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        visited = new boolean[n][m];
        graph = new int[n][m];

        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(String.valueOf(line[j]));
            }
        }
        System.out.println(solvePoint(0, 0));
    }
    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
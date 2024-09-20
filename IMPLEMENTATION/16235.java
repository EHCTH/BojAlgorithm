import java.util.*;

public class Main {
    static int n, m, k;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[][] addGraph = new int[10][10];
    static int[][] graph = new int[10][10];
    static LinkedList<Coordinate> tree = new LinkedList<>();

    static void spring() {
        for (Coordinate c : tree) {
            if (graph[c.first][c.second] >= c.third) {
                graph[c.first][c.second] -= c.third;
                c.third++;
            }
            else c.alive = false;
        }
    }
    static void summer() {
        Iterator<Coordinate> it = tree.iterator();
        while (it.hasNext()) {
            Coordinate c = it.next();
            if (!c.alive) {
                graph[c.first][c.second] += c.third / 2;
                it.remove();
            }
        }
    }

    static LinkedList<Coordinate> autumn() {
        LinkedList<Coordinate> ret = new LinkedList<>();
        for (Coordinate c : tree) {
            int x = c.first, y = c.second, z = c.third;
            if (z % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + dx[i], ny = y + dy[i];
                    if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                        ret.add(new Coordinate(nx, ny, 1, true));
                    }
                }
            }
        }
        return ret;
    }

    static void winter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] += addGraph[i][j];
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                addGraph[i][j] = sc.nextInt();
                graph[i][j] = 5;
            }
        }

        int x, y, z;
        for (int i = 0; i < m; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            z = sc.nextInt();
            tree.add(new Coordinate(x - 1, y - 1, z, true));
        }
        for (int year = 0; year < k; year++) {
            spring();
            summer();
            tree.addAll(0, autumn());
            winter();
        }
        System.out.println(tree.size());
    }
}


class Coordinate {
    int first;
    int second;
    int third;
    boolean alive;

    public Coordinate(int first, int second, int third, boolean alive) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.alive = alive;
    }


    public String toString() {
        return this.first + " " + this.second + " " + this.third + " " + this.alive;
    }
}
import java.util.Scanner;

public class Main {
    static final int RIGHT = 1;
    static final int LEFT = 2;
    static final int INF = 987654321;
    static int N, M, H;
    static int[][] graph = new int[30][10];

    public static boolean check() {
        for (int i = 0; i < N; i++) {
            int row = 0, col = i;
            do {
                if (graph[row][col] == LEFT) col--;
                else if (graph[row][col] == RIGHT) col++;
                row++;
            } while (row != H);
            if (col != i) return false;
        }
        return true;
    }

    public static int solutions(int pos, int cnt) {
        int ret = INF;
        if (cnt == 3 || pos >= N * H) {
            if (check()) {
                return cnt;
            }
            return INF;
        }
        int row = pos / N, col = pos % N;
        if (col != N - 1 && graph[row][col] == 0 && graph[row][col + 1] == 0) {
            graph[row][col] = RIGHT;
            graph[row][col + 1] = LEFT;
            ret = Math.min(ret, solutions(pos + 2, cnt + 1));
            graph[row][col] = graph[row][col + 1] = 0;
        }
        ret = Math.min(ret, solutions(pos + 1, cnt));
        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        H = sc.nextInt();

        for (int i = 0; i < M; i++) {
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();
            graph[a - 1][b - 1] = RIGHT;
            graph[a - 1][b] = LEFT;
        }
        int answer = solutions(0, 0);
        if (answer == INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
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
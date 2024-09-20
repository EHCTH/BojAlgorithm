import java.io.BufferedReader;
import java.io.InputStreamReader;
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
    static int n, m, k;
    static int[][] graph = new int[22][22];
    static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static int bfs(Dice dice) {
        Deque<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(dice.x, dice.y));
        boolean[][] visited = new boolean[22][22];
        visited[dice.x][dice.y] = true;
        int cnt = 1;
        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            int x = coordinate.first;
            int y = coordinate.second;
            for (int i = 0 ; i < 4; i++) {
                int nx, ny;
                nx = x + dice.dx[i];
                ny = y + dice.dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (graph[nx][ny] == graph[dice.x][dice.y] && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        cnt++;
                        queue.add(new Coordinate(nx, ny));
                    }
                }
            }
        }
        return cnt;

    }
    static void move(Dice dice) {
        int nx = dice.getNx();
        int ny = dice.getNy();
        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
            dice.reverseDir();
            nx = dice.getNx();
            ny = dice.getNy();
        }
        dice.updateCoordinate(nx, ny);
        dice.move();
    }


    static int run() {
        int answer = 0;
        Dice dice = new Dice();
        for (int i = 0; i < k; i++) {
            move(dice);
            answer += graph[dice.x][dice.y] * bfs(dice);
            int B = graph[dice.x][dice.y];
            dice.updateB(B);
            dice.rotate(B);


        }
        return answer;
    }
    public static void main(String[] args) throws Exception {
        init();
        System.out.println(run());
    }

}

class Dice {
    int x = 0;
    int y = 0;
    int dir = 1;
    int A = 6;
    int B;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    int[] status  = {2, 5, 1, 6, 4, 3};

    // B, F, U, D, L, R
    // 0  1  2  3  4  5

    void updateB(int B) {
        this.B = B;
    }
    void updateCoordinate(int nx, int ny) {
        this.x = nx;
        this.y = ny;
    }
    void move() {
        int b,f,u,d,l,r;
        b = status[0];
        f = status[1];
        u = status[2];
        d = status[3];
        l = status[4];
        r = status[5];

        if (dir == 0) {
            status[0] = u;
            status[3] = b;
            status[1] = d;
            status[2] = f;
        }
        else if (dir == 1) {
            status[5] = u;
            status[3] = r;
            status[4] = d;
            status[2] = l;

        }
        // B, F, U, D, L, R
        // 0  1  2  3  4  5
        else if (dir == 2) {
            status[1] = u;
            status[3] = f;
            status[0] = d;
            status[2] = b;

        }

        else {
            status[4] = u;
            status[3] = l;
            status[5] = d;
            status[2] = r;
        }
        A = status[3];
    }
    void reverseDir() {
        dir = (dir + 2) % 4;
    }
    int getNx() {
        return x + dx[dir];
    }
    int getNy() {
        return y + dy[dir];
    }
    void rotate(int B) {
        if (A <  B) counterClockWiseRotate();
        else if (A > B) clockWiseRotate();
    }
    void clockWiseRotate() {
        dir = (dir + 1) % 4;
    }

    void counterClockWiseRotate() {
        dir = (dir + 3) % 4;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(x).append(" ").append(y).append(" ").append(A).append(" ").append(dir);
        return sb.toString();
    }
}

class Node {
    int data;
    Node next = null;

    Node(int data) {
        this.data = data;
    }
}

class Coordinate implements Comparable<Coordinate> {
    int first;
    int second;
    int third;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Coordinate(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
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


    /*

     왼쪽이 클 경우 양수
     오른 쪽이 클 경우 음수
     같을 경우 0

     */

    //     first 오름차순 정렬 후 값이 같을경우 second 오름 차 순 정렬
    @Override
    public int compareTo(Coordinate o) {
        int firstSort = Integer.compare(first, o.first);
        if (firstSort == 0) {
            return Integer.compare(second, o.second);
        }
        return firstSort;
    }


}
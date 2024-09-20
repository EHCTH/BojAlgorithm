import java.util.*;
import java.util.List;

public class Main {
    enum Pos {
        FRONT(0),
        LEFT_DIAGONAL(1),
        RIGHT_DIAGONAL(2);
        int pos;
        Pos(int pos) {
            this.pos = pos;
        }
    }
    enum HeaterNumber {
        SPACE(0),
        RIGHT(1),
        LEFT(2),
        UP(3),
        DOWN(4),
        SEARCH(5);
        int number;
        HeaterNumber(int number) {
            this.number = number;
        }

    }
    static final HeaterNumber[] HEATER_NUMBERS = HeaterNumber.values();
    static final Pos[] POS_ARR = Pos.values();
    static final int WALL = 1;
    static final int CUR = 0;
    static final int WALL_COL = 1;
    static final int WALL_ROW = 2;
    static HeaterNumber initHeaterPos(int heater) {
        return HEATER_NUMBERS[heater];
    }
    static int n, m, k, w;
    static int[][][] graph = new int[5][42][42];
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static int[][] heaterX = {{0}, {0, -1, 1}, {0, -1, 1}, {-1, -1, -1}, {1, 1, 1}};
    static int[][] heaterY = {{0}, {1, 1, 1}, {-1, -1, -1}, {0, -1, 1}, {0, -1, 1}};
    static List<Heater> heaterList = new ArrayList<>();
    static List<Coordinate> searchSpace = new ArrayList<>();
    static boolean isFrontNotExistWall(HeaterNumber heaterNumber, int nx, int ny) { // 정면 바람
        if (heaterNumber == HeaterNumber.RIGHT) return graph[WALL_COL][nx][ny] != WALL;
        else if (heaterNumber == HeaterNumber.LEFT) return graph[WALL_COL][nx][ny + 1] != WALL;
        else if (heaterNumber == HeaterNumber.UP) return graph[WALL_ROW][nx][ny] != WALL;
        else return graph[WALL_ROW][nx - 1][ny] != WALL;
    }

    static boolean windPosOne(HeaterNumber heatNumber, int x, int y) { // 대각바람 index 1
        if (heatNumber == HeaterNumber.RIGHT) return (graph[WALL_ROW][x - 1][y] != WALL && graph[WALL_COL][x - 1][y + 1] != WALL);
        else if (heatNumber == HeaterNumber.LEFT) return (graph[WALL_ROW][x - 1][y] != WALL && graph[WALL_COL][x - 1][y] != WALL);
        else if (heatNumber == HeaterNumber.UP) return (graph[WALL_COL][x][y] != WALL && graph[WALL_ROW][x - 1][y - 1] != WALL);
        else return (graph[WALL_ROW][x][y - 1] != WALL && graph[WALL_COL][x][y] != WALL);
    }


    static boolean windPosTwo(HeaterNumber heatNumber, int x, int y) { //  대각 바람 index 2
        if (heatNumber == HeaterNumber.RIGHT) return (graph[WALL_ROW][x][y] != WALL && graph[WALL_COL][x + 1][y + 1] != WALL);
        else if (heatNumber == HeaterNumber.LEFT) return (graph[WALL_COL][x + 1][y] != WALL && graph[WALL_ROW][x][y] != WALL);
        else if (heatNumber == HeaterNumber.UP) return (graph[WALL_COL][x][y + 1] != WALL && graph[WALL_ROW][x - 1][y + 1] != WALL);
        else return (graph[WALL_ROW][x][y + 1] != WALL && graph[WALL_COL][x][y + 1] != WALL);
    }

    static boolean isFrontOrDiagonalNotExistWall(HeaterNumber heatNumber, Pos windPos, int nx, int ny) {
        if (windPos == Pos.FRONT) return isFrontNotExistWall(heatNumber, nx, ny);
        int x = nx - heaterX[heatNumber.number][windPos.pos];
        int y = ny - heaterY[heatNumber.number][windPos.pos];
        if (windPos == Pos.LEFT_DIAGONAL) return windPosOne(heatNumber, x, y);
        return windPosTwo(heatNumber, x, y);
    }

    static void temperatureControl() { // 온도 조절
        int[][] newGraph = new int[42][42];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (graph[CUR][x][y] == 0) continue;
                for (int i = 1; i < 5; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    // 벽체크 우 좌 상 하 순으로
                    if (0 <= nx && nx < n && 0 <= ny && ny < m && graph[CUR][x][y] > graph[CUR][nx][ny]) {
                        if (!isFrontNotExistWall(HEATER_NUMBERS[i], nx, ny)) continue;
                        int div = (graph[CUR][x][y] - graph[CUR][nx][ny]) / 4;
                        newGraph[x][y] -= div;
                        newGraph[nx][ny] += div;
                    }
                }
            }
        }
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                graph[CUR][x][y] += newGraph[x][y];
                if (graph[CUR][x][y] != 0 && (x == 0 || y == 0 || x == n - 1 || y == m - 1)) graph[CUR][x][y] -= 1;
            }
        }
    }

    static void windMove(HeaterNumber heaterNumber, Coordinate coordinate) {
        Deque<Coordinate> queue = new LinkedList<>();
        boolean[][] visited = new boolean[42][42];
        int cx = coordinate.first + heaterX[heaterNumber.number][Pos.FRONT.pos];
        int cy = coordinate.second + heaterY[heaterNumber.number][Pos.FRONT.pos];
        int cCnt = coordinate.cnt;
        if (isFrontNotExistWall(heaterNumber, cx, cy)) {
            visited[cx][cy] = true;
            graph[CUR][cx][cy] += cCnt;
            queue.add(new Coordinate(cx, cy, cCnt - 1));
        }
        while (!queue.isEmpty()) {
            Coordinate c = queue.poll();
            int x = c.first;
            int y = c.second;
            int cnt = c.cnt;
            if (cnt == 0) break;
            for (int windPos = 0; windPos < 3; windPos++) {
                int nx = x + heaterX[heaterNumber.number][POS_ARR[windPos].pos];
                int ny = y + heaterY[heaterNumber.number][POS_ARR[windPos].pos];
                if (0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny]) {
                    if (isFrontOrDiagonalNotExistWall(heaterNumber, POS_ARR[windPos], nx, ny)) {
                        graph[CUR][nx][ny] += cnt;
                        visited[nx][ny] = true;
                        queue.add(new Coordinate(nx, ny, cnt - 1));
                    }
                }
            }
        }
    }

    static void windBlow() {
        for (Heater heater : heaterList) {
            HeaterNumber heaterNumber = heater.getHeaterNumber();
            Coordinate coordinate = heater.getCoordinate();
            windMove(heaterNumber, coordinate);
        }
    }

    static boolean temperatureCheck() {
        for (Coordinate coordinate : searchSpace) {
            int x = coordinate.first;
            int y = coordinate.second;
            if (graph[CUR][x][y] < k) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int wind = 5;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int heater = sc.nextInt();
                HeaterNumber heaterNumber = initHeaterPos(heater);
                if (heaterNumber == HeaterNumber.SPACE) continue;
                Coordinate coordinate = new Coordinate(i, j, wind);
                if (heaterNumber == HeaterNumber.SEARCH) {
                    searchSpace.add(coordinate);
                } else {
                    heaterList.add(new Heater(heaterNumber, coordinate));
                }
            }
        }
        w = sc.nextInt();
        for (int wall = 0; wall < w; wall++) {
            int wallX = sc.nextInt() - 1;
            int wallY = sc.nextInt() - 1;
            int wallKind = sc.nextInt();
            if (wallKind == 0) {
                graph[WALL_ROW][wallX - 1][wallY] = WALL;
            } else {
                graph[WALL_COL][wallX][wallY + 1] = WALL;
            }
        }
        int cnt = 0;
        do {
            if (cnt > 100) break;
            cnt++;
            windBlow();
            temperatureControl();
        }
        while (!temperatureCheck());
        System.out.println(cnt);
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

class Heater {
    Main.HeaterNumber heaterNumber;
    Coordinate coordinate;
    Main.HeaterNumber getHeaterNumber() {
        return heaterNumber;
    }

    Coordinate getCoordinate() {
        return coordinate;
    }

    Heater(Main.HeaterNumber heaterNumber, Coordinate coordinate) {
        this.heaterNumber = heaterNumber;
        this.coordinate = coordinate;
    }

}
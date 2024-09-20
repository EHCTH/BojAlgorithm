import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int r, c, m;
    static int R, C;
    static SharkInfo sharkInfo2D;
    static List<Shark> sharkInfo1D = new ArrayList<>();
    static int[] lookUpTableX = new int[2 * 102 - 2];
    static int[] lookUpTableY = new int[2 * 102 - 2];

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sharkInfo2D = new SharkInfo();
        R = r * 2 - 2;
        C = c * 2 - 2;
        setLookUpTable();
        setSharkInfo();


    }

    static void setLookUpTable() {
        for (int i = 0; i < R; i++) {
            if (i >= r) lookUpTableX[i] = lookUpTableX[i - 1] - 1;
            else lookUpTableX[i] = i;
        }

        for (int i = 0; i < C; i++) {
            if (i >= c) lookUpTableY[i] = lookUpTableY[i - 1] - 1;
            else lookUpTableY[i] = i;
        }
    }

    static void setSharkInfo() throws Exception {
        for (int i = 0; i < m; i++) {
            int x, y, speed, dir, size;
            StringTokenizer st = new StringTokenizer(bf.readLine());
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;
            speed = Integer.parseInt(st.nextToken());
            dir = Integer.parseInt(st.nextToken()) - 1;
            size = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(new Coordinate(x, y), speed, Dir.of(dir), size);
            sharkInfo2D.add(shark);
            sharkInfo1D.add(shark);
        }
    }

    static void predatorSharkEatAllOthers() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (sharkInfo2D.isMoreThanTwoSharkAlive(i, j)) {
                    List<Shark> eatenSharkInfo = sharkInfo2D.initEatenSharksByPredator(i, j);
                    sharkInfo1D.removeAll(eatenSharkInfo);
                }
            }
        }
    }

    static int solution() {
        int sharksCaught = 0;
        for (int j = 0; j < c; j++) {
            sharksCaught += fisherManCatchTheShark(j);
            sharkMove();
            predatorSharkEatAllOthers();
        }
        return sharksCaught;
    }

    static void remove(int x, int y, Shark shark) {
        sharkInfo2D.clear(x, y);
        sharkInfo1D.remove(shark);
    }
    static int fisherManCatchTheShark(int y) {
        int x = 0;
        while (x < r) {
            if (sharkInfo2D.aliveShark(x, y)) {
                Shark shark = sharkInfo2D.get(x, y);
                remove(x, y, shark);
                return shark.size;

            }
            x++;
        }
        return 0;
    }

    static void updateUp(Shark shark) {
        boolean isDirChange = false;
        Coordinate coordinate = shark.coordinate;
        int nx = (coordinate.first - shark.speed) % R;
        if (nx < 0) nx += R;
        if (nx >= r - 1) isDirChange = true;
        coordinate.first = lookUpTableX[nx];
        shark.updateStatus(coordinate, isDirChange);


    }

    static void updateDown(Shark shark) {
        boolean isDirChange = false;
        Coordinate coordinate = shark.coordinate;
        int nx = (coordinate.first + shark.speed) % R;
        if (nx == 0 || nx >= r) isDirChange = true;
        coordinate.first = lookUpTableX[nx];
        shark.updateStatus(coordinate, isDirChange);
    }

    static void updateRight(Shark shark) {
        boolean isDirChange = false;
        Coordinate coordinate = shark.coordinate;
        int ny = (coordinate.second + shark.speed) % C;
        if (ny == 0 || ny >= c) isDirChange = true;
        coordinate.second = lookUpTableY[ny];
        shark.updateStatus(coordinate, isDirChange);
    }

    static void updateLeft(Shark shark) {
        boolean isDirChange = false;
        Coordinate coordinate = shark.coordinate;
        int ny = (coordinate.second - shark.speed) % C;
        if (ny < 0) ny += C;
        if (ny >= c - 1) isDirChange = true;
        coordinate.second = lookUpTableY[ny];
        shark.updateStatus(coordinate, isDirChange);

    }

    static void isDirAndUpdateSharkMove(Shark shark) {
        if (shark.dir == Dir.UP) updateUp(shark);
        else if (shark.dir == Dir.DOWN) updateDown(shark);
        else if (shark.dir == Dir.RIGHT) updateRight(shark);
        else updateLeft(shark);
    }

    static void sharkMove() {
        for (Shark shark : sharkInfo1D) {
            if (shark.isZeroSpeed()) continue;
            sharkInfo2D.remove(shark);
            isDirAndUpdateSharkMove(shark);
            sharkInfo2D.add(shark);
        }
    }


    public static void main(String[] args) throws Exception {
        init();
        System.out.println(solution());
    }
}

class SharkInfo {
    List<Shark>[][] sharkInfo = new ArrayList[102][102];

    public SharkInfo() {
        for (int i = 0; i < 102; i++) {
            for (int j = 0; j < 102; j++) {
                sharkInfo[i][j] = new ArrayList<>();
            }
        }
    }

    public Shark get(int x, int y) {
        return sharkInfo[x][y].get(0);
    }

    public void clear(int x, int y) {
        sharkInfo[x][y] = new ArrayList<>();
    }

    public void remove(Shark shark) {
        Coordinate coordinate = shark.coordinate;
        sharkInfo[coordinate.first][coordinate.second].remove(shark);
    }

    public void add(Shark shark) {
        Coordinate coordinate = shark.coordinate;
        sharkInfo[coordinate.first][coordinate.second].add(shark);
    }

    public void sortBySize(int x, int y) {
        Collections.sort(sharkInfo[x][y]);
    }

    public boolean isMoreThanTwoSharkAlive(int x, int y) {
        return sharkInfo[x][y].size() >= 2;
    }
    public List<Shark> eatenSharks(int x, int y) {
        List<Shark> eatenSharks = new ArrayList<>(sharkInfo[x][y].subList(1, sharkInfo[x][y].size()));
        clear(x, y);
        return eatenSharks;

    }

    public List<Shark> initEatenSharksByPredator(int x, int y) {
        sortBySize(x, y);
        Shark predator = get(x, y);
        List<Shark> eatenSharkInfo = eatenSharks(x, y);
        add(predator);
        return eatenSharkInfo;
    }

    public boolean aliveShark(int x, int y) {
        return !sharkInfo[x][y].isEmpty();
    }
}

enum Dir {
    UP(0),
    DOWN(1),
    RIGHT(2),
    LEFT(3);
    final int number;

    Dir(int number) {
        this.number = number;
    }

    static final Dir[] DIR_ARR = Dir.values();

    static Dir of(int number) throws IllegalAccessException {
        return Arrays.stream(DIR_ARR).filter((dir) -> dir.number == number)
                .findAny()
                .orElseThrow(IllegalAccessException::new);
    }

    Dir change() {
        if (this == RIGHT) return LEFT;
        else if (this == LEFT) return RIGHT;
        else if (this == UP) return DOWN;
        else return UP;
    }


}

class Shark implements Comparable<Shark> {
    Coordinate coordinate;
    int speed;
    Dir dir;
    int size;

    public Shark(Coordinate coordinate, int speed, Dir dir, int size) {
        this.coordinate = coordinate;
        this.speed = speed;
        this.dir = dir;
        this.size = size;
    }

    public void dirChange() {
        dir = dir.change();
    }

    public void updateStatus(Coordinate coordinate, boolean isDirChange) {
        this.coordinate = coordinate;
        if (isDirChange) dirChange();
    }

    public boolean isZeroSpeed() {
        return speed == 0;
    }

    @Override
    public int compareTo(Shark o) {
        return Integer.compare(o.size, size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(coordinate).append(" ").append(dir).append(" ").append(size);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, speed, dir, size);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Shark) {
            Shark shark = (Shark) obj;
            return coordinate.equals(shark.coordinate) && speed == shark.speed
                    && dir == shark.dir && size == shark.size;
        }
        return false;
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
        return Integer.compare(second, o.second);
    }
}
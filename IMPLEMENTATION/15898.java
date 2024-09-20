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
    static int n;
    static int[] dx = {0, 0, 1, 1};
    static int[] dy = {0, 1, 0, 1};
    static KilnInfo kilnInfo = new KilnInfo();
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));


    static void init() throws Exception {
        n = Integer.parseInt(bf.readLine());
        for (int t = 0; t < n; t++) {
            int[][] efficacy = new int[4][4];
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < 4; j++) {
                    efficacy[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            char[][] element = new char[4][4];
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < 4; j++) {
                    element[i][j] = st.nextToken().charAt(0);
                }
            }
            kilnUpdate(t, efficacy, element);
            rotate(t);
        }

    }

    static void rotate(int t) {
        kilnInfo.rotate90(t);
        kilnInfo.rotate180(t);
        kilnInfo.rotate270(t);
    }

    static void kilnUpdate(int t, int[][] efficacy, char[][] element) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Color color = Color.of(element[i][j]);
                kilnInfo.initColor(t, i, j, color);
                kilnInfo.initEfficacy(t, i, j, efficacy[i][j]);
            }
        }

    }


    static List<Kiln>[] initKiln2D() {
        List<Kiln>[] ret = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            ret[i] = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                ret[i].add(new Kiln());
            }
        }
        return ret;
    }
    static int cal(List<Kiln>[] kiln2D) {
        int ret = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ret += kiln2D[i].get(j).cal();
            }
        }
        return ret;
    }

    static int run(int r, int[] answer, int[] rotate, int[] pick) {
        int ret = 0;
        if (r == 3) {
            List<Kiln>[] kiln2D = initKiln2D();
            for (int c = 0; c < 3; c++) {
                int x = dx[answer[c]];
                int y = dy[answer[c]];
                int rot = rotate[c];
                int t = pick[c];
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        Kiln kiln = kilnInfo.getInfo(t, rot, i).get(j);
                        kiln2D[x + i].get(y + j).efficacy += kiln.efficacy;
                        if (kiln2D[x + i].get(y + j).efficacy > 9) kiln2D[x + i].get(y + j).setEfficacy(9);
                        else if (kiln2D[x + i].get(y + j).efficacy < 0) kiln2D[x + i].get(y + j).setEfficacy(0);
                        if (kiln.color == Color.WHITE) continue;
                        kiln2D[x + i].get(y + j).setColor(kiln.color);
                    }
                }
            }
            return cal(kiln2D);
        }
        for (int i = 0; i < 4; i++) {
            answer[r] = i;
            ret = Math.max(ret, run(r + 1, answer, rotate, pick));
        }
        return ret;
    }

    static int permutationRotate(int r, int[] answer, int[] pick) {
        int ret = 0;
        if (r == 3) {
            return run(0, new int[3], answer, pick);
        }
        for (int i = 0; i < 4; i++) {
            answer[r] = i;
            ret = Math.max(ret, permutationRotate(r + 1, answer, pick));
        }
        return ret;
    }
    static int permutationPick(int r, boolean[] visited, int[] answer) {
        int ret = 0;
        if (r == 3) {
            return permutationRotate(0, new int[3], answer);
        }
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            answer[r] = i;
            visited[i] = true;
            ret = Math.max(ret, permutationPick(r + 1, visited, answer));
            visited[i] = false;
        }
        return ret;
    }

    public static void main(String[] args) throws Exception {
        init();
        boolean[] visited = new boolean[n];
        int[] answer = new int[3];
        System.out.println(permutationPick(0, visited, answer));
    }
}


class KilnInfo {
    List<Kiln>[][][] kilnList = new ArrayList[10][5][5];

    KilnInfo() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    kilnList[i][j][k] = new ArrayList<>();
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            init(i, 0);
        }
    }

    void init(int t, int turn) {
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 5; k++) {
                kilnList[t][turn][j].add(new Kiln());
            }
        }
    }

    List<Kiln> getInfo(int t, int turn, int x) {
        return kilnList[t][turn][x];
    }


    void rotate90(int t) {
        init(t, 1);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Kiln kiln = kilnList[t][0][4 - 1 - j].get(i);
                kilnList[t][1][i].get(j).setEfficacy(kiln.efficacy);
                kilnList[t][1][i].get(j).setColor(kiln.color);
            }
        }

    }

    void rotate180(int t) {
        init(t, 2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Kiln kiln = kilnList[t][0][4 - 1 - i].get(4 - 1 - j);
                kilnList[t][2][i].get(j).setEfficacy(kiln.efficacy);
                kilnList[t][2][i].get(j).setColor(kiln.color);


            }
        }
    }

    void rotate270(int t) {
        init(t, 3);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Kiln kiln = kilnList[t][0][j].get(4 - 1 - i);
                kilnList[t][3][i].get(j).setEfficacy(kiln.efficacy);
                kilnList[t][3][i].get(j).setColor(kiln.color);


            }
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(kilnList);
    }

    void initColor(int t, int x, int y, Color color) {
        kilnList[t][0][x].get(y).setColor(color);
    }

    void initEfficacy(int t, int x, int y, int efficacy) {
        kilnList[t][0][x].get(y).setEfficacy(efficacy);
    }

}

class Kiln {
    int efficacy = 0;
    Color color = Color.WHITE;

    public void setEfficacy(int efficacy) {
        this.efficacy = efficacy;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public int cal() {
        return efficacy * color.cal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(efficacy).append(" ").append(color);
        return sb.toString();
    }


}

enum Color {
    RED('R', 7),
    BLUE('B', 5),
    GREEN('G', 3),
    YELLOW('Y', 2),
    WHITE('W', 0);
    char color;
    int cal;

    Color(char color, int cal) {
        this.color = color;
        this.cal = cal;
    }

    static final Color[] COLOR_ARR = Color.values();

    static Color of(char color) throws IllegalArgumentException {
        return Arrays.stream(COLOR_ARR)
                .filter((x) -> x.color == color)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

}

class Node implements Comparable<Node> {
    int u;
    int v;
    int w;

    Node(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    void swap() {
        int tmp = u;
        u = v;
        v = tmp;
    }

    boolean isConnected() {
        return u == v;
    }

    @Override
    public int hashCode() {
        return Objects.hash(u, v, w);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node node = (Node) obj;
            return u == node.u && v == node.v && w == node.w;
        }
        return false;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(w, o.w);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(u).append(" ").append(v).append(" ").append(w);
        return sb.toString();
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
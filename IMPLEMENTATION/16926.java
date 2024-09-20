import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

        /*

          +---+
         /   /|
        +---+ |
        |   | +
        |   |/
        +---+

        */

public class Main {
    static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int n, m, r;
    static List<Integer>[] list = new ArrayList[151];
    static Map<Coordinate, List<Integer>> map = new HashMap<>();
    static int[][] graph = new int[302][302];

    static int[] rotateModInfo = new int[151];

    static String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                List<Integer> choice = map.get(new Coordinate(i, j));
                sb.append(list[choice.get(0)].get(choice.get(1))).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    static void rotate() {
        for (int i = 0; i < Math.min(n, m) / 2; i++) {
            int rotateCnt = r % rotateModInfo[i];
            List<Integer> backList = new ArrayList<>(list[i].subList(0, rotateCnt));
            List<Integer> frontList = new ArrayList<>(list[i].subList(rotateCnt, list[i].size()));
            list[i].clear();
            list[i].addAll(frontList);
            list[i].addAll(backList);

        }
    }

    static void rotateCntCal() {
        for (int i = 0; i < Math.min(n, m) / 2; i++) {
            rotateModInfo[i] = 2 * ((n - (2 * i)) + (m - (2 * i))) - 4;
        }
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 151; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void mapAndListInitForGraph() {
        for (int i = 0 ; i < Math.min(n, m) / 2; i++) {
            int x, y;
            x = i;
            y = i;
            int index = 0;
            for (int j = i;  j < m - i; j++) {
                y = j;
                map.put(new Coordinate(x, y), List.of(i, index++));
                list[i].add(graph[x][y]);
            }
            for (int j = x + 1; j < n - i; j++) {
                x = j;
                map.put(new Coordinate(x, y), List.of(i, index++));
                list[i].add(graph[x][y]);
            }
            for (int j = y - 1;  j >= i; j--) {
                y  = j;
                map.put(new Coordinate(x, y), List.of(i, index++));
                list[i].add(graph[x][y]);
            }
            for (int j = x - 1; j >= i + 1; j--) {
                x = j;
                map.put(new Coordinate(x, y), List.of(i, index++));
                list[i].add(graph[x][y]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        mapAndListInitForGraph();
        rotateCntCal();
        rotate();
        System.out.println(print());
    }
}

class Node {
    char root;
    Node left = null;
    Node right = null;

    Node(char root) {
        this.root = root;
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
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
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static int[] parent = new int[252];

    static {
        for (int i = 0; i < 252; i++) parent[i] = i;
    }

    static int[] rank = new int[252];
    static int[][] graph = new int[52][52];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static final char WALL = '1';
    static final char SPACE = '0';
    static final char KEY = 'K';
    static final char ROBOT = 'S';
    static PriorityQueue<Node> tree = new PriorityQueue<>(Node::compareTo);
    static List<Coordinate> list = new ArrayList<>();

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            String inString = bf.readLine();
            for (int j = 0; j < n; j++) {
                char c = inString.charAt(j);
                graph[i][j] = c;
                if (c == ROBOT || c == KEY) list.add(new Coordinate(i, j));
            }
        }
    }
    static boolean isBoundary(int nx, int ny) {
        return 0 <= nx && nx < n && 0 <= ny && ny < n;
    }
    static boolean isWall(int nx, int ny) {
        return graph[nx][ny] == WALL;
    }
    static boolean isKey(int nx, int ny) {
        return graph[nx][ny] == KEY;
    }

    static boolean isRobot(int nx, int ny) {
        return graph[nx][ny] == ROBOT;
    }

    static void bfs(int u) {
        int[][] distance = new int[52][52];
        for (int i = 0; i < 52; i++) Arrays.fill(distance[i], -1);

        distance[list.get(u).first][list.get(u).second] = 0;
        Deque<Coordinate> queue = new LinkedList<>();
        queue.add(list.get(u));
        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            int x = coordinate.first;
            int y = coordinate.second;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isBoundary(nx, ny) && distance[nx][ny] == -1) {
                    if (isWall(nx, ny)) continue;
                    distance[nx][ny] = distance[x][y] + 1;
                    if (isKey(nx, ny) || isRobot(nx, ny)) {
                        int v = list.indexOf(new Coordinate(nx, ny));
                        if (u >= v) continue;
                        tree.add(new Node(u + 1, v + 1, distance[nx][ny]));
                    }
                    queue.add(new Coordinate(nx, ny));
                }
            }
        }
    }

    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    static boolean merge(Node node) {
        node.u = find(node.u);
        node.v = find(node.v);
        if (node.isConnected()) return false;
        if (rank[node.u] > rank[node.v]) node.swap();
        parent[node.u] = node.v;
        if (rank[node.u] == rank[node.v]) rank[node.v]++;
        return true;
    }
    static int solution() {
        int cnt = 0;
        int answer = 0;
        for (int i = 0; i < m + 1; i++) {
            bfs(i);
        }
        while (!tree.isEmpty()) {
            Node node = tree.poll();
            if (!merge(node)) continue;
            answer += node.w;
            cnt++;
            if (cnt == m) return answer;
        }
        return -1;
    }
    public static void main(String[] args) throws Exception {
        init();
        System.out.println(solution());
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
/*
class Node {
    int data;
    Node next = null;

    Node(int data) {
        this.data = data;
    }
}

 */

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
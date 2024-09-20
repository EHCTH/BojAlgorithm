import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static int n, m, islandCnt;
    static int answer = 0;
    static int[] parent;
    static int[] rank;
    static int[][] graph = new int[12][12];
    static int[][] islandConnectionInfo;
    static boolean[][] visited = new boolean[12][12];
    static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void islandNumberingAndInitInfo() {
        int islandMark = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 1 && !visited[i][j]) {
                    islandNumbering(i, j, islandMark);
                    islandMark++;
                }
            }
        }
        islandConnectionInfo = new int[islandMark - 1][islandMark - 1];
        islandCnt = islandMark - 1;
        for (int i = 0; i < islandCnt; i++) {
            Arrays.fill(islandConnectionInfo[i], Integer.MAX_VALUE);
        }
    }
    static boolean isVisited(int nx, int ny) {
        return !visited[nx][ny];
    }
    static boolean isEqualsIslandAndFirstVisited(int nx, int ny) {
        boolean isIsland = graph[nx][ny] == 1;
        boolean isFirstVisited = isVisited(nx, ny);
        return isIsland && isFirstVisited;
    }
    static boolean isWithinBounds(int nx, int ny) {
        return 0 <= nx && nx < n && 0 <= ny && ny < m;
    }
    static void islandNumbering(int x, int y, int islandMark) {
        visited[x][y] = true;
        graph[x][y] = islandMark;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isWithinBounds(nx, ny) && isEqualsIslandAndFirstVisited(nx, ny)) {
                islandNumbering(nx, ny, islandMark);
            }
        }
    }
    static boolean isSea(int otherIsland) {
        return otherIsland == 0;
    }
    static boolean isOtherIsland(int curIsland, int otherIsland) {
        boolean sea = isSea(otherIsland);
        return !sea && curIsland != otherIsland;
    }
    static void buildABridge(int i, int j) {
        Deque<Coordinate> queue = new LinkedList<>();
        for (int dir = 0; dir < 4; dir++) {
            queue.add(new Coordinate(i, j, 0));
            while (!queue.isEmpty()) {
                Coordinate coordinate = queue.poll();
                int x = coordinate.first;
                int y = coordinate.second;
                int distance = coordinate.third;
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (isWithinBounds(nx, ny)) {
                    if (graph[i][j] == graph[nx][ny]) break;
                    if (isOtherIsland(graph[i][j], graph[nx][ny])) {
                        if (distance < 2) break;
                        if (islandConnectionInfo[graph[i][j] - 1][graph[nx][ny] - 1] > distance) {
                            islandConnectionInfo[graph[i][j] - 1][graph[nx][ny] - 1] = distance;
                            islandConnectionInfo[graph[nx][ny] - 1][graph[i][j] - 1] = distance;
                        }
                        break;
                    }
                    else {
                        queue.add(new Coordinate(nx, ny, distance + 1));
                    }

                }
            }
        }

    }
    static void run() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] >= 1) {
                    buildABridge(i, j);
                }
            }
        }

    }
    static List<Node> initAtKruskalDB() {
        return IntStream.range(0, islandCnt).boxed()
                .flatMap((i) -> IntStream.range(0, islandCnt)
                        .boxed()
                        .filter((j) -> i != j)
                        .map((j) -> new Node(i, j, islandConnectionInfo[i][j])))
                .sorted()
                .collect(Collectors.toList());
    }
    static int find(int x) {
        if (x == parent[x]) return x;
        parent[x] = find(parent[x]);
        return parent[x];
    }
    static boolean merge(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return false;
        if (rank[u] > rank[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }
        parent[u] = v;
        if (rank[u] == rank[v]) rank[v]++;
        return true;
    }
    static int kruskal() {
        List<Node> db = initAtKruskalDB();
        parent = new int[islandCnt];
        rank = new int[islandCnt];
        Arrays.fill(rank, 1);
        for (int i = 0; i < islandCnt; i++) parent[i] = i;

        int cnt = 0;
        for (Node node : db) {
            int u = node.u;
            int v = node.v;
            int w = node.w;
            if (w == Integer.MAX_VALUE) continue;
            if (!merge(u, v)) continue;
            answer += w;
            cnt++;
            if (cnt == islandCnt - 1) break;
        }
        Set<Integer> parentSet = Arrays.stream(parent).boxed().map((x) -> find(x))
                        .collect(Collectors.toSet());
        if (parentSet.size() == 1) return answer;
        return -1;

    }
    public static void main(String[] args) throws Exception {
        init();
        islandNumberingAndInitInfo();
        run();
        System.out.println(kruskal());
    }

}

class Node implements Comparable<Node>{
    int u;
    int v;
    int w;
    Node(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
    @Override
    public int compareTo(Node o) {
        return Integer.compare(w, o.w);
    }
    @Override
    public String toString() {
        return u + " " + w;
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
        int firstSort = Integer.compare(first, o.first);
        if (firstSort == 0) {
            return Integer.compare(second, o.second);
        }
        return firstSort;
    }


}
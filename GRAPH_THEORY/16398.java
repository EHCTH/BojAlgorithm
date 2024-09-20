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
    static int n;
    static List<Node> tree = new ArrayList<>();
    static int[] parent = new int[1002];
    static int[] rank = new int[1002];
    static void init() throws Exception{
        n = Integer.parseInt(bf.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int t = 0, j = 0; j < n; j++) {
                int w = Integer.parseInt(st.nextToken());
                if (t++ <= i) continue;
                Node node = new Node(i, j, w);
                tree.add(node);
            }
        }
    }

    static void setParent() {
        for (int i = 0; i <= n; i++) parent[i] = i;
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
    static long solution() {
        long answer = 0;
        int cnt = 0;
        for (Node node : tree) {
            if (!merge(node)) continue;
            answer += node.w;
            cnt++;
            if (cnt == n - 1) break;
        }
        return answer;
    }
    public static void main(String[] args) throws Exception {
        init();
        setParent();
        Collections.sort(tree);
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
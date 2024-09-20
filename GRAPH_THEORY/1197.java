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
    static List<Node> tree = new ArrayList<>();
    static int V, E;
    static int[] parent = new int[10002];
    static int[] rank = new int[10002];
    static void init() throws Exception{
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i < E; i++) {
            int o, v, w;
            st = new StringTokenizer(bf.readLine());
            o = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            Node node = new Node(o, v, w);
            tree.add(node);
        }
    }
    static void setParent() {
        for (int i = 0; i <= V; i++) parent[i] = i;
    }
    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    static boolean merge(Node node) {
        node.o = find(node.o);
        node.v = find(node.v);
        if (node.o == node.v) return false;
        if (rank[node.o] > rank[node.v]) node.swap();
        parent[node.o] = node.v;
        if (rank[node.o] == rank[node.v]) rank[node.v]++;
        return true;


    }
    static int solution() {
        int answer = 0;
        int cnt = 0;
        for (Node node : tree) {
            if (!merge(node)) continue;
            answer += node.w;
            cnt++;
            if (cnt == V - 1) break;
        }
        return answer;
    }
    public static void main(String[] args) throws Exception{
        init();
        setParent();
        Collections.sort(tree);
        System.out.println(solution());
    }
}

class Node implements Comparable<Node> {
    int o, v, w;
    Node(int o, int v, int w) {
        this.o = o;
        this.v = v;
        this.w = w;
    }
    void swap() {
        int tmp = o;
        o = v;
        v = tmp;
    }
    @Override
    public int hashCode() {
        return Objects.hash(o, v, w);
    }
    @Override
    public int compareTo(Node o) {
        return Integer.compare(w, o.w);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node node = (Node) obj;
            return o == node.o && v == node.v &&  w == node.w;
        }
        return false;
    }

}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static List<String> info = new ArrayList<>();
    static SortedMap<String, Integer> treeMap = new TreeMap<>(Comparator.comparing(String::length)
            .thenComparing(Comparator.naturalOrder()));

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            String inString = bf.readLine();
            info.add(inString);
        }
    }

    // 비기는 조건도 있어가지고 생각해야함
    public static void main(String[] args) throws Exception {
        init();
        for (String s : info) {
            for (int j = 0; j < M; j++) {
                String subSet = s.substring(0, j + 1);
                if (treeMap.get(subSet) == null) {
                    treeMap.put(subSet, 1);
                } else {
                    int v = treeMap.get(subSet);
                    treeMap.put(subSet, v + 1);
                }
            }
        }
        Iterator<Map.Entry<String, Integer>> it = treeMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            String key = entry.getKey();
            int value = entry.getValue();
            if (value <= K) {
//                if (draw(key)) {
                System.out.println(key.length());
                System.out.println(change(key));
                System.exit(0);
//                }
            }
        }
        System.out.println(-1);
    }

    // TODO 비긴경우에도 진걸로 간주해야함
    static boolean draw(String key) {
        int size = key.length() - 1;
        for (String otherKey : treeMap.keySet()) {
            if (key.equals(otherKey) || key.length() != otherKey.length()) continue;
            if (key.substring(0, size).equals(otherKey.substring(0, size))) {
                char keyLastChar = key.charAt(size);
                char otherKeyLastChar = otherKey.charAt(size);
                if (keyLastChar == 'S' &&
                        (otherKeyLastChar == 'S' || otherKeyLastChar == 'P')) return false;
                else if (keyLastChar == 'R' &&
                        (otherKeyLastChar == 'R' || otherKeyLastChar == 'S')) return false;
                else if (keyLastChar == 'P' &&
                        (otherKeyLastChar == 'R' || otherKeyLastChar == 'P')) return false;
            }
        }
        return true;
    }

    static String change(String key) {
        StringBuilder sb = new StringBuilder();
        for (char c : key.toCharArray()) {
            if (c == 'R') sb.append("S");
            else if (c == 'S') sb.append("P");
            else sb.append("R");
        }
        return sb.toString();
    }
}

/*
4 4 1
SPRS
SPRS
SPRS
SPRR

answer = -1

4 3 1
SRP
SPS

PSP
PPP

4 4 3
SSPS
RRSP
SPRP
SPRR

answer = 1, P

4 3 1
RSP
RSR
SPP
SPP
 */
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

    boolean isConnect() {
        return u == v;
    }

    @Override
    public int hashCode() {
        return Objects.hash(u, v, w);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(u).append(" ").append(v).append(" ").append(w);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Node) {
            Node node = (Node) obj;
            return u == node.u && v == node.v && w == node.w;
        }
        return false;
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(w, node.w);
    }
}

class Coordinate implements Comparable<Coordinate> {
    int x;
    int y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) obj;
            return x == coordinate.x && y == coordinate.y;
        }
        return false;
    }

    @Override
    public int compareTo(Coordinate coordinate) {
        return Integer.compare(x, coordinate.x);
    }

}
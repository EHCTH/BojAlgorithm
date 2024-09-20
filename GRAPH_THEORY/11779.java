import java.util.*;


public class Main {
    static int V, E, s, e;
    static final int START = 0;
    static int[] distance = new int[1002];
    static int[] pre = new int[1002];
    static List<List<Coordinate>> list = new ArrayList<>();

    static void init() {
        Arrays.fill(distance, Integer.MAX_VALUE);
        for (int i = 0; i < V; i++) {
            list.add(new ArrayList<>());
        }
    }

    static void print() {
        List<Integer> path = new ArrayList<>();
        int cur = e;
        System.out.println(distance[e]);
        while (cur != s) {
            path.add(cur);
            cur = pre[cur];
        }
        path.add(cur);
        Collections.reverse(path);
        System.out.println(path.size());
        path.stream().map((x) -> x + 1).forEach((x) -> System.out.print(x + " "));
    }

    static void dijkstra() {
        PriorityQueue<Coordinate> queue = new PriorityQueue<>(Coordinate::compareTo);
        queue.offer(new Coordinate(s, 0));
        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            int v = coordinate.first;
            int w = coordinate.second;
            if (distance[v] != w) continue;
            for (Coordinate otherCoordinate : list.get(v)) {
                int oV = otherCoordinate.first;
                int oW = otherCoordinate.second;
                if (distance[oV] > w + oW) {
                    distance[oV] = w + oW;
                    queue.offer(new Coordinate(oV, distance[oV]));
                    pre[oV] = v;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        init();
        for (int i = 0; i < E; i++) {
            int u, v, w;
            u = sc.nextInt() - 1;
            v = sc.nextInt() - 1;
            w = sc.nextInt();
            list.get(u).add(new Coordinate(v, w));
        }
        s = sc.nextInt() - 1;
        e = sc.nextInt() - 1;

        distance[s] = START;
        dijkstra();
        print();


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
        return second - o.second;
    }
}
/*
6 8
1
1 3 2
1 2 3
1 4 5
2 3 2
2 5 8
3 4 2
4 5 6
5 6 1
 */
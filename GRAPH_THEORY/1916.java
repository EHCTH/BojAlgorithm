import java.util.*;


public class Main {
    static final int START = 0;
    static int V, E, s, e;
    static List<List<Coordinate>> list = new ArrayList<>();
    static int[] distance = new int[1002];
    static void init() {
        Arrays.fill(distance, Integer.MAX_VALUE);
        for (int i = 0; i < V; i++) {
            list.add(new ArrayList<>());
        }
    }
    static void dijkstra() {
        PriorityQueue<Coordinate> queue = new PriorityQueue<>(Coordinate::compareTo);
        queue.add(new Coordinate(s, START));
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
                    queue.add(new Coordinate(oV, distance[oV]));
                }
            }
        }
    }

    static void print() {
        System.out.println(distance[e]);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        init();
        for (int i = 0; i < E; i++) {
            int v, e, w;
            v = sc.nextInt() - 1;
            e = sc.nextInt() - 1;
            w = sc.nextInt();
            list.get(v).add(new Coordinate(e, w));
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
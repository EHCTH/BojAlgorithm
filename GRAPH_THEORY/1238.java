import java.util.*;
import java.util.stream.Collectors;


public class Main {
    static final int START = 0;
    static int n, m, x;
    static Scanner sc = new Scanner(System.in);


    static void init(List<List<Coordinate>> list, List<List<Coordinate>> list2) {
        n = sc.nextInt();
        m = sc.nextInt();
        x =  sc.nextInt() - 1;
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
            list2.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int v, e, w;
            v = sc.nextInt() - 1;
            e = sc.nextInt() - 1;
            w = sc.nextInt();
            list.get(v).add(new Coordinate(e, w));
            list2.get(e).add(new Coordinate(v, w));
        }
    }

    static int[] dijkstra(List<List<Coordinate>> list) {
        int[] dist = new int[1002];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[x] = START;
        PriorityQueue<Coordinate> queue = new PriorityQueue<>(Coordinate::compareTo);
        queue.add(new Coordinate(x, 0));
        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            int v = coordinate.first;
            int w = coordinate.second;
            if (dist[v] != w) continue;
            for (Coordinate otherCoordinate : list.get(v)) {
                int oV = otherCoordinate.first;
                int oW = otherCoordinate.second;
                if (dist[oV] > w + oW) {
                    dist[oV] = w + oW;
                    queue.add(new Coordinate(oV, dist[oV]));
                }
            }
        }
        return dist;
    }

    static void print(int[] dist, int[] dist2) {
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, (dist[i] + dist2[i]));
        }
        System.out.println(answer);
    }
    public static void main(String[] args) {
        List<List<Coordinate>> list = new ArrayList<>();
        List<List<Coordinate>> list2= new ArrayList<>();
        init(list, list2);
        int[] dist = dijkstra(list);
        int[] dist2 = dijkstra(list2);

        print(dist, dist2);


    }
}

// TODO 낚시왕 코드

//    static int r, c, m;
//    static Scanner sc = new Scanner(System.in);
//    static int[] dx = {-1, 1, 0, 0};
//    static int[] dy = {0, 0, 1, -1};
//    static Map<Coordinate, Shark> sharkMap = new HashMap<>();
//
//    // ex
//    static Shark[][] sharks = new Shark[100][100]; // ex
//    static int answer = 0;
//    static void print() {
//        sharkMap.entrySet().stream().forEach(System.out::println);
//    }
//
//    static void init() {
//        r = sc.nextInt();
//        c = sc.nextInt();
//        m = sc.nextInt();
//        /*
//        어의 정보는 다섯 정수 r, c, s, d, z (1 ≤ r ≤ R, 1 ≤ c ≤ C, 0 ≤ s ≤ 1000, 1 ≤ d ≤ 4, 1 ≤ z ≤ 10000) 로 이루어져 있다.
//        (r, c)는 상어의 위치, s는 속력, d는 이동 방향, z는 크기이다.
//        d가 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽을 의미한다.
//         */
//
//
//        for (int i = 0; i < m; i++) {
//            int sR, sC, s, d, z;
//            sR = sc.nextInt() - 1;
//            sC = sc.nextInt() - 1;
//            s = sc.nextInt();
//            d = sc.nextInt() - 1;
//            z = sc.nextInt();
//            sharkMap.put(new Coordinate(sR, sC), new Shark(sR, sC, s, d, z));
//            sharks[sR][sC] = new Shark(sR, sC, s, d, z);
//        }
//    }
//
//    static int setSharkX(Shark shark) {
//        int sharkNx = shark.x + (shark.speed * dx[shark.direction]);
////         구하는 공식은 = shark,x + (shark.speed % shark.x) * dx[shark.direction]
//        if (sharkNx < 0) {}
//        else if (sharkNx >= r)
//        return sharkNx;
//    }
//    static int setSharkY(Shark shark) {
//        int sharkNy = shark.y + (shark.speed * dy[shark.direction]);
//    //     구하는 공식 = shark.y + ((shark.speed % shark.y) * dy[shark.direction]);
//        if (sharkNy < 0) return shark.y + ((shark.speed % shark.y) * dy[shark.direction]);
//        else if (sharkNy >= c) return shark.y - ((shark.speed % shark.y)* dy[shark.direction]);
//        return sharkNy;
//
//    }
//    static void sharkMove() {
//        // 범위 넘었을 경우
//        // 이동범위 = 범위 % m;
//        // 범위가 n, m 보다 작을 경우 +
//        // 범위가 m, m 보다 클 경우 -
//
//        List<Shark> ret = new ArrayList<>();
//        Iterator<Map.Entry<Coordinate, Shark>> sharkIterator = sharkMap.entrySet().iterator();
//        while (sharkIterator.hasNext()) {
//            Shark shark = sharkIterator.next().getValue();
////            int sharkX = setSharkX(shark);
////            int sharkY = setSharkY(shark);
//            // shark dir 도 바뀜에 유의하자
//        }
//
//
//    }
//    static void catchNearAnyShark(int x, int y) {
//        for (int i = 0; i < r; i++) {
//            if (sharkMap.get(new Coordinate(x + i, y)) != null) {
//                answer += sharkMap.get(new Coordinate(x + i, y)).size;
//                sharkMap.remove(new Coordinate(x + i, y));
//                break;
//            }
//        }
//    }
//    static void fisherManMove(int x, int y) {
//        catchNearAnyShark(x, y);
//
//
//    }
//    static void solutions() {
//        int x = 0;
//        int y = 0;
//        while (y < m) {
//            fisherManMove(x, y);
//            sharkMove();
//            y++;
//        }
//    }
//    public static void main(String[] args) {
//        init();
////        print();
////        System.out.println();
////        solutions();
////        print();
//
////        List<Shark> sharks1 = sharkMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
////        sharks1.add(new Shark(1, 1, 3, 5, 3));
////        System.out.println(sharks1.stream().collect(Collectors.groupingBy(Shark::getCoordinate)));
//
//    }
//}

class Node {
    char root;
    Node left = null;
    Node right = null;

    Node(char root) {
        this.root = root;
    }
}
class Shark {
    int x;
    int y;
    int speed;
    int direction;
    int size;
    Coordinate coordinate;
    Shark(int x, int y, int speed, int direction, int size) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.size = size;
        this.coordinate = setCoordinate();
    }
    public String toString() {
        return "[(" + x + ", " + y + "), "  + speed + " " + direction + " " + size + "]";
    }
    public Coordinate setCoordinate() {
        return new Coordinate(x, y);
    }
    public Coordinate getCoordinate() {
        return coordinate;
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
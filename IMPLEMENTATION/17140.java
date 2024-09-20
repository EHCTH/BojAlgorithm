import java.util.*;

public class Main {
    static int r, c, k;
    static int n = 3, m = 3;
    static int[][] graph = new int[200][200];

    static void listSort(List<Coordinate> list) {
        list.sort(Comparator.comparing(Coordinate::getSecond)
                .thenComparing(Coordinate::getFirst));
    }
    static void rCal(){
        for (int i = 0; i < n; i++) {
            List<Coordinate> list = new ArrayList<>();
            int[] cnt = new int[200];
            for (int j = 0; j < m; j++) {
                cnt[graph[i][j]]++;
            }
            for (int j = 1; j < 101; j++) {
                if (cnt[j] > 0) list.add(new Coordinate(j, cnt[j]));
            }
            listSort(list);
            int idx = 0;
            for (Coordinate coordinate : list) {
                graph[i][idx++] = coordinate.first;
                graph[i][idx++] = coordinate.second;
            }
            m = Math.max(m, idx);
            for (; idx < 101; idx++) {
                graph[i][idx] = 0;
            }
        }
    }
    static void cCal(){
        for (int j = 0; j < m; j++) {
            List<Coordinate> list = new ArrayList<>();
            int[] cnt = new int[200];
            for (int i = 0; i < n; i++) {
                cnt[graph[i][j]]++;
            }
            int idx = 0;
            for (int i = 1; i < 101; i++) {
                if (cnt[i] > 0) list.add(new Coordinate(i, cnt[i]));
            }
            listSort(list);
            for (Coordinate coordinate : list) {
                graph[idx++][j] = coordinate.first;
                graph[idx++][j] = coordinate.second;
            }
            n = Math.max(n, idx);
            for (; idx < 101; idx++) {
                graph[idx][j] = 0;
            }
        }
    }
    static int solution() {
        for (int t = 0; t < 101; t++) {
            if (graph[r][c] == k) return t;
            if (n >= m) rCal();
            else cCal();
        }

        return -1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt() - 1;
        c = sc.nextInt() - 1;
        k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        System.out.println(solution());


    }
}


class Coordinate {
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
        return this.first + " " + this.second;
    }

    @Override
    public boolean equals(Object obj) {
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

}
import java.util.*;

public class Main {
    static int n;
    static int[][] area = new int[2][12];
    static int[][] matrix = new int[12][12];
    static int answer = Integer.MAX_VALUE;


    public static void combination(int N, int m, int next, int[][] arrA) {
        if (N == m) {
            run(arrA, m);
            return;
        }

        for (int i = next; i <= n; i++) {
            arrA[0][N] = i; 
            arrA[1][i] = 1; // arr[1][i] == list.contains(i) 중요 포인트
            combination(N + 1, m, i + 1, arrA);
            arrA[1][i] = 0;
        }
    }

    public static void run(int[][] arrA, int sizeA) {
        if (!isConnect(area[0][arrA[0][0]], arrA, sizeA)) return;
        int sizeB = 0;
        int[][] arrB = new int[2][12];
        for (int i = 1; i <= n; i++) {
            if (arrA[1][i] == 1) {
                continue;
            }
            arrB[0][sizeB++] = i;
            arrB[1][i] = 1;
        }

        if (!isConnect(area[0][arrB[0][0]], arrB, sizeB)) return;
        int resultA = 0;
        for (int i = 0; i < sizeA; i++) resultA += area[1][arrA[0][i]];
        int resultB = 0;
        for (int i = 0; i < sizeB; i++) resultB += area[1][arrB[0][i]];
        int result = Math.abs(resultA - resultB);
        answer = Math.min(answer, result);

    }

    public static boolean isConnect(int num, int[][] arr, int size) {
        boolean[] visited = new boolean[n + 1];
        visited[num] = true;
        Deque<Integer> queue = new LinkedList<>();
        queue.add(num);

        int cnt = 1;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int i : matrix[v]) {
                if (i == 0) continue;
                if (!visited[i] && arr[1][i] == 1) {
                    visited[i] = true;
                    cnt++;
                    queue.add(i);
                }
            }
        }

        if (cnt == size) return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            int num = sc.nextInt();
            area[0][i] = i;
            area[1][i] = num;
        }
        for (int i = 1; i <= n; i++) {
            int m = sc.nextInt();
            for (int j = 0; j < m; j++) {
                int num = sc.nextInt();
                matrix[i][num] = num;
            }
        }
        int[][] arrA = new int[2][12];
        for (int i = 1; i <= n / 2; i++) combination(0, i, 1, arrA);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
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
}
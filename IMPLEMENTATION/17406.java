import java.util.*;

public class Main {
    static int n, m, k;
    static int[][] graph = new int[52][52];
    static int minValue = Integer.MAX_VALUE;
    static boolean[] visited = new boolean[8];
    static void printGraph(int[][] graph) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int minCntCalculator(int[][] graph) {
        int cnt = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) cnt = Math.min(cnt, Arrays.stream(graph[i]).sum());
        return cnt;
    }

    static int[][] rotate(int[][] graph, int sX, int sY, int eX, int eY) {
        for (int i = 0; i < Math.min((eX - sX), (eY - sY)) / 2; i++) {
            int x = sX + i;
            int y = sY + i;
            int tmp = graph[x][y];
            int nextTmp;
            for (int j = y + 1; j < eY - i; j++) {
                y = j;
                nextTmp = graph[x][y];
                graph[x][y] = tmp;
                tmp = nextTmp;
            }
            for (int j = x + 1; j < eX - i; j++) {
                x = j;
                nextTmp = graph[x][y];
                graph[x][y] = tmp;
                tmp = nextTmp;
            }
            for (int j = y - 1; j > sY - 1 + i; j--) {
                y = j;
                nextTmp = graph[x][y];
                graph[x][y] = tmp;
                tmp = nextTmp;
            }

            for (int j = x - 1; j > sX - 1 + i; j--) {
                x = j;
                nextTmp = graph[x][y];
                graph[x][y] = tmp;
                tmp = nextTmp;
            }
        }
        return graph;
    }
    static int solution(RotatePoint[] arr, int[][] graph) {
        for (RotatePoint rotatePoint : arr) {
            int r = rotatePoint.r;
            int c = rotatePoint.c;
            int s = rotatePoint.s;
            int startX, startY, endX, endY;
            startX = r - s; startY = c - s;
            endX = r + s + 1; endY = c + s + 1;
            graph = rotate(graph, startX, startY, endX, endY);
        }
        return minCntCalculator(graph);

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        List<RotatePoint> rotateList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            int s = sc.nextInt();
            rotateList.add(new RotatePoint(r, c, s));
        }
        RotatePoint[] rotatePoints = new RotatePoint[k];
        permutation(0, rotateList, rotatePoints);
        System.out.println(minValue);
    }


    static int[][] copyGraph(int[][] graph) {
        int[][] tmpGraph = new int[52][52];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmpGraph[i][j] = graph[i][j];
            }
        }
        return tmpGraph;
    }
    static void permutation(int n, List<RotatePoint> list,  RotatePoint[] arr) {
        if (n == k) {
            minValue = Math.min(minValue, solution(arr, copyGraph(graph)));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[n] = list.get(i);
                permutation(n + 1, list, arr);
                visited[i] = false;
            }
        }
    }
}
class RotatePoint {
    int r;
    int c;
    int s;
    RotatePoint(int r, int c, int s) {
        this.r = r;
        this.c = c;
        this.s = s;
    }

    public String toString() {
        return r + " " + c + " " + s;
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
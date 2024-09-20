import java.util.*;

public class Main {
    static int[][] graph = new int[1024][1024];
    static int n, r;
    static int N;
    static List<Coordinate> list = new ArrayList<>();

    static void reverseRightAndLeft(int i, int j, int fraction) {
        int x = i;
        while (i < x + fraction) {
            swapRightAndLeft(i, j, fraction);
            i++;
        }
    }

    static void reverseUpAndDown(int i, int j, int fraction) {
        int y = j;
        while (j < y + fraction) {
            swapUpAndDown(i, j, fraction);
            j++;
        }
    }

    static void swapUpAndDown(int n, int j, int fraction) {
        int i = 0;
        int idx = 0;
        while (i < (fraction - idx)) {
            int tmp = graph[i + n][j];
            graph[i + n][j] = graph[n + fraction - 1 - i][j];
            graph[n + fraction - 1 - i][j] = tmp;
            i++;
            idx++;
        }
    }

    static void swapRightAndLeft(int i, int n, int fraction) {
        int j = 0;
        int idx = 0;
        while (j < (fraction - idx)) {
            int tmp = graph[i][j + n];
            graph[i][j + n] = graph[i][n + fraction - 1 - j];
            graph[i][n + fraction - 1 - j] = tmp;
            j++;
            idx++;
        }
    }

    static void firstRotate(int fraction) {
        for (int i = 0; i < N; i += fraction) {
            for (int j = 0; j < N; j += fraction) {
                reverseUpAndDown(i, j, fraction);
            }
        }
    }

    static void secondRotate(int fraction) {
        for (int i = 0; i < N; i += fraction) {
            for (int j = 0; j < N; j += fraction) {
                reverseRightAndLeft(i, j, fraction);
            }
        }
    }

    static void thirdRotate(int fraction) {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i += fraction) {
            int cnt = 0;
            for (int r = 0; r < N / fraction; r++) {
                cnt += fraction;
                int x = i;
                for (int j = r * fraction; j < cnt; j++) {
                    int y = r * fraction;
                    for (int k = r * fraction; k < cnt; k++) {
                        tmp[x][y] = graph[cnt - 1 - k + i][j];
                        y++;
                    }
                    x++;
                }
            }
        }
        graph = tmp;
    }

    static void fourthRotate(int fraction) {
        // ClockWise 를 구현했으면 그 코드를 3번 반복해주면 반시계 방향이된다
        int r = 3;
        while (r-- > 0) {
            thirdRotate(fraction);
        }
        /*
            int[][] tmp = new int[N][N];
            for (int i = 0; i < N; i += fraction) {
                int cnt = 0;
                for (int r = 0; r < N / fraction; r++) {
                    cnt += fraction;
                    int x = r * fraction;
                    for (int j = r * fraction; j < cnt; j++) {
                        int y = i;
                        for (int k = r * fraction; k < cnt; k++) {
                            tmp[x][y] = graph[k][cnt - 1 - j + i];
                            y++;
                        }
                        x++;
                    }
                }
            }
            graph = tmp;
         */
    }

    static void fiveRotate(int l) {
        firstRotate(N);
        firstRotate(l);
    }
    static void sixRotate(int l) {
        secondRotate(N);
        secondRotate(l);
    }

    static void sevenRotate(int l) {
        thirdRotate(N);
        fourthRotate(l);
    }

    static void eightRotate(int l) {
        fourthRotate(N);
        thirdRotate(l);
    }

    static void solution(int k, int l) {
        if (k == 1) firstRotate(l);
        else if (k == 2) secondRotate(l);
        else if (k == 3) thirdRotate(l);
        else if (k == 4) fourthRotate(l);
        else if (k == 5) fiveRotate(l);
        else if (k == 6) sixRotate(l);
        else if (k == 7) sevenRotate(l);
        else eightRotate(l);
    }

    static void printGraph(int[][] graph) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        r = sc.nextInt();
        N = 1 << n;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < r; i++) {
            int k = sc.nextInt();
            int l = sc.nextInt();
            list.add(new Coordinate(k, (1 << l)));
        }
        for (Coordinate coordinate : list) {
            solution(coordinate.first, coordinate.second);
        }
        printGraph(graph);
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
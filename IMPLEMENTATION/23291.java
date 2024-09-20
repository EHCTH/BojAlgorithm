import java.util.*;
public class Main {
    static int n, k;
    static int[][] graph = new int[102][102];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int maxValue;
    static int minValue;
    static int col;
    static int cnt;

    static void fishControl() {
        int[][] newGraph = new int[102][102];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (graph[x][y] == 0)
                    continue;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if ((nx < 0 || ny < 0 || nx > n || ny > n) || graph[nx][ny] == 0)
                        continue;
                    int div = graph[x][y] - graph[nx][ny];
                    div /= 5;
                    if (div > 0) {
                        newGraph[x][y] -= div;
                        newGraph[nx][ny] += div;
                    }
                }
            }
        }

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                graph[x][y] += newGraph[x][y];
            }
        }
    }

    static void innerRotate(int row, int innerCol) {
        int[][] tmp = new int[row][innerCol];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < innerCol; j++) {
                tmp[row - 1 - i][j] = graph[n - 1 - i][j];
            }
        }
        int[][] rotate = new int[innerCol][row];
        for (int i = 0; i < innerCol; i++) {
            for (int j = 0; j < row; j++) {
                rotate[i][j] = tmp[row - 1 - j][i];
            }
        }

        for (int i = 0; i < innerCol; i++) {
            for (int j = 0; j < row; j++) {
                graph[n - 1 - innerCol + i][j] = rotate[i][j];
            }
        }
    }

    static int rotateAndConcat(int row, int innerCol) {
        innerRotate(row, innerCol);
        concatTmp(innerCol);
        return cnt % 2 == 0 && cnt != 0 ? row + 1 : row;
    }

    static void blockAddAndConcat() {
        int row = 2;
        col = 0;
        cnt = 0;
        int innerCol = row - 1;
        while (row <= (n - col - innerCol)) {
            innerCol = cnt % 2 == 0 && cnt != 0 ? row : row - 1;
            row = rotateAndConcat(row, innerCol);
            cnt++;
        }
    }

    static void flatten() {
        int[][] newGraph = new int[102][102];
        int idx = 0;
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i > -1; i--) {
                if (graph[i][j] == 0) continue;
                newGraph[n - 1][idx++] = graph[i][j];
            }
        }
        graph = newGraph;
    }

    static void halfRotate(int halfSize, int row) {
        int[][] newList = new int[row][halfSize];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < halfSize; j++) {
                newList[i][j] = graph[n - 1 - i][j];
            }
        }
        int[][] tmpGraph = new int[row][halfSize];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < halfSize; j++) {
                tmpGraph[i][j] = newList[row - 1 - i][halfSize - 1 - j];
            }
        }
        int[][] newArr = new int[102][102];
        for (int i = 0; i < row; i++) {
            int idx = 0;
            for (int j = halfSize; j < n; j++) {
                newArr[n - 1 - i][idx++] = graph[n - 1 - i][j];
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < halfSize; j++) {
                newArr[n - row - i - 1][j] = newList[row - 1 - i][halfSize - 1 - j];
            }
        }
        graph = newArr;

    }

    static void half() {
        int halfSize = n;
        for (int i = 0; i < 2; i++) {
            halfSize /= 2;
            halfRotate(halfSize, i + 1);
        }
    }

    static boolean isNotExtend() {
        maxValue = Integer.MIN_VALUE;
        minValue = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            if (graph[n - 1][j] != 0) {
                maxValue = Math.max(maxValue, graph[n - 1][j]);
                minValue = Math.min(minValue, graph[n - 1][j]);
            }
        }
        if (maxValue - minValue <= k) return true;
        return false;
    }


    static void addFish() {
        for (int j = 0; j < n; j++) {
            if (graph[n - 1][j] == minValue) {
                graph[n - 1][j]++;
            }
        }
    }

    static int run() {
        int result = 0;
        while (!isNotExtend()) {
            result++;
            addFish();
            blockAddAndConcat();
            fishControl();
            flatten();
            half();
            fishControl();
            flatten();
        }
        return result;

    }

    static void concatTmp(int st) {
        int[] tmp = new int[n];
        int idx = 0;
        for (int i = st; i < n; i++) {
            tmp[idx++] = graph[n - 1][i];
        }
        graph[n - 1] = tmp;
        col += st;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int fish = sc.nextInt();
            graph[n - 1][i] = fish;
        }
        System.out.println(run());

    }
}
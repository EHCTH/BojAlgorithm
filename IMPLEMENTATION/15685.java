import java.util.*;

public class Main {
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static int n, x, y, d, g;
    static int answer = 0;
    static int[][] graph = new int[101][101];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            y = sc.nextInt();
            x = sc.nextInt();
            d = sc.nextInt();
            g = sc.nextInt();
            List<Integer> list = new ArrayList<>();
            list.add(d);
            graph[x][y] = 1;
            for (int j = 0; j < g; j++) {
                int listSize = list.size();
                for (int k = 0; k < listSize; k++) {
                    list.add((list.get(listSize - 1 - k) + 1) % 4);
                }
            }
            for (int j : list) {
                x += dx[j];
                y += dy[j];
                graph[x][y] = 1;
            }
        }



        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (graph[i][j] == 1 && graph[i][j + 1] == 1 && graph[i + 1][j + 1] == 1 && graph[i + 1][j] == 1) {
                    answer += 1;
                }
            }
        }
        System.out.println(answer);
    }
}
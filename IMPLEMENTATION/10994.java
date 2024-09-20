import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(bf.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    static long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    static int N, maxNum;
    static char[][] graph = new char[400][400];

    static void recursion(int n, int x, int y) {
        int size = (4 * n) - 3;
        for (int i = 0; i < size; i++) {
            graph[x][y + i] = '*';
            graph[x + i][y] = '*';
            graph[maxNum - 1 - x][y + i] = '*';
            graph[x + i][maxNum - 1 - y] = '*';
        }
        if (n > 1) recursion(n - 1, x + 2, y + 2);

    }

    static void print() {
        for (int i = 0; i < maxNum; i++) {
            for (int j = 0; j < maxNum; j++) {
                System.out.print(graph[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        N = nextInt();
        maxNum = (4 * N) - 3;
        for (int i = 0; i < 400; i++) Arrays.fill(graph[i], ' ');
        recursion(N, 0, 0);
        print();
    }
}
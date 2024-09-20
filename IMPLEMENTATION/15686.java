import java.util.*;


public class Main {
    static int n, m;
    static int[] visited;
    static int chickenCnt = 0;
    static int houseCnt = 0;
    static int[][] house = new int[101][2];
    static int[][] chickenShop = new int[15][2];
    static int[][] chickenM = new int[2000][15];
    static int[] arr;
    static int cnt = 0;

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    static boolean nextPermutation(int[] arr) {
        int N = arr.length - 1;
        int i = N - 1;
        while (i >= 0 && arr[i + 1] <= arr[i]) i--;
        if (i < 0) {
            reverse(arr, 0, N);
            return false;
        }
        int j = N;
        while (arr[j] <= arr[i]) j--;
        swap(arr, i, j);
        reverse(arr, i + 1, N);
        return true;
    }

    static boolean prevPermutation(int[] arr) {
        int N = arr.length - 1;
        int i = N - 1;
        while (i >= 0 && arr[i] <= arr[i + 1]) i--;
        if (i < 0) {
            reverse(arr, 0, N);
            return false;
        }
        int j = N;
        while (arr[i] <= arr[j]) j--;
        swap(arr, i, j);
        reverse(arr, i + 1, N);
        return true;
    }

    static void init() {
        visited = new int[chickenCnt];
        arr = new int[chickenCnt];

        for (int i = 0; i < m; i++) visited[i] = 1;
        for (int i = 1; i < chickenCnt; i++) arr[i] = i;

        do {
            int innerCnt = 0;
            for (int vi = 0; vi < chickenCnt; vi++) {
                if (visited[vi] == 0) continue;
                chickenM[cnt][innerCnt++] = arr[vi];
            }
            cnt++;
        } while (prevPermutation(visited));
    }

    static int solutions() {
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < cnt; i++) {
            int sum = 0;
            for (int j = 0; j < houseCnt; j++) {
                int dist = Integer.MAX_VALUE;
                for (int k = 0; k < m; k++) {
                    dist = Math.min(dist, Math.abs(house[j][0] - chickenShop[chickenM[i][k]][0]) +
                            Math.abs(house[j][1] - chickenShop[chickenM[i][k]][1]));
                }
                sum += dist;
            }
            ret = Math.min(ret, sum);
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int input = sc.nextInt();
                if (input == 0) continue;
                if (input == 1) {
                    house[houseCnt][0] = i;
                    house[houseCnt++][1] = j;
                } else {
                    chickenShop[chickenCnt][0] = i;
                    chickenShop[chickenCnt++][1] = j;
                }
            }
        }
        init();
        System.out.println(solutions());
    }
}
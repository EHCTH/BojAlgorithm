import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r, num;
    static int[][] map;
    static int[][] tmpMap;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        st = new StringTokenizer(br.readLine());
        // 케이스 별로 연산의 수만큼 반복 계산
        for (int i = 0; i < r; i++) {
            //케이스 번호
            num = Integer.parseInt(st.nextToken());

            switch (num) {
                case 1:
                    one();
                    break;
                case 2:
                    two();
                    break;
                case 3:
                    three();
                    break;
                case 4:
                    four();
                    break;
                case 5:
                    five();
                    break;
                case 6:
                    six();
                    break;

            }

        }
        //결과 배열 출력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void one() {
        //1번 연산 : 배열 상하 반전
        tmpMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmpMap[i][j] = map[n - 1 - i][j];
            }
        }
        map = tmpMap;
    }

    public static void two() {
        //2번 연산 : 좌우 반전
        tmpMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmpMap[i][j] = map[i][m - 1 - j];
            }
        }
        map = tmpMap;
    }

    public static void three() {
        tmpMap = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tmpMap[i][j] = map[n - 1 - j][i];
            }
        }
        int tmp = n;
        n = m;
        m = tmp;

        map = tmpMap;

    }

    public static void four() {
        tmpMap = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tmpMap[i][j] = map[j][m - 1 - i];
            }
        }
        int tmp = n;
        n = m;
        m = tmp;

        map = tmpMap;


    }

    public static void five() {
        tmpMap = new int[n][m];
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                tmpMap[i][j] = map[i + n / 2][j];
                tmpMap[i][j + m / 2] = map[i][j];
                tmpMap[i + n / 2][j + m / 2] = map[i][j + m / 2];
                tmpMap[i + n / 2][j] = map[i + n / 2][j + m / 2];
            }
        }
        map = tmpMap;
    }

    public static void six() {
        tmpMap = new int[n][m];
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                tmpMap[i + n / 2][j] = map[i][j];
                tmpMap[i + n / 2][j + m / 2] = map[i + n / 2][j];
                tmpMap[i][j + m / 2] = map[i + n / 2][j + m / 2];
                tmpMap[i][j] = map[i][j + m / 2];
            }
        }
        map = tmpMap;
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

        /*

          +---+
         /   /|
        +---+ |
        |   | +
        |   |/
        +---+

        */
/*
16
2 3 2
2 2 1
2 2 1
2 0 2
3 2 0
3 0 0
3 1 1
3 0 3
2 3 2
2 2 1
2 2 1
2 0 2
3 2 0
3 0 0
3 1 1
3 0 3
 */

public class Main {
    static int ans = 0;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static int[][] domino(int[][] arr, int type, int j) {
        if (type == 1) {
            for (int i = 2; i < 6; i++) {
                if (arr[i][j] != 0) {
                    arr[i - 1][j] = 1;
                    break;
                }
                if (i == 5) {
                    arr[5][j] = 1;
                }
            }
        } else if (type == 2) {
            for (int i = 2; i < 6; i++) {
                if (arr[i][j] != 0 || arr[i][j + 1] != 0) {
                    arr[i - 1][j] = arr[i - 1][j + 1] = 1;
                    break;
                }
                if (i == 5) {
                    arr[5][j] = arr[5][j + 1] = 1;
                }
            }
        } else {
            for (int i = 2; i < 6; i++) {
                if (arr[i][j] != 0) {
                    arr[i - 1][j] = arr[i - 2][j] = 1;
                    break;
                }
                if (i == 5) {
                    arr[5][j] = arr[4][j] = 1;
                }
            }
        }

        for (int i = 2; i < 6; i++) {
            int sum = 0;
            for (int k = 0; k < 4; k++) sum += arr[i][k];
            if (sum == 4) {
                ans++;
                for (int k = i; k > 0; k--) {
                    arr[k] = arr[k - 1];
                }
                arr[0] = new int[4];
            }
        }

        while (true) {
            boolean isBlock = false;
            for (int k = 0; k < 4; k++) {
                if (arr[1][k] != 0) {
                    isBlock = true;
                    break;
                }
            }
            if (!isBlock) break;

            for (int i = 5; i > 0; i--) {
                arr[i] = arr[i - 1];
            }
            arr[0] = new int[4];
        }

        return arr;
    }

    public static void main(String[] args) throws Exception{
        int[][] green = new int[6][4];
        int[][] blue = new int[6][4];
        int[] change = {0, 1, 3, 2};

        int N = Integer.parseInt(bf.readLine());
        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int type = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            green = domino(green, type, j);
            blue = domino(blue, change[type], i);         }

        int cnt = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                cnt += green[i][j] + blue[i][j];
            }
        }
        System.out.println(ans);
        System.out.println(cnt);
    }
}


class Node implements Comparable<Node> {
    int u;
    int v;
    int w;

    Node(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    void swap() {
        int tmp = u;
        u = v;
        v = tmp;
    }

    boolean isConnected() {
        return u == v;
    }

    @Override
    public int hashCode() {
        return Objects.hash(u, v, w);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node node = (Node) obj;
            return u == node.u && v == node.v && w == node.w;
        }
        return false;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(w, o.w);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(u).append(" ").append(v).append(" ").append(w);
        return sb.toString();
    }
}

class Coordinate implements Comparable<Coordinate> {
    int first;
    int second;
    int third;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Coordinate(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
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


    /*

     왼쪽이 클 경우 양수
     오른 쪽이 클 경우 음수
     같을 경우 0

     */

    //     first 오름차순 정렬 후 값이 같을경우 second 오름 차 순 정렬
    @Override
    public int compareTo(Coordinate o) {
        int firstSort = Integer.compare(first, o.first);
        if (firstSort == 0) {
            return Integer.compare(second, o.second);
        }
        return firstSort;
    }
}
import java.util.*;
public class Main {
    static int n, m;
    static int[] arr = new int[500002];
    static int upperIndex(int searchNumber) {
        int s, e;
        s = 0; e = n;
        while (s < e) {
            int mid = (s + e) / 2;
            if (arr[mid] <= searchNumber) s = mid + 1;
            else e = mid;
        }
        return s;
    }
    static int lowerIndex(int searchNumber) {
        int s, e;
        s = 0; e = n;
        while (s < e) {
            int mid = (s + e) / 2;
            if (arr[mid] >= searchNumber) e = mid;
            else s = mid + 1;
        }
        return s;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i  < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr, 0, n);
        m = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int searchNumber = sc.nextInt();
            sb.append(upperIndex(searchNumber) - lowerIndex(searchNumber)).append(" ");
        }
        System.out.println(sb);
    }
}
class Coordinate {
    int first;
    int second;
    int cnt;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Coordinate(int first, int second, int cnt) {
        this.first = first;
        this.second = second;
        this.cnt = cnt;
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
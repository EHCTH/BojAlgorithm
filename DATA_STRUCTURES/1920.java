import java.util.*;


public class Main {
    static int n;
    static int[] arr = new int[100002];
    static int m;
    static int solution(int searchNumber) {
        int s, e;
        s = 0; e = n - 1;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (arr[mid] > searchNumber) e = mid - 1;
            else if (arr[mid] < searchNumber) s = mid + 1;
            else return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        m = sc.nextInt();
        Arrays.sort(arr, 0, n);
        for (int i = 0; i < m; i++) {
            int searchNumber = sc.nextInt();
            System.out.println(solution(searchNumber));
        }

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
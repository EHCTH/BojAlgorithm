import java.util.*;

public class Main {
    static int n, m;
    static int[] arr =  new int[100];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
//        m = sc.nextInt();
//        for (int i = 0; i < n; i++) {
//            arr[i] = sc.nextInt();
//        }
//        Arrays.stream(arr).forEach(System.out::println);
        int maxN = (2 * n);
        for (int i = 1; i < (maxN / 2) + 1; i++) {
            System.out.println("*".repeat(i) + " ".repeat(maxN - i * 2) + "*".repeat(i));
        }
        for (int i = (maxN / 2) + 1; i < maxN; i++)  {
            System.out.println("*".repeat(maxN - i) + " ".repeat((maxN - (maxN - i) * 2)) + "*".repeat(maxN -i));
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
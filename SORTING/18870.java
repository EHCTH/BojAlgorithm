import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int n;
    static int[] arr = new int[1000002];


    static int lowerIndex(int searchNumber, List<Integer> uni) {
        int s, e;
        s = 0; e = uni.size() - 1;
        while (s < e) {
            int mid = (s + e) / 2;
            if (uni.get(mid) >= searchNumber) e = mid;
            else s = mid + 1;
        }
        return s;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            arr[i] = num;
            list.add(num);
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        List<Integer> uni = list.stream().distinct().collect(Collectors.toList());
        for (int i = 0; i < n; i++) {
            sb.append(lowerIndex(arr[i], uni)).append(" ");
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
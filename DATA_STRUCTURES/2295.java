import java.util.*;

public class Main {
    static int n;
    static int[] arr = new int[1005];
    static boolean solution(List<Integer> list, int choiceNumber) {
        int s, e;
        s = 0; e = list.size() - 1;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (list.get(mid) > choiceNumber) e = mid - 1;
            else if (list.get(mid) < choiceNumber) s = mid + 1;
            else return true;

        }
        return false;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        List<Integer> list = new ArrayList<>();
        Arrays.sort(arr, 0, n);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                list.add(arr[i] + arr[j]);
            }
        }
        Collections.sort(list);
        boolean isAnswer = false;
        for (int i = n - 1; i > -1; i--) {
            if (isAnswer) break;
            for (int j = i - 1; j > -1; j--) {
                if (solution(list, arr[i] - arr[j])) {
                    System.out.println(arr[i]);
                    isAnswer = true;
                    break;
                }
            }
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
import java.util.*;

public class Main {
    static int t;
    static int[] arr = {300, 60, 10};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();
        int[] answer = {0, 0, 0};
        if (t % 10 != 0) {
            System.out.println(-1);
            System.exit(0);
        }
        else {
            for (int i = 0; i < arr.length;i ++) {
                answer[i] += t / arr[i];
                t %= arr[i];
            }
        }
        Arrays.stream(answer).forEach((x) -> System.out.print(x + " "));
    }
}
class Coordinate {
    int first;
    int second;

    public Coordinate(int first, int second) {
        this.first = first;
        this.second = second;
    }


    public String toString() {
        return this.first + " " + this.second;
    }
}
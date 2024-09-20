import java.util.*;

        /*

          +---+
         /   /|
        +---+ |
        |   | +
        |   |/
        +---+

        */


public class Main {
    static Scanner sc = new Scanner(System.in);
    static final int MOD = 1_000_000_007;
    static int n;
    public static void main(String[] args) {
        n = sc.nextInt();
        int x, y;
        x = 1;
        y = 1;
        for (int i = 2; i < n; i++) {
            int tmp = y;
            y = (x + y) % MOD;
            x = tmp;
        }
        System.out.println(y + " " + (n - 2));
    }

}
import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

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
    public static void main(String[] args) {
        while (true) {
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();
            if (a == 0 && b == 0) break;
            System.out.println(a + b);
        }
    }
}
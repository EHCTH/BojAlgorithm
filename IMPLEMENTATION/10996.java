import java.util.*;

public class Main {
    static int n;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i < n * 2 + 1; i++) {
            if (i % 2 == 0) sb.append(" *".repeat(n / 2)).append("\n");
            else {
                if (n % 2 == 0) sb.append("* ".repeat(n / 2)).append("\n");
                else sb.append("* ".repeat(n / 2)).append("*").append("\n");
            }
        }
        System.out.println(sb);

    }
}
import java.util.Scanner;

public class Main {
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 1; i < n + 1; i++) {
            System.out.print(" ".repeat(i - 1));
            System.out.println("*".repeat( (n - i) * 2 + 1));
        }

    }
}
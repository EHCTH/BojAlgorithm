import java.util.Scanner;

public class Main {
    static int n;
    static String[] star = new String[3 * 1024  + 1];

    static String run(int n) {
        StringBuilder sb = new StringBuilder();
        if (n == 3) {
            return sb.append(star[0]).append("\n")
                    .append(star[1]).append("\n")
                    .append(star[2]).toString();
        }
        String[] ret = run(n / 2).split("\n");
        for (String i : ret) {
            sb.append(" ".repeat(n / 2) + i + " ".repeat(n / 2)).append("\n");
        }
        for (String i : ret) {
            sb.append(i + " " + i).append("\n");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        star[0] = "  *  ";
        star[1] = " * * ";
        star[2] = "*****";
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        System.out.println(run(n));
    }

}
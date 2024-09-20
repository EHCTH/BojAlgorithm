import java.util.Scanner;

public class Main {
    static int n;
    static String[] stars = new String[3];
    static String solutions(int n) {
        StringBuilder sb = new StringBuilder();
        if (n == 3) {
            sb.append(stars[0]).append("\n")
                    .append(stars[1]).append("\n")
                    .append(stars[2]);
            return sb.toString();
        }
        String[] starsList = solutions(n / 3).split("\n");
        for (String star : starsList) sb.append(star.repeat(3)).append("\n");
        for (String star : starsList) sb.append(star).append(" ".repeat(n / 3)).append(star).append("\n");
        for (String star: starsList) sb.append(star.repeat(3)).append("\n");
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        stars[0] = "***";
        stars[1] = "* *";
        stars[2] = "***";
        n = sc.nextInt();
        System.out.println(solutions(n));
    }
}
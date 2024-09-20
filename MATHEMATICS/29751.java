import java.util.*;

public class Main {
    static double w, h;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        w = sc.nextDouble();
        h = sc.nextDouble();
        double area = w * h / 2;
        System.out.println(String.format("%.1f", area));
    }
}
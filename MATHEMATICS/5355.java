import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static List<String> list = new ArrayList<>();

    static void init() throws Exception {
        T = Integer.parseInt(bf.readLine());
        for (int i = 0; i < T; i++) {
            list.add(bf.readLine());
        }
    }

    static String cal(String s) {
        String[] arr = s.split(" ");
        double d = Double.valueOf(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].charAt(0) == '@') d *= 3;
            else if (arr[i].charAt(0) == '%') d += 5;
            else d -= 7;
        }
        return String.format("%.2f", d);
    }

    static String solution() {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append((cal(s))).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(solution());
    }
}
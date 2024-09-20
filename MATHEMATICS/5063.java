import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, r, e, c;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static void init() throws Exception {
        n = Integer.parseInt(bf.readLine());
    }
    static String cal() {
        if (r + c < e) return "advertise";
        else if (r + c == e) return "does not matter";
        return "do not advertise";
    }
    public static void main(String[] args) throws Exception {
        init();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            r = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            System.out.println(cal());

        }
    }
}
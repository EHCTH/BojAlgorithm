import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        int A = 100, B = 100;
        for (int i = 0; i < n; i++) {
            int a, b;
            StringTokenizer st = new StringTokenizer(bf.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (a < b) A -= b;
            else if (a > b) B -= a;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(A).append("\n").append(B);
        System.out.println(sb);
    }
}
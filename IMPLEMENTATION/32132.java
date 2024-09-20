import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static char[] arr = new char[52];
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static void init() throws Exception {
        n = Integer.parseInt(bf.readLine());
        String inString = bf.readLine();
        for (int i = 0; i < n; i++) {
            arr[i] = inString.charAt(i);
        }
    }
    public static void main(String[] args) throws Exception {
        init();
        int idx = 0;
        char[] answer = new char[52];
        for (int i = 0; i < n; i++) {
            if (idx > 1 && (arr[i] == '4' || arr[i] == '5')) {
                if (answer[idx - 2] == 'P' && answer[idx - 1] == 'S') continue;
                answer[idx++] = arr[i];
            }
            else answer[idx++] = arr[i];
        }
        for (int i = 0 ; i < idx; i++) {
            System.out.print(answer[i]);
        }
    }
}
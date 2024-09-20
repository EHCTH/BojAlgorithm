import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int n, m;
    static int[] arrOne = new int[500002];
    static int[] arrTwo = new int[500002];
    static List<Integer> answer = new ArrayList<>();

    static boolean binarySearch(int choiceNumber) {
        int st = 0;
        int end = m - 1;
        while (st <= end) {
            int mid = (st + end) / 2;
            if (arrTwo[mid] < choiceNumber) {
                st = mid + 1;
            }
            else if (arrTwo[mid] > choiceNumber) {
                end = mid - 1;
            }
            else {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            arrOne[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            arrTwo[i] = sc.nextInt();
        }
        Arrays.sort(arrOne, 0, n);
        Arrays.sort(arrTwo, 0, m);

        for (int i = 0; i < n; i++) {
            if (binarySearch(arrOne[i])) answer.add(arrOne[i]);
        }
        System.out.println(answer.size());
        answer.stream().forEach((x) -> System.out.print(x + " "));
    }
}
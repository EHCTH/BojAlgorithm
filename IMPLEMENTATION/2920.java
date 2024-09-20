import java.util.*;

public class Main {
    static int[] arr = new int[8];

    static String solution() {
        String ret = "";
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1] && arr[i] + 1 == arr[i + 1]) ret = "ascending";
            else if (arr[i] > arr[i + 1] && arr[i] == arr[i + 1] + 1) ret = "descending";
            else return "mixed";
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++)
            arr[i] = sc.nextInt();
        System.out.println(solution());
    }
}
import java.util.*;

public class Main {
    static String a, b;
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = sc.next();
        b = sc.next();
        result = a.length();

        for (int i = 0; i < b.length() - a.length() + 1 ; i++){
            int tmp = 0;
            for (int j = 0; j < a.length(); j++){
                if (a.charAt(j) != b.charAt(j + i)) tmp++;
            }
            if (result > tmp) result = tmp;
        }
        System.out.println(result);
    }
}

class Coordinate {
    int first;
    int second;

    public Coordinate(int first, int second) {
        this.first = first;
        this.second = second;
    }


    public String toString() {
        return this.first + " " + this.second;
    }
}
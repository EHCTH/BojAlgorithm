import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

        /*

          +---+
         /   /|
        +---+ |
        |   | +
        |   |/
        +---+

        */

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int answer = Integer.MIN_VALUE;
    static int N;
    static char[] sentence = new char[20];
    static void init() {
        N = sc.nextInt();
        String inString = sc.next();
        for (int i = 0; i < N; i++) {
            sentence[i] = inString.charAt(i);
        }
    }
    static void print() {
        for (int i = 0; i < N; i++) {
            System.out.print(sentence[i] + " ");
        }
        System.out.println();
    }
    static int cal(char operator, int first, int last) {
        if (operator == '*') return first * last;
        else if (operator == '-') return first - last;
        else return first + last;
    }
    static int transformFromCharToInt(char numberChar) {
        return numberChar - '0';
    }
    static void dfs(int n, int number) {
        if (n == N - 1) {
            answer = Math.max(answer, number);
            return;
        }
        int nextCal = cal(sentence[n + 1], number, transformFromCharToInt(sentence[n + 2]));
        dfs(n + 2, nextCal);
        if (n + 4 < N) {
            nextCal = cal(sentence[n + 3], transformFromCharToInt(sentence[n + 2]),
                    transformFromCharToInt(sentence[n + 4]));
            dfs(n + 4, cal(sentence[n + 1], number, nextCal));
        }
    }
    public static void main(String[] args) {
        init();
        dfs(0, transformFromCharToInt(sentence[0]));
        System.out.println(answer);
    }
    // 77 - 9, 68 * 2 136
}


class Node {
    char root;
    Node left = null;
    Node right = null;

    Node(char root) {
        this.root = root;
    }
}

class Coordinate implements Comparable<Coordinate> {
    int first;
    int second;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Coordinate(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first + " " + second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) obj;
            return first == coordinate.first && second == coordinate.second;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public int compareTo(Coordinate o) {
        return second - o.second;
    }
}
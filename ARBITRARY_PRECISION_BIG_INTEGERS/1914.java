import java.math.BigInteger;
import java.util.*;


public class Main {
    static int n;
    static Scanner sc = new Scanner(System.in);
    static void hanoi(int n, int start, int end) {
        if (n == 1) {
            System.out.println(start + " " + end);
            return;
        }
        hanoi(n - 1, start, 6 - start - end);
        System.out.println(start + " " + end);
        hanoi(n - 1, 6 - start - end, end);

        /*
           3 1 3
             2 1 2
                1 1 3
                1 3 2
             2 2 3
                1 2 1
                1 1 3

           output :
            (1, 3)
            (1, 2)
            (3, 2)
            (1, 3)
            (2, 1)
            (2, 3)
            (1, 3)

         */

    }
    public static void main(String[] args) {
        n = sc.nextInt();
        BigInteger bigInteger =  new BigInteger("2");
        System.out.println(bigInteger.pow(n).subtract(new BigInteger("1")));
        if (n <= 20) {
            hanoi(n, 1, 3);
        }
    }
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
/*
6 8
1
1 3 2
1 2 3
1 4 5
2 3 2
2 5 8
3 4 2
4 5 6
5 6 1
 */
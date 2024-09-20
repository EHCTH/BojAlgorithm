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
    public static void main(String[] args) {
        String s = sc.next();
        switch(s){
            case("A+"):
                System.out.println(4.3);
                break;
            case("A0"):
                System.out.println(4.0);
                break;
            case("A-"):
                System.out.println(3.7);
                break;
            case("B+"):
                System.out.println(3.3);
                break;
            case("B0"):
                System.out.println(3.0);
                break;
            case("B-"):
                System.out.println(2.7);
                break;
            case("C+"):
                System.out.println(2.3);
                break;
            case("C0"):
                System.out.println(2.0);
                break;
            case("C-"):
                System.out.println(1.7);
                break;
            case("D+"):
                System.out.println(1.3);
                break;
            case("D0"):
                System.out.println(1.0);
                break;
            case("D-"):
                System.out.println(0.7);
                break;
            case("F"):
                System.out.println(0.0);
                break;

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
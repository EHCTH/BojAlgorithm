import java.math.BigInteger;
import java.util.*;

public class Main {
    static BigInteger a, b;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = new BigInteger(sc.next());
        String inString = sc.next();
        b = new BigInteger(sc.next());
        System.out.println(inString.equals("*") ? a.multiply(b) : a.add(b));
    }
}
class Coordinate {
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
}
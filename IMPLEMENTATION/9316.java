import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

        /*

          +---+
         /   /|
        +---+ |
        |   | +
        |   |/
        +---+

        */

public class Main {
    static int n;
    static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));


//    static void init() throws Exception {
//        StringTokenizer st = new StringTokenizer(bf.readLine());
//    }

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(bf.readLine());
        IntStream.range(1, n + 1).boxed()
                .forEach((idx) -> sb.append("Hello World, Judge").append(" ").append(idx).append("!").append("\n"));
        System.out.println(sb);

//        init();
//        List<Integer> s = IntStream.range(0, 5)
//                .boxed()
//                .flatMap((x) -> IntStream.range(0, 5)
//                        .boxed()
//                        .map((y) -> x + y))
//                .collect(Collectors.toList());
//        System.out.println(s);
//
//        List<Stream<Integer>> s1 = IntStream.range(0, 5)
//                .boxed()
//                .map((x) -> IntStream.range(0, 5)
//                        .boxed()
//                        .map((y) -> x + y))
//                .collect(Collectors.toList());
//        System.out.println(s1);
    }



}

class Node {
    int data;
    Node next = null;
    Node(int data) {
        this.data = data;
    }
}

class Coordinate implements Comparable<Coordinate> {
    int first;
    int second;
    int third;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Coordinate(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
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


    /*

     왼쪽이 클 경우 양수
     오른 쪽이 클 경우 음수
     같을 경우 0

     */

    //     first 오름차순 정렬 후 값이 같을경우 second 오름 차 순 정렬
    @Override
    public int compareTo(Coordinate o) {
        int firstSort = Integer.compare(first, o.first);
        if (firstSort == 0) {
            return Integer.compare(second, o.second);
        }
        return firstSort;
    }


}
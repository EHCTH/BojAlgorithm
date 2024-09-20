import java.util.*;

        /*

          +---+
         /   /|
        +---+ |
        |   | +
        |   |/
        +---+

        */


public class Main {
    // 5 4 5 2 2 2 5 3 1 4
    static Scanner sc = new Scanner(System.in);
    static int N = 10;
    static int answer = Integer.MIN_VALUE;
    static int[] diceSequence = new int[10];
    static Horse[] horses = new Horse[4];
    static int[] point = {
            0, 2, 4, 6, 8, 10, // 0 ~ 5
            12, 14, 16, 18, 20, // 6 ~ 10
            22, 24, 26, 28, 30, // 11 ~ 15
            32, 34, 36, 38, 40, // 16 ~ 20
            13, 16, 19, // 21 ~ 23
            22, 24, // 24 ~ 25
            28, 27, 26, // 26 ~ 28
            25, 30, 35, // 29 ~ 31
            0 // 32
    };

    static int[] steps = new int[33];
    static int[] shortCut = new int[33];

    static void init() {
        for (int i = 0; i < N; i++) {
            diceSequence[i] = sc.nextInt();
        }
        for (int i = 0; i < 4; i++) {
            horses[i] = new Horse();
        }
        for (int i = 0; i < 32; i++) {
            steps[i] = i + 1;
        }
        /*
                0, 2, 4, 6, 8, 10, // 0 ~ 5
                12, 14, 16, 18, 20, // 6 ~ 10
                22, 24, 26, 28, 30, // 11 ~ 15
                32, 34, 36, 38, 40, // 16 ~ 20
                13, 16, 19, // 21 ~ 23
                22, 24, // 24 ~ 25
                28, 27, 26, // 26 ~ 28
                25, 30, 35, // 29 ~ 31
                0 // 32
         */

        /*
         0                        32

         1                        20               19

         2                        31                   18

         3                        30                       17

         4                        30                            16

         5        21    22   23   29   28   27   26                 15

         6                                                       14
                                  25
              7                                           13

                  8                                 12
                                  24
                         9                    11

                                   10
         */
        steps[20] = 32;
        steps[31] = 20;

        steps[23] = 29;
        steps[25] = 29;

        shortCut[5] = 21;
        shortCut[10] = 24;
        shortCut[15] = 26;

    }

    static boolean findShortCut(int choiceStep) {
        return choiceStep == 5 || choiceStep == 10 || choiceStep == 15;
    }

    static boolean findHorse(Horse choiceHorse, int choiceHorseStep) {
        for (Horse horse : horses) {
            if (horse.find(choiceHorse, choiceHorseStep)) return true;
        }
        return false;
    }

    static void backTracking(int n, int num) {
        if (n == N) {
            answer = Math.max(answer, num);
            return;
        }
        int diceNumber = diceSequence[n];
        for (Horse horse : horses) {
            int choiceHorseStep = horse.step;
            if (choiceHorseStep == 32) continue;
            for (int nextStep = 0; nextStep < diceNumber; nextStep++) {
                if (choiceHorseStep == 32) break;
                if (nextStep == 0 && findShortCut(choiceHorseStep)) choiceHorseStep = shortCut[choiceHorseStep];
                else choiceHorseStep = steps[choiceHorseStep];
            }
            boolean find = findHorse(horse, choiceHorseStep);
            if (choiceHorseStep != 32 && find) continue;
            int preStep = horse.step;
            horse.step = choiceHorseStep;
            backTracking(n + 1, num + point[horse.step]);
            horse.step = preStep;

        }
    }

    public static void main(String[] args) {
        init();
        backTracking(0, 0);
        System.out.println(answer);
    }
}
class Horse {
    int step = 0;
    boolean find(Horse choiceHorse, int choiceHorseStep) {
        if (choiceHorse == this) return false;
        return  step == choiceHorseStep;
    }
    @Override
    public String toString() {
        return "" + step;
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

    public Coordinate() {
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
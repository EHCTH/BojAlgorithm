import java.util.*;
import java.util.stream.Collectors;

public class Main {
    /*
        flag 를 기준으로 앞 밭, 뒷 밭, 방 기본 움직임 설정
        steps 로 각 칸을 설정


        10                 9             8             7                 6                        5


        11     flag == 2 && 1                                             flag == 1 && 1          4


        12              flag == 2 && 2                             flag == 2 && 2                 3


        13                      (flag == 2 && 3 || flag == 3 || 0)                                2


        14            flag == 1 && 4         (flag == 2 && 4 || flag == 3 && 1)                   1


                  flag == 1 && 5                        (flag == 2 && 5 || flag == 3 && 2)


        15          16               17               18                19          (flag==0 && 20 ||
                                                                                       flag == 2 && 6 ||
                                                                                         flag == 3 && 3)
     */
    static int t;
    static final int n = 32;
    static final int m = 32;
    static Map<String, Horse> horseMap = new HashMap<>();
    static String[][] graph;
    static List<String> victoryHorse = new ArrayList<>();
    static String[] map = {
            "..----..----..----..----..----..",
            "..    ..    ..    ..    ..    ..",
            "| \\                          / |",
            "|  \\                        /  |",
            "|   \\                      /   |",
            "|    ..                  ..    |",
            "..   ..                  ..   ..",
            "..     \\                /     ..",
            "|       \\              /       |",
            "|        \\            /        |",
            "|         ..        ..         |",
            "|         ..        ..         |",
            "..          \\      /          ..",
            "..           \\    /           ..",
            "|             \\  /             |",
            "|              ..              |",
            "|              ..              |",
            "|             /  \\             |",
            "..           /    \\           ..",
            "..          /      \\          ..",
            "|         ..        ..         |",
            "|         ..        ..         |",
            "|        /            \\        |",
            "|       /              \\       |",
            "..     /                \\     ..",
            "..   ..                  ..   ..",
            "|    ..                  ..    |",
            "|   /                      \\   |",
            "|  /                        \\  |",
            "| /                          \\ |",
            "..    ..    ..    ..    ..    ..",
            "..----..----..----..----..----.."};

    static void printGraph() {
        StringBuilder sb = new StringBuilder();
        for (String[] r : graph) {
            for (String c : r) sb.append(c);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static int getMoveCnt(char[] move) {
        int ret = 0;
        for (char m : move) {
            if (m == 'F') ret++;
        }
        return ret == 0 ? 5 : ret;
    }

    static Coordinate initHorse(String oName) {
        return Arrays.stream(Horse.HorseInitPos.values())
                .filter((horseInitPos) -> horseInitPos.name.equalsIgnoreCase(oName))
                .map((horseInitPos) -> new Coordinate(horseInitPos.x, horseInitPos.y))
                .findFirst()
                .get();
    }

    static void horseCntSetting(String horseName, int moveCnt) {
        horseMap.putIfAbsent(horseName, new Horse(horseName, initHorse(horseName), moveCnt));
        horseMap.get(horseName).cnt = moveCnt;
    }

    static List<String> removeAndAddOtherList(String alpha, Horse horse) {
        List<String> otherHorseList = new ArrayList<>();
        Iterator<Map.Entry<String, Horse>> it = horseMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Horse> entry = it.next();
            // 삭제
            if (alpha.contains(entry.getKey()) && entry.getValue().steps == horse.steps &&
                entry.getValue().flag == horse.flag) {
                it.remove();
            }
            else {
                if (entry.getValue().steps == horse.steps && !entry.getKey().equals(horse.name) &&
                    entry.getValue().flag == horse.flag) {
                    otherHorseList.add(entry.getKey());
                }
            }
        }
        return otherHorseList;
    }
    static List<String> isUpperCaseHorseName(Horse horse) {
        return removeAndAddOtherList("abcd", horse);
    }

    static List<String> isLowerCaseHorseName(Horse horse) {
        return removeAndAddOtherList("ABCD", horse);
    }
    static void checkTheRemoveOrAddOtherHorse(Horse horse) {
        char horseName = horse.name.charAt(0);
        List<String> otherHorseList = new ArrayList<>();

        //  대문자 말의 경우
        if (Character.isUpperCase(horseName)) otherHorseList.addAll(isUpperCaseHorseName(horse));

        // 소문자 말의 경우
        else otherHorseList.addAll(isLowerCaseHorseName(horse));
        addOtherHorse(horse, otherHorseList);
    }

    static List<String> isNotDuplicateName(String name, List<String> list) { // 이름 중복 제거
        return list.stream().filter((x) -> !x.equals(name)).collect(Collectors.toList());
    }

    static void addOtherHorse(Horse horse, List<String> otherHorseList) {
        otherHorseList.add(horse.name);
        for (String otherHorseName : otherHorseList) {
            Horse otherHorse = horseMap.get(otherHorseName);
            otherHorse.haveOtherHorse.addAll(isNotDuplicateName(otherHorseName, otherHorseList));
        }
        horse.haveOtherHorse.addAll(isNotDuplicateName(horse.name, otherHorseList));
    }

    // TODO CODE 이 아래로 수정 하자 (Refactor)
    static void endGame(Horse horse) {
        for (String otherHorseName : horse.haveOtherHorse) {
            victoryHorse.add(otherHorseName);
            Horse otherHorse = horseMap.get(otherHorseName);
            horseMap.remove(otherHorse.name);
        }
        victoryHorse.add(horse.name);
        horseMap.remove(horse.name);
    }

    static void setOtherHorse(Horse horse, List<Coordinate> coordinateList) {
        for (Coordinate nc : coordinateList) {
            horse.coordinate.first += nc.first;
            horse.coordinate.second += nc.second;
            for (String otherHorseName : horse.haveOtherHorse) {
                Horse otherHorse = horseMap.get(otherHorseName);
                otherHorse.steps = horse.steps;
                otherHorse.flag = horse.flag;
                otherHorse.coordinate.first += nc.first;
                otherHorse.coordinate.second += nc.second;
            }
        }
    }

    static void setHorseMove(Horse horse) {
        int prevHorseSteps = horse.steps;
        horse.steps += horse.cnt;
        int move = horse.cnt * 5;
        List<Coordinate> coordinatesList = new ArrayList<>();

        // 종료 조건
        if ( horse.flag == 0 && horse.steps > 20 ||
                (horse.flag == 2 && horse.steps > 6) ||
                (horse.flag == 3 && horse.steps > 3)) {
            endGame(horse);
            return;
        }

        if (horse.haveOtherHorse.size() != 0) { // 말이 업혀 있는 경우 (업혀 있는 말의 경우)
            if (horse.flag != 0) {
                if (horse.flag == 1) { // 앞 밭
                    coordinatesList.add(new Coordinate(move, -move));

                    if (horse.steps == 3) {
                        horse.flag = 3;
                        horse.steps = 0;
                    }
                    else if (horse.steps >= 6) {
                        horse.flag = 0;
                        int remainCnt = horse.steps % 6;
                        move = remainCnt * 5;
                        int nextMove = remainCnt * 6;
                        horse.steps = 15 + remainCnt;
                        coordinatesList.add(new Coordinate(-move, move));
                        coordinatesList.add(new Coordinate(0, nextMove));
                    }

                } else if (horse.flag == 2) { // 뒷 밭
                    coordinatesList.add(new Coordinate(move, move));
                    if (horse.steps > 6) {
                        int remainCnt = horse.steps % 6;
                        move = remainCnt * 5;
                        coordinatesList.add(new Coordinate(-move, -move));
                    }

                } else if (horse.flag == 3) { // 방
                    coordinatesList.add(new Coordinate(move, move));
                    if (horse.steps > 3) {
                        int remainCnt = horse.steps % 3;
                        move = remainCnt * 5;
                        coordinatesList.add(new Coordinate(-move, -move));
                    }
                }

            } else { // 기본 움직임의 경우,  여기 에서 flag 1, 2설정
                move = horse.cnt * 6;
                if (prevHorseSteps <= 5) {
                    coordinatesList.add(new Coordinate(-move, 0));
                    if (horse.steps == 5) { // 앞밭일경우
                        horse.flag = 1;
                        horse.steps = 0;
                    } else if (horse.steps > 5) {
                        int remainCnt = horse.steps % 5;
                        move = remainCnt * 6;
                        coordinatesList.add(new Coordinate(move, -move));
                    }
                } else if (prevHorseSteps <= 10) {
                    coordinatesList.add(new Coordinate(0, -move));
                    if (horse.steps == 10) {
                        horse.flag = 2;
                        horse.steps = 0;

                    } else if (horse.steps > 10) {
                        int remainCnt = horse.steps % 5;
                        move = remainCnt * 6;
                        coordinatesList.add(new Coordinate(move, move));
                    }
                } else if (prevHorseSteps <= 15) {
                    coordinatesList.add(new Coordinate(move, 0));
                    if (horse.steps > 15) {
                        int remainCnt = horse.steps % 5;
                        move = remainCnt * 6;
                        coordinatesList.add(new Coordinate(-move, move));
                    }
                } else {
                    coordinatesList.add(new Coordinate(0, move));
                    if (horse.steps >= 20) {
                        int remainCnt = horse.steps % 5;
                        move = remainCnt * 6;
                        coordinatesList.add(new Coordinate(0, -move));
                    }
                }
            }
            setOtherHorse(horse, coordinatesList); // 업힌 말 방향 초기화


        } else { // 말이 업혀있지 않는 경우 아직 미완성
            {
                // flag1 앞밭, 2뒷밭, 3방
                if (horse.flag != 0) {
                    if (horse.flag == 1) { // 앞 밭
                        horse.coordinate.first += move;
                        horse.coordinate.second -= move;
                        if (horse.steps == 3) {
                            horse.flag = 3;
                            horse.steps = 0;
                        } else if (horse.steps >= 6) {
                            horse.flag = 0;
                            int remainCnt = horse.steps % 6;
                            move = remainCnt * 5;
                            int nextMove = remainCnt * 6;
                            horse.coordinate.first -= move;
                            horse.coordinate.second += move;
                            horse.steps = 15 + remainCnt;
                            horse.coordinate.second += nextMove;
                        }

                    } else if (horse.flag == 2) { // 뒷 밭
                        horse.coordinate.first += move;
                        horse.coordinate.second += move;
                        if (horse.steps > 6) {
                            int remainCnt = horse.steps % 6;
                            move = remainCnt * 5;
                            horse.coordinate.first -= move;
                            horse.coordinate.second -= move;
                        }

                    } else if (horse.flag == 3) {
                        horse.coordinate.first += move;
                        horse.coordinate.second += move;
                        if (horse.steps > 3) {
                            int remainCnt = horse.steps % 3;
                            move = remainCnt * 5;
                            horse.coordinate.first -= move;
                            horse.coordinate.second -= move;
                        }
                    }

                } else { // 기본 움직임의 경우,  여기 에서 flag 1, 2, 3 설정
                    move = horse.cnt * 6;
                    if (prevHorseSteps <= 5) {
                        horse.coordinate.first -= move;
                        if (horse.steps == 5) {
                            horse.flag = 1;
                            horse.steps = 0;
                        } else { // 그냥 바로 왼쪽으로 꺾을 경우
                            if (horse.steps > 5) {
                                int remainCnt = horse.steps % 5;
                                move = remainCnt * 6;
                                horse.coordinate.first += move;
                                horse.coordinate.second -= move;
                            }
                        }
                    }
                    else if (prevHorseSteps <= 10) {
                        horse.coordinate.second -= move;
                        if (horse.steps == 10) {
                            horse.flag = 2;
                            horse.steps = 0;
                        } else {
                            if (horse.steps > 10) {
                                int remainCnt = horse.steps % 5;
                                move = remainCnt * 6;
                                horse.coordinate.first += move;
                                horse.coordinate.second += move;
                            }
                        }

                    }
                    else if (prevHorseSteps <= 15) {
                        horse.coordinate.first += move;
                        if (horse.steps > 15) {
                            int remainCnt = horse.steps % 5;
                            move = remainCnt * 6;
                            horse.coordinate.first -= move;
                            horse.coordinate.second += move;
                        }


                    } else {
                        horse.coordinate.second += move;
                        if (horse.steps >= 20) {
                            int remainCnt = horse.steps % 5;
                            move = remainCnt * 6;
                            horse.coordinate.second -= move;
                        }
                    }
                }
            }
        }

        checkTheRemoveOrAddOtherHorse(horse);

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        graph = new String[map.length][map.length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                graph[i][j] = String.valueOf(map[i].charAt(j));
            }
        }
        t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            String horseName = sc.next();
            char[] move = sc.next().toCharArray();
            if (victoryHorse.contains(horseName)) {
                System.out.println(victoryHorse);
                continue;
            }
            int moveCnt = getMoveCnt(move);
            horseCntSetting(horseName, moveCnt);
            Horse horse = horseMap.get(horseName);
            setHorseMove(horse);
        }
        for (Horse horse : horseMap.values()) {
            graph[horse.coordinate.first][horse.coordinate.second] = horse.name;
        }
        printGraph();
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

class Horse {
    enum HorseInitPos {
        /*
            말 시작위치

            a = 30, 30
            b = 30, 31
            c = 31, 30
            d = 31, 31

         */
        A("A", 30, 30),
        B("B", 30, 31),
        C("C", 31, 30),
        D("D", 31, 31);
        String name;
        int x;
        int y;

        HorseInitPos(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }

    String name;
    Coordinate coordinate;
    int cnt;
    int flag = 0; // 기본값은 0
    int steps = 0;
    Set<String> haveOtherHorse = new HashSet<>();

    Horse(String name, Coordinate coordinate, int cnt) {
        this.name = name;
        this.coordinate = coordinate;
        this.cnt = cnt;
    }
    @Override
    public String toString() {
        return name + " " + coordinate + " " + flag + " " + steps;
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

        /*

          +---+
         /   /|
        +---+ |
        |   | +
        |   |/
        +---+

        */

public class Main {
    static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static List<Monster> monsterInfo = new ArrayList<>();
    static int n, m, d;
    static int answer = 0;
    static int[][] graph = new int[17][17];

    static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static boolean prePermutation(int[] arr, int end) {
        int n = end - 1;
        int i = n - 1;
        while (i >= 0 && arr[i] <= arr[i + 1]) i--;
        if (i < 0) {
            reverse(arr, 0, n);
            return false;
        }
        int j = n;
        while (arr[i] <= arr[j]) j--;
        swap(arr, i, j);
        reverse(arr, i + 1, n);
        return true;
    }


    static List<Monster> monsterMove(List<Monster> monsterInfo) {
        return monsterInfo.stream().map(monster ->
                new Monster(monster.coordinate.first + 1, monster.coordinate.second))
                .collect(Collectors.toList());
    }
    static List<Monster> passMonsterExclude(List<Monster> moveMonster) {
        return moveMonster.stream()
                .filter((monster) -> !monster.getPass(n))
                .collect(Collectors.toList());
    }

    static boolean allCatchTheMonster(List<Monster> monsterInfo) {
        return monsterInfo.isEmpty();
    }
    static int run(List<Archer> archerInfo) {
        List<Monster> copyMonsterInfo = deepCopy();
        int catchCnt = 0;
        while (true) {
            //TODO while 몬스터가 있거나 x < n 일경우
            Set<Monster> catchMonsterInfo = new HashSet<>();
            for (Archer archer : archerInfo) {
                int minDistance = Integer.MAX_VALUE;
                Monster catchMonster = null; // 기본값
                for (Monster monster : copyMonsterInfo) {
                    int distance = Math.abs(archer.coordinate.second - monster.coordinate.second)
                            + Math.abs(archer.coordinate.first - monster.coordinate.first);
                    if (minDistance >= distance && distance <= archer.range) {
                        if (minDistance == distance) {
                            catchMonster = catchMonster.coordinate.second < monster.coordinate.second ?
                                                    catchMonster : monster;
                            continue;
                        }
                        minDistance = distance;
                        catchMonster = monster;
                    }
                    //TODO manhattanDistance 제일 거리가 작을 경우 catchMonsterInfo 에 추가
                    //TODO 만약 거리가 같을 경우 y 가 적은 값을 우선 순위

                }
                if (catchMonster == null) continue;
                catchMonsterInfo.add(catchMonster);
            }
            copyMonsterInfo.removeAll(catchMonsterInfo); // 차집합
            catchCnt += catchMonsterInfo.size();
            List<Monster> monsterMoveInfo = monsterMove(copyMonsterInfo);
            copyMonsterInfo = passMonsterExclude(monsterMoveInfo);
            if (allCatchTheMonster(copyMonsterInfo)) {
                break;
            }
        }
        return catchCnt;
    }

    static void init() throws Exception {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) monsterInfo.add(new Monster(i, j));
            }
        }
    }

    static List<Monster> deepCopy() {
        return monsterInfo.stream()
                .map((monster) ->new Monster(monster.coordinate.first, monster.coordinate.second))
                .collect(Collectors.toList());
    }
    public static void main(String[] args) throws Exception {
        init();
        int[] visited = new int[m];
        Arrays.fill(visited, 0, 3, 1);

        do {
            List<Archer> archerInfo = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                if (visited[i] == 0) continue;
                archerInfo.add(new Archer(n, i, d));
            }
            answer = Math.max(answer, run(archerInfo));
        }
        while (prePermutation(visited, m));
        System.out.println(answer);
    }
}


class Monster {
    Coordinate coordinate;
    Monster(int x, int y) {
        this.coordinate = new Coordinate(x, y);
    }
    boolean getPass(int n) {
        return coordinate.first >= n;
    }
    @Override
    public String toString() {
        return coordinate + " " ;
    }
}
class Archer {
    Coordinate coordinate;
    int range;

    Archer(int x, int y, int range) {
        this.coordinate = new Coordinate(x, y);
        this.range = range;
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
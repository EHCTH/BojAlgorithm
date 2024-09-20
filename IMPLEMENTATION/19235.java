import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int cnt = 0;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static List<Block> blockInfo = new ArrayList<>();
    static Domino domino = new Domino();

    static void init() throws Exception {
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int type = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            blockInfo.add(new Block(type, x, y));
        }
    }

    static boolean bingo(int[] tmp) {
        return Arrays.stream(tmp).filter((x) -> x >= 1).count() == 4;
    }

    static void move(int[][] tmp) {
        for (int i = 4; i > -1; i--) {
            for (int j = 0; j < 4; j++) {
                int type = tmp[i][j];
                if (type == 1 || type == 3) {
                    if (tmp[i + 1][j] == 0) {
                        int idx = i + 2;
                        while (idx < 6) {
                            if (tmp[idx][j] != 0) break;
                            idx++;
                        }
                        tmp[idx - 1][j] = type;
                        tmp[i][j] = 0;
                    }
                } else if (j != 3 && type == 2 && tmp[i][j + 1] == type) {
                    if (tmp[i + 1][j] == 0 && tmp[i + 1][j + 1] == 0) {
                        int idx = i + 2;
                        while (idx < 6) {
                            if (tmp[idx][j] != 0 || tmp[idx][j + 1] != 0) break;
                            idx++;
                        }
                        tmp[idx - 1][j] = tmp[idx - 1][j + 1] = type;
                        tmp[i][j] = tmp[i][j + 1] = 0;
                    }
                }
            }
        }

    }

    static void delete(int[][] tmp) {
        boolean destruction = false;
        for (int i = 2; i < 6; i++) {
            if (bingo(tmp[i])) {
                cnt++;
                Arrays.fill(tmp[i], 0);
                destruction = true;
            }
        }

        if (!destruction) return;
        move(tmp);
        delete(tmp);
    }

    static boolean lightColorExistBlock(int[] tmp) {
        return Arrays.stream(tmp).anyMatch((x) -> x >= 1);
    }

    static void lightColorBlockDelete(int[][] tmp) {
        int cnt = 0;
        for (int i = 0; i < 2; i++) {
            if (lightColorExistBlock(tmp[i])) cnt++;
        }
        for (int i = 6 - cnt; i < 6; i++) {
            Arrays.fill(tmp[i], 0);
        }
        move(tmp);
        delete(tmp);
    }

    static void add(int[][] tmp, int type, int j) {
        if (type == 1) {
            for (int i = 2; i < 6; i++) {
                if (tmp[i][j] >= 1) {
                    tmp[i - 1][j] = type;
                    break;
                }
                if (i == 5) tmp[i][j] = type;
            }
        } else if (type == 2) {
            for (int i = 2; i < 6; i++) {
                if (tmp[i][j] >= 1 || tmp[i][j + 1] >= 1) {
                    tmp[i - 1][j] = tmp[i - 1][j + 1] = type;
                    break;
                }
                if (i == 5) tmp[i][j] = tmp[i][j + 1] = type;
            }
        } else {
            for (int i = 2; i < 6; i++) {
                if (tmp[i][j] >= 1) {
                    tmp[i - 1][j] = tmp[i - 2][j] = type;
                    break;
                }
                if (i == 5) tmp[i][j] = tmp[i - 1][j] = type;
            }
        }
        delete(tmp);
        lightColorBlockDelete(tmp);

    }

    public static void main(String[] args) throws Exception {
        init();
        int type, x, y;
        for (Block greenBlock : blockInfo) {
            type = greenBlock.type;
            x = greenBlock.x;
            y = greenBlock.y;
            add(domino.green, type, y);
            Block blueBlock = Block.change(greenBlock);
            type = blueBlock.type;
            x = blueBlock.x;
            y = blueBlock.y;
            add(domino.blue, type, y);
        }
        System.out.println(cnt);
        System.out.println(domino);
    }
}
/*
12
1 1 1
2 3 0
3 2 2
3 2 3
3 1 3
2 0 0
3 2 0
3 1 2
1 0 3
2 0 2
3 0 1
3 2 0
 */

class Domino {
    int[][] blue = new int[6][4];
    int[][] green = new int[6][4];

    void print() {
        for (int i = 0; i < 6; i++) {
            System.out.println(Arrays.toString(green[i]));
        }
    }

    void bluePrint() {
        for (int i = 0; i < 6; i++) {
            System.out.println(Arrays.toString(blue[i]));
        }
    }

    int existBlock() {
        int b = Arrays.stream(blue).flatMapToInt(Arrays::stream)
                .filter((x) -> x >= 1)
                .reduce(0, (x, y) -> x + 1);
        int g = Arrays.stream(green).flatMapToInt(Arrays::stream)
                .filter((x) -> x >= 1)
                .reduce(0, (x, y) -> x + 1);
        return b + g;
    }

    @Override
    public String toString() {
        return "" + existBlock();
    }
}

class Block {
    int type;
    int x, y;

    Block(int type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    static Block change(Block block) {
        if (block.type == 1) return new Block(block.type, block.y, block.x);
        return new Block(5 - block.type, block.y, block.x);
    }
}
/*
8
3 0 0
2 0 0
3 0 0
2 0 2
2 0 2
1 0 1
1 0 2
1 0 3



8
2 0 1
2 0 1
3 0 3
2 0 0
3 0 2
1 0 0
1 0 1
3 0 3


8
1 0 0
2 0 2
2 0 0
1 2 2
1 1 1
1 2 0
2 2 0
1 0 3

7
1 0 0
2 0 2
2 0 0
1 2 2
1 1 1
2 2 0
1 0 3
 */
import java.util.*;

public class Main {
    static Character[][][] cube = new Character[6][3][3];
    static char[] colors = {'w', 'y', 'r', 'o', 'g', 'b'};
    static final int UP = 0;
    static final int DOWN = 1;
    static final int FRONT = 2;
    static final int BACK = 3;
    static final int LEFT = 4;
    static final int RIGHT = 5;

    static void init() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    cube[i][j][k] = colors[i];
                }
            }
        }
    }

    static <T> void swap(T[][] ori, T[][] other) {
        int n = ori.length;
        int m = ori[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                T tmp = other[i][j];
                other[i][j] = ori[i][j];
                ori[i][j] = tmp;
            }
        }
    }

    static void rotate(int dir, boolean isCheck) {
        int rot = 1;
        if (!isCheck) rot = 3;
        while (rot != 0) {
            rot--;
            Character[][] otherCube = new Character[3][3];
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    otherCube[i][j] = cube[dir][2 - j][i];
            swap(cube[dir], otherCube);
        }
    }

    static void rotateDir(int dir, boolean isCheck) {
        rotate(dir, isCheck);
        int rot = 1;
        if (!isCheck) rot = 3;
        Character tmp;
        while (rot != 0) {
            rot--;
            if (dir == UP) {
                for (int i = 0; i < 3; i++) {
                    tmp = cube[BACK][2][i];
                    cube[BACK][2][i] = cube[LEFT][0][2 - i];
                    cube[LEFT][0][2 - i] = cube[FRONT][0][2 - i];
                    cube[FRONT][0][2 - i] = cube[RIGHT][0][2 - i];
                    cube[RIGHT][0][2 - i] = tmp;
                }
            } else if (dir == DOWN) {
                for (int i = 0; i < 3; i++) {
                    tmp = cube[BACK][0][i];
                    cube[BACK][0][i] = cube[RIGHT][2][2 - i];
                    cube[RIGHT][2][2 - i] = cube[FRONT][2][2 - i];
                    cube[FRONT][2][2 - i] = cube[LEFT][2][2 - i];
                    cube[LEFT][2][2 - i] = tmp;

                }
            } else if (dir == FRONT) {
                for (int i = 0; i < 3; i++) {
                    tmp = cube[UP][2][i];
                    cube[UP][2][i] = cube[LEFT][2 - i][2];
                    cube[LEFT][2 - i][2] = cube[DOWN][0][2 - i];
                    cube[DOWN][0][2 - i] = cube[RIGHT][i][0];
                    cube[RIGHT][i][0] = tmp;
                }
            } else if (dir == BACK) {
                for (int i = 0; i < 3; i++) {
                    tmp = cube[UP][0][2 - i];
                    cube[UP][0][2 - i] = cube[RIGHT][2 - i][2];
                    cube[RIGHT][2 - i][2] = cube[DOWN][2][i];
                    cube[DOWN][2][i] = cube[LEFT][i][0];
                    cube[LEFT][i][0] = tmp;

                }
            } else if (dir == LEFT) {
                for (int i = 0; i < 3; i++) {
                    tmp = cube[UP][i][0];
                    cube[UP][i][0] = cube[BACK][i][0];
                    cube[BACK][i][0] = cube[DOWN][i][0];
                    cube[DOWN][i][0] = cube[FRONT][i][0];
                    cube[FRONT][i][0] = tmp;
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    tmp = cube[UP][2 - i][2];
                    cube[UP][2 - i][2] = cube[FRONT][2 - i][2];
                    cube[FRONT][2 - i][2] = cube[DOWN][2 - i][2];
                    cube[DOWN][2 - i][2] = cube[BACK][2 - i][2];
                    cube[BACK][2 - i][2] = tmp;
                }
            }
        }
    }

    static void upDirPrint() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[UP][i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            init();
            int cnt = sc.nextInt();
            for (int j = 0; j < cnt; j++) {
                char[] c = sc.next().toCharArray();
                if (c[0] == 'U') {
                    rotateDir(UP, c[1] == '+');
                } else if (c[0] == 'D') {
                    rotateDir(DOWN, c[1] == '+');
                } else if (c[0] == 'F') {
                    rotateDir(FRONT, c[1] == '+');
                } else if (c[0] == 'B') {
                    rotateDir(BACK, c[1] == '+');
                } else if (c[0] == 'L') {
                    rotateDir(LEFT, c[1] == '+');
                } else {
                    rotateDir(RIGHT, c[1] == '+');
                }
            }
            upDirPrint();
        }

    }
}
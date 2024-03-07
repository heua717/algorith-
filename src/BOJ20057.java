import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20057 {
    static int N, tr, tc, sand;
    static int map[][];
    static int dir[][] = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static int sandDir[][][] = {{{0, -2}, {-1, -1}, {1, -1}, {-1, 0}, {1, 0}, {-2, 0}, {2, 0}, {-1, 1}, {1, 1}},
            {{2, 0}, {1, -1}, {1, 1}, {0, -1}, {0, 1}, {0, -2}, {0, 2}, {-1, -1}, {-1, 1}}};
    static int cal[] = {5, 10, 10, 7, 7, 2, 2, 1, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sand += map[i][j];
            }
        }
        tr = N / 2 + 1;
        tc = N / 2 + 1;
        tornado();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sand -= map[i][j];
            }
        }
        System.out.println(sand);
    }

    static void tornado() {
        int d = -1;
        for (int i = 1; i < N; i++) {
            if (i != N - 1) {
                for (int j = 1; j <= 2; j++) {
                    d = (d + 1) % 4;
                    for (int k = 1; k <= i; k++) {
                        tr = tr + dir[d][0];
                        tc = tc + dir[d][1];
                        sand(tr, tc, d);
                    }
                }
            } else {
                for (int j = 1; j <= 3; j++) {
                    d = (d + 1) % 4;
                    for (int k = 1; k <= i; k++) {
                        tr = tr + dir[d][0];
                        tc = tc + dir[d][1];
                        sand(tr, tc, d);
                    }
                }
            }
        }
    }

    static void sand(int r, int c, int d) {
        int move = 0 ;
        if (map[r][c] == 0) {
            return;
        }
        if (d == 0) {
            for (int i = 0; i < 9; i++) {
                int nr = r + sandDir[0][i][0];
                int nc = c + sandDir[0][i][1];
                int x = (map[r][c] * cal[i]) / 100;
                if (x == 0) {
                    continue;
                }
                move += x;
                if (nr > 0 && nr <= N && nc > 0 && nc <= N) {
                    map[nr][nc] += x;
//                    map[r][c] -= x;
                }
            }

        } else if (d == 1) {
            for (int i = 0; i < 9; i++) {
                int nr = r + sandDir[1][i][0];
                int nc = c + sandDir[1][i][1];
                int x = (map[r][c] * cal[i]) / 100;
                if (x == 0) {
                    continue;
                }
                move += x;
                if (nr > 0 && nr <= N && nc > 0 && nc <= N) {
                    map[nr][nc] += x;
//                    map[r][c] -= x;
                }
            }
        } else if (d == 2) {
            for (int i = 0; i < 9; i++) {
                int nr = r + sandDir[0][i][0] * -1;
                int nc = c + sandDir[0][i][1] * -1;
                int x = (map[r][c] * cal[i]) / 100;
                if (x == 0) {
                    continue;
                }
                move += x;
                if (nr > 0 && nr <= N && nc > 0 && nc <= N) {
                    map[nr][nc] += x;
//                    map[r][c] -= x;
                }
            }
        } else {
            for (int i = 0; i < 9; i++) {
                int nr = r + sandDir[1][i][0] * -1;
                int nc = c + sandDir[1][i][1] * -1;
                int x = (map[r][c] * cal[i]) / 100;
                if (x == 0) {
                    continue;
                }
                move += x;
                if (nr > 0 && nr <= N && nc > 0 && nc <= N) {
                    map[nr][nc] += x;
//                    map[r][c] -= x;
                }
            }
        }
        int nr = r + dir[d][0];
        int nc = c + dir[d][1];
        if (nr > 0 && nr <= N && nc > 0 && nc <= N) {
            map[nr][nc] += map[r][c] - move;
        }
        map[r][c] = 0;
    }

}

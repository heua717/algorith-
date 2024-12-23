import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ17143 {
    static int R, C, M, ans, eatR, eatC;
    static int dir[][] = {{0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int map[][];
    static ArrayList<int []> moveList = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[R + 1][C + 1];


        for (int m = 1; m <= M; m++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            map[r][c] = z;
            moveList.add(new int[]{r, c, s, d, z});
        }

        for (int i = 1; i <= C; i++) {
            eatR = 0;
            eatC = 0;
            fishing(i);
            Collections.sort(moveList, (a, b) -> b[b.length-1] - a[a.length-1]);
            map = new int[R + 1][C + 1];
            move_shark();
        }
        System.out.println(ans);
    }

    static void move_shark() {
        int size = moveList.size();
        for (int i = 0; i < size; i++) {
            int x[] = moveList.remove(0);
            int r = x[0];
            int c = x[1];
            int s = x[2];
            int d = x[3];
            int z = x[4];
            if (r == eatR && c == eatC) {
                continue;
            }
            move(r, c, s, d, z);

        }
    }

    static void move(int r, int c, int s, int d, int z) {
        int nr = r;
        int nc = c;
        int ns = s;
        int nd = d;
        if (d == 1 || d == 2) { // 상하 이동인지 판별
            if (ns >= (R - 1) * 2) {
                ns = ns % ((R - 1) * 2);
                if (ns == 0) {
                    d = 99;
                }
            }
            if (d == 1) {
                if (ns <= r - 1) {
                    nr = nr + (dir[1][0] * ns);
                } else if (ns - (r - 1) <= R - 1) {
                    nr = 1;
                    ns = ns - (r - 1);
                    nr = nr + (dir[2][0] * ns);
                    nd = 2;
                } else {
                    nr = R;
                    ns = ns - (r - 1) - (R - 1);
                    nr = nr + (dir[1][0] * ns);
                }
            } else if (d == 2) {
                if (ns <= R - r) {
                    nr = nr + (dir[2][0] * ns);
                } else if (ns <= (R - r) + (R - 1)) {
                    nr = R;
                    ns = ns - (R - r);
                    nr = nr + dir[1][0] * ns;
                    nd = 1;
                } else {
                    nr = 1;
                    ns = ns - (R - r) - (R - 1);
                    nr = nr + dir[2][0] * ns;
                }
            }
        } else if (d == 3 || d == 4) { // 좌우 이동인지 판별
            if (ns >= (C - 1) * 2) {
                ns = ns % ((C - 1) * 2);
                if (ns == 0) {
                    d = 99;
                }
            }
            if (d == 3) {
                if (ns <= C - c) {
                    nc = nc + (dir[3][1] * ns);
                } else if (ns <= (C - c) + (C - 1)) {
                    nc = C;
                    ns = ns - (C - c);
                    nc = nc + dir[4][1] * ns;
                    nd = 4;
                } else {
                    nc = 1;
                    ns = ns - (C - c) - (C - 1);
                    nc = nc + dir[3][1] * ns;
                }
            } else if (d == 4) {
                if (ns <= c - 1) {
                    nc = nc + (dir[4][1] * ns);
                } else if (ns - (c - 1) <= C - 1) {
                    nc = 1;
                    ns = ns - (c - 1);
                    nc = nc + (dir[3][1] * ns);
                    nd = 3;
                } else {
                    nc = C;
                    ns = ns - (c - 1) - (C - 1);
                    nc = nc + (dir[4][1] * ns);
                }
            }
        }

        if (z > map[nr][nc]) {
            map[nr][nc] = z;
            moveList.add(new int[]{nr, nc, s, nd, z});
        }


    }

    static void fishing(int c) {
        for (int r = 1; r <= R; r++) {
            if (map[r][c] != 0) {
                eatR = r;
                eatC = c;
                ans += map[r][c];
                map[r][c] = 0;
                break;
            }
        }
    }


}

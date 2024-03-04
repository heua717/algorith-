import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15683 {
    static int N, M, cctvCnt, blank, min = Integer.MAX_VALUE;
    static int map[][], map2[][];
    // cctv 정보 입력
    static int CCTV[][];
    // 탐색 방향
    static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    // 번호 별 탐색 방향
    static int cctvDir[][] = {{}, {0}, {0, 2}, {0, 3}, {0, 2, 3}, {0, 1, 2, 3}};
    static int selected[];
    static boolean visited[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        map2 = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        CCTV = new int[9][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                int x = Integer.parseInt(st.nextToken());
                map[i][j] = x;
                if (x >= 1 && x <= 5) {
                    cctvCnt++;
                    CCTV[cctvCnt][0] = i;
                    CCTV[cctvCnt][1] = j;
                }

            }
        }
        selected = new int[cctvCnt + 1];
        rec(1);
        System.out.println(min);

    }

    static void rec(int k) {
        if (k == cctvCnt + 1) {
            copy_Map();
            dfs();
            return;
        }
        for (int j = 0; j < 4; j++) {
            selected[k] = j;
            rec(k + 1);
            selected[k] = 0;
        }
    }

    static void copy_Map() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map2[i][j] = map[i][j];
            }
        }
    }

    static void dfs() {
        for (int i = 1; i <= cctvCnt; i++) {
            int r = CCTV[i][0];
            int c = CCTV[i][1];
            int d = selected[i];
            int cctvN = map2[r][c];
            for (int k = 0; k < cctvDir[cctvN].length; k++) {
                d = (d + cctvDir[cctvN][k]) % 4;
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                while (true) {
                    if (isGO(nr, nc, map2)) {
                        if (map2[nr][nc] > 5 || map2[nr][nc] < 1) {
                            map2[nr][nc] = -1;
                        }
                    } else {
                        break;
                    }
                    nr += dir[d][0];
                    nc += dir[d][1];
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map2[i][j] == 0) {
                    blank++;
                }
            }
        }
        min = Math.min(min, blank);
        blank = 0;
    }

    static boolean isGO(int r, int c, int map[][]) {
        if (r <= N && r > 0 && c <= M && c > 0 && map[r][c] != 6) {
            return true;
        } else {
            return false;
        }
    }


}

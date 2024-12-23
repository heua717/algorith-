import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12100 {
    static int N, ans;
    static int map[][][];
    static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
                ans = Math.max(ans, map[i][j][0]);
            }
        }
        rec_func(0);
        System.out.println(ans);
    }

    static void move(int d) {
        if (d == 0) {
            for (int r = 1; r <= N; r++) {
                for (int c = N - 1; c >= 1; c--) {
                    move_func(r, c, d);
                }
            }
        } else if (d == 1) {
            for (int r = N - 1; r >= 1; r--) {
                for (int c = 1; c <= N; c++) {
                    move_func(r, c, d);
                }
            }
        } else if (d == 2) {
            for (int r = 1; r <= N; r++) {
                for (int c = 2; c <= N; c++) {
                    move_func(r, c, d);
                }
            }
        } else if (d == 3) {
            for (int r = 2; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    move_func(r, c, d);
                }
            }
        }
    }

    static void move_func(int r, int c, int d) {
        int x = map[r][c][0];
        int nr = r;
        int nc = c;
        if (x == 0) {
            return;
        }
        while (true) {
            nr = nr + dir[d][0];
            nc = nc + dir[d][1];
            int y = map[nr][nc][0];
            if (y == 0) { // 이동하는 칸이 0인 경우
                if ((d == 0 && nc == N) || (d == 1 && nr == N) || (d == 2 && nc == 1) || (d == 3 && nr == 1)) { // 끝자리에 오면 그대로 옮겨주기
                    map[nr][nc][0] = map[r][c][0];
                    map[r][c][0] = 0;
                    break;
                } else { // 끝자리가 아니면 다음칸 탐색
                    continue;
                }
            } else { // 다른 숫자를 만났을 때
                if (map[nr][nc][1] == 0) { // 그 숫자가 합쳐지지 않은 숫자일 때
                    if (map[nr][nc][0] == map[r][c][0]) { // 합쳐질 수 있는 숫자이면 합쳐주기
                        map[nr][nc][0] = map[r][c][0] * 2;
                        map[r][c][0] = 0;
                        map[nr][nc][1] = 1;
                        ans = Math.max(map[nr][nc][0], ans); // 합쳐진 수에 대해 최댓값 갱신하기
                    } else { // 합쳐 질 수 있는 숫자가 아니라면
                        if ((nr - dir[d][0]) == r && (nc - dir[d][1]) == c) {
                            break;
                        }
                        map[nr - dir[d][0]][nc - dir[d][1]][0] = map[r][c][0]; // 그 숫자 전칸에 숫자를 옮겨주기
                        map[r][c][0] = 0;
                    }
                } else {
                    map[nr - dir[d][0]][nc - dir[d][1]][0] = map[r][c][0];
                    map[r][c][0] = 0;
                }
                break;
            }
        }
    }


    static void rec_func(int r) {
        if (r == 5) { // 최대 이동횟수 5번 조건 만족시 그만 이동하기
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[][][] map2 = new int[N + 1][N + 1][2];
            copy_map(map2, map);
            move(i);
            rec_func(r + 1);
            copy_map(map, map2);
        }
    }

    static void copy_map(int a[][][], int b[][][]) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                a[i][j][0] = b[i][j][0];
                a[i][j][1] = 0;
                b[i][j][1] = 0;
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1938 {
    static int N, ans = Integer.MAX_VALUE;
    static int map[][];
    static int log[][];
    static int goal[][];
    static boolean visited[][][];
    static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    static int mode; // 통나무가 가로인지 세로인지 1=가로(행) 2=세로(열)
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1][3];
        log = new int[3][2];
        goal = new int[3][2];
        int cnt = 0;
        int cnt2 = 0;
        String str;
        for (int i = 1; i <= N; i++) {
            str = br.readLine();
            for (int j = 1; j <= N; j++) {
                if (str.charAt(j-1) == 'B') {
                    log[cnt][0] = i;
                    log[cnt][1] = j;
                    cnt++;
                    map[i][j] = -1;
                } else if (str.charAt(j-1)== 'E') {
                    goal[cnt2][0] = i;
                    goal[cnt2][1] = j;
                    cnt2++;
                    map[i][j] = 99;
                } else {
                    map[i][j] = str.charAt(j - 1) - '0';
                }
            }
        }
        if (log[1][0] == log[2][0]) {
            mode = 1;
        } else {
            mode = 2;
        }
        bfs();
        if (ans == Integer.MAX_VALUE) {
            ans = 0;
        }
        System.out.println(ans);

    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        visited[log[1][0]][log[1][1]][mode] = true;
        q.add(new int[]{log[1][0], log[1][1], 0, mode});

        while (!q.isEmpty()) {
            int x[] = q.poll();
            int r = x[0];
            int c = x[1];
            int depth = x[2];
            int m = x[3];
            if (r==goal[1][0] && c==goal[1][1] && goal_check(r, c, m)) {
                ans = Math.min(ans, depth);
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (nr <= N && nr > 0 && nc <= N && nc > 0 && map[nr][nc] != 1) {
                    if (check(nr, nc, m)) {
                        if (!visited[nr][nc][m]) {
                            visited[nr][nc][m] = true;
                            q.add(new int[]{nr, nc, depth + 1, m});
                        }
                    }
                }
            }
            if (rotate(r, c)) {
                if (m == 1 && !visited[r][c][2]) {
                    visited[r][c][2] = true;
                    q.add(new int[]{r, c, depth + 1, 2});
                } else if (m == 2 && !visited[r][c][1]) {
                    visited[r][c][1] = true;
                    q.add(new int[]{r, c, depth + 1, 1});
                }
            }
        }


    }

    static boolean check(int r, int c, int m) {
        if (m == 2) {
            if (r + 1 <= N && r - 1 > 0 && map[r + 1][c] != 1 && map[r - 1][c] != 1) {
                return true;
            } else {
                return false;
            }
        } else {
            if (c + 1 <= N && c - 1 > 0 && map[r][c + 1] != 1 && map[r][c - 1] != 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    static boolean rotate(int r, int c) {
        for (int d = 0; d < 8; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            if (nr <= 0 || nr > N || nc <= 0 || nc > N || map[nr][nc] == 1) {
                return false;
            }
        }
        return true;
    }

    static boolean goal_check(int r, int c, int m) {
        if (m == 2) {
            if (r + 1 == goal[2][0] && r - 1 == goal[0][0]) {
                return true;
            }
        } else if (m == 1) {
            if (c - 1 == goal[0][1] && c + 1 == goal[2][1]) {
                return true;
            }
        }
        return false;

    }
}


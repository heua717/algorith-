import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1941 {
    static int ans, yCnt;
    static int map[][];
    static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int select[];
    static boolean visited[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String str;

    public static void main(String[] args) throws IOException {
        map = new int[5][5];
        select = new int[8];
        for (int i = 0; i < 5; i++) {
            str = br.readLine();
            for (int j = 0; j < 5; j++) {
                if (str.charAt(j) == 'Y') {
                    map[i][j] = 1;
                } else {
                    map[i][j] = 2;
                }
            }
        }
        select[0] = -1;
        comb(1, 0);
        System.out.println(ans);


    }

    static void comb(int r, int cnt) {
        if (r == 8) {
            visited = new boolean[7][7];
            if (check()) {
                ans++;
            }
            return;
        }

        for (int i = select[r - 1] + 1; i < 25; i++) {
            if (map[i / 5][i % 5] == 1) {
                cnt++;
            }
            if (cnt >= 4) {
                cnt--;
                continue;
            }
            select[r] = i;
            comb(r + 1, cnt);
            if (map[select[r] / 5][select[r] % 5] == 1) {
                cnt--;
            }
        }
    }

    static boolean check() {
        int cnt = 1;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 1; i < 8; i++) {
            int r = select[i] / 5;
            int c = select[i] % 5;
            visited[r][c] = true;
            if (i == 1) {
                q.add(new int[]{r, c});
                visited[r][c] = false;
            }
        }

        while (!q.isEmpty()) {
            int x[] = q.poll();
            int r = x[0];
            int c = x[1];
            for (int d = 0; d < 4; d++) {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5 && visited[nr][nc]) {
                    cnt++;
                    visited[nr][nc] = false;
                    q.add(new int[]{nr, nc});
                }
            }

        }
        if (cnt == 7) {
            return true;
        } else {
            return false;
        }


    }
}

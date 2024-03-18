import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1520 {
    static int N, M;
    static int map[][], dp[][];
    static int dir[][] = {{0, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }


        System.out.println(dfs(1, 1));


    }

    static int dfs(int r, int c) {
        if (r == N && c == M) {
            return 1;
        }
        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        dp[r][c] = 0;
        for (int k = 1; k <= 4; k++) {
            int nr = r + dir[k][0];
            int nc = c + dir[k][1];
            if (nr > 0 && nr <= N && nc > 0 && nc <= M && map[nr][nc] < map[r][c]) {
                dp[r][c] += dfs(nr, nc);
            }
        }
        return dp[r][c];
    }

}

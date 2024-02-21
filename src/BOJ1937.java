import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1937 {
    static int N, max;
    static int map[][][];
    static int delta[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static boolean visited[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N][2];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                map[i][j][0] = x;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }
        System.out.println(max);
    }

    static int dfs(int r, int c) {
        if (visited[r][c]) {
            return map[r][c][1]+1;
        }
        visited[r][c] = true;
        for (int k = 0; k < 4; k++) {
            int nr = r + delta[k][0];
            int nc = c + delta[k][1];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                if (map[nr][nc][0] > map[r][c][0]) {
                    map[r][c][1] = Math.max(map[r][c][1], dfs(nr, nc));
                }
            }
        }
        return map[r][c][1] + 1;
    }
}

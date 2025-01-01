import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ18405 {
    static int N, S, K, X, Y, ans;
    static int map[][];
    static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static ArrayList<int []> virus = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int x = Integer.parseInt(st.nextToken());
                map[i][j] = x;
                if (x != 0) {
                    virus.add(new int[]{i, j, x});
                }

            }
        }

        Collections.sort(virus, (v1, v2) -> v1[2] - v2[2]);
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        bfs();
        ans = map[X][Y];

        System.out.println(ans);
    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < virus.size(); ) {
            int x[] = virus.remove(0);
            q.add(new int[]{x[0], x[1], 0});
        }

        while (!q.isEmpty()) {
            int x[] = q.poll();
            int r = x[0];
            int c = x[1];
            int depth = x[2];
            if (depth == S) {
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (nr > 0 && nr <= N && nc > 0 && nc <= N && map[nr][nc] == 0) {
                    map[nr][nc] = map[r][c];
                    q.add(new int[]{nr, nc, depth + 1});
                }
            }


        }
    }
}

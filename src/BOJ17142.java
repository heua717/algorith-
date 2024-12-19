import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17142 {
    static int N, M, ans = Integer.MAX_VALUE, vCnt, cnt;
    static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int map[][], map2[][];
    static int selected[];
    static boolean visited[][];
    static boolean v[];
    static ArrayList<int[]> virus;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        virus = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int x = Integer.parseInt(st.nextToken());
                map[i][j] = x;

                if (x == 2) {
                    virus.add(new int[]{i, j, 0});
                    vCnt++;
                }
            }
        }
        v = new boolean[vCnt];
        selected = new int[M];
        comb(0, 0);
        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }
        System.out.println(ans);

    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            int[] x = virus.get(selected[i]);
            visited[x[0]][x[1]] = true;
            q.add(x);
        }
        // 더이상 바이러스가 복제되지 않을 때 까지 복제하기
        while (!q.isEmpty()) {
            int x[] = q.poll();
            int r = x[0];
            int c = x[1];
            cnt = Math.max(cnt, x[2]);
            for (int d = 0; d < 4; d++) {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (nr > 0 && nr <= N && nc > 0 && nc <= N && !visited[nr][nc] && map[nr][nc] != 1) {
                    if (map[nr][nc] == 0) {
                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc, x[2] + 1});
                        continue;
                    }
                    boolean isOk = false;
                    if (map[nr][nc] == 2) {
                        for (int i = 1; i <= N; i++) {
                            for (int j = 1; j <= N; j++) {
                                if (map[i][j] == 0 && !visited[i][j]) {
                                    isOk = true;
                                    i = N;
                                    j = N;
                                }
                            }
                        }
                    }
                    if (isOk) {
                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc, x[2] + 1});
                    }
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    return;
                }
            }
        }
        ans = Math.min(ans, cnt);
    }

    // 바이러스 갯수만큼 중복없이 뽑아주기
    static void comb(int r, int n) {
        // 활성할 바이러스 수 만큼 뽑았을 때 바이러스 복제 시작
        if (r == M) {
            visited = new boolean[N + 1][N + 1];
            cnt = 0;
            bfs();
            return;
        }

        for (int i = n; i < vCnt; i++) {
            if (v[i]) {
                continue;
            }
            selected[r] = i;
            v[i] = true;
            comb(r + 1, selected[r] + 1);
            v[i] = false;
        }
    }
}

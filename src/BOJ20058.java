import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20058 {
    static int N, Q, size, max, sum, ice, delCnt;
    static int map[][], map2[][];
    static int level[], del[][];
    static int delta[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static boolean visited[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        level = new int[Q + 1];
        size = (int) Math.pow(2, N);
        map = new int[size + 1][size + 1];
        map2 = new int[size + 1][size + 1];
        visited = new boolean[size + 1][size + 1];
        del = new int[size * size + 1][2];
        for (int i = 1; i <= size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= Q; i++) {
            level[i] = Integer.parseInt(st.nextToken());
            rotate(level[i]);
            copyArr();
            iceDel();
        }
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (map[i][j] > 0) {
                    ice = 0;
                    dfs(i, j);
                    max = Math.max(max, ice);
                    sum += map[i][j];
                }
            }
        }
        System.out.println(sum);
        System.out.println(max);


    }

    static void dfs(int r, int c) {
        if (visited[r][c]) {
            return;
        }
        visited[r][c] = true;
        for (int k = 0; k < 4; k++) {
            int nr = r + delta[k][0];
            int nc = c + delta[k][1];
            if (nr > 0 && nr <= size && nc > 0 && nc <= size && map[nr][nc] > 0) {
                dfs(nr, nc);
            }
        }
        ice++;
    }

    static void iceDel() {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = i + delta[k][0];
                    int nc = j + delta[k][1];
                    if (nr > 0 && nr <= size && nc > 0 && nc <= size && map[nr][nc] > 0) {
                        cnt++;
                    }
                }
                if (cnt < 3) {
                    delCnt++;
                    del[delCnt][0] = i;
                    del[delCnt][1] = j;
                }
            }
        }

        for (int i = 1; i <= delCnt; i++) {
            map[del[i][0]][del[i][1]]--;
        }
        delCnt = 0;
    }


    static void rotate(int lv) {
        int a = (int) Math.pow(2, lv);
        int b = (int) Math.pow(2, N) / a;
        for (int k = 1; k <= b * b; k++) {
            int x = k % b;
            if (x == 0) x = b;
            int y = (k - 1) / b + 1;
            for (int i = 1; i <= a; i++) {
                for (int j = 1; j <= a; j++) {
                    map2[(y - 1) * a + j][x * a + 1 - i] = map[(y - 1) * a + i][(x - 1) * a + j];
                }
            }
        }
    }

    static void copyArr() {
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                map[i][j] = map2[i][j];
            }
        }
    }
}

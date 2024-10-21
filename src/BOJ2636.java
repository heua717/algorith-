import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636 {
    static int N, M, cheese, ans;
    static int map[][];
    static boolean visited[][];
    static ArrayList<int[]> list = new ArrayList<>();
    static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                if (n == 1) {
                    list.add(new int[]{i, j});
                    cheese++;
                }
            }
        }
        if (cheese == 0) {
            System.out.println(0);
            System.out.println(0);
            return;
        }
        while (true) {
            visited = new boolean[N][M];
            dfs(0,0);
            ans++;
            int size = cheese;
            for (int i = 0; i < size; i++) {
                int n[] = list.remove(0);
                int r = n[0];
                int c = n[1];
                for (int k = 0; k < 4; k++) {
                    int nr = r + dir[k][0];
                    int nc = c + dir[k][1];
                    if (visited[nr][nc]) {
                        map[r][c] = 0;
                        cheese--;
                        break;
                    }
                }
                if (map[r][c] == 1) {
                    list.add(new int[]{r, c});
                }
            }
            if (cheese == 0) {
                System.out.println(ans);
                System.out.println(size);
                break;
            }

        }

    }



    static void dfs(int r, int c) {
        if (visited[r][c]) {
            return;
        }
        visited[r][c] = true;
        for (int k = 0; k < 4; k++) {
            int nr = r + dir[k][0];
            int nc = c + dir[k][1];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
                dfs(nr, nc);
            }
        }
    }
}

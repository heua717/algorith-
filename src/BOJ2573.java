import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573 {

    static int N, M, iceCnt, ans;
    static int map[][];
    static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static boolean visited[][];
    static ArrayList<int[]> list = new ArrayList<>();
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
                if (n != 0) {
                    list.add(new int[]{i, j, 0});
                }
            }
        }

        while (true) {
            ans++;
            ice_del();
            if (list.size() == 0) {
                System.out.println(0);
                break;
            }
            visited = new boolean[N][M];
            if (bfs() != list.size()) {
                System.out.println(ans);
                break;
            }
        }


    }

    static void ice_del() {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int r = list.get(i)[0];
            int c = list.get(i)[1];
            int delCnt = 0;
            for (int k = 0; k < 4; k++) {
                int nr = r + dir[k][0];
                int nc = c + dir[k][1];
                if (map[nr][nc] == 0) {
                    delCnt++;
                }
            }
            list.set(i, new int[]{r, c, delCnt});
        }
        int a = 0;
        while (true) {
            if (list.size() == 0 || list.size() == a) {
                break;
            }
            int i = list.get(a)[0];
            int j = list.get(a)[1];
            int n = list.get(a)[2];
            if (map[i][j] <= n) {
                map[i][j] = 0;
                list.remove(a);
                continue;
            } else {
                map[i][j] -= n;
                list.set(a, new int[]{i, j, 0});
                a++;
            }
        }
    }

    static int bfs() {
        int cnt = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(list.get(0));
        while (!q.isEmpty()) {
            int a[] = q.poll();
            int r = a[0];
            int c = a[1];
            visited[r][c] = true;
            for (int k = 0; k < 4; k++) {
                int nr = r + dir[k][0];
                int nc = c + dir[k][1];
                if (map[nr][nc] > 0 && !visited[nr][nc]) {
                    q.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                    cnt++;
                }
            }
        }
        return cnt;

    }
}

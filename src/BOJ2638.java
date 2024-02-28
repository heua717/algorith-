import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2638 {
    static int N, M, ans;
    static int map[][], cheese[][];
    static boolean visited[][];
    static int cheeseCnt, cheeseDel;
    static int delta[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static ArrayList<Integer> list = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        cheese = new int[N * M][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int x = Integer.parseInt(st.nextToken());
                map[i][j] = x;
                if (x == 1) {
                    cheese[cheeseCnt][0] = i;
                    cheese[cheeseCnt][1] = j;
                    cheeseCnt++;
                }
            }
        }
        air(0, 0);
        while (true) {
            ans++;
            cheese_del();
            for (int i = 0; i < list.size();) {
                int x = list.remove(i);
                int r = cheese[x][0];
                int c = cheese[x][1];
                air(r, c);
                cheeseDel++;
            }
            if (cheeseCnt - cheeseDel == 0) {
                break;
            }
        }
        System.out.println(ans);

    }

    static void air(int r, int c) {
        if (visited[r][c]) {
            return;
        }
        visited[r][c] = true;
        for (int k = 0; k < 4; k++) {
            int nr = r + delta[k][0];
            int nc = c + delta[k][1];
            if (nr >= 0 && nr < N && nc >= 0 && nc < M&& map[nr][nc] == 0) {
                air(nr, nc);
            }
        }

    }

    static void cheese_del() {
        for (int i = 0; i < cheeseCnt; i++) {
            int delCnt = 0;
            int r = cheese[i][0];
            int c = cheese[i][1];
            if (map[r][c] == 0) {
                continue;
            }
            for (int k = 0; k < 4; k++) {
                int nr = r + delta[k][0];
                int nc = c + delta[k][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && visited[nr][nc]) {
                    delCnt++;
                }
            }
            if (delCnt >= 2) {
                map[r][c]=0;
                list.add(i);
            }
        }
    }
}

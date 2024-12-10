import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16928 {
    static int N, M, ans = Integer.MAX_VALUE; //사다리의 수 N, 뱀의 수 M
    static int ladder[][], snake[][], map[][];
    static int dice[];
    static boolean visitied[];
//    static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[11][11];
        visitied = new boolean[111];
        dice = new int[7];
        ladder = new int[N + 1][2];
        snake = new int[M + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            ladder[i][0] = Integer.parseInt(st.nextToken());
            ladder[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            snake[i][0] = Integer.parseInt(st.nextToken());
            snake[i][1] = Integer.parseInt(st.nextToken());
        }
        bfs();
        System.out.println(ans);

    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 1, 0});

        while (!q.isEmpty()) {
            int n[] = q.poll();
            int r = n[0];
            int c = n[1];
            int depth = n[2];
            int x = (r - 1) * 10 + c%10;

            if (x >= 100) {
                ans = Math.min(depth, ans);
                continue;
            }
            for (int i = 1; i <= 6; i++) {
                dice[i] = x + i;
                if (dice[i] >= 100) {
                    dice[i] = 100;
                }
            }
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= 6; j++) {
                    if (ladder[i][0] == dice[j]) {
                        dice[j] = ladder[i][1];
                    }
                }
            }
            for (int i = 1; i <= M; i++) {
                for (int j = 1; j <= 6; j++) {
                    if (snake[i][0] == dice[j]) {
                        dice[j] = snake[i][1];
                    }
                }
            }
            for (int i = 1; i <= 6; i++) {
                int num = dice[i];
                if (visitied[num]) {
                    continue;
                }
                visitied[num] = true;
                int nr = num / 10 + 1;
                int nc = num % 10;
                if (nc == 0) {
                    nc = 10;
                }
                q.add(new int[]{nr, nc, depth + 1});

            }
        }
    }
}

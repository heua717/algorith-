import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569 {
    static int R, C, H, ans;
    static int dir[][] = {{1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 1, 0}, {0, 0, -1}, {0, -1, 0}}; // 위, 아래, 오른쪽, 앞, 왼쪽, 뒤
    static int tomato[][][];
    static boolean visited[][][];
    static Queue<Integer> q = new LinkedList<>();
    static Queue<Integer> q2 = new LinkedList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        tomato = new int[H + 1][R + 1][C + 1];
        visited = new boolean[H + 1][R + 1][C + 1];
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= R; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= C; k++) {
                    int t = Integer.parseInt(st.nextToken());
                    tomato[i][j][k] = t;
                    if (t == 1) {
                        q.add(i);
                        q.add(j);
                        q.add(k);
                        visited[i][j][k] = true;
                    }
                }
            }
        }

        if (ripe_tomato()) {
            System.out.println(ans);
        } else {
            while (true) {
                ans++;
                if (!q.isEmpty()) {
                    bfs();
                }
                while (!q2.isEmpty()) {
                    q.add(q2.poll());
                }
                if (ripe_tomato() || q.isEmpty()) {
                    break;
                }
            }
            if (ripe_tomato()) {
                System.out.println(ans);
            } else {
                System.out.println(-1);
            }
        }


    }
    static void bfs() {
        while (!q.isEmpty()) {
            int h = q.poll();
            int r = q.poll();
            int c = q.poll();
            for (int k = 0; k < 6; k++) {
                int nh = h + dir[k][0];
                int nr = r + dir[k][1];
                int nc = c + dir[k][2];
                if (nh > 0 && nh <= H && nr > 0 && nr <= R && nc > 0 && nc <= C && !visited[nh][nr][nc]) {
                    if (tomato[nh][nr][nc] == 0) {
                        visited[nh][nr][nc] = true;
                        tomato[nh][nr][nc] = 1;
                        q2.add(nh);
                        q2.add(nr);
                        q2.add(nc);
                    }
                }
            }

        }
    }

    static boolean ripe_tomato() {
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= R; j++) {
                for (int k = 1; k <= C; k++) {
                    if (tomato[i][j][k] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

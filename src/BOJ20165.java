import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20165 {
    static int N, M, R, ans, cnt;
    static int domino[][];
    static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // E = 0, S = 1, W = 2, N = 3
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        domino = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                domino[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= R; i++) {
            int d = -1;
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            String str = st.nextToken();
            if (str.equals("E")) {
                d = 0;
            } else if (str.equals("S")) {
                d = 1;
            } else if (str.equals("W")) {
                d = 2;
            } else {
                d = 3;
            }
            cnt = 0;
            domino_Attack(r, c, d);
            ans += cnt;
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            domino_Defence(r, c);
        }
        System.out.println(ans);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (domino[i][j] > 0) {
                    System.out.print("S ");
                } else {
                    System.out.print("F ");
                }
            }
            System.out.println();
        }

    }

    static void domino_Attack(int r, int c, int d) {
        int n = domino[r][c];
        if (n < 0) {
            return;
        }
        cnt++;
        domino[r][c] = -domino[r][c];
        for (int i = 1; i < n; i++) {
            int nr = r + (dir[d][0] * i);
            int nc = c + (dir[d][1] * i);
            if (nr > 0 && nr <= N && nc > 0 && nc <= M) {
                domino_Attack(nr, nc, d);
            }

        }


    }

    static void domino_Defence(int r, int c) {
        if (domino[r][c] < 0) {
            domino[r][c] = -domino[r][c];
        }
    }
}

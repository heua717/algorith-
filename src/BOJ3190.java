import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3190 {
    static int N, K, L, currD, snakeH = 1, snakeT = 1, ans;
    static int delta[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int map[][], snake[][];
    static int moveI[], moveD[];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        snake = new int[N * N + 1][2];
        K = Integer.parseInt(br.readLine());
        snake[snakeH][0] = 1;
        snake[snakeH][1] = 1;
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 1;
        }
        map[1][1] = -1;
        L = Integer.parseInt(br.readLine());
        moveI = new int[L + 1];
        moveD = new int[L + 1];
        for (int i = 1; i <= L; i++) {
            st = new StringTokenizer(br.readLine());
            moveI[i] = Integer.parseInt(st.nextToken());
            if (st.nextToken().trim().equals("D")) {
                moveD[i] = 1;
            } else {
                moveD[i] = -1;
            }
        }
        map[1][1] = -1;
//        for (int i = 1; i <= L; i++) {
//            System.out.println(moveD[i]);
//        }
        int nr;
        int nc;
        int num = 1;
        while (true) {
            int r = snake[snakeH][0];
            int c = snake[snakeH][1];
            if (num <= L && ans == moveI[num]) {
                currD = currD + moveD[num];
                if (currD == -1) {
                    currD = 3;
                }
                if (currD == 4) {
                    currD = 0;
                }
                num++;
            }
            ans++;
            nr = r + delta[currD][0];
            nc = c + delta[currD][1];
            if (nr > 0 && nr <= N && nc > 0 & nc <= N && map[nr][nc] != -1) {
                snakeH++;
                if (map[nr][nc] != 1) {
                    map[snake[snakeT][0]][snake[snakeT][1]] = 0;
                    snakeT++;
                }
                map[nr][nc] = -1;
                snake[snakeH][0] = nr;
                snake[snakeH][1] = nc;
            } else {
                break;
            }
        }
        System.out.println(ans);

    }
}


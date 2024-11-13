import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500 {
    static int N, M, cnt, max, ans;
    static int tetr[][];
    static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int tFunc[][] = {{0, 1, 0}, {1, 2, 1}, {2, 1, 2}, {1, 0, 1}, {0, 0, 1}, {1, 1, 2}, {2, 2, 3}, {3, 3, 0}, {0, 0, 3}, {1, 1, 0}, {2, 2, 1}, {3, 3, 2}, {0, 0, 0}, {1, 1, 1}, {0, 1, 2}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tetr = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                tetr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                tetr_func(i,j); // T미노를 제외한 나머지 미노들의 최대 합
                tetrT_func(i,j); // T미노 최대합 구하기
            }
        }

        System.out.println(ans);
    }

    static void tetrT_func(int r, int c) {
                for (int d = 0; d < 4; d++) {
                    cnt = 0 ;
                    max = tetr[r][c];
                    for (int k = 0; k < 3; k++) {
                        int x = (d + k) % 4;
                        int nr = r + dir[x][0];
                        int nc = c + dir[x][1];
                        if (nr > 0 && nr <= N && nc > 0 && nc <= M) {
                            max += tetr[nr][nc];
                            cnt++;
                        } else {
                            break;
                        }
                    }
                    if (cnt == 3) {
                        ans = Math.max(ans, max);
                    }

                }
    }


    static void tetr_func(int r, int c) {
                for (int k = 0; k < 15; k++) {
                    int nr = r;
                    int nc = c;
                    max = tetr[nr][nc];
                    cnt = 0;
                    for (int d = 0; d < 3; d++) {
                        nr = nr + dir[tFunc[k][d]][0];
                        nc = nc + dir[tFunc[k][d]][1];
                        if (nr > 0 && nr <= N && nc > 0 && nc <= M) {
                            cnt++;
                            max += tetr[nr][nc];
                        }
                    }
                    if (cnt == 3) {
                        ans = Math.max(ans, max);
                    }
                }
            }

}

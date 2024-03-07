import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17406 {
    static int N, M, K, rSum, min = Integer.MAX_VALUE;
    static int seleted[];
    static boolean visited[];
    static int map[][], map2[][], map3[][], rotate[][];
    static int dir[][] = {{0, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        map2 = new int[N + 1][M + 1];
        map3 = new int[N + 1][M + 1];
        seleted = new int[K + 1];
        visited = new boolean[K + 1];
        rotate = new int[K + 1][3];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            rotate[i][0] = Integer.parseInt(st.nextToken());
            rotate[i][1] = Integer.parseInt(st.nextToken());
            rotate[i][2] = Integer.parseInt(st.nextToken());
        }
        rec_func(1);
        System.out.println(min);

    }

    static void rec_func(int k) {
        if (k == K + 1) {
            copy_map(map, map2);
            copy_map(map2, map3);
            arr_rotate();
            r_sum();
            visited[seleted[k - 1]] = false;
            return;
        }
        for (int i = 1; i <= K; i++) {
            if (!visited[i]) {
                seleted[k] = i;
                visited[i] = true;
                rec_func(k + 1);
                visited[i] = false;
            }
        }
    }

    private static void arr_rotate() {
        for (int k = 1; k <= K; k++) {
            int x = seleted[k];
            int s = rotate[x][2];
            for (int a = 1; a <= s; a++) {
                for (int d = 1; d <= 4; d++) {
                    int r = rotate[x][0];
                    int c = rotate[x][1];
                    if (d == 1) {
                        r = r - a;
                        c = c - a;
                    } else if (d == 2) {
                        r = r - a;
                        c = c + a;
                    } else if (d == 3) {
                        r = r + a;
                        c = c + a;
                    } else if (d == 4) {
                        r = r + a;
                        c = c - a;
                    }
                    for (int i = 1; i <= a * 2; i++) {
                        int nr = r + dir[d][0];
                        int nc = c + dir[d][1];
                        map3[nr][nc] = map2[r][c];
                        r = nr;
                        c = nc;
                    }
                }
            }
            copy_map(map3, map2);
        }

    }


    //m2에 m1복사
    static void copy_map(int m1[][], int m2[][]) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                m2[i][j] = m1[i][j];
            }
        }
    }

    //배열 행 누적합 및 최솟값 갱신
    static void r_sum() {
        for (int i = 1; i <= N; i++) {
            rSum = 0;
            for (int j = 1; j <= M; j++) {
                rSum += map2[i][j];
            }
            min = Math.min(rSum, min);
        }
    }
}

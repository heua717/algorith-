import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17135 {
    static int N, M, D, monster, kill, max, rm;
    static int map[][], map2[][];
    static int dir[][] = {{0, -1}, {-1, 0}, {0, 1}};
    static int selected[];
    static int archer[], bfs[];
    static ArrayList<Integer> klist = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        selected = new int[M + 1];
        map = new int[N + 1][M + 1];
        map2 = new int[N + 1][M + 1];
        archer = new int[4];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    monster++;
                }
            }
        }

        rec_f(1);
        System.out.println(max);
    }

    static void copy_map() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map2[i][j] = map[i][j];
            }
        }
    }
    static void rec_f(int k) {
        if (k == 4) {
            copy_map();
            rm = 0;
            kill = 0;
            for (int i = 1; i <= 3; i++) {
                int x = selected[i];
                archer[i] = x;
            }
            defence();
            return;
        }

        for (int i = selected[k - 1] + 1; i <= M; i++) {
            selected[k] = i;
            rec_f(k + 1);
        }
    }

    static void defence() {
        while (rm + kill != monster) {
            attack();
            move();
        }
        max = Math.max(kill, max);
    }




    static void attack() {
        Queue<int[]> q = new LinkedList<>();
        for (int i = 1; i <= 3; i++) {
            q.add(new int[]{N, archer[i], 1});

            while (!q.isEmpty()) {
                int a[] = q.poll();
                int r = a[0];
                int c = a[1];
                int d = a[2];

                if (map2[r][c] == 1) {
                    klist.add(r);
                    klist.add(c);
                    q.clear();
                    break;
                }
                if (d >= D) {
                    continue;
                }
                for (int k = 0; k < 3; k++) {
                    int nr = r + dir[k][0];
                    int nc = c + dir[k][1];
                    if (nr > 0 && nc > 0 && nc <= M) {
                        if (map2[nr][nc] == 0) {
                            q.add(new int[]{nr, nc, d + 1});
                        } else if (map2[nr][nc] == 1) {
                            klist.add(nr);
                            klist.add(nc);
                            q.clear();
                            break;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < klist.size();) {
            int r = klist.remove(0);
            int c = klist.remove(0);
            if (map2[r][c] == 1) {
                kill++;
                map2[r][c] = 0;
            }
        }
    }

    static void move() {
        for (int i = 1; i <= M; i++) {
            if (map2[N][i] == 1) {
                rm++;
            }
        }
        for (int i = N; i > 1; i--) {
            for (int j = 1; j <= M; j++) {
                map2[i][j] = map2[i-1][j];
            }
        }
        for (int i = 1; i <= M; i++) {
            map2[1][i] = 0;
        }
    }



}

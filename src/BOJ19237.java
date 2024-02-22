import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ19237 {
    static int N, M, K, ans;
    static int shark[][];
    static int sharkMove[][][];
    static int sharkSmell[];
    static int map[][][];
    static List<int[]> list = new ArrayList<>();
    static List<int[]> sList = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int delta[][] = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static StringTokenizer st ;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        sharkSmell = new int[2];
        // 0 = 냄새남긴 상어 번호, 1 = 냄새 지워지기 까지 남은 수 2 = 현재 위치한 상어번호
        map = new int[N + 1][N + 1][3];
        // 2차원 배열 0,1 = r,c 2 = 번호, 3 = 방향
        shark = new int[M + 1][4];
        // sharkMove[i][j][k] = i번째 상어가 j방향을 보고 있을 때 우선순위 방향 k
        sharkMove = new int[M + 1][5][5];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x != 0) {
                    shark[x][0] = i;
                    shark[x][1] = j;
                    shark[x][2] = x;
                }
                map[i][j][2] = x;
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            shark[i][3] = Integer.parseInt(st.nextToken());
            list.add(shark[i][2]-1,shark[i]);
        }
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= 4; k++) {
                    sharkMove[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        while (true) {
            if (ans > 1000 || list.size() == 1) {
                break;
            }
            // 냄새 추가
            addSmell();
            moveShark();
            delSmell();
            ans++;
        }

        if (ans > 1000) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }

    }

    static void moveShark() {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int a[] = list.remove(0);
            int nr = 0, nc = 0, d2 = 0;
            int r = a[0];
            int c = a[1];
            int x = a[2];
            int d = a[3];
            for (int k = 1; k <= 4; k++) {
                d2 = sharkMove[x][d][k];
                nr = r + delta[d2][0];
                nc = c + delta[d2][1];
                // 냄새가 없는 빈칸
                if (isGo(nr, nc)) {
                    break;
                } else if (k == 4) {
                    for (int l = 1; l <= 4; l++) {
                        d2 = sharkMove[x][d][l];
                        nr = r + delta[d2][0];
                        nc = c + delta[d2][1];
                        // 냄새가 있을 때 자기 냄새일 경우
                        if (isGo2(nr, nc, x)) {
                            break;
                        }
                    }
                }
            }

            if (map[nr][nc][2] != 0 && map[nr][nc][2] < x) {
                map[r][c][2] = 0;
                continue;
            } else if (map[nr][nc][2] == 0 || map[nr][nc][2] > x) {
                list.add(new int[]{nr, nc, x, d2});
                map[nr][nc][2] = x;
                map[r][c][2] = 0;

            }

        }
    }

    static void addSmell() {
        int size = list.size();
        for (int a = 0; a < size; a++) {
            int s[] = list.get(a);
            int r = s[0];
            int c = s[1];
            int x = s[2];
            if (map[r][c][1] == 0) {
                sList.add(new int[]{r, c});
            }
            map[r][c][0] = x;
            map[r][c][1] = K;
        }
    }

    static void delSmell() {
        for (int i = 0; i < sList.size(); i++) {
            int a[] = sList.get(i);
            int r = a[0];
            int c = a[1];
            map[r][c][1]--;
            if (map[r][c][1] == 0) {
                sList.remove(i--);
                map[r][c][0] = 0;
            }
        }
    }



    static boolean isGo(int r, int c) {
        if (r > 0 && r <= N && c > 0 && c <= N && map[r][c][1] == 0) {
            return true;
        } else {
            return false;
        }
    }

    static boolean isGo2(int r, int c, int x) {
        if (r > 0 && r <= N && c > 0 && c <= N && map[r][c][0] == x) {
            return true;
        } else {
            return false;
        }
    }


}

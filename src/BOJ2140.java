import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2140 {
    static int N , ans;
    static int mine[][];
    static int dir[][] = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    static ArrayList<int[]> list = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String str;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        if (N < 3) {
            System.out.println(0);
            return;
        }
        mine = new int[N][N];

        for (int i = 0; i < N; i++) {
            str = br.readLine();
            for (int j = 0; j < N; j++) {
                String s[] = str.split("");
                if (s[j].equals("#")) {
                    mine[i][j] = -1;
                } else {
                    mine[i][j] = Integer.parseInt(s[j]);
                    list.add(new int[]{i, j});
                }
            }
        }
        // -99는 지뢰 -98는 안전한 곳
//        if (mine[0][0] == 1) { // 왼쪽 상단 체크
//            mine[1][1] = -99;
//        } else {
//            mine[1][1] = -98;
//        }
//        if (mine[0][N - 1] == 1) { // 오른쪽 상단 체크
//            mine[1][N - 2] = -99;
//        } else {
//            mine[1][N - 2] = -98;
//        }
//        if (mine[N - 1][0] == 1) { // 왼쪽 하단 체크
//            mine[N - 2][1] = -99;
//        } else {
//            mine[N - 2][1] = -98;
//        }
//        if (mine[N - 1][N - 1] == 1) { // 오른쪽 하단 체크
//            mine[N - 2][N - 2] = -99;
//        } else {
//            mine[N - 2][N - 2] = -98;
//        }

        for (int i = 0; i < list.size(); ) {
            int n[] = list.remove(i);
            mine_search(n[0], n[1]);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (mine[i][j] == -99) {
                    ans++;
                }
            }
        }



        if (N < 5) {
            System.out.println(ans);
        } else {
            ans = ans + ((N - 4) * (N - 4));
            System.out.println(ans);
        }
    }

    static void mine_search(int r, int c) {
        if (mine[r][c] == 3) {
            for (int d = 0; d < 8; d++) {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }
                if (mine[nr][nc] == -1) {
                    mine[nr][nc] = -99;
                }
            }
        }
        if (mine[r][c] == 0) {
            for (int d = 0; d < 8; d++) {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }
                if (mine[nr][nc] == -1) {
                    mine[nr][nc] = -98;
                }
            }
        }
        if (mine[r][c] == 1) {
            boolean isMine = false;
            for (int d = 0; d < 8; d++) {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || mine[nr][nc]>=0) {
                    continue;
                }
                if (mine[nr][nc] == -1 && !isMine) {
                    mine[nr][nc] = -99;
                    isMine = true;
                    continue;
                } else if(mine[nr][nc]==-1 && isMine){
                    mine[nr][nc] = -98;
                    continue;
                }
                if (mine[nr][nc] == -99) {
                    isMine=true;
                }


            }
        }

        if (mine[r][c] == 2) {
            int cnt = 0;
            for (int d = 0; d < 8; d++) {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || mine[nr][nc]>=0) {
                    continue;
                }
                if (mine[nr][nc] == -99) {
                    cnt++;
                    continue;
                }
                if (mine[nr][nc] == -1 && cnt == 2) {
                    mine[nr][nc] = -98;
                } else if (mine[nr][nc] == -1 && cnt == 1) {
                    mine[nr][nc] = -99;
                }
            }
        }
    }

}

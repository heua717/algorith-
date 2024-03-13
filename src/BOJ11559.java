import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11559 {
    static int ans, puyo;
    static boolean isPuyo;
    static char map[][], map2[][];
    static boolean visited[][];
    static ArrayList<Integer> list = new ArrayList<>();
    static int delta[][] = {{0, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        map = new char[12][6];
        visited = new boolean[12][6];
        for (int i = 0; i <= 11; i++) {
            map[11 - i] = br.readLine().toCharArray();
        }


        while (true) {
            isPuyo = false;
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] == '.') {
                        visited[i][j] = true;
                    } else {
                        visited[i][j] = false;
                    }
                }
            }
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (!visited[i][j]) {
                        if (dfs(i, j, 1) >= 4) {

                            while (list.size() != 0) {
                                map[list.remove(0)][list.remove(0)] = '.';
                            }

                            isPuyo = true;
                        } else {
                            list.clear();
                        }
                    }
                }
            }
            if (isPuyo) {
                puyo++;
            } else {
                break;
            }
            down_puyo();
        }
        System.out.println(puyo);


    }

    static int dfs(int r, int c, int cnt) {
        list.add(r);
        list.add(c);
        visited[r][c] = true;
        for (int k = 1; k <= 4; k++) {
            int nr = r + delta[k][0];
            int nc = c + delta[k][1];
            if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6 && !visited[nr][nc] && map[r][c] == map[nr][nc]) {
                cnt = dfs(nr, nc, cnt + 1);
            }
        }

        return cnt;
    }

    static void down_puyo() {
        for (int i = 1; i <= 11; i++) {
            for (int j = 0; j <= 5; j++) {
                if (map[i][j] != '.') {
                    for (int k = i - 1; k >= 0; k--) {
                        if (map[k][j] == '.') {
                            map[k][j] = map[k + 1][j];
                            map[k + 1][j] = '.';
                        }
                    }
                }
            }
        }
    }
}

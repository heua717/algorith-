import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2096 {
    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int map[][], dpmax[][], dpmin[][];
    static int delta[][] = {{1, -1}, {1, 0}, {1, 1}};
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][3];
        dpmax = new int[N][3];
        dpmin = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dpmin[i][j] = Integer.MAX_VALUE;
            }
        }
        dpmax[0][0] = map[0][0];
        dpmin[0][0] = map[0][0];
        dpmax[0][1] = map[0][1];
        dpmin[0][1] = map[0][1];
        dpmax[0][2] = map[0][2];
        dpmin[0][2] = map[0][2];

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    for (int k = 1; k < 3; k++) {
                        int nr = i + delta[k][0];
                        int nc = j + delta[k][1];
                        dpmax[nr][nc] = Math.max(dpmax[nr][nc], dpmax[i][j] + map[nr][nc]);
                        dpmin[nr][nc] = Math.min(dpmin[nr][nc], dpmin[i][j] + map[nr][nc]);
                    }
                } else if (j == 1) {
                    for (int k = 0; k < 3; k++) {
                        int nr = i + delta[k][0];
                        int nc = j + delta[k][1];
                        dpmax[nr][nc] = Math.max(dpmax[nr][nc], dpmax[i][j] + map[nr][nc]);
                        dpmin[nr][nc] = Math.min(dpmin[nr][nc], dpmin[i][j] + map[nr][nc]);

                    }
                } else if (j == 2) {
                    for (int k = 0; k < 2; k++) {
                        int nr = i + delta[k][0];
                        int nc = j + delta[k][1];
                        dpmax[nr][nc] = Math.max(dpmax[nr][nc], dpmax[i][j] + map[nr][nc]);
                        dpmin[nr][nc] = Math.min(dpmin[nr][nc], dpmin[i][j] + map[nr][nc]);
                    }
                }
            }
        }
        int max = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, dpmax[N - 1][i]);
            min = Math.min(min, dpmin[N - 1][i]);
        }
        System.out.println(max + " " + min);

    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17404 {
    static int N, ans = Integer.MAX_VALUE;
    static int RGB[][], dp[][][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        RGB = new int[N + 1][4];
        dp = new int[N + 1][4][4];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                RGB[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) {
                    dp[i][j][k] = 1000 * 1000;
                }
            }
        }
        dp[2][1][2] = RGB[1][1] + RGB[2][2];
        dp[2][1][3] = RGB[1][1] + RGB[2][3];
        dp[2][2][1] = RGB[1][2] + RGB[2][1];
        dp[2][2][3] = RGB[1][2] + RGB[2][3];
        dp[2][3][1] = RGB[1][3] + RGB[2][1];
        dp[2][3][2] = RGB[1][3] + RGB[2][2];

        for (int i = 3; i <= N; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) {
                    for (int l = 1; l <= 3; l++) {
                        if (k == l) {
                            continue;
                        }
                        if (i == N && j == k) {
                            continue;
                        }
                        dp[i][j][k] = Math.min(dp[i - 1][j][l] + RGB[i][k], dp[i][j][k]);

                    }
                }
            }
        }
//        for (int i = 1; i <= 3; i++) {
//            for (int j = 1; j <= 3; j++) {
//                if (i == j) {
//                    continue;
//                }
//                for (int k = 1; k <= 3; k++) {
//                    if (k == j) {
//                        continue;
//                    }
//                    dp[N][i][j] = Math.min(dp[N][i][j], dp[N - 1][i][k] + RGB[N][j]);
//                    ans = Math.min(dp[N][i][j], ans);
//                }
//            }
//        }
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i == j) {
                    continue;
                }
                ans = Math.min(ans, dp[N][i][j]);
            }
        }

        System.out.println(ans);




    }
}

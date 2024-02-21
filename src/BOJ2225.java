import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2225 {
    static int N, K, ans;
    static long result;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long dp[][];
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new long[K + 1][N + 1];


        for (int i = 0; i <= N; i++) {
            dp[1][i] = 1;
        }
        for (int i = 1; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k];
                    dp[i][j] = dp[i][j] % 1000000000;
                }
            }
        }

        System.out.println(dp[K][N]);

    }
}

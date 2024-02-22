import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BOJ4811 {
    static int N;
    static long dp[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        dp = new long[32][32];
        for (int i = 1; i <=31; i++) {
            dp[0][i] = 1;
        }
        int a = 30;
        for (int i = 1; i <= 30; i++) {
            for (int j = 1; j <= 30; j++) {
                if (j < 31) {
                    dp[i][j] = dp[i - 1][j + 1] + dp[i][j - 1];
                }
            }
        }
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                return;
            }
            System.out.println(dp[N][1]);

        }

    }
}

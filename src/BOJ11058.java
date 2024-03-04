import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11058 {
    static int N;
    static long[] dp, select, buff;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1];
        select = new long[N + 1];
        buff = new long[N + 1];
        if (N < 4) {
            System.out.println(N);
            return;
        }
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        select[2] = 1;
        select[3] = 2;
        buff[3] = 1;
        buff[4] = 2;
        for (int i = 4; i <= N; i++) {
            for (int j = 1; j <= i - 3; j++) {
                int b = j + 2;
                int x = i - b;
                dp[i] = Math.max(Math.max(dp[i], dp[j] + buff[b] * x),dp[i-1]+1);
            }
            select[i] = dp[i - 1];
            buff[i] = select[i - 1];
        }

        System.out.println(dp[N]);

    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9084 {
    static int T, N , M;
    static int coin[], dp[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            coin = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());
            dp = new int[10001];
            for (int i = coin[1]; i <= M; i=i+coin[1]) {
                dp[i] = 1;
            }
            for (int i = 2; i <= N; i++) {
                int n = coin[i];
                dp[n]++;
                for (int j = n ; j <= M; j++) {
                    if (j < n) {
                        continue;
                    }
                    dp[j] = dp[j] + dp[j - n];

                    }
            }

            sb.append(dp[M]).append('\n');
        }
        System.out.println(sb);
    }
}

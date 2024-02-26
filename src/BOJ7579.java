import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7579 {
    static int N, M, memory, min = Integer.MAX_VALUE;
    static int A[], C[];
    static int dp[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N + 1];
        C = new int[N + 1];
        for (int i = 1; i <= 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (i == 1) {
                    A[j] = Integer.parseInt(st.nextToken());
                } else {
                    C[j] = Integer.parseInt(st.nextToken());
                    memory += C[j];
                }
            }
        }
        dp = new int[N + 1][memory + 1];
        for (int i = 1; i <= N; i++) {
            dp[i][C[i]] = A[i];
        }
        for (int i = 1; i <= N; i++) {
            int a = A[i];
            int c = C[i];
            for (int j = 0; j <= memory; j++) {
                if (j >= c) {
                    dp[i][j] = Math.max(dp[i - 1][j - c] + a,dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                };

                if (dp[i][j] >= M) {
                    min = Math.min(min, j);
                }
            }
        }
        System.out.println(min);






    }
}

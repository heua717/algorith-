import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14728 {
    static int N, T;
    static int k[], s[], dp[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        k = new int[N + 1];
        s = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            k[i] = Integer.parseInt(st.nextToken());
            s[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N + 1][T + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= T; j++) {
                if (k[i] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - k[i]] + s[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N][T]);


    }
}

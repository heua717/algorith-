import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5557 {
    static int N, c1, c2;
    static long ans;
    static int arr[];
    static long dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new long[N + 1][21];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        ans = arr[N];
        dp[1][arr[1]] = 1;
        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 20; j++) {
                c1 = j + arr[i];
                c2 = j - arr[i];
                if (c1 <= 20) {
                    dp[i][j] += dp[i - 1][c1];
                }
                if (c2 >= 0) {
                    dp[i][j] += dp[i - 1][c2];
                }
            }
        }
        System.out.println(dp[N - 1][(int)ans]);
    }
}

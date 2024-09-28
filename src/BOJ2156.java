import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2156 {
    static int N, ans;
    static int arr[], dp[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        if (N == 1) {
            System.out.println(arr[1]);
            return;
        }

        if (N == 2) {
            System.out.println(arr[1] + arr[2]);
            return;
        }

        dp[1][1] = arr[1];
        dp[2][0] = arr[1];
        dp[2][1] = arr[2];
        dp[2][2] = arr[1] + arr[2];

        for (int i = 3; i <= N; i++) {
            dp[i][2] = dp[i - 1][1] + arr[i];
            dp[i][1] = dp[i - 1][0] + arr[i];
            dp[i][0] = Math.max(dp[i - 1][1], Math.max(dp[i - 1][2],dp[i-1][0]));
        }
        if (N > 2) {
            ans = Math.max(dp[N][0], Math.max(dp[N][1], dp[N][2]));
        }
        System.out.println(ans);

    }
}

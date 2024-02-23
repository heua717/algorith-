import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2616 {
    static int N, num, max;
    static int arr[], sum[], dp[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        num = Integer.parseInt(br.readLine());
        int size = N - num + 1;
        sum = new int[size+1];
        dp = new int[4][size + 1];
        for (int i = 1; i <= num; i++) {
            sum[1] += arr[i];
        }
        for (int i = 2; i <= size; i++) {
            sum[i] = sum[i - 1] - arr[i - 1] + arr[num + i - 1];
        }

//        for (int i = 1; i <= size; i++) {
//            System.out.println(sum[i]);
//        }
        for (int i = 1; i <= size; i++) {
            dp[1][i] = Math.max(sum[i], dp[1][i-1]);
        }
        for (int i = 2; i <= 3; i++) {
            for (int j = 1+num; j <= size; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - num] + sum[j]);
            }
        }
        System.out.println(dp[3][size]);
    }
}

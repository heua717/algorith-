import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2056 {
    static int N, max;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int arr[][], dp[][];
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][102];
        dp = new int[N + 1][1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cnt =0;
            while (st.countTokens() > 0) {
                arr[i][cnt++] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= N; i++) {
            dp[i][0] = arr[i][0];
            max = Math.max(dp[i][0], max);
        }
//        for (int i = 0; i <= N; i++) {
//            for (int j = 0; j < 102; j++) {
//                System.out.print(arr[i][j]);
//            }
//            System.out.println();
//        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= arr[i][1]; j++) {
                dp[i][0] = Math.max(dp[i][0], arr[i][0] + dp[arr[i][j+1]][0]);
                max = Math.max(dp[i][0], max);
            }
        }
//        for (int i = 1; i <= N; i++) {
//            System.out.println(dp[i][0]);
//        }
        System.out.println(max);


    }
}

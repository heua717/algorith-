import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10942 {
    static int N, M ,S, E;
    static int arr[];
    static boolean dp[][];
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new boolean[N + 1][N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i][i] = true;
        }
        for (int i = N - 1; i >= 1; i--) {
            for (int j = i + 1; j <= N; j++) {
                if (i - j == -1) {
                    if (arr[i] == arr[j]) {
                        dp[i][j] = true;
                    }
                } else {
                    if (arr[i] == arr[j] && dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                    }
                }
            }
        }
        M = Integer.parseInt(br.readLine());
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            if (dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]) {
                sb.append(1).append('\n');
            } else {
                sb.append(0).append('\n');
            }
        }
        System.out.println(sb);


    }


}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14267 {
    static int N, M;
    static int comp[], rank[], dp[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        rank = new int[N + 1];
        dp = new int[N + 1];
        comp = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            rank[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            comp[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
        }
        dp[1] = 0;
        dp[2] = comp[2];
        sb.append(dp[1]).append(' ').append(dp[2]).append(' ');
        for (int i = 3; i <= N; i++) {
            dp[i] = comp[i] + dp[rank[i]];
            sb.append(dp[i]).append(' ');
        }
        System.out.println(sb);

    }
}

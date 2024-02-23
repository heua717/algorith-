import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ14567 {
    static int N, M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int dp[];
    static ArrayList<Integer> list[];

    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            dp[i] = 1;
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[b].add(a);
        }
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < list[i].size(); j++) {
                dp[i] = Math.max(dp[i], dp[list[i].get(j)] + 1);
            }
        }
        for (int i = 1; i <= N; i++) {
            System.out.print(dp[i]+" ");
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int C, N;
    static int value[], dp[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        value = new int[101];
        dp = new int[100001];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int incr = Integer.parseInt(st.nextToken());
            value[cost] = Math.max(incr, value[cost]);
            dp[cost] = value[cost];
        }
        int i = 0;
        while (true) {
            i++;
            for (int j = 1; j <= i/2; j++) {
                dp[i] = Math.max(dp[i - j] + dp[j], dp[i]);
            }
            if (dp[i] >= C) {
                break;
            }
        }

        System.out.println(i);




    }
}

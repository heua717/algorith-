import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2629 {
    static int wCnt, bCnt, sum;
    static int weight[], beads[], dp[][], dp2[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        wCnt = Integer.parseInt(br.readLine());
        weight = new int[wCnt + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= wCnt; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
            sum += weight[i];
        }
        bCnt = Integer.parseInt(br.readLine());
        beads = new int[bCnt + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= bCnt; i++) {
            beads[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[wCnt + 1][sum + 1];
        dp2 = new int[sum + 1];
        for (int i = 0; i <= wCnt; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= wCnt; i++) {
            int w = weight[i];
            for (int j = 0; j <= sum; j++) {
                if (j < w) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - w], dp[i - 1][j]);
                }
            }
        }
        for (int i = 1; i <= bCnt; i++) {
            int b = beads[i];
            for (int j = 1; j <= sum; j++) {
                if (dp[wCnt][j] == 1) {
                    if (j == b) {
                        sb.append("Y ");
                        break;
                    }
                    if (j + b <= sum) {
                        if (dp[wCnt][j + b] == 1) {
                            sb.append("Y ");
                            break;
                        }
                    }
                }
                if (j == sum) {
                    sb.append("N ");
                }
            }
        }


        System.out.println(sb);



    }
}

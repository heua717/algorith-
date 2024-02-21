import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2240 {
    static int T, W, plumCnt = 1, max;
    static int plumsum[][], dp[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        // W번 이동했을 때 T번째 자두의 누적 최댓 값
        dp = new int[W + 1][T + 1];
        // T번째에 받을 수 있는 자두 수(0) 와 자두나무 번호(1)
        plumsum = new int[T+1][2];

        int curr = Integer.parseInt(br.readLine());
        // plumcnt 는 1부터
        plumsum[plumCnt][0]++;
        plumsum[plumCnt][1] = curr;
        for (int i = 1; i < T; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == curr) {
                plumsum[plumCnt][0]++;
            } else if (x != curr) {
                curr = x;
                plumCnt++;
                plumsum[plumCnt][0]++;
                plumsum[plumCnt][1] = x;
            }

        }
//        for (int i = 1; i <= plumCnt; i++) {
//            System.out.println(plumsum[i][0] + " == " + plumsum[i][1]);
//        }
//        for (int i = 1; i <= plumCnt; i++) {
//            System.out.println(plumsum[i]);
//        }
//        System.out.println(plumCnt);
        if (plumsum[1][1] == 1) {
            dp[0][1] = plumsum[1][0];
        } else {
            dp[1][1] = plumsum[1][0];
        }
        max = Math.max(dp[0][1], dp[1][1]);
        for (int i = 2; i <= plumCnt; i++) {
            if (plumsum[i][1] == 1) {
                dp[0][i] = dp[0][i - 1] + plumsum[i][0];
            } else {
                dp[0][i] = dp[0][i - 1];
            }
            for (int j = 1; j <= W; j++) {
                int x = plumsum[i][1];
                if (j % 2 == 1) {
                    if (x == 2) {
                        dp[j][i] = Math.max(dp[j - 1][i - 1] + plumsum[i][0], dp[j][i - 1] + plumsum[i][0]);
                    } else {
                        dp[j][i] = Math.max(dp[j - 1][i], dp[j][i - 1]);
                    }
                } else {
                    if (x == 1) {
                        dp[j][i] = Math.max(dp[j - 1][i - 1] + plumsum[i][0], dp[j][i - 1] + plumsum[i][0]);
                    } else {
                        dp[j][i] = Math.max(dp[j - 1][i], dp[j][i - 1]);
                    }
                }
                max = Math.max(dp[j][i], max);
            }
        }
        System.out.println(max);


    }
}

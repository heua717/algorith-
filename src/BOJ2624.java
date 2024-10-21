import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ2624 {
    static int T, K, max ,min;
    static int coin[][], dp[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        coin = new int[K + 1][2];
        dp = new int[K + 1][T + 1];

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            coin[i][0] = Integer.parseInt(st.nextToken());
            coin[i][1] = Integer.parseInt(st.nextToken());
            dp[i][0] = 1;
        }

        Arrays.sort(coin, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]){
                    return o1[1] - o2[1];
                }else{
                    return o1[0] - o2[0];
                }
            }
        });
        for (int i = 1; i <= coin[1][1]; i++) {
            if (coin[1][0] * i > T) {
                break;
            }
            dp[1][coin[1][0] * i]++;

        }

        for (int k = 2; k <= K; k++) {
            int c = coin[k][0];
            int cnt = coin[k][1];
            for (int m = 1; m <= T; m++) {
                for (int i = 0; i <= cnt; i++) {
                    if (m < (c * i)) {
                        break;
                    }
                    dp[k][m] = dp[k][m] + dp[k - 1][m - (c * i)];
                }
            }
        }
        System.out.println(dp[K][T]);


        }
    }


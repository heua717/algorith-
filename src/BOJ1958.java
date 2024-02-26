import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1958 {
    static String str1, str2, str3;
    static int dp[][][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        str1 = br.readLine();
        str2 = br.readLine();
        str3 = br.readLine();
        int s1 = str1.length();
        int s2 = str2.length();
        int s3 = str3.length();
        char c1[] = str1.toCharArray();
        char c2[] = str2.toCharArray();
        char c3[] = str3.toCharArray();
        dp = new int[s1 + 1][s2 + 1][s3 + 1];
        for (int i = 0; i < s2; i++) {
            for (int j = 0; j < s3; j++) {
                if (c1[0] == c2[i] && c1[0] == c3[j]) {
                    dp[1][i + 1][j + 1] = 1;
                } else {
                    dp[1][i + 1][j + 1] = Math.max(dp[1][i][j + 1], dp[1][i+1][j]);
                }
            }
        }
        for (int i = 1; i < s1; i++) {
            for (int j = 0; j < s2; j++) {
                for (int k = 0; k < s3; k++) {
                    if (c1[i] == c2[j] && c1[i] == c3[k]) {
                        dp[i + 1][j + 1][k + 1] = dp[i][j][k] + 1;
                    } else {
                        dp[i + 1][j + 1][k + 1] = Math.max(dp[i + 1][j + 1][k], dp[i + 1][j][k + 1]);
                        dp[i + 1][j + 1][k + 1] = Math.max(dp[i + 1][j + 1][k + 1], dp[i][j + 1][k + 1]);
                    }
                }
            }
        }

        System.out.println(dp[s1][s2][s3]);
    }
}

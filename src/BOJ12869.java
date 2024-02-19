import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12869 {
    static int N, ans, min=Integer.MAX_VALUE;
    static int delta[][] = {{0, 0, 0}, {9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int arr[], dp[][][];
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        if (N == 1) {
            if (arr[1] % 9 == 0) {
                ans = arr[1] / 9;
            } else {
                ans = arr[1] / 9 + 1;
            }
        } else if (N == 2) {
            while (true) {
                ans++;
                if (arr[1] > arr[2]) {
                    arr[1] -= 9;
                    arr[2] -= 3;
                } else {
                    arr[2] -= 9;
                    arr[1] -= 3;
                }
                if (arr[1] <= 0 && arr[2] <= 0) {
                    break;
                }
            }
        } else {
            while (true) {
                if (arr[1] >= 25 && arr[2] >= 25 && arr[3] >= 25) {
                    arr[1] -= 13;
                    arr[2] -= 13;
                    arr[3] -= 13;
                    ans += 3;
                } else {
                    break;
                }
            }
            for (int i = 1; i <= 6; i++) {
                dfs(arr[1],arr[2],arr[3], i, 1);
            }
            ans += min;
        }

        System.out.println(ans);
    }

    static void dfs(int a,int b, int c, int num, int depth) {
        a -= delta[num][0];
        b -= delta[num][1];
        c -= delta[num][2];
        if (a <= 0 && b <= 0 && c <= 0) {
            min = Math.min(depth, min);
            return;
        }
        for (int k = 1; k <= 6; k++) {
            if (min <= depth + 1) {
                continue;
            }
            dfs(a,b,c, k, depth + 1);
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14002 {
    static int N, max, idx;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int arr[], dp[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (arr[i] > arr[j]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
            if (max < dp[i]) {
                max = dp[i];
                idx = i;
            }
        }
        System.out.println(max);
        LinkedList<Integer> l = new LinkedList<>();
        l.addFirst(arr[idx]);
        for (int i = idx; i > 0; i--) {
            if (max-1 == dp[i]) {
                max--;
                l.addFirst(arr[i]);
                if (max == 0) {
                    break;
                }
            }
        }
        for (int i = 0; i < l.size(); i++) {
            System.out.print(l.get(i)+" ");
        }




    }
}

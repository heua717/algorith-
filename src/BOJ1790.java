import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1790 {
    static int N, K, max, prev;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if (N <= 9) {
            if (K <= N) {
                System.out.println(K);
                return;
            } else if (K > N) {
                System.out.println(-1);
                return;
            }
        }
        max = 9;

        for (int i = 2; i <= 9; i++) {
            if (N < Math.pow(10, i)) {
                max += (N - Math.pow(10, (i - 1)) + 1) * i;
                break;
            } else {
                max += i * Math.pow(10, (i - 1)) * 9;
            }
        }
        if (K < 10 && N >= 9) {
            System.out.println(K);
            return;
        }
        if (K > max) {
            System.out.println(-1);
            return;
        }
        if (K >= 788888890) {
            if (K == 788888890) {
                System.out.println(1);
                return;
            } else {
                System.out.println(0);
                return;
            }

        }



        prev = 9;
        for (int i = 2; i <= 9; i++) {
            prev += Math.pow(10, (i - 1)) * i * 9;

            if (K <= prev) {
                prev -= Math.pow(10, (i - 1)) * i * 9;
                System.out.print(func(i));
                break;
            }
        }


    }

    static int func(int n) {
        int ans = 0;
        int x = K - prev;
        x = x % (10 * n);
        if (x % n == 0) {
            ans = x / n - 1;
            if (ans == -1) {
                ans = 9;
            }
        } else if (x % n == 1) {
            ans = (K - prev) / ((int) Math.pow(10, n - 1) * n) + 1;
        } else {
            ans = (K - prev) / ((int) Math.pow(10, n - (x % n)) * n);
            ans = ans % 10;
        }

        return ans;
    }
}

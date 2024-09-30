import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16987 {
    static int N, max, crack;
    static int egg[][];
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        egg = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            egg[i][0] = Integer.parseInt(st.nextToken());
            egg[i][1] = Integer.parseInt(st.nextToken());
        }
        if (N == 1) {
            System.out.println(0);
            return;
        }

        rec_func(1);
        System.out.println(max);

    }

    static void rec_func(int n) {
        if (n == N + 1 || crack == N || check(n)) {
            max = Math.max(max, crack);
            return;
        }
        if (egg[n][0] <= 0) {
            rec_func(n + 1);
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (i == n) {
                continue;
            }
            if (egg[i][0] <= 0) {
                continue;
            } else {
                egg[n][0] = egg[n][0] - egg[i][1];
                egg[i][0] = egg[i][0] - egg[n][1];
                if (egg[i][0] <= 0) {
                    crack++;
                }
                if (egg[n][0] <= 0) {
                    crack++;
                }
            }
            rec_func(n + 1);
            if (egg[n][0] <= 0) {
                crack--;
            }
            if (egg[i][0] <= 0) {
                crack--;
            }
            egg[n][0] = egg[n][0] + egg[i][1];
            egg[i][0] = egg[i][0] + egg[n][1];
        }
    }

    static boolean check(int a) {
        for (int i = 1; i <= N; i++) {
            if (i == a) {
                continue;
            }
            if (egg[i][0] > 0) {
                return false;
            }
        }
        return true;
    }




}

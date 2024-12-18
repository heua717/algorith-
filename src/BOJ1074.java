import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074 {
    static int N, N2, R, C, ans;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken()) + 1;
        C = Integer.parseInt(st.nextToken()) + 1;
        N2 = (int) Math.pow(2, N);
        rec_func(N2, R, C);
        System.out.println(ans);

    }

    // 2^N x 2^N 공간을 4등분하여 현재 위치에 맞게 재분배하기
    static void rec_func(int range, int r, int c) {
        if (range == 1) {
            return;
        }
        int n = range / 2;

        if (r <= n && c <= n) {
            rec_func(n, r, c);
        } else if (r >= n + 1 && c >= n + 1) {
            ans += n * n * 3;
            rec_func(n, r - n, c - n);
        } else if (r >= n + 1 && c <= n) {
            ans += n * n * 2;
            rec_func(n, r - n, c);
        } else if (r <= n && c >= n + 1) {
            ans += n * n;
            rec_func(n, r, c - n);
        }
    }
}

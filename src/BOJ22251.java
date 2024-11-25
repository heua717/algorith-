import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ22251 {
    static int N, K, P, X, ans;
    static int digitX[], digitN[], selected[];
    static int tran[][];
    static int dig[][] = {{1, 1, 1, 0, 1, 1, 1}, {0, 1, 0, 0, 1, 0, 0}, {1, 0, 1, 1, 1, 0, 1}, {1, 1, 0, 1, 1, 0, 1}, {0, 1, 0, 1, 1, 1, 0}
            , {1, 1, 0, 1, 0, 1, 1}, {1, 1, 1, 1, 0, 1, 1}, {0, 1, 0, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 0, 1, 1, 1, 1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        while (true) {
            if (N / (int)Math.pow(10,K-1) != 0) {
                break;
            }
            K--;
        }
        tran = new int[10][10];
        digitX = new int[K];
        digitN = new int[K];
        selected = new int[K];
        tran_digit();
        int x = X;
        int n = N;
        for (int i = 0; i <K; i++) {
            if (i != K-1) {
                digitX[i] = x / (int) Math.pow(10, K - 1 - i);
                digitN[i] = n / (int) Math.pow(10, K - 1 - i);
                x = x % (int) Math.pow(10, K - 1 - i);
                n = n % (int) Math.pow(10, K - 1 - i);
            } else {
                digitX[i] = x;
                digitN[i] = n;
            }
        }
        digit_func(0, 0);
        System.out.println(ans);




    }

    static void digit_func(int k, int p) {
        if (p > P) {
            return;
        }
        if (k == K) {
            int n = 0;
            int x = 0;
            for (int i = 0; i < K; i++) {
                n += digitN[i] * Math.pow(10, K - 1 - i);
                x += selected[i] * Math.pow(10, K - 1 - i);
            }
            if (x <= n) {
                ans++;
            }
            if (x == X || x == 0) {
                ans--;
            }
            return;
        }
        for (int i = 0; i < 10; i++) {
            int a = digitX[k];
            selected[k] = i;
            p+=tran[a][i];
            digit_func(k+1,p);
            p -= tran[a][i];

        }

    }


    static void tran_digit() {
        int cnt;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cnt = 0;
                for (int k = 0; k < 7; k++) {
                    if (i == j) {
                        break;
                    }
                    if (dig[i][k] != dig[j][k]) {
                        cnt++;
                    }
                }
                tran[i][j] = cnt;
            }
        }
    }
}

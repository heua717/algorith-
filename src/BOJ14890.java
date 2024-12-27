import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890 {
    static int N, L, ans;
    static int map[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = N * 2;
        func();
        System.out.println(ans);

    }

    static void func() {
        for (int i = 1; i <= N; i++) {
            int cnt = 1;
            int x = map[i][1];
            for (int j = 2; j <= N; j++) {
                if (x == map[i][j]) {
                    cnt++;
                    continue;
                } else {
                    if (x + 1 == map[i][j]) { // 현재 위치보다 다음칸이 1 클 경우
                        if (cnt < L) {
                            ans--;
                            break;
                        } else {
                            cnt = 1;
                            x = map[i][j];
                        }
                    } else if (x == map[i][j] + 1) { // 현재 위치보다 다음칸이 1 작을 경우
                        cnt = 1;
                        x = map[i][j];
                        while (cnt != L) {
                            if (j + 1 > N) {
                                break;
                            }
                            if (map[i][j + 1] == x) {
                                cnt++;
                                j++;
                            } else {
                                break;
                            }
                        }
                        if (cnt == L) {
                            cnt = 0;
                            x = map[i][j];
                        } else {
                            ans--;
                            break;
                        }
                    } else { // 1이상의 차이가 날 경우
                        ans--;
                        break;
                    }
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            int cnt = 1;
            int x = map[1][i];
            for (int j = 2; j <= N; j++) {
                if (x == map[j][i]) {
                    cnt++;
                    continue;
                } else {
                    if (x + 1 == map[j][i]) { // 현재 위치보다 다음칸이 1 클 경우
                        if (cnt < L) {
                            ans--;
                            break;
                        } else {
                            cnt = 1;
                            x = map[j][i];
                        }
                    } else if (x == map[j][i] + 1) { // 현재 위치보다 다음칸이 1 작을 경우
                        cnt = 1;
                        x = map[j][i];
                        while (cnt != L) {
                            if (j + 1 > N) {
                                break;
                            }
                            if (map[j+1][i] == x) {
                                cnt++;
                                j++;
                            } else {
                                break;
                            }
                        }
                        if (cnt == L) {
                            cnt = 0;
                            x = map[j][i];
                        } else {
                            ans--;
                            break;
                        }
                    } else { // 1이상의 차이가 날 경우
                        ans--;
                        break;
                    }
                }
            }
        }
    }
}

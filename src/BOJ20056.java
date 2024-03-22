import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20056 {
    static int N, M, K, ans;
    static ArrayList<int[]> list[];
    static ArrayList<int[]> fList = new ArrayList<>();
    static ArrayList<Integer> dList = new ArrayList<>();
    static int dir[][] = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new ArrayList[N * N + 1];
        if (M == 0) {
            System.out.println(0);
            return;
        }
        for (int i = 1; i <= N * N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int x = (r - 1) * N + c;
            fList.add(new int[]{x, 1});
            list[x].add(new int[]{r, c, m, s, d});

        }
        for (int i = 1; i <= K; i++) {
            fire_move();
            fire_div();
        }
        for (int i = 0; i < fList.size(); ) {
            int x[] = fList.remove(i);
            for (int j = 0; j < x[1]; j++) {
                ans += list[x[0]].remove(0)[2];

            }
        }
        System.out.println(ans);

    }

    static void fire_move() {
        for (int i = 0; i < fList.size(); ) {
            int n[] = fList.remove(0);
            for (int j = 0; j < n[1]; j++) {
                int a[] = list[n[0]].remove(0);
                int r = a[0];
                int c = a[1];
                int m = a[2];
                int s = a[3];
                int d = a[4];
                r += dir[d][0] * s;
                c += dir[d][1] * s;
                r = r % (N);
                c = c % (N);

                if (r == 0) {
                    r = N;
                }
                if (c == 0) {
                    c = N;
                }
                if (r < 0) {
                    r = N + r;
                }
                if (c < 0) {
                    c = N + c;
                }
                int x = (r - 1) * N + c;
                if (!dList.contains(x)) {
                    dList.add(x);
                }
                list[x].add(new int[]{r, c, m, s, d});
            }


        }
    }


    static void fire_div() {
        for (int a = 1; a <= dList.size(); ) {
            int i = dList.remove(0);
            if (list[i].size() == 0) {
                continue;
            }
            if (list[i].size() >= 2) {
                boolean isOk = true;
                int size = list[i].size();
                int r = list[i].get(0)[0];
                int c = list[i].get(0)[1];
                int m = 0;
                int s = 0;
                int d = list[i].get(0)[4] % 2;
                for (int j = 0; j < list[i].size(); ) {
                    int x[] = list[i].remove(0);
                    m += x[2];
                    s += x[3];
                    if (x[4] % 2 != d) {
                        isOk = false;
                    }
                }
                m = m / 5;
                if(m==0){
                    continue;
                }
                s = s / size;
                for (int k = 0; k <= 7; k = k + 2) {
                    if (!isOk) {
                        k = k + 1;
                        isOk = true;
                    }
                    list[i].add(new int[]{r, c, m, s, k});
                }
            }
            fList.add(new int[]{i, list[i].size()});
        }
    }
}

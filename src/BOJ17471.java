import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17471 {
    static int N, diff, cnt, sum, ans = Integer.MAX_VALUE;
    static int citizen[];
    static int select[], select2[];
    static boolean visited[], visited2[];
    static ArrayList<Integer> node[];
    static Queue<Integer> q;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        node = new ArrayList[N + 1];
        citizen = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        // 각 구역별 인구정보 저장하기
        for (int i = 1; i <= N; i++) {
            node[i] = new ArrayList<>();
            citizen[i] = Integer.parseInt(st.nextToken());
            cnt += citizen[i];
        }

        //인접한 구역 저장하기 node[구역번호].get(0~size()-1)
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            while (st.hasMoreTokens()) {
                node[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 1; i <= N / 2; i++) {
            select = new int[i + 1];
            visited = new boolean[N + 1];
            comb(i, 1);
        }
        if (ans == Integer.MAX_VALUE) { // 구역간의 인구수 차이가 갱신되지 않으면 -1
            ans = -1;
        }
        System.out.println(ans);

    }

    // 선택된 도시들이 구역을 형성하고 있는지 체크
    static boolean check(int s[]) {
        visited2 = new boolean[N + 1];
        if (s.length == 2) { // 도시가 하나 뽑혔을때는 한 구역으로 인정
            return true;
        }
        // 첫번째 도시를 뽑아 dfs
        q = new LinkedList<>();
        q.add(s[1]);
        visited2[s[1]] = true;
        while (!q.isEmpty()) { // 인접한 도시중 같은 구역으로 나눠진 도시들만 체크하기
            int x = q.poll();
            for (int i = 0; i < node[x].size(); i++) {
                int y = node[x].get(i);
                if (visited2[y]) {
                    continue;
                } else {
                    for (int j = 1; j < s.length; j++) {
                        if (s[j] == y) {
                            q.add(y);
                            visited2[y] = true;
                        }
                    }
                }
            }
        }
        for (int i = 1; i < s.length; i++) { // 구역이 모두 이어져있지 않으면 false 반환
            if (!visited2[s[i]]) {
                return false;
            }
        }
        return true;

    }

    // N개의 도시중 1~N/2 만큼 뽑기
    static void comb(int n, int r) {
        if (r == n + 1) {
            sum = 0;
            select2 = new int[N - n + 1];
            int idx = 1;
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    select2[idx++] = i;
                }
            }
            if (check(select) && check(select2)) { // 나눠진 두개의 구역이 서로 인접하게 이어져있는지 체크하고 둘의 인구 차이 구하기
                for (int i = 1; i < select.length; i++) {
                    sum += citizen[select[i]];
                }
                diff = Math.abs((cnt - sum) - sum);
                ans = Math.min(diff, ans);
            }
            return;
        }

        for (int i = select[r - 1] + 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            select[r] = i;
            comb(n, r + 1);
            visited[i] = false;
        }
    }


}

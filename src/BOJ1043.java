import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1043 {
    static int N, M, tCnt, ans;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Integer> tList[];
    static ArrayList<Integer> pList[];
    static int [] arr;
    static boolean pVisited[], tVisited[];
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        tCnt = Integer.parseInt(st.nextToken());
        tList = new ArrayList[N + 1];
        pList = new ArrayList[M + 1];
        for (int i = 1; i <= N; i++) {
            tList[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            pList[i] = new ArrayList<>();
        }
        pVisited = new boolean[M + 1];
        tVisited = new boolean[N + 1];
        arr = new int[tCnt + 1];
        for (int i = 1; i <= tCnt; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= x; j++) {
                int num = Integer.parseInt(st.nextToken());
                tList[num].add(i);
                pList[i].add(num);
            }
        }

        for (int i = 1; i <= tCnt; i++) {
            dfs(arr[i]);
        }
        for (int i = 1; i <= M; i++) {
            if (!pVisited[i]) {
                ans++;
            }
        }
        System.out.println(ans);


    }

    static void dfs(int a) {
        if (tVisited[a]) {
            return;
        }
        tVisited[a] = true;
        for (int i = 0; i < tList[a].size(); i++) {
            int x = tList[a].get(i);
            if (pVisited[x]) {
                continue;
            }
            pVisited[x] = true;
            for (int j = 0; j < pList[x].size(); j++) {
                int y = pList[x].get(j);
                dfs(y);
            }
        }
    }

}

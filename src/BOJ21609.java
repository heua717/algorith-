import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21609 {
    static int N, M, ans, rainbowCnt;
    static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int block[][], copy[][];
    static boolean visited[][][], visited2[][];
    static ArrayList<int[]> list1, list2;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        block = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                block[i][j] = Integer.parseInt(st.nextToken()) + 1;
                //검은색 블록 = 0 무지개 블록 = 1 일반 블록 = 2~M+1 빈칸 = -1
            }
        }
        list2 = new ArrayList<>();
        while (true) {
            visited = new boolean[M + 2][N + 1][N + 1];
            int max = 0;
            rainbowCnt = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    visited2 = new boolean[N + 1][N + 1];
                    list1 = new ArrayList<>();
                    int m = block[i][j];
                    if (m >= 2 && !visited[m][i][j]) { // 기준 블록은 일반 블록이어야 하기 때문에 일반 블록으로 bfs + 작은행 작은 열 부터 시작하기 때문에 항상 기준 블록
                        max = bfs(i, j, m);
                        if (list1.size() < 2) { // 그룹이 아닐경우 continue
                            continue;
                        }
                        if (list1.size() == list2.size()) { // 같은 크기의 그룹을 만나면 무지개블록 갯수 비교 같을 경우 뒤에 선택된 그룹이 큰행,큰열 이기 때문에 변경
                            if (max >= rainbowCnt) {
                                rainbowCnt = max;
                                list2 = (ArrayList<int[]>) list1.clone();
                            }
                        }
                        if (list1.size() > list2.size()) { // 더 큰 그룹을 만나면 항상 무지개블록 갯수를 초기화
                            rainbowCnt = max;
                            list2 = (ArrayList<int[]>) list1.clone();
                        }

                    }
                }
            }
            if (list2.size() < 2) { // 모든 격자를 돌았는데 그룹이 안나오면 탈출
                break;
            } else {
                ans += Math.pow(list2.size(), 2);
                while (!list2.isEmpty()) { // 선택된 그룹 비워주면서 빈공간으로 대체하기
                    int n[] = list2.remove(0);
                    int r = n[0];
                    int c = n[1];
                    block[r][c] = -1;
                }
            }
            gravity_func();
            rotate_func();
            gravity_func();
        }
        System.out.print(ans);

    }


    static int bfs(int r, int c, int col) {
        int cnt = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[col][r][c] = true;
        visited2[r][c] = true;
        while (!q.isEmpty()) {
            int n[] = q.poll();
            list1.add(n);
            for (int d = 0; d < 4; d++) {
                int nr = n[0] + dir[d][0];
                int nc = n[1] + dir[d][1];
                if (nr > 0 && nr <= N && nc > 0 && nc <= N && !visited2[nr][nc]) {
                    if (block[nr][nc] == col) { // 일반 블록일 경우 따로 방문체크
                        q.add(new int[]{nr, nc});
                        visited2[nr][nc] = true;
                        visited[col][nr][nc] = true;
                        continue;
                    }
                    if (block[nr][nc] == 1) {
                        q.add(new int[]{nr, nc});
                        visited2[nr][nc] = true;
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }


    static void rotate_func() {
        copy_func();
        for (int i = N; i > 0; i--) {
            for (int j = 1; j <= N; j++) {
                block[N - i + 1][j] = copy[j][i];
            }
        }
    }

    static void copy_func() {
        copy = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                copy[i][j] = block[i][j];
            }
        }
    }

    static void gravity_func() {
        for (int i = N - 1; 1 <= i; i--) {
            for (int j = 1; j <= N; j++) {
                if (block[i][j] == -1 || block[i][j] == 0) continue;
                int tmp = block[i][j], ni = i;
                while (ni + 1 <= N && block[ni + 1][j] == -1)
                    ni++;
                if (block[ni][j] == -1) {
                    block[ni][j] = tmp;
                    block[i][j] = -1;
                }
            }
        }
    }

}

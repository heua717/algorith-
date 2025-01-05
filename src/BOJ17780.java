import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ17780 {
    static int N, K, ans;
    static int map[][], horse[][];
    static int dir[][] = {{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static LinkedList<Integer> list[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        horse = new int[K + 1][3];
        map = new int[N + 2][N + 2]; // 맵의 외부까지 사용하기 위해 +2를 사용
        list = new LinkedList[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                list[i][j] = new LinkedList<>();
                if (map[i][j] == 0) { // 맵의 외부는 파랑색으로 처리하기 때문에 0,2를 파란색으로 두기 위해 0을 3으로 변경 (3은 흰색 칸)
                    map[i][j] = 3;
                }
            }
        }

        for (int i = 1; i <= K; i++) { // K개의 말의 이동방향을 저장하고 말 순서저장을 위한 맵(list)에 말의 번호를 저장해준다.
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            horse[i][0] = r;
            horse[i][1] = c;
            horse[i][2] = d;
            list[r][c].add(i);
        }
        ans = -1; // 정답이 갱신 안되는 경우는 1000회가 넘도록 말이 4개 이상 쌓이지 않을 경우, -1 출력
        func();

        System.out.println(ans);

    }

    static void func() {
        int cnt = 0;
        while (++cnt <= 1000) { // 턴이 1000이 넘지 않는 선에서 반복하여 말을 움직여주기
            for (int i = 1; i <= K; i++) { // 낮은 번호의 말부터 이동해주기
                int r = horse[i][0];
                int c = horse[i][1];
                int d = horse[i][2];

                if (list[r][c].get(0) != i) { // 말이 가장 아래가 아니라면 아무 행동도 하지 않고 다음말로 넘어가기
                    continue;
                }
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (map[nr][nc] == 3) { // 이동하는 칸이 흰색 일 경우 칸의 아래부터 이동
                    for (int j = 0; j < list[r][c].size(); ) {
                        int x = list[r][c].remove();
                        list[nr][nc].add(x);
                        horse[x][0] = nr;
                        horse[x][1] = nc;
                    }
                } else if (map[nr][nc] == 1) { // 이동 하는 칸이 빨간색 칸일 경우 칸의 위부터 이동하는 칸의 위로 쌓아주기
                    for (int j = 0; j < list[r][c].size(); ) {
                        int x = list[r][c].removeLast();
                        list[nr][nc].add(x);
                        horse[x][0] = nr;
                        horse[x][1] = nc;
                    }
                } else { // 맵의 외부거나 파란색 칸일 경우 이동방향을 바꿔주고 다시 칸을 지정하여 확인하기
                    if (d == 1) {
                        d = 2;
                    } else if (d == 2) {
                        d = 1;
                    } else if (d == 3) {
                        d = 4;
                    } else if (d == 4) {
                        d = 3;
                    }
                    horse[i][2] = d;
                    nr = r + dir[d][0];
                    nc = c + dir[d][1];
                    if (map[nr][nc] == 0 || map[nr][nc] == 2) { // 방향을 바꿔 이동하는 칸이 외부이거나 파란색칸일 경우 방향만 바꾼채로 넘어가기
                        continue;
                    } else if (map[nr][nc] == 3) { // 방향을 바꿔 이동하는 칸이 흰색일 경우 위와 같음
                        for (int j = 0; j < list[r][c].size(); ) {
                            int x = list[r][c].remove();
                            list[nr][nc].add(x);
                            horse[x][0] = nr;
                            horse[x][1] = nc;
                        }
                    } else if (map[nr][nc] == 1) { // 방향을 바꿔 이동하는 칸이 빨간색 일 경우 위와 같음
                        for (int j = 0; j < list[r][c].size(); ) {
                            int x = list[r][c].removeLast();
                            list[nr][nc].add(x);
                            horse[x][0] = nr;
                            horse[x][1] = nc;
                        }
                    }
                }
                if (list[nr][nc].size() >= 4) { // 이동이 끝나고 이동한 칸의 말이 4개 이상 쌓여있으면 정답을 갱신하고 탈출하기
                    ans = cnt;
                    cnt = 1001;
                    break;
                }

            }
        }
    }


}

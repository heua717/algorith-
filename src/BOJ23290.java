import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ23290 {
    static int M, S, max, ans; // 초기 물고기의 수 M, 연습 횟수 S
    static int fDir[][] = {{0, 0}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}}; // 물고기 이동 <-부터 반시계 방향으로 [1,2,3,4,5,6,7,8]
    static int sDir[][] = {{0, 0}, {-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // 상어 이동 상하좌우[1,2,3,4]
    static int[][] map, smell; // 맵에는 물고기의 수 표기하기
    static int selected[]; // 상어 이동경로 완전탐색하기
    static ArrayList<int[]> smellList, eatList; // 상어가 이동하면서 물고기를 제외시킬 때 먹는 위치, 냄새를 남길 위치 저장하기
    static boolean visited[][]; // 상어 이동시 먹었던 곳 또 먹는경우 방지
    static Shark shark;
    static ArrayList<Integer> fList[][], fList2[][]; // fList는 초기 값 fList2는 첫 이동후 물고기 정보
    static ArrayList<int[]> fCopyList;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        map = new int[5][5];
        smell = new int[5][5];
        st = new StringTokenizer(br.readLine());
        fList = new ArrayList[5][5];
        fList2 = new ArrayList[5][5];
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                fList[i][j] = new ArrayList<>();
                fList2[i][j] = new ArrayList<>();
            }
        }
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map[r][c]++;
            fList[r][c].add(d);
        }
        st = new StringTokenizer(br.readLine());
        shark = new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for (int s = 0; s < S; s++) {
            selected = new int[3];

            fCopyList = new ArrayList<>();
            copy_fList(); // fCopyList에 이동전 복사 할 물고기 정보 저장
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 4; j++) {
                    for (int d = 0; d < fList[i][j].size(); ) { // fList2에 물고기 이동시키기
                        move_fish(i, j, fList[i][j].remove(d));
                    }
                }
            }
            max = -1;
            rec_func(0); // 64가지 상어 이동경로 경우의 수 중 물고기를 가장 많이 제외시키는 경로를 selected 에 저장
            smellList = new ArrayList<>();
            eatList = new ArrayList<>();
            move_shark(); // 위에서 지정한 경로대로 상어를 이동시키며 물고기를 제외시키는 위치정보를 smellList, eatList에 저장
            smell_check(); // 위에서 지정한 상어 이동경로에 새로운 물고기 냄새 추가 + 기존 물고기 냄새 갱신
            add_fish(); // 이동 후 제외되지 않은 물고기(fList2) + 복사해두었던 물고기 합쳐서(fCopyList) fList에 저장
        }
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                ans += map[i][j];
            }
        }
        System.out.println(ans);


    }

    static void add_fish() {
        while (!eatList.isEmpty()) {
            int x[] = eatList.remove(0);
            int r = x[0];
            int c = x[1];
            fList2[r][c].clear();
        }
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 0; k < fList2[i][j].size(); ) {
                    fList[i][j].add(fList2[i][j].remove(k));
                }
            }
        }
        // while(!fCopyList.isEmpty()) 를 사용하면 시간 초과 발생
        for (int i = 0; i < fCopyList.size(); i++) {
            fList[fCopyList.get(i)[0]][fCopyList.get(i)[1]].add(fCopyList.get(i)[2]);
        }

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                map[i][j] = fList[i][j].size();
            }
        }


    }

    // 상어가 물고기를 제외시킨 지역에 물고기 냄새 추가 + 기존 정보 갱신
    static void smell_check() {
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                if (smell[i][j] == 1) {
                    smell[i][j]++;
                } else if (smell[i][j] == 2) {
                    smell[i][j] = 0;
                }
            }
        }
        while (!smellList.isEmpty()) {
            int[] x = smellList.remove(0);
            int r = x[0];
            int c = x[1];
            smell[r][c] = 1;
        }
    }

    static void move_shark() { // 상어를 이동시키며 상어 이동경로에 물고기 제외 시키기
        int x[] = shark.getD();
        int r = shark.getR();
        int c = shark.getC();
        for (int i = 0; i < x.length; i++) {
            int d = x[i];
            r += sDir[d][0];
            c += sDir[d][1];
            if (map[r][c] > 0) {
                map[r][c] = 0;
                smellList.add(new int[]{r, c});
                eatList.add(new int[]{r, c});
            }
        }
        shark.setR(r);
        shark.setC(c);

    }

    // 기존에 저장된 물고기 정보를 받아 이동방향을 정하여 이동 fList -> fList2로 이동 *새로운 배열에 이동하는 이유는 이동된 물고기들이 중복 이동할 수 있기 때문
    static void move_fish(int r, int c, int k) {
        int d = k;
        for (int i = 0; i < 8; i++) { // 반시계 방향으로 회전하기 위한 로직
           int dx = d;
            if ((d - i) > 0) {
                dx = d - i;
            } else {
                dx = 8 + (d - i);
            }

            int nr = r + fDir[dx][0];
            int nc = c + fDir[dx][1];
            if (!is_Go(nr, nc) || smell[nr][nc] > 0 || (shark.getR() == nr && shark.getC() == nc)) { // 격자 밖이면 이동x, 상어가 있는 칸이면 이동x, 물고기 냄새가 있는 칸이면 이동x
                continue;
            } else { // 이동이 가능하면 물고기를 이동시키고 종료
                map[r][c]--;
                map[nr][nc]++;
                fList2[nr][nc].add(dx);
                return;
            }
        }
        // 이동이 불가능할 경우에도 물고기 정보 저장
        fList2[r][c].add(d);
    }


    static void rec_func(int n) {
        if (n >= 3) { // 3가지 이동방향을 뽑았을 때
            int x = shark_moveVerify(selected);
            if (max < x) {
                max = x;
                shark.setD(new int[]{selected[0], selected[1], selected[2]});
            }
            return;
        }
        for (int i = 1; i <= 4; i++) {
            selected[n] = i;
            rec_func(n + 1);
        }
    }

    static int shark_moveVerify(int s[]) {
        visited = new boolean[5][5];
        int eat = 0;
        int r = shark.getR();
        int c = shark.getC();
        for (int i = 0; i < s.length; i++) {
            int d = s[i];
            r = r + sDir[d][0];
            c = c + sDir[d][1];
            if (!is_Go(r, c)) {
                return -1;
            } else {
                if (!visited[r][c]) {
                    eat += map[r][c];
                }
                visited[r][c] = true;
            }
        }

        return eat;
    }


    static boolean is_Go(int r, int c) {
        if (r > 0 && r < 5 && c > 0 && c < 5) {
            return true;
        } else {
            return false;
        }
    }

    // l2에 있는 데이터 l1에 복사하기
    static void copy_fList() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 0; k < fList[i][j].size(); k++) {
                    fCopyList.add(new int[]{i, j, fList[i][j].get(k)});
                }
            }
        }
    }


    static class Shark {
        int r;
        int c;
        int d[];

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }

        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }

        public int[] getD() {
            return d;
        }

        public void setD(int[] d) {
            this.d = d;
        }

        public Shark(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}

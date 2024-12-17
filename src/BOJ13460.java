import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13460 {
    static int N, M, ans = Integer.MAX_VALUE, max = Integer.MAX_VALUE;
    static int map[][];
    static int dir[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static Bead red, blue;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            char a[] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (a[j] == '#') {
                    map[i][j] = -1;
                } else if (a[j] == '.') {
                    map[i][j] = 0;
                } else if (a[j] == 'R') {
                    red = new Bead(i, j,1);
                    map[i][j] = 1;
                } else if (a[j] == 'B') {
                    blue = new Bead(i, j, 2);
                    map[i][j] = 2;
                } else {
                    map[i][j] = 99;
                }
            }
        }

        for (int k = 0; k < 4; k++) {
            int m2[][] = new int[N][M];
            copy_map(m2,map);
            rec_func(red, blue, k);
            max = Math.min(ans, max);
            copy_map(map,m2);
        }
        if (max > 10) {
            System.out.println(-1);
        } else {
            System.out.println(max);
        }
    }

    //구슬 bfs 함수
    static void rec_func(Bead red, Bead blue, int d) {
        if (red.getMoveCnt() > ans) { // 구슬 이동 횟수가 현재까지 최소 이동 값 보다 크면 그만
            return;
        }
        if (red.getMoveCnt() > 10 || blue.getMoveCnt() > 10) { // 구슬이 10번 이상 움직였으면 그만하기
            return;
        }
        Bead r = new Bead(red.getR(), red.getC(), red.getMoveCnt(), red.getCol());
        Bead b = new Bead(blue.getR(), blue.getC(), blue.getMoveCnt(), blue.getCol());
        int map2[][] = new int[N][M];
        copy_map(map2, map); // map2에 기존 map 복사

        if (move_BeadOK(b.getR(), b.getC(), d) == 99) {
            ans = Integer.MAX_VALUE;
            return;
        } else if (move_BeadOK(r.getR(), r.getC(), d) == 99) { // 이동방향으로 쭉 갔을 때 레드만 골인하는 경우 정답 갱신 + 끝내기
            ans = Math.min(ans, r.getMoveCnt() + 1);
            return;
        } else if (move_BeadOK(r.getR(), r.getC(), d) == -1 && move_BeadOK(b.getR(), b.getC(), d) == -1) {
            return;
        }


        if (map[r.getR() + dir[d][0]][r.getC() + dir[d][1]] == 2) {
            if (move_BeadOK(b.getR(), b.getC(), d) == -1) {
                return;
            } else {
                r.setMoveCnt(r.getMoveCnt() + 1);
                b.setMoveCnt(b.getMoveCnt() + 1);
                moveBead(b, d);
                moveBead(r, d);
            }
        } else if (map[b.getR() + dir[d][0]][b.getC() + dir[d][1]] == 1) {
            if (move_BeadOK(r.getR(), r.getC(), d) == -1) {
                return;
            } else {
                r.setMoveCnt(r.getMoveCnt() + 1);
                b.setMoveCnt(b.getMoveCnt() + 1);
                moveBead(r, d);
                moveBead(b, d);
            }
        } else{ //먼저 이동하다가 벽전에 다른 색 구슬을 만나 멈출 수 있기 때문에 두번씩 움직여서 끝까지 움직여주기
            r.setMoveCnt(r.getMoveCnt() + 1);
            b.setMoveCnt(b.getMoveCnt() + 1);
            moveBead(r, d);
            moveBead(b, d);
            moveBead(r, d);
            moveBead(b, d);
        }

        if (r.getMoveCnt() >= 10) {
            copy_map(map, map2);
            return;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int k = 0; k < 4; k++) {
            // 이동방향 정하기 (빨간구슬 기준으로) 왔던 방향으로 되돌아 가거나 똑같은 방향을 그대로 가는 것은 제외
            if ((k % 2 == 0 && d % 2 == 0) || (k % 2 == 1 && d % 2 == 1)) {
                continue;
            }
            int nr = r.getR();
            int nc = r.getC();
            int nr2 = b.getR();
            int nc2 = b.getC();
            if (move_BeadOK(nr2, nc2, k) == 99) { //둘다 이동방향으로 갔을 때 골인지점에 들어가면 끝
                continue;
            } else if (move_BeadOK(nr, nc, k) == 2 && move_BeadOK(nr2, nc2, k) == -1) {
                continue;
            } else if (move_BeadOK(nr2, nc2, k) == 1 && move_BeadOK(nr, nc, k) == -1) {
                continue;
            } else if (move_BeadOK(nr, nc, k) == -1 && move_BeadOK(nr2, nc2, k) == -1) {
                continue;
            } else {
                q.add(k);
            }

        }
        while (!q.isEmpty()) {
            rec_func(r, b, q.poll());
        }
        if (q.isEmpty()) {
            copy_map(map, map2);
            return;
        }

    }

    // m2배열을 m1에 복사하기
    static void copy_map(int m1[][], int m2[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                m1[i][j] = m2[i][j];
            }
        }
    }

    //구슬의 움직이는 방향에 따른 값 반환하기(첫 움직임이 벽이면 -1, 쭉 움직였을 때 골인지점이 있으면 99, 벽이면 0 -> 0이면 이동가능)
    static int move_BeadOK(int r, int c, int d) {
        int nr = r + dir[d][0];
        int nc = c + dir[d][1];
        if (map[nr][nc] == 99) {
            return 99;
        } else if (map[nr][nc] == -1) {
            return -1;
        }

        // 한칸움직였는데 빈칸이면 쭉 이동하기
        while (true) {
            nr += dir[d][0];
            nc += dir[d][1];
            if (map[nr][nc] == 99) {
                return 99;
            }  else if(map[nr][nc] == -1){
                return 0;
            }
        }
    }

    // 구슬 이동하기 이동하는 방향에 벽, 구슬, 골인지점이 없을 때 이동
    static void moveBead(Bead bead, int d) {
        int nr = bead.getR();
        int nc = bead.getC();
        while (true) {
            nr += dir[d][0];
            nc += dir[d][1];
            if (map[nr][nc] == -1 || map[nr][nc] == 1 || map[nr][nc] == 2) {
                nr -= dir[d][0];
                nc -= dir[d][1];
                break;
            }
        }

        map[bead.getR()][bead.getC()] = 0;
        map[nr][nc] = bead.getCol();
        bead.setR(nr);
        bead.setC(nc);
    }

    // 구슬 클래스 작성 (좌표 R,C 컬러 1=빨강, 2=파랑, 움직인 횟수 moveCnt
    static class Bead {
        int r;
        int c;
        int col;
        int moveCnt;

        public int getCol() {
            return col;
        }


        public Bead(int r, int c, int moveCnt, int col) {
            this.r = r;
            this.c = c;
            this.moveCnt = moveCnt;
            this.col = col;
        }

        public int getMoveCnt() {
            return moveCnt;
        }

        public void setMoveCnt(int moveCnt) {
            this.moveCnt = moveCnt;
        }

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


        public Bead(int r, int c, int col) {
            this.r = r;
            this.c = c;
            this.col = col;
        }
    }

}


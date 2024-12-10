import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ19236 {
    static int ans, max;
    static int dir[][] = {{0, 0}, {-1, 0}, {-1, - 1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    static int map[][];
    static Fish fish[];
    static Shark shark;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        map = new int[4][4];
        fish = new Fish[17];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int n = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                fish[n] = new Fish(i, j, d, true);
                map[i][j] = n;
            }
        }
        // 처음 0,0으로 상어 보내기
        shark = new Shark(0, 0, fish[map[0][0]].getD(), map[0][0]);
        fish[map[0][0]].setAlive(false);
        map[0][0] = -1;

        rec_func(shark);
        System.out.println(ans);

    }

    static void rec_func(Shark s) {
        move_fish();
        int r = s.getR();
        int c = s.getC();
        int d = s.getD();
        int eat = s.getEat();
        int nr = r;
        int nc = c;
        Queue<Shark> q = new LinkedList<>();
        while (true) { //현재 상어가 먹을 수 있는 물고기들 Queue에 넣기
            nr = nr + dir[d][0];
            nc = nc + dir[d][1];
            if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) {
                break;
            }
            if (map[nr][nc] > 0) { // 이동가능한 범위 내에서 물고기들만 q에 추가
                q.add(new Shark(nr, nc, fish[map[nr][nc]].getD(), eat + map[nr][nc]));
            }
        }
        if (q.isEmpty()) { // 위에서 상어가 더이상 먹을 수 있는 물고기가 없을 때 지금까지 먹은 물고기의 수 갱신하며 종료하기
            ans = Math.max(ans, eat);
            return;
        }
        while (!q.isEmpty()) {
            Shark s2 = q.poll(); // 상어가 먹을 물고기의 정보
            int map2[][] = new int[4][4]; // 먹고나서 되돌아 왔을 때 기존의 상태를 유지하기 위한 맵
            Fish fish2[] = new Fish[17]; // 먹고나서 되돌아 왔을 때 기존의 물고기 정보를 유지하기 위한 클래스
            copy_fish(fish2,fish); //현재 물고기 정보 복사해두기(상어가 먹고나면 물고기가 이동하면서 물고기 정보가 변경됨)
            copy_map(map2, map); //상어가 먹기전 상태의 맵
            int fishN = map[s2.getR()][s2.getC()];
            fish[fishN].setAlive(false);// 상어가 먹는 물고기 먹혔는지 안먹혔는지 처리해주기
            map[s2.getR()][s2.getC()] = -1; // 상어의 위치 갱신
            map[r][c] = 0; // 상어가 이동한 기존의 자리는 빈공간으로 갱신
            rec_func(s2); // 물고기를 먹으면서 이동하여 다음 물고기를 먹으러 가기(더 이상 먹을 수 있는 물고기가 존재하지 않을 때 까지)
            //이전 정보로 갱신
            fish[fishN].setAlive(true);
            copy_fish(fish, fish2);
            copy_map(map, map2);
        }

    }
    //물고기들 정보 복사
    static void copy_fish(Fish f1[], Fish f2[]) {
        for (int i = 1; i <= 16; i++) {
            f1[i] = new Fish(f2[i].getR(), f2[i].getC(), f2[i].getD(), f2[i].isAlive());

        }
    }
    //맵 정보 복사
    static void copy_map(int[][] m, int[][] m2) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                m[i][j] = m2[i][j];
            }
        }
    }
    //물고기 이동
    static void move_fish() {
        for (int i = 1; i <= 16; i++) {
            if (!fish[i].isAlive()) { //잡아먹힌 물고기는 패스
                continue;
            }
            int r = fish[i].getR();
            int c = fish[i].getC();
            int d = fish[i].getD();
            for (int k = 0; k < 8; k++) { //이동 가능할 때 까지 반시계 방향으로 반복이동
                int x = (d + k) % 8;
                if (x == 0) {
                    x = 8;
                }
                int nr = r + dir[x][0];
                int nc = c + dir[x][1];
                if (isGO(nr, nc)) { //격자판을 넘지 않고 상어가 아닌자리를 판별하여 이동하기
                    int tmp = map[nr][nc];
                    map[nr][nc] = i;
                    map[r][c] = tmp;
                    fish[i].setR(nr);
                    fish[i].setC(nc);
                    fish[i].setD(x);
                    if (map[r][c] > 0) { //이동한 칸이 빈자리가 아닌 경우 이동한 자리의 물고기 정보 갱신
                        fish[map[r][c]].setR(r);
                        fish[map[r][c]].setC(c);
                    }
                    break;
                }
            }
        }
    }

    //격자판을 넘지 않고 상어가 아닌자리를 판별
    static boolean isGO(int r, int c) {
        if (r >= 0 && r < 4 && c >= 0 && c < 4 && map[r][c] != -1) {
            return true;
        } else {
            return false;
        }
    }

    static class Shark {
        int r;
        int c;
        int d;
        int eat;

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

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }

        public int getEat() {
            return eat;
        }

        public void setEat(int eat) {
            this.eat = eat;
        }

        public Shark(int r, int c, int d, int eat) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.eat = eat;
        }
    }
    static class Fish {
        int r;
        int c;
        int d;
        boolean isAlive = true;

        public boolean isAlive() {
            return isAlive;
        }

        public void setAlive(boolean alive) {
            isAlive = alive;
        }

        public Fish(int r, int c, int d, boolean isAlive) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.isAlive = isAlive;
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

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }
    }
}

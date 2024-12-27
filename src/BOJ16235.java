import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16235 {
    static int N, M, K, deadCnt;
    static int map[][], map2[][];
    static ArrayList<Tree> tree, tree2;
    static int dir[][] = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    static ArrayList<int[]> deadList, incList;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        map2 = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map2[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
            }
        }

        tree = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            tree.add(new Tree(r, c, z, true));
        }
        Collections.sort(tree, (t1, t2) -> t1.getZ() - t2.getZ());

        for (int i = 1; i <= K; i++) {
            incList = new ArrayList<>();
            deadList = new ArrayList<>();
            spring();
            summer();
            fall();
            winter();
        }
        System.out.println(tree.size());

    }

    static void spring() {
        for (int i = 0; i < tree.size(); i++) {
            Tree t = tree.get(i);

            int r = t.r;
            int c = t.c;
            int z = t.z;
            if (map[r][c] < z) {
                deadList.add(new int[]{r, c, z});
                t.alive = false;
                deadCnt++;
            } else {
                map[r][c] -= z;
                t.z = z + 1;
                if ((z + 1) % 5 == 0) {
                    incList.add(new int[]{r, c});
                }
            }
        }
    }

    static void summer() {
        for (int i = 0; i < deadList.size(); i++) {
            int x[] = deadList.get(i);
            map[x[0]][x[1]] += x[2] / 2;
        }
    }

    static void fall() {
        tree2 = new ArrayList<>();
        for (int i = 0; i < incList.size(); i++) {
            int x[] = incList.get(i);
            int r = x[0];
            int c = x[1];
            for (int d = 0; d < 8; d++) {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (nr > 0 && nr <= N && nc > 0 && nc <= N) {
                    tree2.add(new Tree(nr, nc, 1, true));
                }
            }
        }
        for (Tree t : tree) {
            if (t.alive) {
                tree2.add(t);
            }
        }
        tree = tree2;

    }

    static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] += map2[i][j];
            }
        }
    }

    static class Tree {
        int r;
        int c;
        int z;
        boolean alive;

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

        public int getZ() {
            return z;
        }

        public void setZ(int z) {
            this.z = z;
        }


        public Tree(int r, int c, int z, boolean alive) {
            this.r = r;
            this.c = c;
            this.z = z;
            this.alive = alive;
        }

    }


}

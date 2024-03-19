import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14499 {
    static int N, M, K, R, C;
    static int dice[], dice2[];
    static int map[][];
    static int dir[][] = {{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dice = new int[7];
        dice2 = new int[7];
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < K; k++) {
            move_dice(Integer.parseInt(st.nextToken()));
            copy_dice();
        }

    }
    // 주사위를 굴리고 나서도 원본의 도면을 그대로 유지하기 ex) 1번은 항상 위 6번은 항상 아래
    static void move_dice(int k) {
        R = R + dir[k][0];
        C = C + dir[k][1];
        if (R < 0 || R >= N || C < 0 || C >= M) {
            R = R - dir[k][0];
            C = C - dir[k][1];
            return;
        }

        if (k == 1) {
            dice2[1] = dice[4];
            dice2[2] = dice[2];
            dice2[3] = dice[1];
            dice2[4] = dice[6];
            dice2[5] = dice[5];
            dice2[6] = dice[3];
        } else if (k == 2) {
            dice2[1] = dice[3];
            dice2[2] = dice[2];
            dice2[3] = dice[6];
            dice2[4] = dice[1];
            dice2[5] = dice[5];
            dice2[6] = dice[4];
        } else if (k == 3) {
            dice2[1] = dice[2];
            dice2[2] = dice[6];
            dice2[3] = dice[3];
            dice2[4] = dice[4];
            dice2[5] = dice[1];
            dice2[6] = dice[5];
        } else if (k == 4) {
            dice2[1] = dice[5];
            dice2[2] = dice[1];
            dice2[3] = dice[3];
            dice2[4] = dice[4];
            dice2[5] = dice[6];
            dice2[6] = dice[2];
        }

        if (map[R][C] == 0) {
            map[R][C] = dice2[6];
        } else {
            dice2[6] = map[R][C];
            map[R][C] = 0;
        }
        System.out.println(dice2[1]);
    }

    static void copy_dice() {
        for (int i = 1; i <= 6; i++) {
            dice[i] = dice2[i];
        }
    }

}

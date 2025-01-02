import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ16719 {
    static char input[];
    static boolean visited[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        input = br.readLine().toCharArray();
        visited = new boolean[input.length];
        func(0, input.length-1);
    }

    static void func(int s, int e) {
        if (s > e) {
            return;
        }
        int idx = s;
        for (int i = s; i <= e; i++) {
            if (input[idx] > input[i]) {
                idx = i;
            }
        }

        visited[idx] = true;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                System.out.print(input[i]);
            }
        }
        System.out.println();
        func(idx + 1, e);
        func(s, idx - 1);
    }
}

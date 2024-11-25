import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2668 {
    static int N, ans, max;
    static boolean visited[];
    static int arr[];
    static ArrayList<Integer> list;
    static Set<Integer> set = new TreeSet<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            list = new ArrayList<>();
            num_sel(i);
        }
        System.out.println(set.size());
        Iterator<Integer> iter = set.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

    static void num_sel(int n) {
        int cnt = 0;
        int x = n;
        while (true) {
            if (visited[n]) {
                break;
            }
            cnt++;
            visited[n] = true;
            list.add(n);
            n = arr[n];
        }
        if (x == n) {
            for (int i = 0; i < list.size(); i++) {
                set.add(list.get(i));
            }
        }
    }
}

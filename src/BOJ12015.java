import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ12015 {
    static int N;
    static int arr[];
    static List<Integer> list = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        list.add(arr[1]);
        for (int i = 2; i <= N; i++) {
            if (list.get(list.size() - 1) < arr[i]) {
                list.add(arr[i]);
                continue;
            } else {
                int bot = 0;
                int top = list.size();
                while (bot < top) {
                    int mid = (bot + top) / 2;
                    if (list.get(mid) < arr[i]) {
                        bot = mid + 1;
                    } else {
                        top = mid;
                    }
                }
                list.set(bot, arr[i]);
            }

        }
        System.out.println(list.size());


    }
}

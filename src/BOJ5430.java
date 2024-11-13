import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ5430 {
    static int T, N, error, func;
    static String p, s;
    static ArrayList<Integer> list ;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            error = 1;
            s="";
            list = new ArrayList<>();
            p = br.readLine();
            N = Integer.parseInt(br.readLine());
            String str = br.readLine();
            for (int a = 1; a < str.length(); a++) {
                if (N == 0) {
                    break;
                }
                if (str.charAt(a) == ',' || str.charAt(a) == ']') {
                    list.add(Integer.parseInt(s));
                    s = "";
                    continue;
                }
                s += str.charAt(a)+"";
            }
            func = 1;
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == 'R') {
                    func = -func;
                }
                if (p.charAt(i) == 'D') {
                    if (list.size() == 0) {
                        System.out.println("error");
                        error = -1;
                        break;
                    }
                    if (func == 1) {
                        list.remove(0);
                    } else if (func == -1) {
                        list.remove(list.size() - 1);
                    }
                }
            }
            if (error == -1) {
                continue;
            }
            System.out.print("[");
            if (list.size() == 0) {
                System.out.println("]");
                continue;
            }
            if (func == 1) {
                for (int size = 0; size < list.size(); size++) {
                    if (size != list.size() - 1) {
                        System.out.print(list.get(size) + ",");
                    } else {
                        System.out.println(list.get(size) + "]");
                    }
                }
            } else {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (size != 0) {
                        System.out.print(list.get(size) + ",");
                    } else {
                        System.out.println(list.get(size) + "]");
                    }
                }
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ2504 {
    static int ans, xCnt, yCnt; // curr 은 현재 열려있는 괄호 값 2 또는 3
    static boolean xOpen, yOpen;
    static char c[];
    static ArrayList<String> cal = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        c = br.readLine().toCharArray();
        if (c.length == 1) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(') {
                xOpen = true;
                xCnt++;
                cal.add("(");
            } else if (c[i] == '[') {
                yOpen = true;
                yCnt++;
                cal.add("[");
            } else if (c[i] == ')') {
                xCnt--;
                if (xCnt == 0) {
                    xOpen = false;
                }
                cal.add(")");
            } else if (c[i] == ']') {
                yCnt--;
                if (yCnt == 0) {
                    yOpen = false;
                }
                cal.add("]");
            }
            if (!xOpen && !yOpen) {
                if (!is_Cal()) {
                    System.out.println(0);
                    return;
                } else {
                    ans += function();
                }
            }

        }
        if (xOpen || yOpen) {
            ans = 0;
        }
        System.out.println(ans);

    }

    static int function() {
        int result = 0;
        int val = 1;
        while (!cal.isEmpty()) {
            String str = cal.remove(0);
            if (str.equals("(")) {
                xOpen = true;
                val *= 2;
            } else if (str.equals("[")) {
                yOpen = true;
                val *= 3;
            } else if (str.equals(")")) {
                if (xOpen) {
                    result += val;
                }
                val /= 2;
                yOpen = false;
                xOpen = false;
            } else if (str.equals("]")) {
                if (yOpen) {
                    result += val;
                }
                val /= 3;
                xOpen = false;
                yOpen = false;
            }
        }
        return result;
    }


    static boolean is_Cal() {
        for (int i = 0; i < cal.size(); i++) {
            if (cal.get(i) == ")") {
                if (i == 0) {
                    return false;
                }
                if (cal.get(i - 1) == "[") {
                    return false;
                }
            }
            if (cal.get(i) == "]") {
                if (i == 0) {
                    return false;
                }
                if (cal.get(i - 1) == "(") {
                    return false;
                }
            }
        }
        return true;
    }
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ16637 {
    static int N, ans, max, size;
    static String str1, str2;
    static int select[];
    static int num[];
    static char cal[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(Integer.parseInt(br.readLine()));
            return;
        }
        if (N == 3) {
            char c[] = br.readLine().toCharArray();
            int x = 0;
            if (c[1] == '+') {
                x = (c[0] - '0') + (c[2] - '0');
            } else if (c[1] == '-') {
                x = (c[0] - '0') - (c[2] - '0');

            } else if (c[1] == '*') {
                x = (c[0] - '0') * (c[2] - '0');
            }
            System.out.println(x);
            return;
        }
        str1 = br.readLine();
        ans = Integer.MIN_VALUE;
        size = N / 2;
        if (size % 2 == 1) {
            size = size / 2 + 1;
        } else {
            size = size / 2;
        }

        for (int i = 0; i <= size; i++) {
            select = new int[i];
            func(i, 0);
        }

        System.out.println(ans);
    }

    static void calc(int x, int y, char c) {
        if (c == '+') {
            str2 += (x + y) + "";
        } else if (c == '-') {
            str2 += (x - y) + "";
        } else {
            str2 += (x * y) + "";
        }
    }

    // String에 있는 수식 계산하기
    static void func2() {
        num = new int[str2.length()];
        cal = new char[str2.length()];
        int nidx = 0;
        int cidx = 0;
        for (int i = 0; i < str2.length(); i++) {
            char c = str2.charAt(i);
            if (i == 0) { // 처음은 무조건 숫자기 때문에 숫자 넣어주기
                if (str2.charAt(i + 1) != '+' && str2.charAt(i + 1) != '-' && str2.charAt(i + 1) != '*') { //숫자 다음 연산자가 아닐경우 두 자리수 이기 때문에 두자리수 처리
                    num[nidx++] = (c - '0') * 10 + (str2.charAt(i + 1) - '0');
                    i++;
                } else {
                    num[nidx++] = c - '0';
                }
            } else {
                if (c == '+' || c == '*') {
                    cal[cidx++] = c;
                    continue;
                } else if (c == '-') {
                    if (str2.charAt(i - 1) != '+' && str2.charAt(i - 1) != '*' && str2.charAt(i - 1) != '-') { // - 연산자를 만났을 때 -전에 연산자가 아니면 음수 처리해주기
                        cal[cidx++] = c;
                        continue;
                    } else {
                        if (i + 2 >= str2.length()) {
                            num[nidx++] = -(str2.charAt(i + 1) - '0');
                            i++;
                            continue;
                        } else {
                            if (str2.charAt(i + 2) != '+' && str2.charAt(i + 2) != '*' && str2.charAt(i + 2) != '-') {
                                num[nidx++] = -(str2.charAt(i + 1) - '0') * 10 + (str2.charAt(i + 2) - '0');
                                i = i + 2;
                                continue;
                            } else {
                                num[nidx++] = -(str2.charAt(i + 1) - '0');
                                i++;
                            }
                        }
                    }
                } else {
                    if (i + 1 < str2.length()) {
                        if (str2.charAt(i + 1) != '+' && str2.charAt(i + 1) != '*' && str2.charAt(i + 1) != '-') {
                            num[nidx++] = (c - '0') * 10 + (str2.charAt(i + 1) - '0');
                            i++;
                        } else {
                            num[nidx++] = c - '0';
                        }
                    } else {
                        num[nidx++] = c - '0';
                    }
                }
            }
        }

        max = num[0];
        for (int i = 0; i < cidx; i++) {
            char c = cal[i];
            if (c == '+') {
                max += num[i + 1];
            } else if (c == '-') {
                max -= num[i + 1];
            } else {
                max *= num[i + 1];
            }
        }

    }
    // 붙어있지 않은 수의 조합 뽑기
    static void func(int n, int r) {
        if (r == n) {
            str2 = "";
            if (select.length == 0) { // 하나도 뽑지 않았을 경우 원문 그대로 계산
                str2 = str1;
            }
            //괄호 부분 먼저 계산해서 String에 넣어주기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < select.length; j++) {
                    if (i == select[j] * 2) {
                        calc(str1.charAt(i) - '0', str1.charAt(i + 2) - '0', str1.charAt(i + 1));
                        i = i + 2;
                        break;
                    } else if (j == select.length - 1) {
                        str2 += str1.charAt(i) + "";
                    }
                }
            }
            // 맨 앞자리가 -면 0으로 시작해주기
            if (str2.charAt(0) == '-') {
                str2 = '0' + str2;
            }
            max = 0;
            func2();
            ans = Math.max(max, ans);
            return;
        }

        for (int i = 0; i < N / 2; i++) {
            if (r > 0) {
                if (select[r - 1] + 1 >= i) {
                    continue;
                }
            }
            select[r] = i;
            func(n, r + 1);
        }

    }

}


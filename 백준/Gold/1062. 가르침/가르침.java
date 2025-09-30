import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, k;
    static String[] strings;
    static boolean[] alpha = new boolean[26];
    static int count;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (k < 5) {
            System.out.println(0);
            return;
        } else if (k == 26) {
            System.out.println(n);
            return;
        }

        strings = new String[n];
        count = 0;

        for (int i = 0; i < n; i++) {
            strings[i] = br.readLine();
        }

        alpha['a' - 'a'] = true;
        alpha['n' - 'a'] = true;
        alpha['t' - 'a'] = true;
        alpha['i' - 'a'] = true;
        alpha['c' - 'a'] = true;

        backtracking(0, 0);

        System.out.println(count);
    }

    private static void backtracking(int curChar, int target) {
        if (k - 5 == target) {
            countReadable();
            return;
        }

        for (int i = curChar; i < 26; i++) {
            if (!alpha[i]) {
                alpha[i] = true;
                backtracking(i + 1, target + 1);
                alpha[i] = false;
            }
        }
    }

    public static void countReadable() {
        int curCount = 0;

        for (int i = 0; i < n; i++) {
            boolean flag = true;
            char[] charArray = strings[i].toCharArray();

            for (int j = 0; j < charArray.length; j++) {
                if (!alpha[charArray[j] - 'a']) {
                    flag = false;
                }
            }

            if(flag) curCount ++;
        }

        count = Math.max(curCount, count);
    }
}


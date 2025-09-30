import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static List<int[]> list = new ArrayList<>();
    static boolean[] used;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        /**
         * 재료 1개일 때, 2개일 때, 3개일 때, ..., n개 일 때
         */
        for (int i = 1; i <= n; i++) {
            used = new boolean[n];
            backtracking(0, i);
        }

        System.out.println(min);
    }

    private static void backtracking(int start, int target) {
        if (start == target) {
            int mul = 1;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (used[i]) {
                    mul *= list.get(i)[0];
                    sum += list.get(i)[1];
                }
            }
            min = Math.min(min, Math.abs(sum - mul));
        }

        for (int i = start; i < n; i++) {
            if (!used[i]) {
                used[i] = true;
                backtracking(i+1, target);
                used[i] = false;
            }
        }
    }
}


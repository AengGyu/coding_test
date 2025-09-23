import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] city;
    static List<int[]> home = new ArrayList<>();
    static List<int[]> chicken = new ArrayList<>();
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        city = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());

                if (city[i][j] == 1) home.add(new int[]{i, j});
                else if (city[i][j] == 2) chicken.add(new int[]{i, j});
            }
        }

        visited = new boolean[chicken.size()];

        backtracking(0, 0);
        System.out.println(min);
    }

    private static void backtracking(int count, int idx) {
        if (count == m) {
            int totalDist = 0;
            for (int i = 0; i < home.size(); i++) { // i번째 집에서 가장 가까운 치킨집 거리 구하기
                int dist = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (visited[j]) {
                        dist = Math.min(
                                dist,
                                Math.abs(home.get(i)[0] - chicken.get(j)[0]) + Math.abs(home.get(i)[1] - chicken.get(j)[1]));
                    }
                }
                totalDist += dist;
            }
            min = Math.min(min, totalDist);
            return;
        }

        for (int i = idx; i < chicken.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtracking(count + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}
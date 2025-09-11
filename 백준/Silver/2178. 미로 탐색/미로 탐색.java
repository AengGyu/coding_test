import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int n, m;
    static int[][] maze;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        maze = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String row = br.readLine();

            for (int j = 0; j < m; j++) {
                maze[i][j] = row.charAt(j) - '0';
            }
        }

        bfs(0,0);

        System.out.println(maze[n - 1][m - 1]);
    }

    private static void bfs(int startY, int startX) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startY, startX});
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curY = cur[0];
            int curX = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextY =  curY + dx[i];
                int nextX = curX + dy[i];

                if (nextY >= 0 && nextX >= 0 && nextY < n && nextX < m) {
                    if (maze[nextY][nextX] != 0 && !visited[nextY][nextX]) {
                        queue.add(new int[]{nextY, nextX});
                        visited[nextY][nextX] = true;
                        maze[nextY][nextX] = maze[curY][curX] + 1;
                    }
                }
            }
        }
    }
}
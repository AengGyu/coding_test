import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int row, col;
    static char[][] field;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳
    static int maxLen = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        field = new char[row][col];
        visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            String input = br.readLine();

            for (int j = 0; j < input.length(); j++) {
                field[i][j] = input.charAt(j);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (field[i][j] == 'L') {
                    bfs(i, j);
                }
            }
        }

        System.out.println(maxLen);
    }

    private static void bfs(int y, int x) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited = new boolean[row][col];

        int count = 0;
        visited[y][x] = true;
        queue.add(new int[]{y, x, count});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int curY = cur[0];
            int curX = cur[1];
            int curLen = cur[2];

            if (maxLen < curLen) {
                maxLen = curLen;
            }

            for (int i = 0; i < 4; i++) {
                int nextY = curY + dy[i];
                int nextX = curX + dx[i];

                if (nextX >= 0 && nextX < col && nextY >= 0 && nextY < row) {
                    if (!visited[nextY][nextX] && field[nextY][nextX] == 'L') {
                        visited[nextY][nextX] = true;
                        queue.add(new int[]{nextY, nextX, curLen + 1});
                    }
                }
            }
        }
    }
}
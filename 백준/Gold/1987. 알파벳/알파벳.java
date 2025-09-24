import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int r, c;
    static char[][] board;
    static Set<Character> visitedSet = new HashSet<>();
    static int MaxCount = 1;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            String row = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = row.charAt(j);
            }
        }

        visitedSet.add(board[0][0]);
        dfs(0,0,1);

        System.out.println(MaxCount);
    }

    private static void dfs(int y, int x, int count) {
        MaxCount = Math.max(MaxCount, count);

        for (int i = 0; i < 4; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];

            if(nextX >= c || nextX < 0 || nextY >= r || nextY < 0) continue;

            if (visitedSet.contains(board[nextY][nextX])) continue;

            visitedSet.add(board[nextY][nextX]);
            dfs(nextY, nextX, count + 1);
            visitedSet.remove(board[nextY][nextX]);
        }

    }
}
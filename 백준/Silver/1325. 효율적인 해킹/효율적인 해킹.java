import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int n, m;
    static List<Integer>[] graph;
    static int[] count;
    static boolean[] visited;

    static Deque<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
        m = Integer.parseInt(st.nextToken()); // 입력 개수

        graph = new ArrayList[n + 1]; // 1~n번 컴퓨터가 신뢰하는 관계를 저장할 리스트
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>(); // i번 컴퓨터가 신뢰하는 컴퓨터를 저장할 리스트
        }

        count = new int[n + 1];
        int max = 0;

        // A가 B를 신뢰하는 경우에는 B를 해킹하면, A도 해킹할 수 있다는 소리다.
        // 3 -> 1, 3 -> 2
        // 3이 1을 신뢰한다고 3이 1을 해킹할 수 있다? ㄴㄴ
        // 1이 3을 해킹할 수 있는 거임
        // 간선 방항이 입력의 반대로 되어야 함
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[b].add(a);
        }

        // 모든 노드에서 bfs
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];

            count[i] = bfs(i);

            if (count[i] > max) {
                max = count[i];
            }
        }

        for (int i = 1; i <= n ; i++) {
            if (count[i] == max) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb);
    }

    private static int bfs(int start) {

        queue.add(start);
        visited[start] = true;

        int cnt = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Integer next : graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
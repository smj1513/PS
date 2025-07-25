import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int M = inputs[0], N = inputs[1], K = inputs[2];

            int[][] graph = new int[N][M];
            boolean[][] visited = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                int[] nodes = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                graph[nodes[1]][nodes[0]] = 1;
            }
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (graph[i][j] == 1 && !visited[i][j]) {
                        dfs(visited, graph, i, j, N, M);
                        cnt++;
                    }
                }
            }

            System.out.println(cnt);
        }
    }

    public static void dfs(boolean[][] visited, int[][] graph, int x, int y, int N, int M) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int drx = dx[i] + x;
            int dry = dy[i] + y;
            if (vaild(drx, dry, N, M) && !visited[drx][dry] && graph[drx][dry] == 1) {
                dfs(visited, graph, drx, dry, N, M);
            }
        }

    }

    public static boolean vaild(int x, int y, int N, int M) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
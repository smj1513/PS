
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int N;
    static int[][] graph;
    static boolean[][] visited;
    static int cnt = 0;
    static int groupCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][];
        visited = new boolean[N][N];
        List<Integer> result = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            graph[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && graph[i][j] == 1) {
                    dfs(i, j);
                    result.add(groupCnt+1);
                    groupCnt = 0;
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
        result.stream().sorted().forEach(System.out::println);


    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int drx = dx[i] + x;
            int dry = dy[i] + y;
            if (vaild(drx, dry) && !visited[drx][dry] && graph[drx][dry] == 1) {
                groupCnt++;
                dfs(drx, dry);
            }
        }
    }

    public static boolean vaild(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}

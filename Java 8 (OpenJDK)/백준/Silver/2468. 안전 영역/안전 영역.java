
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int N;
    static int max = 0;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }
        for (int i = 0; i <= max; i++) {
            visited = new boolean[N][N];
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (!visited[j][k] && map[j][k] > i) {
                        dfs(j, k, i);
                        cnt++;
                    }
                }
            }
            result = Math.max(cnt, result);
        }
        System.out.println(result);
    }

    public static void dfs(int x, int y, int boundary) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isValid(nx, ny) && !visited[nx][ny] && map[nx][ny] > boundary) {
                dfs(nx, ny, boundary);
            }
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}

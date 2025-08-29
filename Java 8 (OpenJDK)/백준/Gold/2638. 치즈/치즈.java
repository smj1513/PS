
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static int N, M;
    static int time;
    static int cheese;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    cheese++;
                }
            }
        }
        while (cheese > 0) {
            bfs(0, 0);
            time++;
        }
        System.out.println(time);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        boolean[][] visited = new boolean[N][M];
        int[][] inEdge = new int[N][M];
        visited[x][y] = true;
        List<int[]> removePoint = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if (isValid(nx, ny) && !visited[nx][ny]) {
                    if (map[nx][ny] != 0) {
                        if (++inEdge[nx][ny] >= 2 && !visited[nx][ny]) {
                            removePoint.add(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                    } else {
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        for (int[] points : removePoint) {
            map[points[0]][points[1]] = 0;
            cheese--;
        }

    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Stream;

public class Solution {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            int max = 0;
            for (int i = 0; i < N; i++) {
                map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            for (int i = 0; i < 100; i++) {
                int cnt = 0;
                boolean[][] visited = new boolean[map.length][map.length];
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        if (!visited[j][k] && map[j][k] > i) {
                            bfs(map, visited, j, k, i);
                            cnt++;
                        }
                    }
                }
                max = Math.max(max, cnt);
            }
            System.out.println("#"+tc+" "+max);
        }
    }

    static void bfs(int[][] map, boolean[][] visited, int x, int y, int day) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited[x][y] = true;
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int drx = dx[i] + current[0];
                int dry = dy[i] + current[1];
                if (valid(drx, dry, map.length) && !visited[drx][dry] && map[drx][dry] > day) {
                    queue.add(new int[]{drx, dry});
                    visited[drx][dry] = true;
                }
            }
        }
    }

    public static boolean valid(int x, int y, int N) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}

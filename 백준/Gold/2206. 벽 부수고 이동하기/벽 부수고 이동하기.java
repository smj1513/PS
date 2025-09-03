//BOJ2206 벽 부수고 이동하기
//www.acmicpc.net/problem/2206

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] map;
    static boolean[][][] visited;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];
        for (int i = 0; i < N; i++) {
            map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 1, 0});//x, y, depth, walls
        visited[0][0][0] = true; //정상 이동
        visited[0][0][1] = true; //벽 부수고 이동
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            int dist = cur[2];
            int broken = cur[3];
            if (x == N - 1 && y == M - 1) {
                return dist;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isValid(nx, ny)) {
                    // 다음 이동할 곳이 벽이 아닌 경우
                    if (map[nx][ny] == 0) {
                        // 해당 상태(broken)로 아직 방문하지 않았다면
                        if (!visited[nx][ny][broken]) {
                            visited[nx][ny][broken] = true;
                            queue.offer(new int[]{nx, ny, dist + 1, broken});
                        }
                    }
                    // 다음 이동할 곳이 벽인 경우
                    else {
                        // 벽을 부술 기회가 남아있고, 벽을 부순 상태로 아직 방문하지 않았다면
                        if (broken == 0 && !visited[nx][ny][1]) {
                            visited[nx][ny][1] = true;
                            // 벽을 부쉈으므로 상태를 1로 변경
                            queue.offer(new int[]{nx, ny, dist + 1, 1});
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int[][] visited;
    static int N, M, K;
    static final int[] dx = { -1, 1, 0, 0 };
    static final int[] dy = { 0, 0, -1, 1 };
    static int startX, startY;
    static int endX, endY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken()) - 1;
        startY = Integer.parseInt(st.nextToken()) - 1;
        endX = Integer.parseInt(st.nextToken()) - 1;
        endY = Integer.parseInt(st.nextToken()) - 1;
        System.out.println(bfs());
//		for (int[] v : visited) {
//			System.out.println(Arrays.toString(v));
//		}
//		for (char[] m : map) {
//			System.out.println(Arrays.toString(m));
//		}

    }

    public static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {startX,startY});
        visited[startX][startY] = 1;
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentTime = visited[current[0]][current[1]];

            for(int i = 0 ; i < 4 ;i++) {
                for(int k = 1; k <= K ;k++) {
                    int nx = current[0] + dx[i]*k;
                    int ny = current[1] + dy[i]*k;

                    // 1. 맵 밖으로 나가거나 벽을 만나면 해당 방향으로 더는 진행 불가
                    if (!isValid(nx, ny) || map[nx][ny] == '#') {
                        break;
                    }

                    // 2. ✨ 핵심 최적화 조건문 ✨
                    //    이미 방문한 곳인데, 현재 경로보다 더 빨리 도착한 경로가 있다면 더 볼 필요 없음
                    if (visited[nx][ny] != 0 && visited[nx][ny] < currentTime + 1) {
                        break;
                    }

                    // 3. 이미 같은 시간에 다른 경로로 방문했다면, 큐에 넣을 필요는 없지만 지나갈 수는 있음
                    if (visited[nx][ny] != 0 && visited[nx][ny] == currentTime + 1) {
                        continue;
                    }

                    // 4. 새로운 지점 발견
                    if (nx == endX && ny == endY) {
                        return currentTime;
                    }

                    visited[nx][ny] = currentTime + 1;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }
        return -1;
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

}

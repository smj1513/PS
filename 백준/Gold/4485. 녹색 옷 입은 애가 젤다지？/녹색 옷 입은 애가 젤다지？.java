
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] distance;
    static int N = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = 0;
        while (N != 0) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            map = new int[N][N];
            distance = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                Arrays.fill(distance[i], Integer.MAX_VALUE);
            }
            dijkstra(0, 0);
            System.out.printf("Problem %d: %d\n", ++tc, distance[N - 1][N - 1]);
        }
    }

    public static void dijkstra(int startX, int startY) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.add(new int[]{startX, startY, map[startX][startY]});
        distance[startX][startY] = map[startX][startY];
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curW = cur[2];
            if (curX == N - 1 && curY == N - 1) {
                break;
            }
            if (visited[curX][curY] || distance[curX][curY] < curW) {
                continue;
            }
            visited[curX][curY] = true;


            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];
                if (isValid(nx, ny) && !visited[nx][ny] && distance[nx][ny] > distance[curX][curY] + map[nx][ny]) {
                    distance[nx][ny] = distance[curX][curY] + map[nx][ny];
                    pq.offer(new int[]{nx, ny, distance[nx][ny]});
                }
            }
        }
    }

    static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}

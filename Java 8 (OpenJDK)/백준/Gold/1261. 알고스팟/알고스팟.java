
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int[][] map;
	static int N, M;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	static boolean[][] visited;
	static int[][] distance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		distance = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			Arrays.fill(distance[i], Integer.MAX_VALUE);
		}
		dijkstra();
		System.out.println(distance[N - 1][M - 1]);

	}

	public static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> distance[a[0]][a[1]]));
		distance[0][0] = 0;
		pq.offer(new int[]{0, 0});
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int x = cur[0];
			int y = cur[1];
			if (visited[x][y]) {
				continue;
			}
			visited[x][y] = true;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (isValid(nx, ny) && !visited[nx][ny] ) {
					if(distance[nx][ny] > distance[x][y] + map[nx][ny]){
					distance[nx][ny] = distance[x][y] + map[nx][ny];
					pq.offer(new int[]{nx, ny});}
				}
			}
		}
	}

	public static boolean isValid(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

}

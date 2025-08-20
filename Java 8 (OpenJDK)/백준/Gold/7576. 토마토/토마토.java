
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, -1, 0, 1};
	static int M, N;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		List<int[]> start = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				if (n == 1) {
					start.add(new int[]{i, j});
				}
			}
		}
		bfs(start);
		int max = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0){
					max = -1;
					System.out.println(max);
					return;
				}
				max = Math.max(max, map[i][j]);
			}
		}
		System.out.println(max-1);

	}

	public static void bfs(List<int[]> points) {
		Queue<int[]> queue = new ArrayDeque<>();
		points.forEach(arr->{
			visited[arr[0]][arr[1]] = true;
			queue.add(arr);
		});
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			for (int i = 0; i < 4; i++) {
				int drx = current[0] + dx[i];
				int dry = current[1] + dy[i];
				if (isValid(drx, dry) && !visited[drx][dry] && map[drx][dry] != -1) {
					map[drx][dry] = map[current[0]][current[1]] + 1;
					queue.add(new int[]{drx, dry});
					visited[drx][dry] = true;
				}
			}
		}
	}

	public static boolean isValid(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
}

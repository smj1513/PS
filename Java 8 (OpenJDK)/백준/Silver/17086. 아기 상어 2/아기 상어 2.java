
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[][] map;
	static boolean[][] visited;
	static int N, M;
	static int[] dx = {0, -1, 0, 1, -1, -1, 1, 1};
	static int[] dy = {-1, 0, 1, 0, -1, 1, -1, 1};
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		List<int[]> starts = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					map[i][j] = 0;
					starts.add(new int[]{i, j});
				}
			}
		}
		bfs(starts);
		for (int i = 0 ; i < N ;i++){
			for (int j = 0 ; j<M;j++){
				max = Math.max(map[i][j], max);
			}
		}
		System.out.println(max);
	}

	public static void bfs(List<int[]> starts) {
		Queue<int[]> queue = new ArrayDeque<>();
		for (int[] start : starts) {
			queue.add(new int[]{start[0], start[1]});
			visited[start[0]][start[1]] = true;
		}
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			for (int i = 0; i < 8; i++) {
				int nx = current[0] + dx[i];
				int ny = current[1] + dy[i];
				if (isValid(nx, ny) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.add(new int[]{nx, ny});
					map[nx][ny] = map[current[0]][current[1]] + 1;
				}
			}
		}
	}

	public static boolean isValid(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}


}

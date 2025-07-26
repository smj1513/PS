
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Main {
	static int[][] graph;
	static int N, M;
	static boolean[][] visited;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] NM = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = NM[0];
		M = NM[1];
		graph = new int[N][];
		visited = new boolean[N][M];
		int targetX = -1, targetY = -1;
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			graph[i] = new int[M];
			for (int j = 0; j < M; j++) {
				int val = Integer.parseInt(line[j]);
				graph[i][j] = val;
				if (val == 2) {
					targetX = i;
					targetY = j;
				}
			}
		}
		bfs(targetX, targetY);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append((graph[i][j] == 1 ? -1 : (graph[i][j] == 0 ? 0 : graph[i][j] - 2))).append(" ");
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}

	public static boolean valid(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

	public static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		visited[x][y] = true;
		queue.add(new int[]{x, y});
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			for (int i = 0; i < 4; i++) {
				int drx = current[0] + dx[i];
				int dry = current[1] + dy[i];
				if (valid(drx, dry) && !visited[drx][dry] && graph[drx][dry] != 0) {
					visited[drx][dry] = true;
					graph[drx][dry] = graph[current[0]][current[1]] + 1;
					queue.add(new int[]{drx, dry});
				}
			}
		}
	}
}

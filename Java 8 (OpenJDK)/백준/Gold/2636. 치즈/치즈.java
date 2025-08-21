
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
* 색종이-2 와 유사한 전략. 현재 영역이 0이면서 next가 1인 경우 테두리를 의미함.
* 단, bfs로 탐색하면서 내부 공기층은 피해야 하므로 해당 부분을 가지치기 하는 영역이 추가됨.
*
* */
public class Main {
	static int[][] map;
	static int N, M;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int cheese = 0;
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1){
					cheese++;
				}
			}
		}
		int prev = 0;
		while(cheese!=0){
			prev = cheese;
			bfs(0, 0);
			cnt++;
		}
		System.out.println(cnt);
		System.out.println(prev);

	}

	public static void bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		visited[x][y] = true;
		queue.add(new int[]{x, y});
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			for (int i = 0; i < 4; i++) {
				int drx = current[0] + dx[i];
				int dry = current[1] + dy[i];
				if (isValid(drx, dry) && !visited[drx][dry] && map[current[0]][current[1]] == 0) {
					visited[drx][dry] = true;
					if(map[drx][dry] == 1){
						map[drx][dry]--;
						cheese--;
						continue;
					}
					queue.add(new int[]{drx, dry});
				}
			}
		}
	}

	public static boolean isValid(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] avg = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int R = Integer.parseInt(st.nextToken());
				int G = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				avg[i][j] = (R + G + B) / 3;
			}
		}
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (avg[i][j] >= T) {
					avg[i][j] = 255;
				} else {
					avg[i][j] = 0;
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && avg[i][j] == 255) {
					dfs(avg, visited, i, j);
					cnt++;
				}
			}
		}
		System.out.println(cnt);

	}

	public static void dfs(int[][] avg, boolean[][] visited, int x, int y) {
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			if (isValid(nx, ny) && !visited[nx][ny] && avg[nx][ny] == 255) {
				dfs(avg, visited, nx, ny);
			}
		}
	}

	static boolean isValid(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}
}

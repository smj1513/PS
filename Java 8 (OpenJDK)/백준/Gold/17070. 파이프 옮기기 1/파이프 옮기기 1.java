
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int N;
	static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		dp = new int[N + 1][N + 1][3];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(dfs(1, 1, 0));

	}

	public static int dfs(int x, int y, int dir) {
		if (!isValid(x, y) || map[x][y] == 1) {
			return 0;
		}
		if (dir == 2 && (map[x - 1][y] == 1 || map[x][y - 1] == 1)) {
			return 0;
		}
		if (dp[x][y][dir] != 0) {
			return dp[x][y][dir];
		}
		if (x == N && y == N) {
			return 1;
		}

		if (x == 1 && y == 1) {
			return dp[x][y][dir] += dfs(x, y+1, 0);
		}
		// 가로
		if (dir == 0) {
			dp[x][y][dir] += dfs(x, y+1, 0) + dfs(x + 1, y+1, 2);
		} else if (dir == 1) {//세로
			dp[x][y][dir] += dfs(x+1, y , 1) + dfs(x + 1, y + 1, 2);
		} else if (dir == 2) {//대각
			dp[x][y][dir] += dfs(x, y+1, 0) + dfs(x+1, y , 1) + dfs(x + 1, y + 1, 2);
		}
		return dp[x][y][dir];
	}

	public static boolean isValid(int x, int y) {
		return 1 <= x && x <= N && 1 <= y && y <= N;
	}

}

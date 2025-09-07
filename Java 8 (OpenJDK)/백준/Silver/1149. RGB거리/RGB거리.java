
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] rgbs;
	static int[][] dp; //
	static int N;
	static int test_cnt1 = 0, test_cnt2 = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		rgbs = new int[N + 1][3];
		dp = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				rgbs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int min = Math.min(dfs(0, 0), Math.min(dfs(0, 1), dfs(0, 2)));
//		int t = Math.min(dfs_bf(0, 0), Math.min(dfs_bf(0, 1), dfs_bf(0, 2)));

		System.out.println(min);
//		System.out.println(test_cnt1);
//		System.out.println(test_cnt2);
	}

	public static int dfs(int row, int col) {
		if (dp[row][col] != 0) {
			return dp[row][col];
		}
		dp[row][col] = rgbs[row][col];
		if (row == N) {
			return dp[row][col];
		}
		dp[row][col] += Math.min(dfs(row + 1, (col + 1) % 3), dfs(row + 1, (col + 2) % 3));
		test_cnt1++;
		return dp[row][col];
	}

//	public static int dfs_bf(int row, int col) {
//		int sum = rgbs[row][col];
//		if (row == N) {
//			return sum;
//		}
//		sum += Math.min(dfs_bf(row + 1, (col + 1) % 3), dfs_bf(row + 1, (col + 2) % 3));
//		test_cnt2++;
//
//		return sum;
//	}
}

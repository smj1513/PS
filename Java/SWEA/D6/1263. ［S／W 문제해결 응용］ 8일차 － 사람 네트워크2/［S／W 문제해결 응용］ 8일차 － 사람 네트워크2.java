
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[][] distance;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			distance = new int[N + 1][N + 1];

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					int val = Integer.parseInt(st.nextToken());
					if (i == j) {
						distance[i][j] = 0;
					} else if (val == 0) {
						distance[i][j] = 1_000_000_000;
					} else {
						distance[i][j] = val;
					}
				}
			}
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
					}
				}
			}
			int[] cc = new int[N + 1];
			cc[0] = 1_000_000_000;
			for (int i = 1; i <= N; i++) {
				cc[i] = Arrays.stream(distance[i]).sum();
			}
			System.out.println("#" + tc + " " + Arrays.stream(cc).min().getAsInt());


		}
	}
}

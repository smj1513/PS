import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] map;

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n + 1][n + 1];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		long[][][] dp = new long[n + 1][n + 1][3];
		dp[1][2][0] = 1;
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(map[i][j] == 1) {
					continue;
				}
				
				if(j - 1 >= 1 && map[i][j] == 0) {	// 가로 이동
					dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][2];
				}
				
				if(i - 1 >= 1 && map[i][j] == 0) {	// 세로 이동
					dp[i][j][1] += dp[i - 1][j][1] + dp[i - 1][j][2];
				}
				
				if(i - 1 >= 1 && j - 1 >= 1) {		// 대각선 이동
					if(map[i][j] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) {
						dp[i][j][2] += dp[i - 1][j - 1][2] + dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1];
					}
				}
			}
		}
		
		long result = dp[n][n][0] + dp[n][n][1] + dp[n][n][2];
		
		System.out.println(result);

	}

}

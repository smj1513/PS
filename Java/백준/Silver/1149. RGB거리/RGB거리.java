import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] prices, dp;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		prices = new int[n][3];
		dp = new int[n][3];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			prices[i][0] = Integer.parseInt(st.nextToken());
			prices[i][1] = Integer.parseInt(st.nextToken());
			prices[i][2] = Integer.parseInt(st.nextToken());
			
			Arrays.fill(dp[i], -1);
		}
		
		int result = Math.min(func(n - 1, 0), Math.min(func(n - 1, 1), func(n - 1, 2)));
		
		System.out.println(result);

	}
	
	public static int func(int n, int color) {
		if(n == 0) {
			return prices[0][color];
		}
		
		if(dp[n][color] != -1) {
			return dp[n][color];
		}
		
		if(color == 0) {
			dp[n][0] = prices[n][0] + Math.min(func(n - 1, 1), func(n - 1, 2));
		} else if(color == 1) {
			dp[n][1] = prices[n][1] + Math.min(func(n - 1, 0), func(n - 1, 2));
		} else {
			dp[n][2] = prices[n][2] + Math.min(func(n - 1, 0), func(n - 1, 1));
		}
		
		return dp[n][color];
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int n;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		dp = new int[n + 1];
		dp[1] = 0;
		
		for(int i = 2; i <= n; i++) {
			int min = dp[i - 1] + 1;
			
			if(i % 2 == 0) {
				min = Math.min(min, dp[i / 2] + 1);
			}
			
			if(i % 3 == 0) {
				min = Math.min(min, dp[i / 3] + 1);
			}
			
			dp[i] = min;
		}

		System.out.println(dp[n]);
		
	}
	
}

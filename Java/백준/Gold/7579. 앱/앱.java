import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[] memory, cost, dp;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		memory = new int[n + 1];
		cost = new int[n + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int sumCost = 0;
		for(int i = 1; i <= n; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			sumCost += cost[i];
		}
		
		dp = new int[sumCost + 1];
		for(int i = 1; i <= n; i++) {
			int cur = cost[i];
			for(int j = sumCost; j >= cur; j--) {
				dp[j] = Math.max(dp[j], dp[j - cur] + memory[i]);
			}
		}
		
		for(int i = 0; i <= sumCost; i++) {
			if(dp[i] >= m) {
				System.out.println(i);
				return;
			}
		}
		
	}

}

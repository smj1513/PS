import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, k;
	static int[] weight, value;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		weight = new int[n + 1];
		value = new int[k + 1];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[n + 1][k + 1];		
		for(int i = 1; i <= n; i++) {
			int itemWeight = weight[i];
			int itemValue = value[i];
			
			for(int j = 1; j <= k; j++) {
				if(itemWeight <= j) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - itemWeight] + itemValue);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		
		System.out.println(dp[n][k]);

	}

}

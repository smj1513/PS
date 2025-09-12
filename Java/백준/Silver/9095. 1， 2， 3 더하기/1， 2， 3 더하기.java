import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int n;
	static int[] dp;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        dp = new int[12];
    	Arrays.fill(dp, -1);
    	
    	dp[1] = 1;
    	dp[2] = 2;
    	dp[3] = 4;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
        	n = Integer.parseInt(br.readLine());
        	
        	sb.append(dfs(n)).append("\n");
        }
        
        System.out.println(sb);

	}
    
    public static int dfs(int n) {
    	if(dp[n] != -1) {
    		return dp[n];
    	}
    	
    	dp[n] = dfs(n - 1) + dfs(n - 2) + dfs(n - 3);
    	return dp[n];
    }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[][] city, dp;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        city = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dp = new int[m + 1][n + 1];
        dp[1][1] = 1;
        
        for(int i = 1; i <= m; i++) {
        	for(int j = 1; j <= n; j++) {
        		if(i == 1 && j == 1) continue;
        		if(city[i][j] == 1) {
        			dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        		} else {
        			dp[i][j] = 0;
        		}
        	}
        }
        
        System.out.println(dp[m][n] == 1 ? "Yes" : "No");

    }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] map, dp;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        
        map = new int[m][n];
        for(int i = 0; i < m; i++) {
        	char[] str = br.readLine().toCharArray();
        	for(int j = 0; j < n; j++) {
        		map[i][j] = str[j] - '0';
        	}
        }
        
        dp = new int[m][n];
        for(int[] row : dp) {
        	Arrays.fill(row, -1);  
        }
        visited = new boolean[m][n];
        
        for(int j = 0; j < n; j++) {
        	if(map[0][j] == 0) {
        		if(dfs(0, j)) {
        			System.out.println("YES");
        			return;
        		}
        	}
        }
        
        System.out.println("NO");

	}
    
    public static boolean dfs(int x, int y) {
    	if(map[x][y] == 1) {
    		return false;
    	}
    	
    	if(dp[x][y] != -1) {
    		return dp[x][y] == 1;
    	}
    	
    	if(visited[x][y]) {
    		return false;
    	}
    	visited[x][y] = true;
    	
    	if(x == m - 1) {
    		dp[x][y] = 1;
    		visited[x][y] = false;
    		return true;
    	}
    	
    	boolean flag = false;
    	for(int i = 0; i < 4; i++) {
    		int nx = x + dx[i];
    		int ny = y + dy[i];
    		
    		if(nx < 0 || ny < 0 || nx >= m || ny >= n) {
    			continue;
    		}
    		
    		if(dfs(nx, ny)) {
    			flag = true;
    			break;
    		}
    	}
    	
    	dp[x][y] = flag ? 1 : 0;
    	visited[x][y] = false;
    	return flag;
    }

}

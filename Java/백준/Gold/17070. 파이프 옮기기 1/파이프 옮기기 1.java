import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, cnt;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		for(int i = 0; i < n; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cnt = 0;
		dfs(0, 1, 0);
		
		System.out.println(cnt);
	}
	
	public static void dfs(int x, int y, int dir) {
		if(x == n - 1 && y == n - 1) {
			cnt++;
			return;
		}
				
		
		if(dir == 0 || dir == 2) {
			if(y < n - 1 && map[x][y + 1] == 0) {
				dfs(x, y + 1, 0);
			}
		}
		
		if(dir == 1 || dir == 2) {
			if(x < n - 1 && map[x + 1][y] == 0) {
				dfs(x + 1, y, 1);
			}
		}
		
		if(dir == 0 || dir == 1 || dir == 2){
			if(x < n - 1 && y < n - 1 && map[x + 1][y] == 0 && map[x][y + 1] == 0 && map[x + 1][y + 1] == 0) {
				dfs(x + 1, y + 1, 2);
			}
		}
	}

}

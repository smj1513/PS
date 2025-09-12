import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[n][n];
		dfs(0, 0);
		
		System.out.println(sb.length() == 0 ? "Hing" : sb);

	}
	
	public static void dfs(int x, int y) {
		if(map[x][y] == -1) {
			sb.append("HaruHaru");
			return;
		}
		
		visited[x][y] = true;
		int k = map[x][y];
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i] * k;
			int ny = y + dy[i] * k;
			
			if(nx < 0 || ny < 0 || nx >= n || ny >= n) {
				continue;
			}
			
			if(visited[nx][ny]) {
				continue;
			}
			
			visited[nx][ny] =true;
			dfs(nx, ny);
			visited[nx][ny] = false;
		}
	}

}

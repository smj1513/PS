import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] maze, acSum;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		maze = new int[n][m];
		acSum = new int[n + 1][m + 1];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				int candy = Integer.parseInt(st.nextToken());
				maze[i][j] = candy;
				acSum[i + 1][j + 1] = candy;
			}
		}		
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				int max = Math.max(Math.max(acSum[i - 1][j - 1], acSum[i - 1][j]), acSum[i][j - 1]);
				acSum[i][j] += max;
			}
		}
		
		System.out.println(acSum[n][m]);

	}

}

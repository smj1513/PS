import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		arr = binomial();
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			sb.append(arr[m][n]).append("\n");
		}

		System.out.println(sb);
		
	}
	
	public static int[][] binomial() {
		arr = new int[31][31];
		
		for(int i = 0; i<= 30; i++) {
			int jRange = Math.min(i, 30);
			for(int j = 0; j <= jRange; j++) {
				if(j == 0 || j == i) {
					arr[i][j] = 1;
					continue;
				} else {
					arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
				}
			}
		}
		
		return arr;
	}

}

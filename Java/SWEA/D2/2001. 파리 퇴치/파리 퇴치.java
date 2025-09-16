import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int arr[][] = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int max = 0;
			for(int i = 0; i <= N-M; i++) {
				for(int j = 0; j <= N-M; j++) {
					int sum = 0;
                    for (int r = i; r < i + M; r++) {
                        for (int c = j; c < j + M; c++) {
                            sum += arr[r][c];
                        }
                    }
                    max = Math.max(max, sum);
				}
			}
			
			System.out.println("#" + test_case +" " + max);
		}
	}

}

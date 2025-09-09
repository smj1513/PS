import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] ability;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		ability = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				ability[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int size = (1 << n) - 1;
		
		int half = n / 2;
		for(int i = 0; i <= size; i++) {
			if((i & 1) == 0) {
				continue;
			}
			
			if(Integer.bitCount(i) != half) {
				continue;
			}
			
			int other = (~i) & size;
			int sub = Math.abs(calculate(i) - calculate(other));
			if(sub == 0) {
				System.out.println(0);
				return;
			}
			
			result = Math.min(result, sub);
		}
		
		System.out.println(result);

	}
	
	static int calculate(int bit) {
		int sum = 0;
		
		for(int i = 0; i < n; i++) {
			if((bit & (1 << i)) == 0) {
				continue;
			}
			
			for(int j = i + 1; j < n; j++) {
				if((bit & (1 << j)) == 0) {
					continue;
				}
				
				sum += ability[i][j] + ability[j][i];
			}
		}
		
		return sum;
	}

}

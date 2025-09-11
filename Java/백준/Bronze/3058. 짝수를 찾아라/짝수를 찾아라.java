import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			int[] arr = new int[7];
			
			int sum = 0;
			int min = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 7; i++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i] = num;
				if(num % 2 == 0) {
					sum += num;
					min = Math.min(num, min);
				}
			}
			
			sb.append(sum).append(" ").append(min).append("\n");
		}
		
		System.out.println(sb);
		
	}

}

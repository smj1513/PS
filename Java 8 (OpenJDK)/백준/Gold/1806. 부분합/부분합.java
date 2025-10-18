
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int[] dp = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i - 1] = Integer.parseInt(st.nextToken());
			dp[i] = dp[i - 1] + arr[i - 1];
		}
		if(dp[N] < S){
			System.out.println(0);
			return;
		}
		int minLen = Integer.MAX_VALUE;
		for (int i = 0, j = 1; i < j; ) {
			int start = dp[i];
			int end = dp[j];
			int n = end - start;
			if (n >= S) {
				minLen = Math.min(minLen, j - i);
				i++;
			} else {
				if (j < N) {
					j++;
				}else{
					break;
				}
			}
		}
		System.out.println(minLen == Integer.MAX_VALUE ? 0:minLen );
	}
}


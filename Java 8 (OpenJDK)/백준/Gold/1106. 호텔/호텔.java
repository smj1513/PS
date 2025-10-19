
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<int[]> costPairs = new ArrayList<>();
		//value가 높은 순서대로, 같다면 코스트가 작은 순서대로 정렬
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			costPairs.add(new int[]{a, b});
		}
		int[] dp = new int[C + 101];
		Arrays.fill(dp, 1_000_000_000);
		dp[0] = 0;
		for (int[] kv : costPairs) {
			int cost = kv[0];
			int value = kv[1];
			for (int i = value; i < C + 101; i++) {
				dp[i] = Math.min(dp[i], dp[i - value] + cost);
			}
		}
		int result = Integer.MAX_VALUE;
		for (int i = C; i<dp.length;i++){
			result = Math.min(dp[i], result);
		}
		System.out.println(result);


	}
}

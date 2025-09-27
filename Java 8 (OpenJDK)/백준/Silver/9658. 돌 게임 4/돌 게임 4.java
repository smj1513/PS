
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// dp[i] : 돌이 i개 남았을 때 상근이가 이길 수 있는가?
		// true: 이긴다, false: 진다
		boolean[] dp = new boolean[N + 1];

		if (N >= 1) {
			dp[1] = false; // SK가 1개 -> 패배
		}
		if (N >= 2) {
			dp[2] = true; // sk가 1개 -> ky가 1개 -> 승리
		}
		if (N >= 3) {
			dp[3] = false; // SK가 1개 -> KY가 1개 -> SK가 1개 패배
		}
		if (N >= 4) {
			dp[4] = true; // SK가 3개 -> KY가 1개 (승리)
		}

		for (int i = 5; i <= N; i++) {
			// i-1, i-3, i-4 상태 중 하나라도 상대방이 지는(false) 경우가 있다면 나는 이길 수 있다.
			if (!dp[i - 1] || !dp[i - 3] || !dp[i - 4]) {
				dp[i] = true;
			} else {
				dp[i] = false;
			}
		}

		if (dp[N]) {
			System.out.println("SK");
		} else {
			System.out.println("CY");
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			boolean[] isRead = new boolean[10];
			int cnt = 0;
			long N = Integer.parseInt(br.readLine());
			int i;
			long K = N;
			for (i = 0; cnt < 10; i++) {
				char[] arr = String.valueOf(K).toCharArray();
				for (char c : arr) {
					int num = c - '0';
					if (!isRead[num]) {
						isRead[num] = true;
						cnt++;
					}
				}
				K += N;
			}
			System.out.println("#" + tc + " " + i*N);
		}
	}
}

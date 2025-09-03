
import java.util.Scanner;

/*
 * f(1) = 1
 * f(2) = 3
 * f(3) = 5
 * f(4) = 11
 * f(5) = 21
 * */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N + 1];
		if (N == 1) {
			System.out.println(1);
			return;
		}
		if (N == 2) {
			System.out.println(3);
			return;
		}
		if (N == 3) {
			System.out.println(5);
			return;
		}
		if (N == 4) {
			System.out.println(11);
			return;
		}

		dp[1] = 1;
		dp[2] = 3;
		dp[3] = 5;
		dp[4] = 11;
		
		for (int i = 5; i <= N; i++) {
			if (i % 2 == 0) {
				dp[i] = ((dp[i - 1] * 2) + 1) % 10007;
			} else {
				dp[i] = ((dp[i - 1] * 2) - 1) % 10007;
			}
		}
		System.out.println(dp[N]);
	}
}

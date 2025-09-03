
import java.util.Scanner;

// 4차 접근 - 하향식 dp
public class Main {
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        dp = new int[N + 1];
        if (N == 1) {
            System.out.println(0);
            return;
        }
        if (N == 2 || N == 3) {
            System.out.println(1);
            return;
        }

        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        int min = 0;
        for (int i = 4; i <= N; i++) {
            //1을 뺀다
            min = dp[i - 1] + 1;

//			X가 3으로 나누어 떨어지면, 3으로 나눈다.
            if (i % 3 == 0) {
                min = Math.min(min, dp[i / 3] + 1);
            }
//			X가 2로 나누어 떨어지면, 2로 나눈다.
            if (i % 2 == 0) {
                min = Math.min(min, dp[i / 2] + 1);
            }
            dp[i] = min;
        }
        System.out.println(dp[N]);
    }
}



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* // 3xN을 2x1, 1x2만으로만 구성하는 경우의 수는 N이 홀수일 경우는 발생하지 않는다.
 * f(1) = 0
 * f(2) = 3
 * f(3) = 0
 * */
public class Main {
    static int[] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        //3xi의 테이블을 채울 수 있는 경우의 수
        if (N == 0) {
            System.out.println(1);
            return;
        }
        if (N == 1) {
            System.out.println(0);
            return;
        }
        if (N == 2) {
            System.out.println(3);
            return;
        }
        if (N == 3) {
            System.out.println(0);
            return;
        }
        dp = new int[N + 1];
        dp[0] = 1;
        dp[2] = 3;

        int result = dfs(N);
        System.out.println(result);
    }

    public static int dfs(int i) {
        if (i < 0 || i % 2 == 1) {
            return 0;
        }
        if (dp[i] != 0) {
            return dp[i];
        }
        dp[i] = dfs(i - 2) * 3;
        for (int j = 4; j <= N; j++)
            dp[i] += dfs(i - j) * 2;
        return dp[i];
    }
}

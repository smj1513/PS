
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] dp = new int[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i <= 10000; i++) {
            dp[i] = 1;
        }
        for (int i = 2; i <= 10000; i++) {
            dp[i] += dp[i - 2];
        }
        for (int i = 3; i <= 10000; i++) {
            dp[i] += dp[i - 3];
        }

        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            System.out.println(dp[N]);
        }
    }

}

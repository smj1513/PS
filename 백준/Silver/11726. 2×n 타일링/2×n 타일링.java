
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dp;
    /*
    * f(1) = | -> 1
    * f(2) = ||, = ->2
    * f(3) => |=, =|, ||| -> 3
    * f(4) => ||||, ==, |=|, =||,||= -> 5
    * f(n) => f(n-1) + f(n-2)
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        dp[1] = 1;
        if (N >= 2) {
            dp[2] = 2;   
        }
        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2])%10_007;
        }
        System.out.println(dp[N]);
    }
}

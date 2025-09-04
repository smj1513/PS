
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    static int N, M;

    //mCn = m-1Cn + m-1Cn-1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            dp = new int[M + 1][N + 1];// nCr을 구하는 조합
            if (M == N) {
                System.out.println(1);
                continue;
            }
            if (N == 1) {
                System.out.println(M);
                continue;
            }
            dp[0][0] = 0;
            dp[2][1] = 2;
            for (int i = 1; i <= M; i++) {
                dp[i][1] = i;
                if (i <= N) {
                    dp[i][i] = 1;
                }
            }
            for (int i = 3; i <= M; i++) {
                for (int j = 2; j <= N; j++) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
            System.out.println(dp[M][N]);

        }
    }

}

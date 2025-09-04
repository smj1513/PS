
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    static int[][] map;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } 
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {

                int prevMax = Math.max(dp[i-1][j], dp[i][j-1]);
                prevMax = Math.max(prevMax, dp[i-1][j-1]);

                dp[i][j] = map[i][j] + prevMax;
            }
        }

        System.out.println(dp[N][M]);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] RGB;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        RGB = new int[N][3];
        dp = new int[N + 1][3]; // 1,2,3....N번째를 규칙에 맞게 칠하는 최소 비용
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                RGB[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < 3; k++) {
            dp[1][k] = RGB[0][k];
            dp[1][(k + 1) % 3] = 1_000_000_000;
            dp[1][(k + 2) % 3] = 1_000_000_000;
            for (int i = 2; i <= N; i++) {
                for (int j = 0; j < 3; j++) {
                    int mid = (j + 1) % 3;
                    int right = (j + 2) % 3;
                    dp[i][j] = Math.min(RGB[i - 1][j] + dp[i - 1][right], RGB[i - 1][j] + dp[i - 1][mid]);
                }
            }
            min = Math.min(min, Math.min(dp[N][(k + 1) % 3], dp[N][(k + 2) % 3]));
        }
        System.out.println(min);
    }
}

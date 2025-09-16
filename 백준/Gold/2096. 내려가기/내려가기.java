
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][][] dp; //i j 경로로 내려왔을 때의 현재까지의 최댓값 : [i][j][0], 최솟값[i][j][1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][3];
        dp = new int[N + 1][3][2];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j][1] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    dp[i][j][0] = map[i - 1][j] + Math.max(dp[i - 1][0][0], dp[i - 1][1][0]);
                    dp[i][j][1] = map[i - 1][j] + Math.min(dp[i - 1][0][1], dp[i - 1][1][1]);
                }
                if (j == 1) {
                    dp[i][j][0] = map[i - 1][j] + Math.max(dp[i - 1][0][0], Math.max(dp[i - 1][1][0], dp[i - 1][2][0]));
                    dp[i][j][1] = map[i - 1][j] + Math.min(dp[i - 1][0][1], Math.min(dp[i - 1][1][1], dp[i - 1][2][1]));
                }
                if (j == 2) {
                    dp[i][j][0] = map[i - 1][j] + Math.max(dp[i - 1][1][0], dp[i - 1][2][0]);
                    dp[i][j][1] = map[i - 1][j] + Math.min(dp[i - 1][1][1], dp[i - 1][2][1]);
                }
            }
        }
        int max = Math.max(dp[N][0][0], Math.max(dp[N][1][0], dp[N][2][0]));
        int min = Math.min(dp[N][0][1], Math.min(dp[N][1][1], dp[N][2][1]));
        System.out.println(max + " " + min);

    }
//
//    public static int dfs(int i, int j, boolean isMax) {
//        if (isMax && dp[i][j][0] != 0) {
//            return dp[i][j][0];
//        }
//        if (!isMax && dp[i][j][1] != 0) {
//            return dp[i][j][1];
//        }
//        if (i == 0) {
//            return 0;
//        }
//        // 최대값을 구하기 위한 초기화
//        int maxPrev = Integer.MIN_VALUE;
//// 최소값을 구하기 위한 초기화
//        int minPrev = Integer.MAX_VALUE;
//
//// 현재 j로 올 수 있는 이전 j의 범위(k)를 순회
//// k의 범위: Math.max(0, j - 1) 부터 Math.min(2, j + 1) 까지
//        for (int k = Math.max(0, j - 1); k <= Math.min(2, j + 1); k++) {
//            maxPrev = Math.max(maxPrev, dfs(i - 1, k, true));
//            minPrev = Math.min(minPrev, dfs(i - 1, k, false));
//        }
//
//// dp 테이블 갱신
//        dp[i][j][0] = map[i - 1][j] + maxPrev; // 최대값
//        dp[i][j][1] = map[i - 1][j] + minPrev; // 최소값dp[i][j][1] = map[i - 1][j] + Math.min(dfs(i - 1, 1, false), dfs(i - 1, 2, false));
//
//        return isMax ? dp[i][j][0] : dp[i][j][1];
//    }

}
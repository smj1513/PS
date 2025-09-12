
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] distance;
    static int[][] dp;
    static int ALL_VISITED = 0;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        distance = new int[N][N];
        dp = new int[N][1 << N]; // 1 * 2^n
        ALL_VISITED = (1 << N) - 1;
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                distance[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dfs(0, 1));

    }

    private static int dfs(int from, int visited) {
        if (visited == ALL_VISITED) {
            return distance[from][0] == 0 ? 1_000_000_000: distance[from][0];
        }
        if (dp[from][visited] != -1) {
            return dp[from][visited];
        }
        dp[from][visited] = 1_000_000_000;

        for (int to = 0; to < N; to++) {
            // (visited & ( 1<< to)) == 0 : 방문하지않았다면
            // distance[from][to] != 0 : 길이 존재하는 도시
            if ((visited & (1 << to)) == 0 && distance[from][to] != 0) {
                //visted | (1 << to) : 현재 도시를 방문했다고 체크하고 재귀 호출
                int newCost = distance[from][to] + dfs(to, visited | (1 << to));
                dp[from][visited] = Math.min(dp[from][visited], newCost);
            }
        }
        return dp[from][visited];

    }
}

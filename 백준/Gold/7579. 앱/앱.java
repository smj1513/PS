
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class App {
    int memory;
    int cost;

    public App(int memory, int cost) {
        this.memory = memory;
        this.cost = cost;
    }
}

public class Main {
    static App[] apps;
    static int[][] dp;
    static int N, M;
    static int minCost = Integer.MAX_VALUE;
    static List<Integer> costs = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        apps = new App[N];
        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int allCost = 0;
        for (int i = 0; i < N; i++) {
            int memory = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st2.nextToken());
            allCost += cost;
            apps[i] = new App(memory, cost);
        }

        dp = new int[N + 1][allCost + 1];
        for (int i = 1; i <= N; i++) {
            App current = apps[i - 1];
            for (int j = 0; j <= allCost; j++) {
                if (j < current.cost) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], current.memory + dp[i - 1][j - current.cost]);
                }
            }
        }
        for (int i = 0; i <= allCost; i++) {

            if (dp[N][i] >= M) {
                minCost = i;
                break;
            }
        }
        System.out.println(minCost);
    }


}

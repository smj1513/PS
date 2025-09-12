
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Class {
    int important, cost;

    public Class(int important, int cost) {
        this.important = important;
        this.cost = cost;
    }
}

public class Main {
    static Class[] classes;
    static int[][] dp;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        classes = new Class[K + 1];
        dp = new int[K + 1][N + 1];
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int imp = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            classes[i] = new Class(imp, cost);
            Arrays.fill(dp[i], -1);
        }
        System.out.println(dfs(K, N));

    }

    public static int dfs(int idx, int boundary) {
        if (dp[idx][boundary] != -1) {
            return dp[idx][boundary];
        }
        if (idx == 0 || boundary == 0) {
            return 0;
        }
        if (classes[idx].cost > boundary) {
            dp[idx][boundary] = dfs(idx - 1, boundary);
        } else if (classes[idx].cost <= boundary) {
            dp[idx][boundary] = Math.max(dfs(idx - 1, boundary), classes[idx].important + dfs(idx - 1, boundary - classes[idx].cost));
        }
        return dp[idx][boundary];
    }
}

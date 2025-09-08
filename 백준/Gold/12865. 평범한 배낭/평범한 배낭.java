
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Knap {
    int w;
    int v;

    public Knap(int w, int v) {
        this.w = w;
        this.v = v;
    }
}


public class Main {
    static Knap[] knaps;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        knaps = new Knap[N + 1];
        dp = new int[N + 1][K+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            knaps[i] = new Knap(w, v);
        }
        System.out.println(dfs(N, K));
    }

    public static int dfs(int idx, int boundary) {
        if (dp[idx][boundary] != 0) {
            return dp[idx][boundary];
        }
        if (idx == 0 || boundary == 0) {
            return 0;
        } else if (idx > 0 && knaps[idx].w > boundary) {
            dp[idx][boundary] = dfs(idx - 1, boundary);
        } else if (idx > 0 && knaps[idx].w <= boundary) {
            dp[idx][boundary] = Math.max(knaps[idx].v + dfs(idx - 1, boundary - knaps[idx].w), dfs(idx - 1, boundary));
        }
        return dp[idx][boundary];
    }
}

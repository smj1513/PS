
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Knap {
    int v, c;

    public Knap(int v, int c) {
        this.v = v;
        this.c = c;
    }
}

public class Solution {
    static Knap[] knaps;
    static int[][] dp;
    static int N, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            knaps = new Knap[N + 1];
            dp = new int[N + 1][C + 1];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                knaps[i] = new Knap(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                Arrays.fill(dp[i], -1);
            }
            System.out.println("#"+tc+" "+dfs(N, C));
        }
    }

    public static int dfs(int idx, int boundary) {
        if (dp[idx][boundary] != -1) {
            return dp[idx][boundary];
        }

        if (idx == 0 || boundary == 0) {
            return 0;
        }
        else if (boundary < knaps[idx].v) {
            dp[idx][boundary] = dfs(idx - 1, boundary);
        } else if (boundary >= knaps[idx].v) {
            dp[idx][boundary] = Math.max(dfs(idx - 1, boundary), knaps[idx].c + dfs(idx - 1, boundary - knaps[idx].v));
        }
        return dp[idx][boundary];
    }
}

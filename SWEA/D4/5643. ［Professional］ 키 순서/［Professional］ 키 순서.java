
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, cnt;
    static int[][] adj;
    static int[][] radj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            adj = new int[N + 1][N + 1];
            radj = new int[N + 1][N + 1];
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                radj[b][a] = adj[a][b] = 1; // a보다 b가 키가 크다.
            }
            int ans = 0;
            for (int i = 1; i <= N; i++) {//모든 학생에 대해 자신보다 키가 큰 학생 탐색, 작은 학생 탐색
                cnt = 0;
                boolean[] visited = new boolean[N + 1];
                dfs(adj, i, visited); //수행하고 나면 count에는 나보다 큰 학생 수 저장
                dfs(radj, i, visited); //수행하고 나면 count에는 나보다 큰 학생 수 저장
                if (cnt == N - 1) ++ans;
            }
            System.out.println("#" + tc + " " + ans);
        }
    }

    private static void dfs(int[][] graph, int cur, boolean[] visited) {
        visited[cur] = true;
        for (int i = 1; i <= N; i++) {
            if (graph[cur][i] == 1 && !visited[i]) {
                ++cnt;
                dfs(graph, i, visited);
            }
        }
    }

}

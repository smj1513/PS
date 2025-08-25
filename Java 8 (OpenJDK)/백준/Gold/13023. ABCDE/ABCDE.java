
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<List<Integer>> graph = new ArrayList<>(2001);

    static boolean is;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // A->B->C->D->E
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        is = false;
        for (int i = 0; i < N; i++) {
            dfs(new boolean[N], i, 0);
            if (is) {
                break;
            }
        }
        System.out.println(is ? 1 : 0);

    }

    public static void dfs(boolean[] visited, int v, int depth) {
        visited[v] = true;
        if (depth == 4) {
            is = true;
            return;
        }
        for (int nv : graph.get(v)) {
            if (!visited[nv]) {
                dfs(visited, nv, depth + 1);
                visited[nv] = false;
                if (is) return;
            }
        }
    }
}

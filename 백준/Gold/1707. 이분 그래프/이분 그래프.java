

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Vertex {
    int v;
    int color;

    public Vertex(int v, int color) {
        this.v = v;
        this.color = color;
    }
}

//[지시사항] Mermaid 마크다운 문법을 사용하여 그래프를 그려서 설명
public class Main {
    static List<Integer>[] graph;
    static int[] visited;
    static boolean check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            graph = new List[V + 1];
            for (int i = 1; i <= V; i++) {
                graph[i] = new LinkedList<>();
            }
            visited = new int[V + 1];
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }
            check = true;
            for (int i = 1; i <= V; i++) {
                if (!check) {
                    break;
                }
                if (visited[i] == 0) {
                    dfs(new Vertex(i, 1));
                }
            }
            System.out.println(check ? "YES" : "NO");
        }
    }

    public static void dfs(Vertex v) {
        visited[v.v] = v.color;
        for (int nv : graph[v.v]) {
            if (visited[nv] == v.color) {
                check = false;
                return;
            }
            if (visited[nv] == 0) {
                dfs(new Vertex(nv, -v.color));
            }
        }
    }

}

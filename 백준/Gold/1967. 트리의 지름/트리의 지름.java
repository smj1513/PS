
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
    int v, w;

    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }

}


public class Main {

    static List<Node>[] graph;
    static boolean[] visited;
    static int max = 0;
    static int node = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N < 2) {
            System.out.println(0);
            return;
        }
        graph = new List[N + 1];
        for (int i = 0; i < N; i++) {
            graph[i + 1] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        visited = new boolean[N + 1];
        dfs(1, 0);


        visited = new boolean[N + 1];
        dfs(node, 0);
        System.out.println(max);
    }

    public static void dfs(int v, int sum) {
        if (sum > max) {
            max = sum;
            node = v;
        }
        visited[v] = true;
        for (Node nv : graph[v]) {
            if (!visited[nv.v]) {
                dfs(nv.v, sum + nv.w);
            }
        }
    }
}

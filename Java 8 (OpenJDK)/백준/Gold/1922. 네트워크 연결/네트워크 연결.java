
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(cost, o.cost);
    }
}

public class Main {
    static int N, M;
    static int[] parent;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(edges);
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        int cost = 0;
        int cnt = 0;
        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                cost += edge.cost;
                if (++cnt == N - 1) break;
            }
        }
        System.out.println(cost);
    }

    public static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    public static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fb != fa) {
            parent[fb] = fa;
            return true;
        }
        return false;
    }
}

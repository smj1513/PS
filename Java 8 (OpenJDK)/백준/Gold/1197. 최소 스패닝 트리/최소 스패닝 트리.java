
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int from, to, weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(weight, o.weight);
    }
}

public class Main {
    static int V, E;
    static int[] parent;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edges = new Edge[E];
        parent = new int[V + 1];
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(edges);
        int result = 0;
        int cnt = 0;
        int n = V -1;
        for (Edge edge : edges){
            if(union(edge.from, edge.to)){
                result += edge.weight;
                if(++cnt == n) break;
            }
        }
        System.out.println(result);

    }

    static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            parent[fb] = fa;
            return true;
        }
        return false;
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }
}


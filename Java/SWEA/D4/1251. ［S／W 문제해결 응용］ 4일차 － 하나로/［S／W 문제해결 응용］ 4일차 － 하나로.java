
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

class Island {
    int idx;
    int x, y;

    public Island(int x, int y) {
        this.idx = Solution.idx++;
        this.x = x;
        this.y = y;
    }
}

class Edge implements Comparable<Edge> {
    Island from, to;
    double cost;

    public Edge(Island from, Island to) {
        this.from = from;
        this.to = to;
        long x = from.x - to.x;
        long y = from.y - to.y;
        cost = ((x * x) + (y * y));
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.cost, o.cost);
    }
}

public class Solution {
    static int idx;
    static int[] parent;
    static int V;
    static List<Edge> edges;
    static Island[] islands;
    static double E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            edges = new ArrayList<>();
            idx = 0;
            V = Integer.parseInt(br.readLine());
            int[] xList = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] yList = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            E = Double.parseDouble(br.readLine());
            parent = new int[V];
            for (int i = 0; i < V; i++) {
                parent[i] = i;
            }
            islands = new Island[V];
            for (int i = 0; i < V; i++) {
                int x = xList[i];
                int y = yList[i];
                islands[i] = new Island(x, y);
            }
            for (int i = 0; i < V; i++) {
                for (int j = i + 1; j < V; j++) {
                    edges.add(new Edge(islands[i], islands[j]));
                }
            }
            Collections.sort(edges);
            int cnt = 0;
            double cost = 0;
            for (Edge edge : edges) {
                if (union(edge.from.idx, edge.to.idx)) {
                    cost += edge.cost;
                    if (++cnt == V - 1) break;
                }
            }
            System.out.println("#" + tc + " " + Math.round(cost * E));
        }
    }


    public static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            parent[fb] = fa;
            return true;
        }
        return false;
    }

    public static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }


}

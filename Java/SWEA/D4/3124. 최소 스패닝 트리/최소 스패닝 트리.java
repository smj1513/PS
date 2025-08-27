
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Vertex implements Comparable<Vertex>{
    int v;
    int cost;

    public Vertex(int v, int cost) {
        this.v = v;
        this.cost = cost;
    }

    @Override
    public int compareTo(Vertex o) {
        return Integer.compare(cost, o.cost);
    }
}

public class Solution {

    static List<Vertex>[] graph;
    static BitSet visited;
    static int[] minEdge;
    static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            graph = new List[V + 1];
            visited = new BitSet(V + 1);
            minEdge = new int[V + 1];
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }
            Arrays.fill(minEdge, Integer.MAX_VALUE);
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                graph[from].add(new Vertex(to, cost));
                graph[to].add(new Vertex(from, cost));
            }
            System.out.println("#" + tc + " " + prim(1));
        }
    }

    public static long prim(int start) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(start, 0));
        minEdge[start] = 0;
        long result = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            if (minEdge[current.v] < current.cost || visited.get(current.v)) {
                continue;
            }
            visited.set(current.v);
            result += current.cost;
            if (++cnt == V) {
                break;
            }
            for (Vertex nv : graph[current.v]) {
                if (!visited.get(nv.v) && minEdge[nv.v] > nv.cost) {
                    minEdge[nv.v] = nv.cost;
                    pq.offer(new Vertex(nv.v, nv.cost));
                }
            }
        }
        return result;
    }
}

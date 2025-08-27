
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class Island {
    int idx;
    long x, y;

    public Island(long x, long y) {
        this.idx = Solution.idx++;
        this.x = x;
        this.y = y;
    }

    long distance(Island other) {
        long x = this.x - other.x;
        long y = this.y - other.y;
        return x * x + y * y;
    }
}

class Vertex implements Comparable<Vertex> {
    Island v;
    long cost;

    public Vertex(Island v, long cost) {
        this.v = v;
        this.cost = cost;
    }

    @Override
    public int compareTo(Vertex o) {
        return Long.compare(cost, o.cost);
    }
}

public class Solution {
    static List<Vertex>[] graph;
    static boolean[] visited;
    static long[] distance;
    static double e;
    static Island[] islands;
    static int idx, V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            idx = 0;
            V = Integer.parseInt(br.readLine());
            long[] xList = Stream.of(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            long[] yList = Stream.of(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            e = Double.parseDouble(br.readLine());
            graph = new List[V];
            for (int i = 0; i < V; i++) {
                graph[i] = new ArrayList<>();
            }
            visited = new boolean[V];
            distance = new long[V];
            Arrays.fill(distance, Long.MAX_VALUE);
            islands = new Island[V];
            for (int i = 0; i < V; i++) {
                long x = xList[i];
                long y = yList[i];
                islands[i] = new Island(x, y);
            }
            for (int i = 0; i < V; i++) {
                for (int j = i + 1; j < V; j++) {
                    Island a = islands[i];
                    Island b = islands[j];
                    long cost = a.distance(b);
                    graph[i].add(new Vertex(b, cost));
                    graph[j].add(new Vertex(a, cost));
                }
            }
            System.out.println("#" + tc + " " + Math.round(prim(0)));
        }
    }

    public static double prim(int start) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        long result = 0;
        int cnt = 0;
        pq.add(new Vertex(islands[start], 0));
        distance[start] = 0;
        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            if (visited[current.v.idx] || distance[current.v.idx] < current.cost) {
                continue;
            }
            visited[current.v.idx] = true;
            result += current.cost;
            if (++cnt == V) {
                break;
            }
            for (Vertex nv : graph[current.v.idx]) {
                if (!visited[nv.v.idx] && distance[nv.v.idx] > nv.cost) {
                    distance[nv.v.idx] = nv.cost;
                    pq.offer(new Vertex(nv.v, nv.cost));
                }
            }
        }
        return result * e;
    }
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Vertex implements Comparable<Vertex> {
    int v, w;

    public Vertex(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.w - o.w;
    }
}

public class Main {
    static int N, M, X;
    static List<Vertex>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Vertex(to, cost));
        }
        int maxlen = 0;
        for (int i = 1; i <= N; i++) {
            int to = dijkstra(i, X);
            int from = dijkstra(X, i);
            maxlen = Math.max(maxlen, to + from);
        }
        System.out.println(maxlen);
    }

    public static int dijkstra(int start, int end) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(start, 0));
        boolean[] visited = new boolean[N + 1];
        int[] distance = new int[N + 1];
        Arrays.fill(distance, 1_000_000_000);
        distance[start] = 0;
        while (!pq.isEmpty()) {
            Vertex cur = pq.poll();
            if (cur.v == end) {
                return distance[cur.v];
            }
            if (visited[cur.v] || distance[cur.v] < cur.w) {
                continue;
            }
            visited[cur.v] = true;
            for (Vertex nv : graph[cur.v]) {
                if (!visited[nv.v] && distance[nv.v] > distance[cur.v] + nv.w) {
                    distance[nv.v] = distance[cur.v] + nv.w;
                    pq.offer(new Vertex(nv.v, distance[nv.v]));
                }
            }
        }
        return distance[end];
    }
}

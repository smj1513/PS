
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int to;
    long w;

    public Edge(int to, long w) {
        this.to = to;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(o.w, this.w);
    }
}

public class Main {
    static List<List<Edge>> graph = new ArrayList<>();
    static int N, M;
    static int start, end;
    static long[] distance;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        distance = new long[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            graph.get(from).add(new Edge(to, w));
            graph.get(to).add(new Edge(from, w));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        bfs();
        System.out.println(distance[end]);
    }

    public static void bfs() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, Long.MAX_VALUE));
        distance[start] = Long.MAX_VALUE;
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            visited[current.to] = true;
            if(current.to == end){
                return;
            }
            for (Edge next : graph.get(current.to)) {
                if (!visited[next.to]) {
                    if (distance[next.to] <= next.w) {
                        pq.add(next);
                        distance[next.to] = Math.min(distance[current.to], next.w);

                    }
                }
            }
        }
    }
}
